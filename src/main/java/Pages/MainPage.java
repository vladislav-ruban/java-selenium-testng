package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
        initialWait(driver);
    }

    @Override
    public void initialWait(WebDriver driver) {
        waitUtils.waitForElementToBeVisible(productsAndAlcoholTitle);
    }

    @FindBy(xpath = "//div[@class='blue-title-with-grey-line']")
    private WebElement productsAndAlcoholTitle;

    @FindBy(xpath = ".//a[@class='ga_cats_lateral'][@data-tracker-cid='6']")
    private WebElement categoryMobileConnectionButton;

    @FindBy(xpath = ".//a[@class='ga_cats_lateral'][@data-tracker-cid='52']")
    private WebElement categoryMobilePhonesButton;

    @FindBy(xpath = ".//img[@decoding='async']")
    private WebElement adGoogleImg;

    private String categoryXpathPattern = ".//a[@class='ga_cats_lateral'][contains(text(), '%s')]";

    public void goToCategoryMobilePhones() {
        Actions action = new Actions(driver);
        action.moveToElement(categoryMobileConnectionButton).perform();
        waitUtils.waitForElementToBeClickable(categoryMobilePhonesButton);
        categoryMobilePhonesButton.click();
    }

    public void hoverOnCategory(String categoryName) {
        String categoryXpath = String.format(categoryXpathPattern, categoryName);
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath(categoryXpath))).perform();
    }

    public void goToCategory(String categoryName) {
        String categoryXpath = String.format(categoryXpathPattern, categoryName);
        waitUtils.waitForElementToBeClickable(driver.findElement(By.xpath(categoryXpath)));
        driver.findElement(By.xpath(categoryXpath)).click();
    }
}
