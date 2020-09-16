package Pages;

import Utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage extends BasePage{
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//a[@class='ga_cats_lateral'][@data-tracker-cid='6']")
    private WebElement categoryMobileConnectionButton;

    @FindBy(how = How.XPATH, using = "//a[@class='ga_cats_lateral'][@data-tracker-cid='52']")
    private WebElement categoryMobilePhonesButton;

    public void goToCategoryMobilePhones() {
        Actions action = new Actions(driver);
        action.moveToElement(categoryMobileConnectionButton).perform();
        WaitUtils.waitForElementToBeClickable(categoryMobilePhonesButton);
        categoryMobilePhonesButton.click();
    }
}
