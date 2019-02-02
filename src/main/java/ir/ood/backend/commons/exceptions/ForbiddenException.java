package ir.ood.backend.commons.exceptions;

public class ForbiddenException extends BaseException {

    public ForbiddenException(String code, String details) {
        super(code, details);
    }
}
