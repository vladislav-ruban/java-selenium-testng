import DriverFactory.DriverFactory;
import Enums.Browsers;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;

    @BeforeTest
    @Parameters({"browser"})
    public void setUp(Browsers browser) {
        driver = DriverFactory.getBrowser(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    public void openPage() {
        driver.get("https://price.ua/");
    }

    public WebDriver getDriver() {
        return driver;
    }
}
