package ir.ood.backend.userManagement.interfaces.web;

import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;
import ir.ood.backend.userManagement.interaction.login.LoginCommand;
import ir.ood.backend.userManagement.interaction.login.LoginInteractor;

import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
public class LoginHandler {

    @Inject
    private LoginInteractor loginInteractor;

    @Path("/login/")
    @POST
    @UnitOfWork
    public Map login(@NotNull LoginCommand command) {
        return loginInteractor.login(command).serialize();
    }

}
