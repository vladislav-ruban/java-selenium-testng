import DriverFactory.DriverFactory;
import Enums.Browsers;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    @Parameters({"browser"})
    public void setUp(@Optional("CHROME") Browsers browser) {
        driver = DriverFactory.getBrowser(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void openPage() {
        driver.get("https://price.ua/");
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
