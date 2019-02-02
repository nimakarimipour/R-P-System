package ir.ood.backend.userManagement.domain;

import java.util.List;
import java.util.Optional;

public interface WorkerRepo {
    Optional<Worker> findById(int id);

    Worker findByEmail(String username);

    void save(Worker worker);

    List<Worker> findAll();

    List<Worker> findBySection(int id);

}
