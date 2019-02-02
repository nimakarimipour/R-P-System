package ir.ood.backend.report.interaction;

import com.google.inject.Inject;
import ir.ood.backend.commons.exceptions.BusinessLogincException;
import ir.ood.backend.commons.exceptions.NotFoundException;
import ir.ood.backend.commons.web.PermissionUtils;
import ir.ood.backend.evaluation.domain.Evaluation;
import ir.ood.backend.evaluation.domain.EvaluationRepo;
import ir.ood.backend.report.domain.Report;
import ir.ood.backend.report.domain.ReportRepository;
import ir.ood.backend.userManagement.domain.Worker;
import ir.ood.backend.userManagement.domain.WorkerRepo;

import java.util.List;
import java.util.Optional;

public class ReportInteractorImpl implements ReportInteractor {

    @Inject
    private WorkerRepo workerRepo;
    @Inject
    private ReportRepository reportRepository;
    @Inject
    private EvaluationRepo evaluationRepo;

    @Override
    public Report create(Worker worker, CreateReportCommand command) {
        PermissionUtils.errorIfNot(worker.isSuperuser());
        Worker reviewer = errorOnNull(workerRepo.findById(command.reviewerId), "worker");
        Worker reviewee = errorOnNull(workerRepo.findById(command.revieweeId), "worker");
        Evaluation evaluation = errorOnNull(evaluationRepo.findById(command.evaluationId), "evaluation");
        Report report = new Report(reviewer, reviewee, evaluation);
        reportRepository.save(report);
        return report;
    }

    @Override
    public Report enterScores(Worker worker, EnterGradesCommand command) {
        Report report = errorOnNull(reportRepository.findById(command.reportId), "report");
        if (report.hasScores())
            throw new BusinessLogincException("has_scores", "This report already has scores");
        PermissionUtils.errorIfNot(report.isReviewer(worker));
        command.criterionScoreTextMap.forEach(report::enterGrade);
        reportRepository.save(report);
        return report;
    }

    private <T> T errorOnNull(Optional<T> optional, String entityName) {
        return optional.orElseThrow(() -> new NotFoundException("no_such_" + entityName, entityName + " not found"));
    }

    @Override
    public List<Report> listAll() {
        return reportRepository.findAll();
    }

    @Override
    public List<Report> findByReviewer(FindByWorkerCommand command) {
        return reportRepository.findByReviewer(command.id);
    }

    @Override
    public List<Report> findByReviewee(FindByWorkerCommand command) {
        return reportRepository.findByReviewee(command.id);
    }
}
