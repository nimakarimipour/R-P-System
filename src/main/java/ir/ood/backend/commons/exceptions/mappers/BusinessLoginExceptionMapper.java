package ir.ood.backend.commons.exceptions.mappers;

import ir.ood.backend.commons.exceptions.BusinessLogincException;

public class BusinessLoginExceptionMapper extends BaseExceptionMapper<BusinessLogincException> {

    @Override
    protected int getStatusCode() {
        return 409;
    }
}
