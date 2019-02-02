package ir.ood.backend.evaluation.interaction.evaluationCreation;

import java.util.Map;

public class AddQualitativeCriterionCommand extends AddCriterionCommand {
    public Map<String, Integer> grades;

    @Override
    public void validate() {
        super.validate();
        validateField(grades, grades -> grades != null && grades.keySet().size() > 0, "grades");
    }
}
