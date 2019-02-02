package ir.ood.backend.evaluation.domain;

import java.util.List;
import java.util.Optional;

public interface EvaluationRepo {

    List<Evaluation> listAll();

    Optional<Evaluation> findById(int id);

    void save(Evaluation evaluation);

    List<Evaluation> findBySection(int sectionId);

}
