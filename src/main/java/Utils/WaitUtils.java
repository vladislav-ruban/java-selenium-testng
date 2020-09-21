package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private final int TIMEOUT = 10;
    private final int POLLING = 100;

    WebDriver driver;
    private WebDriverWait wait;
    private Wait fluentWait;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT);
        fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofMillis(POLLING));
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForURLToContain(String expectedURLPart) {
        wait.until(ExpectedConditions.urlContains(expectedURLPart));
    }

    public void waitForElementToBeVisible(WebElement element) {
        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementsToBeVisible(List<WebElement> listWebElements) {
        fluentWait.until(ExpectedConditions.visibilityOfAllElements(listWebElements));
    }

    public void waitForElementToBeInvisible(WebElement element) {
        fluentWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForElementsToBeInvisible(List<WebElement> listWebElements) {
        fluentWait.until(ExpectedConditions.invisibilityOfAllElements(listWebElements));
    }

    public void waitForElementToContainText(WebElement element) {
        if(!element.getText().isEmpty()) fluentWait.until(driver -> element.getText().length() > 0);
    }

}
