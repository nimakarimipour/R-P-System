package ir.ood.backend.evaluation.domain.grading;

import ir.ood.backend.commons.web.Serializable;

import java.util.List;


abstract class GradingStrategy implements Serializable {

    protected List<Grade> grades;

    GradingStrategy(List<Grade> grades) {
        this.grades = grades;
    }

    abstract boolean isValidGradeText(String text);

    abstract Integer getValueForGradeText(String text);

}
