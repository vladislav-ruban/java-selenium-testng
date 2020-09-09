package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductPage extends BasePage{
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.CSS, using = "#mod_4137928")
    private WebElement addToWishlistButton;

    public void addToWishlist() {
        waitForElementToBeClickable(addToWishlistButton);
        Actions action = new Actions(driver);
        action.moveToElement(addToWishlistButton).click().perform();
    }
}
