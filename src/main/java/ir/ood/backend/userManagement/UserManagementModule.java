package ir.ood.backend.userManagement;

import com.google.inject.AbstractModule;
import ir.ood.backend.userManagement.domain.SectionRepository;
import ir.ood.backend.userManagement.domain.WorkerRepo;
import ir.ood.backend.userManagement.interaction.worker.WorkerInteractor;
import ir.ood.backend.userManagement.interaction.worker.WorkerInteractorImpl;
import ir.ood.backend.userManagement.interaction.login.LoginInteractor;
import ir.ood.backend.userManagement.interaction.login.LoginInteractorImpl;
import ir.ood.backend.userManagement.interaction.section.SectionInteractor;
import ir.ood.backend.userManagement.interaction.section.SectionInteractorImpl;
import ir.ood.backend.userManagement.interaction.token.TokenManager;
import ir.ood.backend.userManagement.interaction.token.TokenManagerImpl;
import ir.ood.backend.userManagement.interfaces.db.SectionRepositoryImpl;
import ir.ood.backend.userManagement.interfaces.db.WorkerRepoImpl;

public class UserManagementModule extends AbstractModule {

    protected void configure() {
        bind(WorkerRepo.class).to(WorkerRepoImpl.class);
        bind(LoginInteractor.class).to(LoginInteractorImpl.class);
        bind(TokenManager.class).to(TokenManagerImpl.class);
        bind(WorkerInteractor.class).to(WorkerInteractorImpl.class);
        bind(SectionRepository.class).to(SectionRepositoryImpl.class);
        bind(SectionInteractor.class).to(SectionInteractorImpl.class);
    }

}
