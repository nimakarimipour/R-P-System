package ir.ood.backend.report.interfaces.web;


import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;
import ir.ood.backend.commons.web.Serializable;
import ir.ood.backend.report.domain.Report;
import ir.ood.backend.report.interaction.CreateReportCommand;
import ir.ood.backend.report.interaction.EnterGradesCommand;
import ir.ood.backend.report.interaction.FindByWorkerCommand;
import ir.ood.backend.report.interaction.ReportInteractor;
import ir.ood.backend.userManagement.interaction.token.TokenManager;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/api/reports")
@Produces(MediaType.APPLICATION_JSON)
public class ReportHandler {

    @Inject
    private ReportInteractor interactor;
    @Inject
    private TokenManager tokenManager;

    @POST
    @UnitOfWork
    public Map create(@NotEmpty @HeaderParam("Authorization") String token, CreateReportCommand command) {
        return interactor.create(tokenManager.findWorker(token), command).serialize();
    }

    @Path("/score")
    @POST
    @UnitOfWork
    public Map score(@NotEmpty @HeaderParam("Authorization") String token, EnterGradesCommand command) {
        return interactor.enterScores(tokenManager.findWorker(token), command).serialize();
    }

    @Path("/reviewer/{id}")
    @GET
    @UnitOfWork
    public Object findByReviewer(@PathParam("id") int id) {
        return serialize(interactor.findByReviewer(new FindByWorkerCommand(id)));
    }

    @Path("/reviewee/{id}")
    @GET
    @UnitOfWork
    public Object findByReviewee(@PathParam("id") int id) {
        return serialize(interactor.findByReviewee(new FindByWorkerCommand(id)));
    }

    @GET
    @UnitOfWork
    public Object listAll() {
        return serialize(interactor.listAll());
    }

    private Object serialize(List<Report> list) {
        return list.stream().map(Serializable::serialize).collect(Collectors.toList());
    }

}
