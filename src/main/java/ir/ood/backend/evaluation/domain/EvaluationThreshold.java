package ir.ood.backend.evaluation.domain;

import ir.ood.backend.commons.web.Serializable;

import javax.persistence.Embeddable;
import java.util.Map;

@Embeddable
public class EvaluationThreshold implements Serializable {

    private int punishmentThreshold;
    private String punishmentAction;
    private int rewardThreshold;
    private String rewardAction;

    public EvaluationThreshold(int punishmentThreshold, int rewardThreshold) {
        this.punishmentThreshold = punishmentThreshold;
        this.rewardThreshold = rewardThreshold;
    }

    EvaluationThreshold() {
    }

    void setActions(String rewardAction, String punishmentAction) {
        this.rewardAction = rewardAction;
        this.punishmentAction = punishmentAction;
    }

    public boolean shouldBeRewarded(int score) {
        return score > rewardThreshold;
    }

    public boolean shouldBePunished(int score) {
        return score < punishmentThreshold;
    }

    @Override
    public Map serialize() {
        return mapOf(
                "reward", rewardThreshold,
                "punishment", punishmentThreshold,
                "rewardAction", rewardAction,
                "punishmentAction", punishmentAction
        );
    }
}
