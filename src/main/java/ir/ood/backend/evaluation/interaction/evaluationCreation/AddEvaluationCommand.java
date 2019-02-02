package ir.ood.backend.evaluation.interaction.evaluationCreation;

import ir.ood.backend.commons.requestParams.Validatable;
import org.apache.commons.lang3.StringUtils;

public class AddEvaluationCommand extends Validatable {

    public String title;
    public int sectionId;

    @Override
    public void validate() {
        validateField(title, StringUtils::isNotEmpty, "title");
        validateField(sectionId, i -> i > 0, "sectionId");
    }
}
