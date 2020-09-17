package Pages;

import Utils.Collectors;
import Utils.Converters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchResultsPage extends BasePage {
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//div[@class='white-wrap']/a[@class='model-name ga_card_mdl_title']")
    private List<WebElement> searchResultTitles;

    @FindBy(xpath = ".//a[@class='model-name ga_card_mdl_title']")
    private WebElement firstSearchResult;

    @FindBy(xpath = ".//a[@class='model-name ga_card_mdl_title']/following::span[@class='price']")
    private WebElement firstSearchResultPrice;

    @FindBy(xpath = ".//div[@class='price-wrap']//span[@class='price']")
    private List<WebElement> searchResultPrices;

    @FindBy(xpath = ".//a[@class='link ga_cat_sort low-to-hight']")
    private WebElement sortLowToHighButton;

    @FindBy(xpath = ".//a[@class='link ga_cat_sort low-to-hight active']")
    private WebElement sortLowToHighButtonActive;

    @FindBy(xpath = ".//a[@class='link ga_cat_sort hight-to-low']")
    private WebElement sortHighToLowButton;

    @FindBy(xpath = ".//a[@class='link ga_cat_sort hight-to-low active']")
    private WebElement sortHighToLowButtonActive;

    public void verifySearchResults(String searchQuery) {
        waitForElementToAppear(sortLowToHighButton);
        List<String> searchResultTitlesString = new ArrayList<>();
        for (WebElement title : searchResultTitles) searchResultTitlesString.add(title.getText().toLowerCase());
        assertThat(searchResultTitlesString, everyItem(containsString(searchQuery)));
    }

    public void sortByPriceLowToHigh() {
        waitForElementToAppear(sortLowToHighButton);
        sortLowToHighButton.click();
    }

    public void sortByPriceHighToLow() {
        waitForElementToAppear(sortHighToLowButton);
        sortHighToLowButton.click();
    }

    public void verifySortResultsLowToHigh() {
        waitForElementToAppear(sortLowToHighButtonActive);
        ArrayList<Integer> integerPricesSorted = new ArrayList<Integer>();
        ArrayList<Integer> integerPrices = Collectors.collectAndParseToIntResultPrices(searchResultPrices);
        integerPricesSorted.addAll(integerPrices);
        Collections.sort(integerPricesSorted);
        Assert.assertEquals(integerPrices, integerPricesSorted);
    }

    public void verifySortResultsHighToLow() {
        waitForElementToAppear(sortHighToLowButtonActive);
        ArrayList<Integer> integerPricesSorted = new ArrayList<Integer>();
        ArrayList<Integer> integerPrices = Collectors.collectAndParseToIntResultPrices(searchResultPrices);
        integerPricesSorted.addAll(integerPrices);
        Collections.sort(integerPricesSorted);
        Collections.reverse(integerPricesSorted);
        Assert.assertEquals(integerPrices, integerPricesSorted);
    }

    public void goToFirstResult() {
        waitForElementToBeClickable(firstSearchResult);
        firstSearchResult.click();
    }

    public int getFirstResultPrice() {
        return (Converters.stringCutAndParseToInt(firstSearchResultPrice.getText()));
    }

}
