package Pages;

import Utils.Converters;
import Utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class Header extends BasePage {

    public Header(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//button[@class='close announcement-acb']")
    private WebElement closeAnnouncementButton;

    @FindBy(how = How.XPATH, using = "//header[@id='site-header']")
    private WebElement header;

    @FindBy(how = How.XPATH, using = "//input[@id='SearchForm_searchPhrase']")
    private WebElement searchBar;

    @FindBy(how = How.XPATH, using = "//a[@class='wishlist-panel-link']")
    private WebElement wishlistButton;

    @FindBy(how = How.XPATH, using = "//span[@class='amount-wish']")
    private WebElement wishlistPriceLabel;

    @FindBy(how = How.XPATH, using = "//a[@form-name='login']")
    private WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//button[@class='close announcement-acb']")
    private WebElement closeAdButton;

    @FindBy(how = How.XPATH, using = "//div[@class='form-content type-login']")
    private WebElement loginForm;

    @FindBy(how = How.XPATH, using = "//input[@name='LoginForm[username]']")
    private WebElement loginFormEmailInput;

    @FindBy(how = How.XPATH, using = "//input[@name='LoginForm[password]']")
    private WebElement loginFormPasswordInput;

    @FindBy(how = How.XPATH, using = "//div[@class='tab active']//div[@class='title']/span")
    private WebElement emailNotConfirmedMessage;

    @FindBy(how = How.XPATH, using = "//div[@id='go-tab-userregister']")
    private WebElement loginFormRegisterTab;

    @FindBy(how = How.XPATH, using = "//input[@id='RegisterUserFirmForm_user_email']")
    private WebElement loginFormRegisterTabEmailInput;

    @FindBy(how = How.XPATH, using = "//input[@id='user_user_password']")
    private WebElement loginFormRegisterTabPasswordInput;

    @FindBy(how = How.XPATH, using = "//a[@data-type='userregister']")
    private WebElement loginFormRegisterButton;

    @FindBy(how = How.XPATH, using = "//input[@id='RegisterUserFirmForm_user_email']/following-sibling::div[@class='error-text']")
    private WebElement loginFormErrorMessageUnderEmail;

    @FindBy(how = How.XPATH, using = "//input[@id='user_user_password']/following-sibling::div[@class='error-text']")
    private WebElement loginFormErrorMessageUnderPassword;

    public void closeAnnouncement() {
        WaitUtils.waitForElementToBeClickable(closeAnnouncementButton);
        closeAnnouncementButton.click();
    }

    public void openLoginForm() {
        WaitUtils.waitForElementToBeClickable(loginButton);
        loginButton.click();
    }

    public void loginWithCredentials(String email, String password) {
        WaitUtils.waitForElementToBeVisible(loginForm);
        WaitUtils.waitForElementToBeClickable(loginFormEmailInput);
        loginFormEmailInput.click();
        loginFormEmailInput.sendKeys(email);
        loginFormPasswordInput.click();
        loginFormPasswordInput.sendKeys(password);
        loginFormPasswordInput.submit();
    }

    public void GoToLoginFormRegisterTab() {
        WaitUtils.waitForElementToBeClickable(loginFormRegisterTab);
        loginFormRegisterTab.click();
    }

    public void registerWithCredentials(String email, String password) {
        WaitUtils.waitForElementToBeClickable(loginFormRegisterTabEmailInput);
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

    public void verifyEmailNotConfirmedMessage(String expectedMessage) {
        WaitUtils.waitForElementToBeInvisibe(loginForm);
        WaitUtils.waitForElementToBeVisible(emailNotConfirmedMessage);
        Assert.assertEquals(emailNotConfirmedMessage.getText().toLowerCase(), expectedMessage.toLowerCase());
    }

    public void searchFor(String searchQuery) {
        WaitUtils.waitForElementToBeVisible(searchBar);
        searchBar.click();
        searchBar.sendKeys(searchQuery);
        searchBar.submit();
    }

    public void openWishlist() {
        WaitUtils.waitForElementToBeClickable(wishlistButton);
        wishlistButton.click();
    }

    public void verifyWishlistPrice(int expectedPrice) {
        WaitUtils.waitForElementToBeVisible(wishlistPriceLabel);
        int wishlistPrice = Converters.stringCutAndParseToInt(wishlistPriceLabel.getText());
        Assert.assertEquals(wishlistPrice, expectedPrice);
    }
}
