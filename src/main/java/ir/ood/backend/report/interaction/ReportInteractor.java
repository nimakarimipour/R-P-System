package ir.ood.backend.report.interaction;

import ir.ood.backend.report.domain.Report;
import ir.ood.backend.userManagement.domain.Worker;

import java.util.List;

public interface ReportInteractor {

    Report create(Worker worker, CreateReportCommand command);

    Report enterScores(Worker worker, EnterGradesCommand command);

    List<Report> listAll();

    List<Report> findByReviewer(FindByWorkerCommand command);

    List<Report> findByReviewee(FindByWorkerCommand command);


}
