package ir.ood.backend.userManagement.interaction.login;

import com.google.inject.Inject;
import ir.ood.backend.commons.exceptions.ForbiddenException;
import ir.ood.backend.userManagement.domain.Worker;
import ir.ood.backend.userManagement.domain.WorkerRepo;
import ir.ood.backend.userManagement.interaction.token.TokenManager;

public class LoginInteractorImpl implements LoginInteractor {

    @Inject
    private TokenManager tokenManager;
    @Inject
    private WorkerRepo repo;

    @Override
    public LoginResponse login(LoginCommand params) {
        params.validate();
        Worker worker = repo.findByEmail(params.email);
        if (worker == null || worker.getEmail() == null || !worker.getPassword().equals(params.password)) {
            throw new ForbiddenException("invalidCredentials", "User with that email does not exist");
        }
        String token = tokenManager.createToken(worker);
        return new LoginResponse(worker, token);
    }
}
