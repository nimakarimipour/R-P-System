package ir.ood.backend.commons.requestParams;

import ir.ood.backend.commons.exceptions.InvalidParameterException;

import java.util.function.Predicate;

public abstract class Validatable {

    protected <T> void validateField(T value, Predicate<T> condition, String fieldName) {
        if (condition.test(value)) {
            return;
        }
        throw new InvalidParameterException("invalidField", String.format("Value of `%s` is not valid", fieldName));
    }

    public abstract void validate();

}
