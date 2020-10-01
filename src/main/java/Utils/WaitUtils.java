package Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private final int TIMEOUT = 15;
    private final int POLLING = 100;

    WebDriver driver;
    private WebDriverWait wait;
    private Wait fluentWait;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT);
        fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofMillis(POLLING))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
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

    public void waitForElementPresenceBy(By locator) {
        fluentWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementsPresenceBy(By locator) {
        fluentWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void waitForElementToBeClickable(By locator) {
        fluentWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementToBeVisibleAfterRefresh(WebElement element) {
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }

    public void waitForElementsToBeVisibleAfterRefresh(List<WebElement> list) {
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(list)));
    }

}

