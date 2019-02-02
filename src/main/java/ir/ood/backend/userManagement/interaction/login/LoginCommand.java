package ir.ood.backend.userManagement.interaction.login;

import ir.ood.backend.commons.requestParams.Validatable;
import org.apache.commons.lang3.StringUtils;

public class LoginCommand extends Validatable {

    public String email;
    public String password;

    @Override
    public void validate() {
        validateField(email, StringUtils::isNotEmpty, "email");
        validateField(password, StringUtils::isNotEmpty, "password");
    }
}
