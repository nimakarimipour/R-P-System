package ir.ood.backend.evaluation.interaction.evaluationCreation;

import ir.ood.backend.commons.requestParams.Validatable;

public class FindBySectionCommand extends Validatable {
    public int sectionId;

    public FindBySectionCommand(int sectionId) {
        this.sectionId = sectionId;
    }


    private FindBySectionCommand() {
    }

    @Override
    public void validate() {
        validateField(sectionId, sectionId -> sectionId > 0, "sectionId");
    }
}
