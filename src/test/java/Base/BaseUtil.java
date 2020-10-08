package Base;

import org.openqa.selenium.WebDriver;

public class BaseUtil {

    private WebDriver driver;

    public WebDriver getDriver() { return driver; }

    public void setDriver(WebDriver driver) { this.driver = driver;}

    public void openMainPage() {
        getDriver().get("https://price.ua/");
    }
}
