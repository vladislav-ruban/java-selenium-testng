package Pages;

import Utils.Converters;
import Utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Header {

    WebDriver driver;
    WaitUtils waitUtils;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUtils = new WaitUtils(driver);
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

    @FindBy(xpath = ".//a[@form-name='login']")
    private WebElement loginButton;

    @FindBy(xpath = ".//button[@class='close announcement-acb']")
    private WebElement closeAdButton;

    @FindBy(xpath = ".//div[@class='form-content type-login']")
    private WebElement loginForm;

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

    public void closeAnnouncement() {
        waitUtils.waitForElementToBeClickable(closeAnnouncementButton);
        closeAnnouncementButton.click();
    }

    public void openLoginForm() {
        waitUtils.waitForElementToBeClickable(loginButton);
        loginButton.click();
    }

    public void loginWithCredentials(String email, String password) {
        waitUtils.waitForElementToBeVisible(loginForm);
        waitUtils.waitForElementToBeClickable(loginFormEmailInput);
        loginFormEmailInput.click();
        loginFormEmailInput.sendKeys(email);
        loginFormPasswordInput.click();
        loginFormPasswordInput.sendKeys(password);
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

    public void verifyEmailNotConfirmedMessage(String expectedMessage) {
        waitUtils.waitForElementToBeInvisible(loginForm);
        waitUtils.waitForElementToBeVisible(emailNotConfirmedMessage);
        Assert.assertEquals(emailNotConfirmedMessage.getText().toLowerCase(), expectedMessage.toLowerCase());
    }

    public void searchFor(String searchQuery) {
        waitUtils.waitForElementToBeVisible(searchBar);
        searchBar.click();
        searchBar.sendKeys(searchQuery);
        searchBar.submit();
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
