import Pages.Header;
import Pages.MainPage;
import Pages.ProductPage;
import Pages.SearchResultsPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductActionsFunctionalityTest extends BaseTest {
    private final String searchQuery = "dell precision";

    Header header;
    MainPage mainPage;
    SearchResultsPage searchResultsPage;
    ProductPage productPage;

    @BeforeClass
    public void initializePage() {
        header = new Header(getDriver());
        mainPage = new MainPage(getDriver());
        searchResultsPage = new SearchResultsPage(getDriver());
        productPage = new ProductPage(getDriver());
    }

    @Test
    public void addToWishlistTest() {
        header.searchFor(searchQuery);
        searchResultsPage.verifySearchResults(searchQuery);
        int price = searchResultsPage.getFirstResultPrice();
        searchResultsPage.goToFirstResult();
        productPage.addToWishlist();
        header.openWishlist();
        header.verifyWishlistPrice(price);
    }
}
