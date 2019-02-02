package ir.ood.backend.evaluation;

import com.google.inject.AbstractModule;
import ir.ood.backend.evaluation.domain.EvaluationRepo;
import ir.ood.backend.evaluation.interaction.evaluationCreation.EvaluationInteractor;
import ir.ood.backend.evaluation.interaction.evaluationCreation.EvaluationInteractorImpl;
import ir.ood.backend.evaluation.interfaces.db.EvaluationRepoImpl;

public class EvaluationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EvaluationInteractor.class).to(EvaluationInteractorImpl.class);
        bind(EvaluationRepo.class).to(EvaluationRepoImpl.class);
    }

}
