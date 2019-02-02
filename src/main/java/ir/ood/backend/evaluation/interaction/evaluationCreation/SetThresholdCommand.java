package ir.ood.backend.evaluation.interaction.evaluationCreation;

import ir.ood.backend.commons.requestParams.Validatable;
import org.apache.commons.lang3.StringUtils;

public class SetThresholdCommand extends Validatable {

    public int evaluationId;
    public int punishmentThreshold;
    public int rewardThreshold;

    @Override
    public void validate() {
        validateField(punishmentThreshold, punishmentThreshold -> punishmentThreshold <= rewardThreshold, "punishmentThreshold");
        validateField(evaluationId, evaluationId -> evaluationId > 0, "evaluationId");
    }
}
