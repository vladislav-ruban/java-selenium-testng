package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{

    public ProductPage(WebDriver driver) {
        super(driver);
        initialWait(driver);
    }

    @Override
    public void initialWait(WebDriver driver) {
        waitUtils.waitForElementToBeVisible(pageTitle);
    }

    @FindBy(xpath = "//div[@class='page-title']")
    private WebElement pageTitle;

    @FindBy(xpath = ".//span[contains(@class, 'add-to-wishlist-link')]")
    private WebElement addToWishlistButton;

    public void addToWishlist() {
        waitUtils.waitForElementToBeClickable(addToWishlistButton);
        Actions action = new Actions(driver);
        action.moveToElement(addToWishlistButton).click().perform();
    }
}
