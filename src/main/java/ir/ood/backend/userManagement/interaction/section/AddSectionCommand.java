package ir.ood.backend.userManagement.interaction.section;

import ir.ood.backend.commons.requestParams.Validatable;
import org.apache.commons.lang3.StringUtils;

public class AddSectionCommand extends Validatable {

    public String title;

    @Override
    public void validate() {
        validateField(title, StringUtils::isNotEmpty, "title");
    }
}
