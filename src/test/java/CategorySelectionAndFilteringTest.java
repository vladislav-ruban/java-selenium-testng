import Enums.ManufacturersMobilePhones;
import Pages.CategoryMobilePhonesPage;
import Pages.Header;
import Pages.MainPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CategorySelectionAndFilteringTest extends BaseTest {
    private final String categoryNameExpected = "Мобильные телефоны, смартфоны";
    private final int maxPriceFixed = 1300;
    private final String minPrice = "1000";
    private final String maxPrice = "2000";

    @BeforeMethod
    public void goToCategory() {
        Header header = new Header(driver);
        header.closeAnnouncement();
        MainPage mainPage = new MainPage(driver);
        mainPage.goToCategoryMobilePhones();
        header.closeAnnouncement();
    }

    @Test
    public void categorySelectionTest() {
        CategoryMobilePhonesPage categoryMobilePhonesPage = new CategoryMobilePhonesPage(driver);
        categoryMobilePhonesPage.verifyCategoryName(categoryNameExpected);
    }

    @Test
    public void manufacturerFilterTest() {
        CategoryMobilePhonesPage categoryMobilePhonesPage = new CategoryMobilePhonesPage(driver);
        categoryMobilePhonesPage.filterByManufacturer(ManufacturersMobilePhones.apple);
        categoryMobilePhonesPage.verifyFilteringByManufacturer();
    }

    @Test
    public void fixedPriceFilterTest() {
        CategoryMobilePhonesPage categoryMobilePhonesPage = new CategoryMobilePhonesPage(driver);
        categoryMobilePhonesPage.filterByPriceMaxFixed(maxPriceFixed);
        categoryMobilePhonesPage.verifyFilteringByPriceFixed();
    }

    @Test
    public void inputPriceFilterTest() {
        CategoryMobilePhonesPage categoryMobilePhonesPage = new CategoryMobilePhonesPage(driver);
        categoryMobilePhonesPage.filterByPriceInput(minPrice, maxPrice);
        categoryMobilePhonesPage.verifyFilteringByPriceInput(minPrice, maxPrice);
    }
}