package Base;

import DriverFactory.DriverFactory;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected WebDriver driver;

    public void setUp() {
        driver = DriverFactory.getBrowser();
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void openMainPage() {
        driver.get("https://price.ua/");
    }

    public void clearCookie() {
        driver.manage().deleteAllCookies();
    }

    public void tearDown() {
        driver.quit();
    }
}
