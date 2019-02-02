package ir.ood.backend.commons.exceptions;

public class NotFoundException extends BaseException {

    public NotFoundException(String code, String details) {
        super(code, details);
    }
}
