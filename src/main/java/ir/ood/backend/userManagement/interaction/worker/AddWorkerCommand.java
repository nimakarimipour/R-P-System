package ir.ood.backend.userManagement.interaction.worker;

import ir.ood.backend.commons.requestParams.Validatable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;

public class AddWorkerCommand extends Validatable {

    public String name;
    public String email;
    public String password;
    public boolean isSuperuser;
    public Integer sectionId;
    public boolean isManager;

    @Override
    public void validate() {
        validateField(name, StringUtils::isNotEmpty, "name");
        validateField(email, StringUtils::isNotEmpty, "email");
        validateField(email, EmailValidator.getInstance()::isValid, "email");
        validateField(password, StringUtils::isNotEmpty, "password");
        validateField(sectionId, Objects::nonNull, "sectionId");
    }

}
