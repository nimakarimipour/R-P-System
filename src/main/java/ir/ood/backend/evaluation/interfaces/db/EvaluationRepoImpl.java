package ir.ood.backend.evaluation.interfaces.db;

import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import ir.ood.backend.evaluation.domain.Evaluation;
import ir.ood.backend.evaluation.domain.EvaluationRepo;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class EvaluationRepoImpl extends AbstractDAO<Evaluation> implements EvaluationRepo {

    @Inject
    public EvaluationRepoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Evaluation> listAll() {
        Query<Evaluation> query = currentSession().createQuery("FROM Evaluation", Evaluation.class);
        return super.list(query);
    }

    @Override
    public Optional<Evaluation> findById(int id) {
        return Optional.ofNullable(super.get(id));
    }

    @Override
    public void save(Evaluation evaluation) {
        super.persist(evaluation);
    }

    @Override
    public List<Evaluation> findBySection(int sectionId) {
        Query<Evaluation> query = currentSession().createQuery("FROM Evaluation as e WHERE e.section.id = :id", Evaluation.class);
        query.setParameter("id", sectionId);
        return super.list(query);
    }
}
