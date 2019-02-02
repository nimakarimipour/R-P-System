package ir.ood.backend.userManagement.interfaces.web;

import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;
import ir.ood.backend.commons.web.Serializable;
import ir.ood.backend.userManagement.domain.Worker;
import ir.ood.backend.userManagement.interaction.worker.AddWorkerCommand;
import ir.ood.backend.userManagement.interaction.worker.WorkerInteractor;
import ir.ood.backend.userManagement.interaction.token.TokenManager;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
public class WorkerHandler {

    @Inject
    private WorkerInteractor interactor;
    @Inject
    private TokenManager tokenManager;

    @POST
    @UnitOfWork
    public Map add(@NotEmpty @HeaderParam("Authorization") String token, @NotNull AddWorkerCommand command) {
        Worker worker = tokenManager.findWorker(token);
        return interactor.add(worker, command).serialize();
    }

    @GET
    @UnitOfWork
    public List<Map> listAll() {
        return interactor.listAll().stream().map(Worker::serialize).collect(Collectors.toList());
    }

    @Path("/section/{id}")
    @GET
    @UnitOfWork
    public List<Map> findBySection(@NotEmpty @HeaderParam("Authorization") String token, @PathParam("id") int sectionId) {
        return interactor.
                findBySection(sectionId).
                stream().
                map(Serializable::serialize).
                collect(Collectors.toList());
    }

}
