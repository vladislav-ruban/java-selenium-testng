package Pages;

import Dto.UserCredentialsDto;
import Utils.Converters;
import Utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Header {

    WebDriver driver;
    WaitUtils waitUtils;

    Actions actions;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUtils = new WaitUtils(driver);
        actions = new Actions(driver);
    }
    
    @FindBy(xpath = ".//button[@class='close announcement-acb']")
    private WebElement closeAnnouncementButton;

    @FindBy(xpath = ".//header[@id='site-header']")
    private WebElement header;

    @FindBy(xpath = ".//input[@id='SearchForm_searchPhrase']")
    private WebElement searchBar;

    @FindBy(xpath = ".//a[@class='wishlist-panel-link']")
    private WebElement wishlistButton;

    @FindBy(xpath = ".//span[@class='amount-wish']")
    private WebElement wishlistPriceLabel;

    @FindBy(xpath = ".//div[@class='link for-login']")
    private WebElement loginButton;

    @FindBy(xpath = ".//button[@class='close announcement-acb']")
    private WebElement closeAdButton;

    @FindBy(xpath = ".//div[@id='user-popup-forms']")
    private WebElement loginForm;

    @FindBy(xpath = ".//div[@class='tab active']")
    private WebElement loginFormActiveTab;

    @FindBy(xpath = ".//input[@name='LoginForm[username]']")
    private WebElement loginFormEmailInput;

    @FindBy(xpath = ".//input[@name='LoginForm[password]']")
    private WebElement loginFormPasswordInput;

    @FindBy(xpath = ".//div[@class='tab active']//div[@class='title']/span")
    private WebElement emailNotConfirmedMessage;

    @FindBy(xpath = ".//div[@id='go-tab-userregister']")
    private WebElement loginFormRegisterTab;

    @FindBy(xpath = ".//input[@id='RegisterUserFirmForm_user_email']")
    private WebElement loginFormRegisterTabEmailInput;

    @FindBy(xpath = ".//input[@id='user_user_password']")
    private WebElement loginFormRegisterTabPasswordInput;

    @FindBy(xpath = ".//a[@data-type='userregister']")
    private WebElement loginFormRegisterButton;

    @FindBy(xpath = ".//input[@id='RegisterUserFirmForm_user_email']/following-sibling::div[@class='error-text']")
    private WebElement loginFormErrorMessageUnderEmail;

    @FindBy(xpath = ".//input[@id='user_user_password']/following-sibling::div[@class='error-text']")
    private WebElement loginFormErrorMessageUnderPassword;

    @FindBy(xpath = ".//a[@id='header-user-link']")
    private WebElement userButton;

    @FindBy(xpath = ".//a[@class='i-profile']")
    private WebElement userPanelButton;

    @FindBy(xpath = ".//a['i-logout']")
    private WebElement userLogoutButton;

    private String removeProductFromWishlistButtonPath = ".//div[@class='local-wish-item']/a[contains(text(), '%s')]/../span[contains(@class, 'remove')]";

    public void closeAnnouncement() {
        waitUtils.waitForElementToBeClickable(closeAnnouncementButton);
        actions.moveToElement(closeAnnouncementButton).click(closeAnnouncementButton).perform();
    }

    public void removeFromWishlist(String productName) {
        WebElement removeProductFromWishlistButton =
                driver.findElement(By.xpath(String.format(removeProductFromWishlistButtonPath, productName)));
        waitUtils.waitForElementToBeClickable(removeProductFromWishlistButton);
        removeProductFromWishlistButton.click();
    }

    public void openLoginForm() {
        waitUtils.waitForElementToBeClickable(loginButton);
        loginButton.click();
        waitUtils.waitForElementPresenceBy(By.xpath(".//div[@role='dialog']"));
    }

    public void loginWithCredentials(String email, String password) {
        waitUtils.waitForElementToBeClickable(loginFormEmailInput);
        loginFormEmailInput.click();
        loginFormEmailInput.sendKeys(email);
        loginFormPasswordInput.click();
        loginFormPasswordInput.sendKeys(password);
        UserCredentialsDto.setEmail(email);
        UserCredentialsDto.setPassword(password);
        loginFormPasswordInput.submit();
    }

    public void GoToLoginFormRegisterTab() {
        waitUtils.waitForElementToBeClickable(loginFormRegisterTab);
        loginFormRegisterTab.click();
    }

    public void registerWithCredentials(String email, String password) {
        waitUtils.waitForElementToBeClickable(loginFormRegisterTabEmailInput);
        loginFormRegisterTabEmailInput.click();
        loginFormRegisterTabEmailInput.sendKeys(email);
        loginFormRegisterTabPasswordInput.click();
        loginFormRegisterTabPasswordInput.sendKeys(password);
        loginFormRegisterButton.click();
    }

    public void verifyErrorMessages(String expectedMessageUnderEmail, String expectedMessageUnderPassword) {
        Assert.assertEquals(loginFormErrorMessageUnderEmail.getText(), expectedMessageUnderEmail);
        Assert.assertEquals(loginFormErrorMessageUnderPassword.getText(), expectedMessageUnderPassword);
    }

    public void verifyEmailNotConfirmedMessage(String expected) {
        waitUtils.waitForElementPresenceBy(By.xpath(".//div[@id='tab-not-confirmed']"));
        String actualMessage = emailNotConfirmedMessage.getText().toLowerCase();
        String expectedMessage = expected.toLowerCase();
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    public void searchFor(String searchQuery) {
        waitUtils.waitForElementToBeClickable(searchBar);
        searchBar.click();
        searchBar.sendKeys(searchQuery);
        searchBar.submit();
    }

    public void openUserContext() {
        waitUtils.waitForElementToBeClickable(userButton);
        userButton.click();
    }

    public void openUserpanel() {
        openUserContext();
        waitUtils.waitForElementToBeClickable(userPanelButton);
        userPanelButton.click();
    }

    public void logOut() {
        openUserContext();
        actions.moveToElement(userLogoutButton).click(userLogoutButton).perform();
    }

    public void openWishlist() {
        waitUtils.waitForElementToBeClickable(wishlistButton);
        wishlistButton.click();
    }

    public void verifyWishlistPrice(int expectedPrice) {
        waitUtils.waitForElementToBeVisible(wishlistPriceLabel);
        int wishlistPrice = Converters.stringCutAndParseToInt(wishlistPriceLabel.getText());
        Assert.assertEquals(wishlistPrice, expectedPrice);
    }
}
