package ir.ood.backend.userManagement.interaction.section;

import com.google.inject.Inject;
import ir.ood.backend.commons.web.PermissionUtils;
import ir.ood.backend.commons.web.Serializable;
import ir.ood.backend.userManagement.domain.Section;
import ir.ood.backend.userManagement.domain.SectionRepository;
import ir.ood.backend.userManagement.domain.Worker;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SectionInteractorImpl implements SectionInteractor {

    @Inject
    private SectionRepository repo;

    @Override
    public List<Map> listAll() {
        return repo.findAll().
                stream().
                map(Serializable::serialize).
                collect(Collectors.toList());
    }

    @Override
    public Section add(Worker worker, AddSectionCommand command) {
        PermissionUtils.errorIfNot(worker.isSuperuser());
        Section section = new Section(command.title);
        repo.save(section);
        return section;
    }
}
