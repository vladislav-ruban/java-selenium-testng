import Dto.ProductNameDto;
import Pages.Header;
import Pages.ProductPage;
import Pages.SearchResultsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductActionsFunctionalityTest extends BaseTest {
    private final String searchQuery = "dell";
    private final String productName = "Apple iPhone 7 Plus 128Gb";

    @BeforeMethod
    public void closeAnnouncement() {
        Header header = new Header(driver);
        header.closeAnnouncement();
    }

    @Test
    public void addToWishlistTest() {
        Header header = new Header(driver);
        header.searchFor(searchQuery);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.verifySearchResults(searchQuery);
        int price = searchResultsPage.getFirstResultPrice();
        searchResultsPage.goToFirstResult();
        ProductPage productPage = new ProductPage(driver);
        productPage.addToWishlist();
        header.openWishlist();
        header.verifyWishlistPrice(price);
    }

    @Test
    public void addToWishlistFromResultsTest() {
        Header header = new Header(driver);
        header.searchFor("Apple iPhone 7 Plus 128Gb");
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.addProductToWishlist(productName);
        header.openWishlist();
        header.removeFromWishlist(ProductNameDto.getName());
    }
}