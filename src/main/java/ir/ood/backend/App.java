package ir.ood.backend;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ir.ood.backend.commons.db.WithTransaction;
import ir.ood.backend.commons.exceptions.BusinessLogincException;
import ir.ood.backend.commons.exceptions.mappers.*;
import ir.ood.backend.evaluation.EvaluationModule;
import ir.ood.backend.evaluation.domain.Evaluation;
import ir.ood.backend.evaluation.domain.EvaluationCriterion;
import ir.ood.backend.evaluation.domain.grading.Grade;
import ir.ood.backend.evaluation.interfaces.web.EvaluationHandler;
import ir.ood.backend.report.ReportModule;
import ir.ood.backend.report.domain.Report;
import ir.ood.backend.report.domain.Score;
import ir.ood.backend.report.interfaces.web.ReportHandler;
import ir.ood.backend.userManagement.UserManagementModule;
import ir.ood.backend.userManagement.domain.Section;
import ir.ood.backend.userManagement.domain.SectionRepository;
import ir.ood.backend.userManagement.domain.Worker;
import ir.ood.backend.userManagement.interaction.worker.AddWorkerCommand;
import ir.ood.backend.userManagement.interaction.worker.WorkerInteractor;
import ir.ood.backend.userManagement.interfaces.web.WorkerHandler;
import ir.ood.backend.userManagement.interfaces.web.LoginHandler;
import ir.ood.backend.userManagement.interfaces.web.SectionHandler;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.hibernate.SessionFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Logger;

public class App extends Application<Configuration> {
    private HibernateBundle<Configuration> hibernate;

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        super.initialize(bootstrap);
        hibernate = new HibernateBundle<>(
                Grade.class, Worker.class, EvaluationCriterion.class, Evaluation.class, Section.class,
                Report.class, Score.class
        ) {
            @Override
            public DataSourceFactory getDataSourceFactory(Configuration configuration) {
                return configuration.getDataSourceFactory();
            }
        };
        bootstrap.addBundle(hibernate);
    }

    public void run(Configuration configuration, Environment environment) throws Exception {
        Injector injector = Guice.createInjector(
                new UserManagementModule(),
                new EvaluationModule(),
                new ReportModule(),
                new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(SessionFactory.class).toInstance(hibernate.getSessionFactory());
                        bind(ConfigurationConstants.class).toInstance(configuration);
                    }
                }
        );
        configureCors(environment);
        registerHandlers(environment, injector);
        registerExceptionMappers(environment, injector);
        ensureAdminIsPresent(configuration, injector);
    }

    private void ensureAdminIsPresent(Configuration configuration, Injector injector) {
        new WithTransaction(hibernate.getSessionFactory()).run(() -> {
            Section section = new Section("مدیریت");
            injector.getInstance(SectionRepository.class).save(section);
            WorkerInteractor interactor = injector.getInstance(WorkerInteractor.class);
            AddWorkerCommand command = getAddSuperuserCommand(configuration, section);
            try {
                interactor.add(command);
                Logger.getLogger(getClass().getName()).info("Admin user created");
            } catch (BusinessLogincException e) {
                Logger.getLogger(getClass().getName()).info("Admin user already exists");
            }
        });
    }

    private AddWorkerCommand getAddSuperuserCommand(Configuration configuration, Section section) {
        AddWorkerCommand command = new AddWorkerCommand();
        command.email = configuration.adminEmail;
        command.password = configuration.adminPassword;
        command.isSuperuser = true;
        command.name = "مدیر کل";
        command.sectionId = section.getId();
        command.isManager = true;
        return command;
    }

    private void registerHandlers(Environment environment, Injector injector) {
        List<Class> classes = List.of(
                LoginHandler.class,
                WorkerHandler.class,
                EvaluationHandler.class,
                SectionHandler.class,
                ReportHandler.class
        );
        classes.forEach(cls -> environment.jersey().register(injector.getInstance(cls)));
    }

    private void registerExceptionMappers(Environment environment, Injector injector) {
        List<Class> classes = new ArrayList<Class>() {{
            add(BusinessLoginExceptionMapper.class);
            add(ForbiddenExceptionMapper.class);
            add(InternalExceptionMapper.class);
            add(InvalidParameterExceptionMapper.class);
            add(NotFoundExceptionMapper.class);
        }};
        classes.forEach(cls -> environment.jersey().register(injector.getInstance(cls)));
    }

    private void configureCors(Environment environment) {
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin,Authorization");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

    }

    @Override
    public String getName() {
        return "Reward and punishment";
    }
}
