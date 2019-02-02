package ir.ood.backend.commons.exceptions;

public class InvalidParameterException extends BaseException {

    public InvalidParameterException(String code, String details) {
        super(code, details);
    }
}
