package ir.ood.backend.userManagement.interfaces.db;

import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import ir.ood.backend.userManagement.domain.Worker;
import ir.ood.backend.userManagement.domain.WorkerRepo;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class WorkerRepoImpl extends AbstractDAO<Worker> implements WorkerRepo {
    @Inject
    public WorkerRepoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void save(Worker worker) {
        super.persist(worker);
    }

    @Override
    public Worker findByEmail(String email) {
        Query<Worker> query = currentSession().
                createQuery("FROM Worker W WHERE W.email=:email", Worker.class).
                setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            Logger.getLogger(getClass().getName()).warning(e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<Worker> findById(int id) {
        return Optional.ofNullable(super.get(id));
    }

    @Override
    public List<Worker> findAll() {
        return super.list(currentSession().createQuery("FROM Worker", Worker.class));
    }

    @Override
    public List<Worker> findBySection(int id) {
        Query<Worker> query = currentSession().createQuery("FROM Worker W WHERE W.section.id=:id", Worker.class);
        query.setParameter("id", id);
        return super.list(query);
    }
}
