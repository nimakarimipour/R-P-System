package ir.ood.backend.evaluation.interaction.evaluationCreation;

import com.google.inject.Inject;
import ir.ood.backend.commons.exceptions.NotFoundException;
import ir.ood.backend.commons.web.PermissionUtils;
import ir.ood.backend.evaluation.domain.Evaluation;
import ir.ood.backend.evaluation.domain.EvaluationCriterion;
import ir.ood.backend.evaluation.domain.EvaluationRepo;
import ir.ood.backend.userManagement.domain.Section;
import ir.ood.backend.userManagement.domain.SectionRepository;
import ir.ood.backend.userManagement.domain.Worker;

import java.util.List;
import java.util.function.Consumer;

public class EvaluationInteractorImpl implements EvaluationInteractor {

    @Inject
    private EvaluationRepo repo;
    @Inject SectionRepository sectionRepository;

    private Evaluation modifyEvaluationViaFunc(int evaluationId, Consumer<Evaluation> func) {
        Evaluation evaluation = repo.findById(evaluationId).
                orElseThrow(() -> new NotFoundException("not_found", "Evaluation does not exist"));
        func.accept(evaluation);
        repo.save(evaluation);
        return evaluation;
    }

    @Override
    public Evaluation createEvaluation(Worker worker, AddEvaluationCommand command) {
        command.validate();
        PermissionUtils.errorIfNotManagerOf(worker, command.sectionId);
        Section section = sectionRepository.
                findById(command.sectionId).
                orElseThrow(() -> new NotFoundException("no_such_section", "Section not found"));
        Evaluation evaluation = new Evaluation(command.title, section);
        repo.save(evaluation);
        return evaluation;
    }

    @Override
    public Evaluation addCriterion(Worker user, AddQualitativeCriterionCommand command) {
        command.validate();
        return modifyEvaluationViaFunc(command.evaluationId, evaluation -> {
            PermissionUtils.errorIfNotManagerOf(user, evaluation.getSectionId());
            EvaluationCriterion criterion = new EvaluationCriterion(command.title, command.weight, command.grades);
            evaluation.addCriterion(criterion);
            repo.save(evaluation);
        });
    }

    @Override
    public Evaluation addCriterion(Worker user, AddQuantitativeCriterionCommand command) {
        command.validate();
        return modifyEvaluationViaFunc(command.evaluationId, evaluation -> {
            PermissionUtils.errorIfNotManagerOf(user, evaluation.getSectionId());
            EvaluationCriterion criterion = new EvaluationCriterion(
                    command.title, command.weight, command.minGrade, command.maxGrade
            );
            evaluation.addCriterion(criterion);
            repo.save(evaluation);
        });
    }

    @Override
    public Evaluation setThreshold(Worker worker, SetThresholdCommand command) {
        command.validate();
        return modifyEvaluationViaFunc(command.evaluationId, evaluation -> {
            PermissionUtils.errorIfNotManagerOf(worker, evaluation.getSectionId());
            evaluation.updateThresholds(command.punishmentThreshold, command.rewardThreshold);
        });
    }

    @Override
    public Evaluation setActions(Worker worker, SetActionsCommand command) {
        PermissionUtils.errorIfNot(worker.isSuperuser());
        return modifyEvaluationViaFunc(
                command.evaluationId,
                evaluation -> evaluation.setActions(command.reward, command.punishment)
        );
    }

    @Override
    public List<Evaluation> findBySection(FindBySectionCommand command) {
        return repo.findBySection(command.sectionId);
    }

    @Override
    public List<Evaluation> listAll() {
        return repo.listAll();
    }
}
