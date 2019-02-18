package framework.utils;

import org.testng.Assert;

import java.io.IOException;
import java.util.Properties;

public class PropertiesFileLoader {

    public static Properties load(String resourceFileName) {
        Properties properties = new Properties();
        try {
            properties.load(PropertiesFileLoader.class.getClassLoader().getResourceAsStream(resourceFileName));
        } catch (IOException e) {
            Assert.fail("Could not open resource file. " + e.getLocalizedMessage());
        }
        return properties;
    }
}
