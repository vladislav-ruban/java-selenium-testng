import Pages.Header;
import Pages.SearchResultsPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchSortingTest extends BaseTest {

    @DataProvider(name="data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { "dell" } };
    }

    Header header;
    SearchResultsPage searchResultsPage;

    @BeforeClass
    public void initializePage() {
        header = new Header(getDriver());
        searchResultsPage = new SearchResultsPage(getDriver());
    }


    @BeforeMethod
    public void closeAnnouncement() {
        header.closeAnnouncement();
    }

    @Test(dataProvider = "data-provider")
    public void searchTest(String searchQuery) {
        header.searchFor(searchQuery);
        searchResultsPage.verifySearchResults(searchQuery);
    }

    @Test(dataProvider = "data-provider")
    public void sortingLowToHighTest(String searchQuery) {
        header.searchFor(searchQuery);
        searchResultsPage.sortByPriceLowToHigh();
        searchResultsPage.verifySortResultsLowToHigh();
    }

    @Test(dataProvider = "data-provider")
    public void sortingHighToLowTest(String searchQuery) {
        header.searchFor(searchQuery);
        searchResultsPage.sortByPriceHighToLow();
        searchResultsPage.verifySortResultsHighToLow();
    }
}
