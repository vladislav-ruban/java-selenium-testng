package Pages;

import Dto.ProductNameDto;
import Utils.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class CategoryMobilePhonesPage extends BasePage{

    public CategoryMobilePhonesPage(WebDriver driver) {
        super(driver);
        initialWait(driver);
    }

    @Override
    public void initialWait(WebDriver driver) {
        waitUtils.waitForElementToBeVisible(caseTypeMonoblock);
    }

    @FindBy(xpath = "//li/a[contains(text(), 'моноблок')]")
    private WebElement caseTypeMonoblock;

    @FindBy(xpath = ".//span[@class='breadcrumbs-last']")
    private WebElement categoryName;

    @FindBy(xpath = ".//a[@class='model-name ga_card_mdl_title']")
    private List<WebElement> modelNameTitles;

    @FindBy(xpath = ".//input[@id='price_min_']")
    private WebElement minPriceInput;

    @FindBy(xpath = ".//input[@id='price_max_']")
    private WebElement maxPriceInput;

    @FindBy(xpath = ".//a[@class='btn btn-purple ga_cat_filter btn-filters-submit btn-ok']")
    private WebElement inputPriceOKButton;

    @FindBy(xpath = ".//div[@class='applied-filters']")
    private WebElement appliedFiltersTitle;

    @FindBy(xpath = ".//div[@class='price-wrap']//span[@class='price']")
    private List<WebElement> searchResultPrices;

    @FindBy(xpath = ".//div[@class='loader-dots']']")
    private WebElement loaderDotsWrap;

    @FindBy(xpath = ".//div[@class='wishlist-popunder active']")
    private WebElement popunderWishListActive;

    private String manufacturerCheckboxPath = ".//a[@data-filter-type='producer'][contains(text(), '%s')]";

    private String fixedPriceCheckboxMaxPath = ".//a[contains(@data-filter-value, '\"price[max]\":\"%s\"')]";
    private String fixedPriceCheckboxMinPath = ".//a[contains(@data-filter-value, '\"price[min]\":\"%s\"')]";
    private int fixedPriceExample;

    private String addToWishlistButtonPath = "//a[text()='%s']/../../span[contains(@class, 'add-to-wishlist-link ')]";
    private String productTitlePath = ".//a[text()='%s']";

    public void addProductToWishlist(String productName) {
        waitUtils.waitForElementToBeVisible(categoryName);
        String addToWishlistButtonXpath = String.format(addToWishlistButtonPath, productName);
        waitUtils.waitForElementPresenceBy(By.xpath(addToWishlistButtonXpath));
        WebElement addToWishlistButton = driver.findElement(By.xpath(addToWishlistButtonXpath));
        ProductNameDto.setName(productName);
        waitUtils.waitForElementToBeClickable(addToWishlistButton);
        addToWishlistButton.click();
        waitUtils.waitForElementToBeVisible(popunderWishListActive);
    }

    public void filterByManufacturer(String manufacturerName) {
        String manufacturerCheckboxXpath = String.format(manufacturerCheckboxPath, manufacturerName);
        waitUtils.waitForElementPresenceBy(By.xpath(manufacturerCheckboxXpath));
        WebElement manufacturerCheckBox = driver.findElement(By.xpath(manufacturerCheckboxXpath));
        waitUtils.waitForElementToBeVisible(manufacturerCheckBox);
        manufacturerCheckBox.click();
    }

    public void verifyFilteringByManufacturer(String manufacturer) {
        waitUtils.waitForElementsToBeVisibleAfterRefresh(modelNameTitles);
        List<String> modelNamesString = Collectors.collectModelNames(modelNameTitles);
        assertThat(modelNamesString, everyItem(containsString(manufacturer)));
    }

    public void verifyCategoryName(String categoryNameExpected) {
        waitUtils.waitForElementToBeVisible(categoryName);
        Assert.assertEquals(categoryName.getText(), categoryNameExpected);
    }

    public void filterByPriceMinFixed(int minValue) {
        String fixedPriceCheckboxXpath = String.format(fixedPriceCheckboxMinPath, minValue);
        waitUtils.waitForElementPresenceBy(By.xpath(fixedPriceCheckboxXpath));
        WebElement fixedPriceCheckbox = driver.findElement(By.xpath(fixedPriceCheckboxXpath));
        waitUtils.waitForElementToBeVisible(fixedPriceCheckbox);
        fixedPriceExample = minValue;
        fixedPriceCheckbox.click();
    }

    public void filterByPriceMaxFixed(int maxValue) {
        waitUtils.waitForElementToBeVisible(searchResultPrices.get(0));
        String fixedPriceCheckboxXpath = String.format(fixedPriceCheckboxMaxPath, maxValue);
        waitUtils.waitForElementPresenceBy(By.xpath(fixedPriceCheckboxXpath));
        WebElement fixedPriceCheckbox = driver.findElement(By.xpath(fixedPriceCheckboxXpath));
        waitUtils.waitForElementToBeVisible(fixedPriceCheckbox);
        fixedPriceExample = maxValue;
        fixedPriceCheckbox.click();
    }

    public void verifyFilteringByPriceMaxFixed(int maxPriceFixed) {
        waitUtils.waitForElementToBeVisible(appliedFiltersTitle);
        waitUtils.waitForElementsToBeVisible(searchResultPrices);
        assertThat(Collectors.collectAndParseToIntResultPrices(searchResultPrices), everyItem(lessThanOrEqualTo(maxPriceFixed)));
    }

    public void verifyFilteringByPriceMinFixed() {
        waitUtils.waitForElementToBeVisible(appliedFiltersTitle);
        waitUtils.waitForElementsToBeVisible(searchResultPrices);
        assertThat(Collectors.collectAndParseToIntResultPrices(searchResultPrices), everyItem(greaterThanOrEqualTo(fixedPriceExample)));
    }

    public void filterByPriceInput(String minPrice, String maxPrice) {
        waitUtils.waitForElementToBeClickable(minPriceInput);
        waitUtils.waitForElementToBeClickable(maxPriceInput);
        minPriceInput.click();
        minPriceInput.sendKeys(minPrice);
        maxPriceInput.click();
        maxPriceInput.sendKeys(maxPrice);
        inputPriceOKButton.click();
    }

    public void verifyFilteringByPriceInput(String minPrice, String maxPrice) {
        waitUtils.waitForURLToContain(minPrice);
        waitUtils.waitForURLToContain(maxPrice);
        int minPriceInt = Integer.parseInt(minPrice);
        int maxPriceInt = Integer.parseInt(maxPrice);
        List<Integer> prices = Collectors.collectAndParseToIntResultPrices(searchResultPrices);
        assertThat(prices, everyItem(greaterThanOrEqualTo(minPriceInt)));
        assertThat(prices, everyItem(lessThanOrEqualTo(maxPriceInt)));
    }
}
