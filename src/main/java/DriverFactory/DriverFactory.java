package DriverFactory;

import Enums.Browsers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DriverFactory {
    public static WebDriver getBrowser(Browsers browser) {
        switch(browser) {
            case CHROME -> {
                return getChromeDriver();
            }
            case FIREFOX -> {
                return getFirefoxDriver();
            }
            case EDGE -> {
                return getEdgeDriver();
            }
            default -> throw new RuntimeException("This driver type is not supported. Its implementation can be added to DriverFactory.");
        }
    }

    public static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        //options.addArguments("start-maximized");
        return new ChromeDriver(options);
    }

    public static WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(new FirefoxProfile());
        options.addPreference("dom.webnotifications.enabled", false);
        return new FirefoxDriver();
    }

    public static WebDriver getEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
}