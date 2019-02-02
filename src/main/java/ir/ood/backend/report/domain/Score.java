package ir.ood.backend.report.domain;

import ir.ood.backend.commons.web.Serializable;
import ir.ood.backend.evaluation.domain.EvaluationCriterion;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Score implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "criterion_id")
    private EvaluationCriterion criterion;
    private String text;
    private int value;

    private Score() {
    }

    public Score(EvaluationCriterion criterion, int value, String text) {
        this.criterion = criterion;
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    @Override
    public Map serialize() {
        return mapOf(
                "criterion", criterion.serialize(),
                "score", text,
                "value", value
        );
    }

}
