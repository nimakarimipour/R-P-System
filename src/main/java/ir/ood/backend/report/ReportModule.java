package ir.ood.backend.report;

import com.google.inject.AbstractModule;
import ir.ood.backend.report.domain.ReportRepository;
import ir.ood.backend.report.interaction.ReportInteractor;
import ir.ood.backend.report.interaction.ReportInteractorImpl;
import ir.ood.backend.report.interfaces.db.ReportRepositoryImpl;

public class ReportModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ReportRepository.class).to(ReportRepositoryImpl.class);
        bind(ReportInteractor.class).to(ReportInteractorImpl.class);
    }
}
