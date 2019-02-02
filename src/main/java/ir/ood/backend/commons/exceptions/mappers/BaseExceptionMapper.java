package ir.ood.backend.commons.exceptions.mappers;

import ir.ood.backend.commons.exceptions.BaseException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public abstract class BaseExceptionMapper<T extends BaseException> implements ExceptionMapper<T> {

    @Override
    public Response toResponse(T exception) {
        return Response.status(getStatusCode())
                .entity(exception.toMap())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    protected abstract int getStatusCode();

}
