package Pages;

import Dto.UserCredentialsDto;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class UserpanelProfilePage extends BasePage {

    public UserpanelProfilePage(WebDriver driver) {
        super(driver);
        initialWait(driver);
    }

    @Override
    public void initialWait(WebDriver driver) {
        waitUtils.waitForElementToBeVisible(usernameLabel);
    }

    @FindBy(xpath = ".//div[@class='user-name']")
    private WebElement usernameLabel;

    public void checkIfYouLogged() {
        waitUtils.waitForElementToBeVisible(usernameLabel);
        Assert.assertEquals(usernameLabel.getText(), UserCredentialsDto.getEmail());
    }

}
