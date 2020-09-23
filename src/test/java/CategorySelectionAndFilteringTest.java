import Enums.ManufacturersMobilePhones;
import Pages.CategoryMobilePhonesPage;
import Pages.Header;
import Pages.MainPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CategorySelectionAndFilteringTest extends BaseTest {
    private final String categoryNameExpected = "Мобильные телефоны, смартфоны";
    private final int maxPriceFixed = 1300;
    private final String minPrice = "1000";
    private final String maxPrice = "2000";

    MainPage mainPage;
    CategoryMobilePhonesPage categoryMobilePhonesPage;
    Header header;

    @BeforeClass
    public void initializePage() {
        mainPage = new MainPage(getDriver());
        categoryMobilePhonesPage = new CategoryMobilePhonesPage(getDriver());
        header = new Header(getDriver());
    }

    @BeforeMethod
    public void goToCategory() {
        header.closeAnnouncement();
        mainPage.goToCategoryMobilePhones();
        header.closeAnnouncement();
    }

    @Test
    public void categorySelectionTest() {
        categoryMobilePhonesPage.verifyCategoryName(categoryNameExpected);
    }

    @Test
    public void manufacturerFilterTest() {
        categoryMobilePhonesPage.filterByManufacturer(ManufacturersMobilePhones.apple);
        categoryMobilePhonesPage.verifyFilteringByManufacturer();
    }

    @Test
    public void fixedPriceFilterTest() {
        categoryMobilePhonesPage.filterByPriceMaxFixed(maxPriceFixed);
        categoryMobilePhonesPage.verifyFilteringByPriceFixed();
    }

    @Test
    public void inputPriceFilterTest() {
        categoryMobilePhonesPage.filterByPriceInput(minPrice, maxPrice);
        categoryMobilePhonesPage.verifyFilteringByPriceInput(minPrice, maxPrice);
    }
}