package ir.ood.backend.userManagement.interaction.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.ood.backend.commons.web.Serializable;
import ir.ood.backend.userManagement.domain.Worker;

import java.util.Map;

public class LoginResponse implements Serializable {

    private Worker worker;
    public String token;

    public LoginResponse(Worker worker, String token) {
        this.worker = worker;
        this.token = token;
    }

    @Override
    public Map serialize() {
        return mapOf(
                "worker", worker.serialize(),
                "token", token
        );
    }
}
