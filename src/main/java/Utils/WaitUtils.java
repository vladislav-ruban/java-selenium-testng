package Utils;

import Pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class WaitUtils extends BasePage {

    public WaitUtils(WebDriver driver) {
        super(driver);
    }

    public static void waitForAllElementsToBeVisible(List<WebElement> listWebElement) {
        wait.until(ExpectedConditions.visibilityOfAllElements(listWebElement));
    }

    public static void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeInvisibe(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForURLToContain(String expectedURLPart) {
        wait.until(ExpectedConditions.urlContains(expectedURLPart));
    }

    public static void waitFluentlyForElementToBeVisible(WebElement element) {
        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitFluentlyForAllElementsToBeVisible(List<WebElement> listWebElements) {
        fluentWait.until(ExpectedConditions.visibilityOfAllElements(listWebElements));
    }

    public static void waitFluentlyForElementToBeInvisible(WebElement element) {
        fluentWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitFluentlyForAllElementsToBeInvisible(List<WebElement> listWebElements) {
        fluentWait.until(ExpectedConditions.invisibilityOfAllElements(listWebElements));
    }

    public static void waitForElementToContainText(WebElement element) {
        fluentWait.until(driver -> element.getText().length() > 0);
    }

}
