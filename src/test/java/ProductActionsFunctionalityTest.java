import Dto.ProductNameDto;
import Pages.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductActionsFunctionalityTest extends BaseTest {
    private final String searchQuery = "dell";
    private final String productName = "Apple iPhone 7 Plus 128Gb";

    Header header;
    MainPage mainPage;
    SearchResultsPage searchResultsPage;
    ProductPage productPage;
    CategoryMobilePhonesPage categoryMobilePhonesPage;

    @BeforeClass
    public void initializePage() {
        header = new Header(getDriver());
        mainPage = new MainPage(getDriver());
        searchResultsPage = new SearchResultsPage(getDriver());
        productPage = new ProductPage(getDriver());
        categoryMobilePhonesPage = new CategoryMobilePhonesPage(getDriver());
    }

    @BeforeMethod
    public void closeAnnouncement() {
        header.closeAnnouncement();
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

    @Test
    public void addToWishlistFromResultsTest() {
        header.searchFor("Apple iPhone 7 Plus 128Gb");
        searchResultsPage.addProductToWishlist(productName);
        header.openWishlist();
        header.removeFromWishlist(ProductNameDto.getName());
    }
}