package ir.ood.backend.userManagement.domain;

import java.util.List;
import java.util.Optional;

public interface SectionRepository {

    Optional<Section> findById(int id);

    List<Section> findAll();

    void save(Section section);

}
