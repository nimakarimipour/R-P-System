package ir.ood.backend.userManagement.interaction.worker;

import com.google.inject.Inject;
import ir.ood.backend.commons.exceptions.BusinessLogincException;
import ir.ood.backend.commons.exceptions.InvalidParameterException;
import ir.ood.backend.commons.web.PermissionUtils;
import ir.ood.backend.userManagement.domain.Section;
import ir.ood.backend.userManagement.domain.SectionRepository;
import ir.ood.backend.userManagement.domain.Worker;
import ir.ood.backend.userManagement.domain.WorkerRepo;

import java.util.List;

public class WorkerInteractorImpl implements WorkerInteractor {

    @Inject
    private SectionRepository sectionRepository;
    @Inject
    private WorkerRepo workerRepository;

    @Override
    public Worker add(Worker worker, AddWorkerCommand command) {
        PermissionUtils.errorIfNot(worker.isSuperuser());
        return add(command);
    }

    @Override
    public Worker add(AddWorkerCommand command) {
        command.validate();
        Worker newWorker = new Worker();
        newWorker.setEmail(command.email);
        newWorker.setPassword(command.password);
        newWorker.setSuperuser(command.isSuperuser);
        newWorker.setName(command.name);
        newWorker.setSection(findSection(command), command.isManager);
        if (workerRepository.findByEmail(command.email) != null) {
            throw new BusinessLogincException("duplicate", "Email already exists");
        }
        workerRepository.save(newWorker);
        return newWorker;
    }

    @Override
    public List<Worker> listAll() {
        return workerRepository.findAll();
    }

    @Override
    public List<Worker> findBySection(int sectionId) {
        return workerRepository.findBySection(sectionId);
    }

    private Section findSection(AddWorkerCommand command) {
        return sectionRepository.findById(command.sectionId).
                orElseThrow(() -> new InvalidParameterException("invalid_section", "No such section"));
    }
}
