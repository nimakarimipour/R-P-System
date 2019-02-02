package ir.ood.backend.report.interaction;

import ir.ood.backend.commons.requestParams.Validatable;

public class CreateReportCommand extends Validatable {

    public int reviewerId;
    public int revieweeId;
    public int evaluationId;

    @Override
    public void validate() {
        validateField(revieweeId, revieweeId -> revieweeId > 0, "revieweeId");
        validateField(reviewerId, reviewerId -> reviewerId > 0, "reviewerId");
        validateField(evaluationId, evaluationId -> evaluationId > 0, "reviewerId");
    }

}
