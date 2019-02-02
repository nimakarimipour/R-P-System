package ir.ood.backend.evaluation.domain.grading;

import ir.ood.backend.commons.web.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Embeddable
public class GradingFields implements Serializable {
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "criterion_id")
    List<Grade> grades;
    int criterionType;

    public GradingFields() {
    }

    private GradingFields(int criterionType) {
        this.criterionType = criterionType;
    }

    public GradingFields(int minGrade, int maxGrade) {
        this(CriterionType.QUANTITATIVE);
        this.grades = List.of(new Grade(minGrade, String.valueOf(minGrade)), new Grade(maxGrade, String.valueOf(maxGrade)));
    }

    public GradingFields(Map<String, Integer> grades) {
        this(CriterionType.QUALITATIVE);
        this.grades = grades.keySet().stream().
                map(text -> new Grade(grades.get(text), text)).
                collect(Collectors.toList());
    }

    public Map serialize() {
        return getStrategy().serialize();
    }

    public Integer getValueForText(String text) {
        return getStrategy().getValueForGradeText(text);
    }

    public boolean isValidGradeText(String scoreText) {
        return getStrategy().isValidGradeText(scoreText);
    }

    private GradingStrategy getStrategy() {
        return criterionType == CriterionType.QUALITATIVE ?
                new QualitativeGradingStrategy(grades) :
                new QuantitativeGradingStrategy(grades);
    }

}
