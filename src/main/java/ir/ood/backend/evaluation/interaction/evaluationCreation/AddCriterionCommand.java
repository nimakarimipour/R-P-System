package ir.ood.backend.evaluation.interaction.evaluationCreation;

import ir.ood.backend.commons.requestParams.Validatable;
import org.apache.commons.lang3.StringUtils;

public abstract class AddCriterionCommand extends Validatable {
    public String title;
    public float weight;
    public int evaluationId;

    @Override
    public void validate() {
        validateField(title, StringUtils::isNotEmpty, "title");
        validateField(weight, weight -> weight > 0, "weight");
        validateField(evaluationId, evaluationId -> evaluationId > 0, "evaluationId");
    }
}
