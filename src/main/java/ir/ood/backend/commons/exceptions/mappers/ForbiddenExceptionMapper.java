package ir.ood.backend.commons.exceptions.mappers;

import ir.ood.backend.commons.exceptions.ForbiddenException;

public class ForbiddenExceptionMapper extends BaseExceptionMapper<ForbiddenException> {

    @Override
    protected int getStatusCode() {
        return 403;
    }
}
