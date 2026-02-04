package TestBase;

import org.openqa.selenium.By;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Locator {

    private static Properties properties;

    static {
        properties = new Properties();

        try(InputStream inputStream = Locator.class.getResourceAsStream("/locators.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static By getLocator(String key) {
        String value = properties.getProperty(key);
        String[] result = value.split("=", 2);

        return switch (LocatorType.valueOf(result[0])) {
            case LocatorType.id -> By.id(result[1]);
            case LocatorType.name -> By.name(result[1]);
            case LocatorType.css -> By.cssSelector(result[1]);
        };
    }

}
