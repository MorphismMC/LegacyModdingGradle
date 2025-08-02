package io.github.morphismmc.legacymodding.util;

import java.nio.charset.Charset;
import java.util.Locale;

public final class StringUtil {

    private StringUtil() {
    }

    /**
     * Get the platform native charset. To see how this differs from the default charset,
     * see <a href="https://openjdk.org/jeps/400">...</a>. This property cannot be overridden via system
     * property.
     */
    public static Charset getNativeCharset() {
        return NativeEncodingHolder.charset;
    }

    public static String capitalize(String input) {
        if (input.isEmpty()) return "";
        return input.substring(0, 1).toUpperCase(Locale.ROOT) + input.substring(1);
    }

    public static String uncapitalize(String input) {
        if (input.isEmpty()) return "";
        return input.substring(0, 1).toLowerCase(Locale.ROOT) + input.substring(1);
    }

    private static class NativeEncodingHolder {

        static final Charset charset;

        static {
            var nativeEncoding = System.getProperty("native.encoding");
            if (nativeEncoding == null) {
                throw new IllegalStateException("The native.encoding system property is not available, but should be since Java 17!");
            }
            charset = Charset.forName(nativeEncoding);
        }
    }
}
