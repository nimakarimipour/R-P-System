package ir.ood.backend.commons.web;

import ir.ood.backend.commons.exceptions.InternalException;

import java.util.HashMap;
import java.util.Map;

public interface Serializable {

    default Map mapOf(Object... args) {
        if (args.length % 2 != 0)
            throw new InternalException("internal_error", "Could not construct a map with even number of key/values");
        Map result = new HashMap();
        for (int i = 0; i < args.length; i += 2)
            result.put(args[i], args[i + 1]);
        return result;
    }

    Map serialize();

}
