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

public class AnyCategoryPage extends BasePage {

    public AnyCategoryPage(WebDriver driver) {
        super(driver);
        initialWait(driver);
    }

    @Override
    public void initialWait(WebDriver driver) {
        waitUtils.waitForElementToBeVisible(categoryName);
    }

    @FindBy(xpath = ".//span[@class='breadcrumbs-last']")
    private WebElement categoryName;

    @FindBy(xpath = ".//a[@class='model-name ga_card_mdl_title']")
    private List<WebElement> modelNameTitles;

    @FindBy(xpath = ".//div[@class='price-wrap']//span[@class='price']")
    private List<WebElement> searchResultPrices;

    @FindBy(xpath = ".//div[@class='wishlist-popunder active']")
    private WebElement popunderWishListActive;

    @FindBy(xpath = ".//div[@class='applied-filters']")
    private WebElement appliedFiltersTitle;

    @FindBy(xpath = ".//input[@id='price_min_']")
    private WebElement minPriceInput;

    @FindBy(xpath = ".//input[@id='price_max_']")
    private WebElement maxPriceInput;

    @FindBy(xpath = ".//a[@class='btn btn-purple ga_cat_filter btn-filters-submit btn-ok']")
    private WebElement inputPriceOKButton;

    private String manufacturerCheckboxPath = ".//a[@data-filter-type='producer'][contains(text(), '%s')]";

    private String addToWishlistButtonPath = "//a[text()='%s']/../../span[contains(@class, 'add-to-wishlist-link ')]";

    private String fixedPriceCheckboxMaxPath = ".//a[contains(@data-filter-value, '\"price[max]\":\"%s\"')]";
    private String fixedPriceCheckboxMinPath = ".//a[contains(@data-filter-value, '\"price[min]\":\"%s\"')]";

    public void verifyCategoryName(String categoryNameExpected) {
        waitUtils.waitForElementToBeVisible(categoryName);
        Assert.assertEquals(categoryName.getText(), categoryNameExpected);
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

    public void addProductToWishlist(String productName) {
        String addToWishlistButtonXpath = String.format(addToWishlistButtonPath, productName);
        waitUtils.waitForElementPresenceBy(By.xpath(addToWishlistButtonXpath));
        WebElement addToWishlistButton = driver.findElement(By.xpath(addToWishlistButtonXpath));
        ProductNameDto.setName(productName);
        waitUtils.waitForElementToBeClickable(addToWishlistButton);
        addToWishlistButton.click();
        waitUtils.waitForElementToBeVisible(popunderWishListActive);
    }

    public void filterByPriceMinFixed(int minValue) {
        String fixedPriceCheckboxXpath = String.format(fixedPriceCheckboxMinPath, minValue);
        waitUtils.waitForElementPresenceBy(By.xpath(fixedPriceCheckboxXpath));
        WebElement fixedPriceCheckbox = driver.findElement(By.xpath(fixedPriceCheckboxXpath));
        waitUtils.waitForElementToBeVisible(fixedPriceCheckbox);
        fixedPriceCheckbox.click();
    }

    public void filterByPriceMaxFixed(int maxValue) {
        waitUtils.waitForElementToBeVisible(searchResultPrices.get(0));
        String fixedPriceCheckboxXpath = String.format(fixedPriceCheckboxMaxPath, maxValue);
        waitUtils.waitForElementPresenceBy(By.xpath(fixedPriceCheckboxXpath));
        WebElement fixedPriceCheckbox = driver.findElement(By.xpath(fixedPriceCheckboxXpath));
        waitUtils.waitForElementToBeVisible(fixedPriceCheckbox);
        fixedPriceCheckbox.click();
    }

    public void verifyFilteringByPriceMaxFixed(int maxPriceFixed) {
        waitUtils.waitForElementToBeVisible(appliedFiltersTitle);
        waitUtils.waitForElementsToBeVisible(searchResultPrices);
        assertThat(Collectors.collectAndParseToIntResultPrices(searchResultPrices), everyItem(lessThanOrEqualTo(maxPriceFixed)));
    }

    public void verifyFilteringByPriceMinFixed(int minPriceFixedFiltered) {
        waitUtils.waitForElementToBeVisible(appliedFiltersTitle);
        waitUtils.waitForElementsToBeVisible(searchResultPrices);
        assertThat(Collectors.collectAndParseToIntResultPrices(searchResultPrices), everyItem(greaterThanOrEqualTo(minPriceFixedFiltered)));
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
