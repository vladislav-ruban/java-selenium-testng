package Pages;

import Enums.Extremum;
import Enums.ManufacturersMobilePhones;
import Utils.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class CategoryMobilePhonesPage extends BasePage{

    public CategoryMobilePhonesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//span[@class='breadcrumbs-last']")
    private WebElement categoryName;

    @FindBy(how = How.XPATH, using = "//a[@class='model-name ga_card_mdl_title']")
    private List<WebElement> modelNameTitles;

    @FindBy(how = How.XPATH, using = "//input[@id='price_min_']")
    private WebElement minPriceInput;

    @FindBy(how = How.XPATH, using = "//input[@id='price_max_']")
    private WebElement maxPriceInput;

    @FindBy(how = How.XPATH, using = "//a[@class='btn btn-purple ga_cat_filter btn-filters-submit btn-ok']")
    private WebElement inputPriceOKButton;

    @FindBy(how = How.XPATH, using = "//div[@class='applied-filters']")
    private WebElement appliedFiltersTitle;

    @FindBy(how = How.XPATH, using = "//div[@class='price-wrap']//span[@class='price']")
    private List<WebElement> searchResultPrices;

    private String manufacturerCheckboxPath = "//a[@data-filter-type='producer'][@data-producer-alias='%s']";
    private String manufacturerExample;

    private String fixedPriceCheckboxMaxPath = "//a[contains(@data-filter-value, '\"price[max]\":\"%s\"')]";
    private String fixedPriceCheckboxMinPath = "//a[contains(@data-filter-value, '\"price[min]\":\"%s\"')]";
    private int fixedPriceExample;
    private Extremum fixedPriceMinOrMax;

    public void filterByManufacturer(ManufacturersMobilePhones manufacturerName) {
        WebElement manufacturerCheckBox = driver.findElement(By.xpath(String.format(manufacturerCheckboxPath, manufacturerName)));
        waitForElementToAppear(manufacturerCheckBox);
        manufacturerExample = manufacturerCheckBox.getText();
        manufacturerCheckBox.click();
    }

    public void verifyFilteringByManufacturer() {
        waitForAllElementsToAppear(modelNameTitles);
        List<String> modelNamesString = Collectors.collectModelNames(modelNameTitles);
        assertThat(modelNamesString, everyItem(containsString(manufacturerExample)));
    }

    public void verifyCategoryName(String categoryNameExpected) {
        waitForElementToAppear(categoryName);
        Assert.assertEquals(categoryName.getText(), categoryNameExpected);
    }

    public void filterByPriceMinFixed(int minValue) {
        WebElement fixedPriceCheckbox = driver.findElement(By.xpath(String.format(fixedPriceCheckboxMinPath, minValue)));
        waitForElementToAppear(fixedPriceCheckbox);
        fixedPriceExample = minValue;
        fixedPriceMinOrMax = Extremum.min;
        fixedPriceCheckbox.click();
    }

    public void filterByPriceMaxFixed(int maxValue) {
        WebElement fixedPriceCheckbox = driver.findElement(By.xpath(String.format(fixedPriceCheckboxMaxPath, maxValue)));
        waitForElementToAppear(fixedPriceCheckbox);
        fixedPriceExample = maxValue;
        fixedPriceMinOrMax = Extremum.max;
        fixedPriceCheckbox.click();
    }

    public void verifyFilteringByPriceFixed() {
        waitForElementToAppear(appliedFiltersTitle);
        waitForURLToContain(String.valueOf(fixedPriceExample));
        switch(fixedPriceMinOrMax) {
            case min -> assertThat(Collectors.collectAndParseToIntResultPrices(searchResultPrices), everyItem(greaterThanOrEqualTo(fixedPriceExample)));
            case max -> assertThat(Collectors.collectAndParseToIntResultPrices(searchResultPrices), everyItem(lessThanOrEqualTo(fixedPriceExample)));
        }
    }

    public void filterByPriceInput(String minPrice, String maxPrice) {
        waitForElementToBeClickable(minPriceInput);
        waitForElementToBeClickable(maxPriceInput);
        typeToInput(minPriceInput, minPrice);
        typeToInput(maxPriceInput, maxPrice);
        inputPriceOKButton.click();
    }

    public void verifyFilteringByPriceInput(String minPrice, String maxPrice) {
        waitForURLToContain(minPrice);
        waitForURLToContain(maxPrice);
        int minPriceInt = Integer.parseInt(minPrice);
        int maxPriceInt = Integer.parseInt(maxPrice);
        List<Integer> prices = Collectors.collectAndParseToIntResultPrices(searchResultPrices);
        assertThat(prices, everyItem(greaterThanOrEqualTo(minPriceInt)));
        assertThat(prices, everyItem(lessThanOrEqualTo(maxPriceInt)));
    }
}