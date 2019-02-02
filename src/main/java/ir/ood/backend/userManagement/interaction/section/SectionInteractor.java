package ir.ood.backend.userManagement.interaction.section;

import ir.ood.backend.userManagement.domain.Section;
import ir.ood.backend.userManagement.domain.Worker;

import java.util.List;
import java.util.Map;

public interface SectionInteractor {

    List<Map> listAll();

    Section add(Worker worker, AddSectionCommand command);

}
