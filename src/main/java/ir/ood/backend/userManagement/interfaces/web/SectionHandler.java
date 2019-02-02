package ir.ood.backend.userManagement.interfaces.web;

import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;
import ir.ood.backend.userManagement.domain.Worker;
import ir.ood.backend.userManagement.interaction.section.AddSectionCommand;
import ir.ood.backend.userManagement.interaction.section.SectionInteractor;
import ir.ood.backend.userManagement.interaction.token.TokenManager;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Path("/api/sections")
@Produces(MediaType.APPLICATION_JSON)
public class SectionHandler {

    @Inject
    private SectionInteractor interactor;
    @Inject
    private TokenManager tokenManager;

    @GET
    @UnitOfWork
    public List<Map> listAll() {
        return interactor.listAll();
    }

    @POST
    @UnitOfWork
    public Map create(@NotEmpty @HeaderParam("Authorization") String token, AddSectionCommand command) {
        Worker worker = tokenManager.findWorker(token);
        return interactor.add(worker, command).serialize();
    }

}
