package Pages;

import Utils.Collectors;
import Utils.WaitUtils;
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

    @FindBy(how = How.XPATH, using = "//a[@data-filter-type='producer'][@data-producer-alias='apple']")
    private WebElement producerAppleCheckbox;

    @FindBy(how = How.XPATH, using = "//a[@class='model-name ga_card_mdl_title']")
    private List<WebElement> modelNameTitles;

    @FindBy(how = How.XPATH, using = "//a[@id='sum_range_0']")
    private WebElement priceFixedTo1300Button;

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

    public void verifyCategoryName(String categoryNameExpected) {
        WaitUtils.waitForElementToAppear(categoryName);
        Assert.assertEquals(categoryName.getText(), categoryNameExpected);
    }

    public void filterByProducerApple() {
        WaitUtils.waitForElementToAppear(producerAppleCheckbox);
        producerAppleCheckbox.click();
    }

    public void verifyFilteringByProducerApple() {
        WaitUtils.waitForAllElementsToAppear(modelNameTitles);
        List<String> modelNamesString = Collectors.collectModelNames(modelNameTitles);
        assertThat(modelNamesString, everyItem(containsString(producerAppleCheckbox.getText())));
    }

    public void filterByPriceFixed() {
        WaitUtils.waitForElementToAppear(priceFixedTo1300Button);
        priceFixedTo1300Button.click();
    }

    public void verifyFilteringByPriceFixed() {
        WaitUtils.waitForElementToAppear(appliedFiltersTitle);
        WaitUtils.waitForURLToContain("price[max]=1300");
        assertThat(Collectors.collectAndParseToIntResultPrices(searchResultPrices), everyItem(lessThanOrEqualTo(1300)));
    }

    public void filterByPriceInput(String minPrice, String maxPrice) {
        WaitUtils.waitForElementToBeClickable(minPriceInput);
        WaitUtils.waitForElementToBeClickable(maxPriceInput);
        typeToInput(minPriceInput, minPrice);
        typeToInput(maxPriceInput, maxPrice);
        inputPriceOKButton.click();
    }

    public void verifyFilteringByPriceInput(String minPrice, String maxPrice) {
        WaitUtils.waitForURLToContain(minPrice);
        WaitUtils.waitForURLToContain(maxPrice);
        int minPriceInt = Integer.parseInt(minPrice);
        int maxPriceInt = Integer.parseInt(maxPrice);
        List<Integer> prices = Collectors.collectAndParseToIntResultPrices(searchResultPrices);
        assertThat(prices, everyItem(greaterThanOrEqualTo(minPriceInt)));
        assertThat(prices, everyItem(lessThanOrEqualTo(maxPriceInt)));
    }
}
