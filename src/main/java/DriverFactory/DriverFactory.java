package DriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import static Utils.Property.getProperty;

public class DriverFactory {
    private static String browser = getProperty("browser");
    public static WebDriver getBrowser() {
        switch(browser) {
            case "CHROME" -> {
                return getChromeDriver();
            }
            case "FIREFOX" -> {
                return getFirefoxDriver();
            }
            case "EDGE" -> {
                return getEdgeDriver();
            }
            default -> throw new RuntimeException("This driver type is not supported. Its implementation can be added to DriverFactory.");
        }
    }

    private static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        //options.addArguments("start-maximized");
        return new ChromeDriver(options);
    }

    private static WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(new FirefoxProfile());
        options.addPreference("dom.webnotifications.enabled", false);
        return new FirefoxDriver();
    }

    private static WebDriver getEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
}