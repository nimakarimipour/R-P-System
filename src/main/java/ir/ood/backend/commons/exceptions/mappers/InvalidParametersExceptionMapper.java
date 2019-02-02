package ir.ood.backend.commons.exceptions.mappers;

import ir.ood.backend.commons.exceptions.InvalidParameterException;

public class InvalidParametersExceptionMapper extends BaseExceptionMapper<InvalidParameterException> {

    @Override
    protected int getStatusCode() {
        return 400;
    }

}
