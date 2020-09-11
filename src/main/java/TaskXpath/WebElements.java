package TaskXpath;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public final class WebElements {
    //Main page "https://price.ua/"
    @FindBy(how = How.XPATH, using = "//div[contains(text(), 'Акции супермаркетов')]")
    private WebElement supermarketsPromotionsTab;

    //Main page "https://price.ua/"
    @FindBy(how = How.XPATH, using = "//li/a[contains(text(), 'Микрокредитование')]")
    private WebElement microcreditsCategory;

    //Main page "https://price.ua/"
    @FindBy(how = How.XPATH, using = "//div[@class='level-links']/a[starts-with(text(), 'Микрокредитование')]")
    private WebElement microcreditsLink;

    //Security cameras category "https://price.ua/catc515t4.html"
    @FindBy(how = How.XPATH, using = "//div[@class='white-wrap']/a[starts-with(text(), 'GreenVision')]")
    private WebElement producerGreenVisionProductTitles;

    //Security cameras "https://price.ua/catc515t4.html"
    @FindBy(how = How.XPATH, using = "//div[@class='white-wrap']/a[not(starts-with(text(), 'GreenVision'))]")
    private WebElement producerNotGreenVisionTitles;

    //Any product category or search results page
    @FindBy(how = How.XPATH, using = "//div[@class='product-block-shadow']/span[not(@class='add-to-wishlist-link left_sided added')]")
    private WebElement everyProductExceptWishlisted;

    //Header or categories list
    @FindBy(how = How.XPATH, using = "//a[@class='item l-fintech is-new' or @data-tracker-title='Fintech']")
    private WebElement microcreditsButton;

    //Header
    @FindBy(how = How.XPATH, using = "//a[@class='item l-sale-supermarkets' or @class='item l-sale ga_header_sales']")
    private List<WebElement> salesInHeaderLinks;

    //When 2 products are in comparison
    @FindBy(how = How.XPATH, using = "//span[@class='compare-counter active' and contains(text(), '2')]")
    private WebElement activeCompareCounterContains2;

    //When 2 products are in wishlist
    @FindBy(how = How.XPATH, using = "//span[@class='wishlist-counter js-wishlist-counter active' and contains(text(), '2')]")
    private WebElement activeWishlistCounterContains2;

    //Any product category page or search result
    @FindBy(how = How.XPATH, using = "//ancestor::div[@class='product-block-shadow']")
    private List<WebElement> wholeResultProductCards;

    //Any product category page, for example: "https://price.ua/catc515t4.html"
    @FindBy(how = How.XPATH, using = "//span[@typeof='ListItem'][last()]")
    private WebElement previousCategory;

    //Any page when categories are visible
    @FindBy(how = How.XPATH, using = "//div[@class='categories']/ul/li[last()]")
    private WebElement lastCategoryInCategoriesMenu;

    //Any page when login form register tab opened and there is error under email input
    @FindBy(how = How.XPATH, using = "//input[@id='RegisterUserFirmForm_user_email']/following-sibling::div[@class='error-text']")
    private WebElement loginFormErrorMessageUnderEmail;

    //Any page when login form register tab opened and there is error under password input
    @FindBy(how = How.XPATH, using = "//input[@id='user_user_password']/following-sibling::div[@class='error-text']")
    private WebElement loginFormErrorMessageUnderPassword;

    //Any page when login form opened and there are errors under inputs
    @FindBy(how = How.XPATH, using = "//input[@id='LoginForm_username']/following::div[@class='error-text']")
    private List<WebElement> loginFormErrorMessagesUnderInputs;

    //Any page when login form register tab opened and there are errors under inputs
    @FindBy(how = How.XPATH, using = "//input[@id='RegisterUserFirmForm_user_email']/following::div[@class='error-text']")
    private List<WebElement> loginFormRegisterTabErrorMessagesUnderInputs;

    //Login form
    @FindBy(how = How.XPATH, using = "//a[@id='go-tab-recovery']/preceding::div[@class='form-line required-star clearer-block with-black-error']/input[@class='input-text']")
    private List<WebElement> loginFormInputs;

    //Main page
    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Смотреть все категории')]/preceding::a[@class='cat-vs-img-link']")
    private List<WebElement> foodAndAlcoholCards;

    //Any product page
    @FindBy(how = How.XPATH, using = "descendant::div[@class='top5-prices-orange']/a")
    private List<WebElement> productTopFivePrices;

    //Any product page
    @FindBy(how = How.XPATH, using = "descendant::div[@class='title product-recomend-name']")
    private List<WebElement> productSimilarModelNames;

    //Main page
    @FindBy(how = How.XPATH, using = "//parent::div[@class='cat-vs-img-photo']")
    private List<WebElement> foodAndAlcoholPhotos;

    //Any product category or search results page
    @FindBy(how = How.XPATH, using = "//parent::div[@class='product-block-shadow']")
    private List<WebElement> wholeProductCards;

    //Categories menu
    @FindBy(how = How.XPATH, using = "//ul[@id='main-categories-menu']/li[count(//ul[@id='main-categories-menu']/li)]/a")
    private WebElement lastCategoryLink;

    //Any product category
    @FindBy(how = How.XPATH, using = "//div[@class='recomend-item clearer-block ga_container'][count(//div[@class='recomend-item clearer-block ga_container'])]")
    private WebElement lastViewingNowProduct;

    //Any product category or search result page
    @FindBy(how = How.XPATH, using = "//div[@class='price-wrap']/span|//div[@class='link-container']/span")
    private List<WebElement> resultPrices;

    //Any product category or search result page
    @FindBy(how = How.XPATH, using = "count(//div[@class='product-block-shadow'])>=1")
    private boolean isProductsPresented;

    //Any product category or search result page
    @FindBy(how = How.XPATH, using = "count(//div[@class='product-block-shadow'])<=40")
    private boolean isProductsQuantityLessOrEqual40;

    //Any product category or search result page
    @FindBy(how = How.XPATH, using = "count(//div[@class='product-block-shadow']) mod 7")
    private int productCardsQuantityMod7;

    //Any product category or search result page
    @FindBy(how = How.XPATH, using = "count(//div[@class='product-block-shadow']) mod 3")
    private int productCardsQuantityMod3;
}
