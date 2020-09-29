package Utils;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Property {
    private static Properties properties = null;

    public static String getBrowser() {
        try {
            properties.load(Objects.requireNonNull(Property.class.getClassLoader()
                    .getResourceAsStream("application.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("browser");
    }

    public static String getProperty(String key) {
        return properties == null ? null : properties.getProperty(key, "");
    }
}
