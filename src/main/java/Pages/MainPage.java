package Pages;

import Utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    WebDriver driver;
    WaitUtils waitUtils;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUtils = new WaitUtils(driver);
    }
    
    @FindBy(xpath = ".//a[@class='ga_cats_lateral'][@data-tracker-cid='6']")
    private WebElement categoryMobileConnectionButton;

    @FindBy(xpath = ".//a[@class='ga_cats_lateral'][@data-tracker-cid='52']")
    private WebElement categoryMobilePhonesButton;

    @FindBy(xpath = ".//img[@decoding='async']")
    private WebElement adGoogleImg;

    public void goToCategoryMobilePhones() {
        Actions action = new Actions(driver);
        action.moveToElement(categoryMobileConnectionButton).perform();
        waitUtils.waitForElementToBeClickable(categoryMobilePhonesButton);
        categoryMobilePhonesButton.click();
    }
}
