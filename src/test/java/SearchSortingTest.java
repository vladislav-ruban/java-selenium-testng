import Pages.Header;
import Pages.SearchResultsPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchSortingTest extends BaseTest {
    private final String searchQuery = "dell";

    Header header;
    SearchResultsPage searchResultsPage;

    @BeforeClass
    public void initializePage() {
        header = new Header(getDriver());
        searchResultsPage = new SearchResultsPage(getDriver());
    }

    @BeforeMethod
    public void search() {
        header.closeAnnouncement();
        header.searchFor(searchQuery);
    }

    @Test
    public void searchTest() {
        searchResultsPage.verifySearchResults(searchQuery);
    }

    @Test
    public void sortingLowToHighTest() {
        searchResultsPage.sortByPriceLowToHigh();
        searchResultsPage.verifySortResultsLowToHigh();
    }

    @Test
    public void sortingHighToLowTest() {
        searchResultsPage.sortByPriceHighToLow();
        searchResultsPage.verifySortResultsHighToLow();
    }
}
