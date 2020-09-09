import Pages.CategoryMobilePhonesPage;
import Pages.Header;
import Pages.MainPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CategorySelectionAndFilteringTest extends BaseTest {
    private final String categoryNameExpected = "Мобильные телефоны, смартфоны";
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

    @Test
    public void categorySelectionTest() {
        header.closeAnnouncement();
        mainPage.goToCategoryMobilePhones();
        categoryMobilePhonesPage.verifyCategoryName(categoryNameExpected);
    }

    @Test
    public void producerFilterTest() {
        categorySelectionTest();
        categoryMobilePhonesPage.filterByProducerApple();
        categoryMobilePhonesPage.verifyFilteringByProducerApple();
    }

    @Test
    public void fixedPriceFilterTest() {
        categorySelectionTest();
        categoryMobilePhonesPage.verifyCategoryName(categoryNameExpected);
        categoryMobilePhonesPage.filterByPriceFixed();
        categoryMobilePhonesPage.verifyFilteringByPriceFixed();
    }

    @Test
    public void inputPriceFilterTest() {
        categorySelectionTest();
        categoryMobilePhonesPage.filterByPriceInput(minPrice, maxPrice);
        categoryMobilePhonesPage.verifyFilteringByPriceInput(minPrice, maxPrice);
    }

}
