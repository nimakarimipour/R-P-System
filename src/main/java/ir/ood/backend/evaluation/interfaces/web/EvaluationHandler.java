package ir.ood.backend.evaluation.interfaces.web;

import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;
import ir.ood.backend.commons.web.Serializable;
import ir.ood.backend.evaluation.interaction.evaluationCreation.*;
import ir.ood.backend.userManagement.domain.Worker;
import ir.ood.backend.userManagement.interaction.token.TokenManager;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/api/evaluation")
@Produces(MediaType.APPLICATION_JSON)
public class EvaluationHandler {

    @Inject
    private EvaluationInteractor interactor;
    @Inject
    private TokenManager tokenManager;

    private Worker toWorker(String token) {
        return tokenManager.findWorker(token);
    }

    @POST
    @UnitOfWork
    public Map createEvaluation(@NotEmpty @HeaderParam("Authorization") String token, @NotNull AddEvaluationCommand command) {
        return interactor.createEvaluation(toWorker(token), command).serialize();
    }

    @Path("/criterion/qualitative")
    @POST
    @UnitOfWork
    public Map addCriterion(@NotEmpty @HeaderParam("Authorization") String token, @NotNull AddQualitativeCriterionCommand command) {
        return interactor.addCriterion(toWorker(token), command).serialize();
    }

    @Path("/criterion/quantitative")
    @POST
    @UnitOfWork
    public Map addCriterion(@NotEmpty @HeaderParam("Authorization") String token, @NotNull AddQuantitativeCriterionCommand command) {
        return interactor.addCriterion(toWorker(token), command).serialize();
    }

    @Path("/threshold")
    @POST
    @UnitOfWork
    public Map setThreshold(@NotEmpty @HeaderParam("Authorization") String token, @NotNull SetThresholdCommand command) {
        return interactor.setThreshold(toWorker(token), command).serialize();
    }

    @Path("/actions")
    @POST
    @UnitOfWork
    public Map setActions(@NotEmpty @HeaderParam("Authorization") String token, @NotNull SetActionsCommand command) {
        return interactor.setActions(toWorker(token), command).serialize();
    }

    @GET
    @UnitOfWork
    public List<Map> listAll(@NotEmpty @HeaderParam("Authorization") String token) {
        return interactor.
                listAll().
                stream().
                map(Serializable::serialize).
                collect(Collectors.toList());
    }

    @Path("/section/{id}")
    @GET
    @UnitOfWork
    public List<Map> findBySection(@NotEmpty @HeaderParam("Authorization") String token, @PathParam("id") int sectionId) {
        return interactor.
                findBySection(new FindBySectionCommand(sectionId)).
                stream().
                map(Serializable::serialize).
                collect(Collectors.toList());
    }

}
