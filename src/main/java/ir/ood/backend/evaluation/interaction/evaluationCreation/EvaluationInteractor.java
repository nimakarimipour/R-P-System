package ir.ood.backend.evaluation.interaction.evaluationCreation;

import ir.ood.backend.evaluation.domain.Evaluation;
import ir.ood.backend.userManagement.domain.Worker;

import java.util.List;

public interface EvaluationInteractor {
    /**
     * Provides methods to create an EvaluationInteractor and add its constituent parts gradually
     */
    Evaluation createEvaluation(Worker worker, AddEvaluationCommand command);

    Evaluation addCriterion(Worker sectionId, AddQualitativeCriterionCommand command);

    Evaluation addCriterion(Worker worker, AddQuantitativeCriterionCommand command);

    Evaluation setThreshold(Worker worker, SetThresholdCommand command);

    Evaluation setActions(Worker worker, SetActionsCommand command);

    List<Evaluation> listAll();

    List<Evaluation> findBySection(FindBySectionCommand command);

}
