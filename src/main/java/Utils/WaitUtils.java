package Utils;

import Pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils extends BasePage {

    private final int TIMEOUT = 10;
    private final int POLLING = 100;

    private WebDriverWait wait;
    private Wait fluentWait;

    public WaitUtils(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, TIMEOUT);
        fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofMillis(POLLING));
    }

    public void waitForAllElementsToBeVisible(List<WebElement> listWebElement) {
        wait.until(ExpectedConditions.visibilityOfAllElements(listWebElement));
    }

    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeInvisibe(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForURLToContain(String expectedURLPart) {
        wait.until(ExpectedConditions.urlContains(expectedURLPart));
    }

    public void waitFluentlyForElementToBeVisible(WebElement element) {
        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitFluentlyForAllElementsToBeVisible(List<WebElement> listWebElements) {
        fluentWait.until(ExpectedConditions.visibilityOfAllElements(listWebElements));
    }

    public void waitFluentlyForElementToBeInvisible(WebElement element) {
        fluentWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitFluentlyForAllElementsToBeInvisible(List<WebElement> listWebElements) {
        fluentWait.until(ExpectedConditions.invisibilityOfAllElements(listWebElements));
    }

    public void waitForElementToContainText(WebElement element) {
        fluentWait.until(driver -> element.getText().length() > 0);
    }

}
