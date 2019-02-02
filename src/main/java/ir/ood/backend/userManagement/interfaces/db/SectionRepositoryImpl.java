package ir.ood.backend.userManagement.interfaces.db;

import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import ir.ood.backend.userManagement.domain.Section;
import ir.ood.backend.userManagement.domain.SectionRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class SectionRepositoryImpl extends AbstractDAO<Section> implements SectionRepository {

    @Inject
    public SectionRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<Section> findById(int id) {
        return Optional.ofNullable(super.get(id));
    }

    @Override
    public List<Section> findAll() {
        Query<Section> query = currentSession().createQuery("FROM Section", Section.class);
        return super.list(query);
    }

    @Override
    public void save(Section section) {
        super.persist(section);
    }
}
