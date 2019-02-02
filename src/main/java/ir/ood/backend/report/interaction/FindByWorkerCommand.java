package ir.ood.backend.report.interaction;

import ir.ood.backend.commons.requestParams.Validatable;

public class FindByWorkerCommand extends Validatable {

    public int id;

    private FindByWorkerCommand() {
    }

    public FindByWorkerCommand(int id) {
        this.id = id;
    }

    @Override
    public void validate() {
        validateField(id, id -> id > 0, "id");
    }
}
