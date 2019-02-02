package ir.ood.backend.evaluation.interaction.evaluationCreation;

public class AddQuantitativeCriterionCommand extends AddCriterionCommand {
    public int maxGrade;
    public int minGrade;

    @Override
    public void validate() {
        super.validate();
        validateField(minGrade, minGrade -> minGrade <= maxGrade, "min and max grades");
    }
}
