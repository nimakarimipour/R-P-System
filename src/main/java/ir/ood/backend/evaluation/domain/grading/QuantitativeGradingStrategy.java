package ir.ood.backend.evaluation.domain.grading;

import java.util.List;
import java.util.Map;

class QuantitativeGradingStrategy extends GradingStrategy {

    QuantitativeGradingStrategy(List<Grade> grades) {
        super(grades);
    }

    @Override
    public Map serialize() {
        return mapOf("minGrade", grades.get(0).getValue(), "maxGrade", grades.get(1).getValue());
    }

    @Override
    Integer getValueForGradeText(String text) {
        return Integer.valueOf(text);
    }

    @Override
    boolean isValidGradeText(String text) {
        try {
            return grades.get(0).getValue() <= Integer.parseInt(text) &&
                    Integer.parseInt(text) <= grades.get(1).getValue();
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
