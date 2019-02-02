package ir.ood.backend.commons.exceptions;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseException extends RuntimeException {

    private String code;
    private String details;

    public BaseException(String code, String details) {
        this.code = code;
        this.details = details;
    }

    public Map<String, Object> toMap() {
        return new HashMap<String, Object>() {{
            put("code", code);
            put("details", details);
        }};
    }

}
