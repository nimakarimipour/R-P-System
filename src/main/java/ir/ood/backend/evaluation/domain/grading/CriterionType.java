package ir.ood.backend.evaluation.domain.grading;

import java.util.List;

public class CriterionType {
    public static final int QUALITATIVE = 1;
    public static final int QUANTITATIVE = 2;
    public static final int BOTH = 3;

    public static final List<Integer> validChoices = List.of(QUALITATIVE, QUANTITATIVE, BOTH);
}
