package Utils;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Property {
    private static Properties properties = null;
    public static String getProperty(String key) {
        try {
            properties = new Properties();
            properties.load(Objects.requireNonNull(Property.class.getClassLoader()
                    .getResourceAsStream("configuration.properties")));
            return properties == null ? null : properties.getProperty(key, "");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
