package io.einharjar.cognitive_wrapper.utils;
import org.apache.commons.lang3.StringUtils;

public final class ObjectHelper {

    private ObjectHelper() {

    }

    public static void checkNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkString(String s, String message) {
        if (StringUtils.isBlank(s)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkString(String s) {
        if (StringUtils.isBlank(s)) {
            throw new IllegalArgumentException();
        }
    }
}
