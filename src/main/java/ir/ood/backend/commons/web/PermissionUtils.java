package ir.ood.backend.commons.web;

import ir.ood.backend.commons.exceptions.ForbiddenException;
import ir.ood.backend.userManagement.domain.Worker;

public class PermissionUtils {

    public static void errorIfNot(boolean condition) {
        if (!condition)
            throw new ForbiddenException("permissions", "User does not have permission for this action");
    }

    public static void errorIfNotManagerOf(Worker manager, int sectionId) {
        if (manager.isSuperuser())
            return;
        if (sectionId != manager.getSectionId() || !manager.isManager())
            throw new ForbiddenException("permissions", "User is not the manager of this section");
    }

}
