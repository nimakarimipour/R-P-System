package ir.ood.backend.evaluation.domain.grading;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class QualitativeGradingStrategy extends GradingStrategy {

    QualitativeGradingStrategy(List<Grade> grades) {
        super(grades);
    }

    @Override
    public Map serialize() {
        return mapOf("scores", grades.stream().map(Grade::getText).collect(Collectors.toList()));
    }

    @Override
    boolean isValidGradeText(String text) {
        return grades.stream().anyMatch(grade -> grade.getText().equals(text));
    }

    @Override
    Integer getValueForGradeText(String text) {
        for (Grade grade : grades)
            if (grade.getText().equals(text))
                return grade.getValue();
        return null;
    }
}
