package ir.ood.backend.evaluation.interaction.evaluationCreation;

import ir.ood.backend.commons.requestParams.Validatable;
import org.apache.commons.lang3.StringUtils;

public class SetActionsCommand extends Validatable {
    public int evaluationId;
    public String reward;
    public String punishment;

    @Override
    public void validate() {
        validateField(reward, StringUtils::isNotEmpty, "reward");
        validateField(punishment, StringUtils::isNotEmpty, "punishment");
    }
}
