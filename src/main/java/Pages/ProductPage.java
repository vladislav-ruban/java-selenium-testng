package Pages;

import Utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

    WebDriver driver;
    WaitUtils waitUtils;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUtils = new WaitUtils(driver);
    }

    @FindBy(xpath = ".//span[contains(@class, 'add-to-wishlist-link')]")
    private WebElement addToWishlistButton;

    public void addToWishlist() {
        waitUtils.waitForElementToBeClickable(addToWishlistButton);
        Actions action = new Actions(driver);
        action.moveToElement(addToWishlistButton).click().perform();
    }
}
