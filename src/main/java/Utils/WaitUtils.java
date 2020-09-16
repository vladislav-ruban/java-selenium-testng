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

    public static void waitForAllElementsToAppear(List<WebElement> listWebElement) {
        wait.until(ExpectedConditions.visibilityOfAllElements(listWebElement));
    }

    public static void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForURLToContain(String expectedURLPart) {
        wait.until(ExpectedConditions.urlContains(expectedURLPart));
    }

}
