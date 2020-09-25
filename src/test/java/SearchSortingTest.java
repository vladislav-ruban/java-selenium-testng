import Pages.Header;
import Pages.SearchResultsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchSortingTest extends BaseTest {

    @DataProvider(name="data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { "acer aspire" }, { "dell" }, {"eizo"} };
    }

    @BeforeMethod
    public void closeAnnouncement() {
        Header header = new Header(driver);
        header.closeAnnouncement();
    }

    @Test(dataProvider = "data-provider")
    public void searchTest(String searchQuery) {
        Header header = new Header(driver);
        header.searchFor(searchQuery);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.verifySearchResults(searchQuery);
    }

    @Test(dataProvider = "data-provider")
    public void sortingLowToHighTest(String searchQuery) {
        Header header = new Header(driver);
        header.searchFor(searchQuery);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.sortByPriceLowToHigh();
        searchResultsPage.verifySortResultsLowToHigh();
    }

    @Test(dataProvider = "data-provider")
    public void sortingHighToLowTest(String searchQuery) {
        Header header = new Header(driver);
        header.searchFor(searchQuery);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.sortByPriceHighToLow();
        searchResultsPage.verifySortResultsHighToLow();
    }
}
