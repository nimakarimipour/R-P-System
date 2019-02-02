package ir.ood.backend.report.domain;

import java.util.List;
import java.util.Optional;

public interface ReportRepository {

    void save(Report report);

    Optional<Report> findById(int id);

    List<Report> findAll();

    List<Report> findByReviewee(int id);

    List<Report> findByReviewer(int id);

}
