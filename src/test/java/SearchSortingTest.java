import Pages.Header;
import Pages.SearchResultsPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchSortingTest extends BaseTest {
    private final String searchQuery = "dell precision";

    Header header;
    SearchResultsPage searchResultsPage;

    @BeforeClass
    public void initializePage() {
        header = new Header(getDriver());
        searchResultsPage = new SearchResultsPage(getDriver());
    }

    @Test
    public void searchTest() {
        header.searchFor(searchQuery);
        searchResultsPage.verifySearchResults(searchQuery);
    }

    @Test
    public void sortingLowToHighTest() {
        header.searchFor(searchQuery);
        searchResultsPage.sortByPriceLowToHigh();
        searchResultsPage.verifySortResultsLowToHigh();
    }

    @Test
    public void sortingHighToLowTest() {
        header.searchFor(searchQuery);
        searchResultsPage.sortByPriceHighToLow();
        searchResultsPage.verifySortResultsHighToLow();
    }

}
