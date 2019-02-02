package ir.ood.backend.report.interfaces.db;

import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import ir.ood.backend.report.domain.Report;
import ir.ood.backend.report.domain.ReportRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class ReportRepositoryImpl extends AbstractDAO<Report> implements ReportRepository {

    @Inject
    public ReportRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<Report> findById(int id) {
        return Optional.ofNullable(get(id));
    }

    @Override
    public void save(Report report) {
        super.persist(report);
    }

    @Override
    public List<Report> findAll() {
        Query<Report> query = currentSession().createQuery("FROM Report");
        return super.list(query);
    }

    @Override
    public List<Report> findByReviewee(int id) {
        Query<Report> query = currentSession().
                createQuery("FROM Report AS r WHERE r.reviewee.id = :id", Report.class).
                setParameter("id", id);
        return super.list(query);
    }

    @Override
    public List<Report> findByReviewer(int id) {
        Query<Report> query = currentSession().
                createQuery("FROM Report AS r WHERE r.reviewer.id = :id", Report.class).
                setParameter("id", id);
        return super.list(query);
    }
}
