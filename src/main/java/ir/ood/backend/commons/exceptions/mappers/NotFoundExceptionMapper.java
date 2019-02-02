package ir.ood.backend.commons.exceptions.mappers;

import ir.ood.backend.commons.exceptions.NotFoundException;

public class NotFoundExceptionMapper extends BaseExceptionMapper<NotFoundException> {

    @Override
    protected int getStatusCode() {
        return 404;
    }
}
