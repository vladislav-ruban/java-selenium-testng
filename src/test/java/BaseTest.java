import DriverFactory.DriverFactory;
import Enums.Browsers;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {
    private WebDriver driver;

    @BeforeTest
    @Parameters({"browser"})
    public void setUp(@Optional("CHROME") Browsers browser) {
        driver = DriverFactory.getBrowser(browser);
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    public void openPage() {
        driver.get("https://price.ua/");
    }

    @AfterMethod
    public void clearCookie() {
        driver.manage().deleteAllCookies();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
