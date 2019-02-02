package ir.ood.backend.userManagement.interaction.worker;

import ir.ood.backend.userManagement.domain.Worker;

import java.util.List;

public interface WorkerInteractor {
    Worker add(Worker worker, AddWorkerCommand command);
    Worker add(AddWorkerCommand command);
    List<Worker> listAll();
    List<Worker> findBySection(int sectionId);
}
