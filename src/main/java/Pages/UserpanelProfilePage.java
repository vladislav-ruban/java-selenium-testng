package Pages;

import Dto.UserCredentialsDto;
import Utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class UserpanelProfilePage {

    WebDriver driver;
    WaitUtils waitUtils;

    public UserpanelProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUtils = new WaitUtils(driver);
    }

    @FindBy(xpath = ".//div[@class='user-name']")
    private WebElement usernameLabel;

    public void checkIfYouLogged() {
        waitUtils.waitForElementToBeVisible(usernameLabel);
        Assert.assertEquals(usernameLabel.getText(), UserCredentialsDto.getEmail());
    }

}
