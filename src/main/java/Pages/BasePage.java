package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private final int TIMEOUT = 10;
    private final int POLLING = 100;

    protected WebDriver driver;
    protected static WebDriverWait wait;
    protected static Wait fluentWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT);
        fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofMillis(POLLING));
        PageFactory.initElements(driver, this);
    }

    protected void typeToInput(WebElement input, String keys) {
        input.click();
        input.sendKeys(keys);
    }

}
