package Utils;

import java.io.IOException;
import java.util.Properties;

public class Property {

    public static String getProperty(String key) {
        Properties properties = new Properties();
        try {
            properties.load(Property.class.getResourceAsStream("configuration.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key == null ? null : properties.getProperty(key, "");
    }
}
