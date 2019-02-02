package ir.ood.backend.userManagement.interaction.token;

import ir.ood.backend.userManagement.domain.Worker;

public interface TokenManager {

    String createToken(Worker worker);

    Worker findWorker(String token);

}
