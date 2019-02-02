package ir.ood.backend.evaluation.domain;


import ir.ood.backend.commons.web.Serializable;
import ir.ood.backend.evaluation.domain.grading.GradingFields;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class EvaluationCriterion implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String title;
    private float weight;
    @Embedded
    private GradingFields gradingFields;


    private EvaluationCriterion() {
        gradingFields = new GradingFields();
    }

    private EvaluationCriterion(String title, float weight) {
        this();
        this.title = title;
        this.weight = weight;
    }

    public EvaluationCriterion(String title, float weight, int minGrade, int maxGrade) {
        this(title, weight);
        this.gradingFields = new GradingFields(minGrade, maxGrade);
    }

    public EvaluationCriterion(String title, float weight, Map<String, Integer> grades) {
        this(title, weight);
        this.gradingFields = new GradingFields(grades);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    Integer getValueForGradeText(String gradeText) {
        return gradingFields.getValueForText(gradeText);
    }

    boolean isValidGradeText(String text) {
        return gradingFields.isValidGradeText(text);
    }

    @Override
    public Map serialize() {
        return mapOf(
                "id", id,
                "title", title,
                "weight", weight,
                "grading", gradingFields.serialize()
        );
    }
}
