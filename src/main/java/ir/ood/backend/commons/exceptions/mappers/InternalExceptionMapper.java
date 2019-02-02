package ir.ood.backend.commons.exceptions.mappers;

import ir.ood.backend.commons.exceptions.InternalException;

public class InternalExceptionMapper extends BaseExceptionMapper<InternalException> {

    @Override
    protected int getStatusCode() {
        return 500;
    }
}
