package steps;

import Base.BaseTest;
import Dto.ProductNameDto;
import Pages.CategoryMobilePhonesPage;
import Pages.Header;
import Pages.MainPage;
import Pages.SearchResultsPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsDefinition extends BaseTest {

    String searchQuery;

    @Before
    public void open() {
        setUp();
        clearCookie();
    }

    @After
    public void close() {
        tearDown();
    }

    @Given("I navigate to main page")
    public void iNavigateToMainPage() {
        openMainPage();
    }

    @When("I enter {string} to search bar")
    public void iEnterToSearchBar(String searchQuery) {
        Header header = new Header(driver);
        header.searchFor(searchQuery);
    }

    @Then("I should see search result page with results title contains {string}")
    public void iShouldSeeSearchResultPageWithResultsTitleContains(String searchQuery) {
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.verifySearchResults(searchQuery);
    }

    @When("I sort by price low to high")
    public void iSortByPriceLowToHigh() {
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.sortByPriceLowToHigh();
    }

    @Then("I should see prices sorted low to high")
    public void iShouldSeePricesSortedLowToHigh() {
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.verifySortResultsLowToHigh();
    }

    @When("I sort by price high to low")
    public void iSortByPriceHighToLow() {
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.sortByPriceHighToLow();
    }

    @Then("I should see prices sorted high to low")
    public void iShouldSeePricesSortedHighToLow() {
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.verifySortResultsHighToLow();
    }

    @When("I open category mobile connection and subcategory mobile phones")
    public void iOpenCategoryMobileConnectionAndSubcategoryMobilePhones() {
        MainPage mainPage = new MainPage(driver);
        Header header = new Header(driver);
        header.closeAnnouncement();
        mainPage.goToCategoryMobilePhones();
    }

    @Then("I should see category mobile phones page")
    public void iShouldSeeCategoryMobilePhonesPage() {
        CategoryMobilePhonesPage categoryMobilePhonesPage = new CategoryMobilePhonesPage(driver);
        categoryMobilePhonesPage.verifyCategoryName("Мобильные телефоны, смартфоны");
    }

    @And("I filter products by manufacturer {string}")
    public void iFilterProductsByManufacturer(String manufacturerAlias) {
        CategoryMobilePhonesPage categoryMobilePhonesPage = new CategoryMobilePhonesPage(driver);
        categoryMobilePhonesPage.filterByManufacturer(manufacturerAlias);
    }

    @Then("I should see products by chosen manufacturer")
    public void iShouldSeeProductsByChosenManufacturer() {
        CategoryMobilePhonesPage categoryMobilePhonesPage = new CategoryMobilePhonesPage(driver);
        categoryMobilePhonesPage.verifyFilteringByManufacturer();
    }

    @And("I filter products by input price from {string} to {string}")
    public void iFilterProductsByInputPriceFromTo(String minPrice, String maxPrice) {
        CategoryMobilePhonesPage categoryMobilePhonesPage = new CategoryMobilePhonesPage(driver);
        categoryMobilePhonesPage.filterByPriceInput(minPrice, maxPrice);
    }

    @Then("I should see products with price in range from {string} to {string}")
    public void iShouldSeeProductsWithPriceInRangeFromTo(String minPrice, String maxPrice) {
        CategoryMobilePhonesPage categoryMobilePhonesPage = new CategoryMobilePhonesPage(driver);
        categoryMobilePhonesPage.filterByPriceInput(minPrice, maxPrice);
    }

    @And("I filter products by {string}")
    public void iFilterProductsBy(String maxPriceFixed) {
        CategoryMobilePhonesPage categoryMobilePhonesPage = new CategoryMobilePhonesPage(driver);
        categoryMobilePhonesPage.filterByPriceMaxFixed(Integer.parseInt(maxPriceFixed));
    }

    @Then("I should see products with prices up to {string}")
    public void iShouldSeeProductsWithPricesUpTo(String maxPriceFixed) {
        CategoryMobilePhonesPage categoryMobilePhonesPage = new CategoryMobilePhonesPage(driver);
        categoryMobilePhonesPage.verifyFilteringByPriceMaxFixed(Integer.parseInt(maxPriceFixed));
    }

    @Then("I see logged state instead of login button")
    public void iSeeLoggedStateInsteadOfLoginButton() {
        Header header = new Header(driver);
        header.verifyUserLogged();
    }

    @And("I log out")
    public void iLogOut() {
        Header header = new Header(driver);
        header.openUserpanel();
        header.logOut();
    }

    @When("I login with email {string} and password {string}")
    public void iLoginWithEmailAndPassword(String email, String password) {
        Header header = new Header(driver);
        header.closeAnnouncement();
        header.openLoginForm();
        header.loginWithCredentials(email, password);
    }

    @Then("I see message informing me email unconfirmed")
    public void iSeeMessageInformingMeEmailUnconfirmed() {
        Header header = new Header(driver);
        header.verifyEmailNotConfirmedMessage("Email не подтвержден");
    }

    @Then("I see errors under inputs")
    public void iSeeErrorsUnderInputs() {
        final String expectedMessageUnderEmail = "Необходимо заполнить поле «Адрес электронной почты».";
        final String expectedMessageUnderPassword = "Необходимо заполнить поле «Пароль».";
        Header header = new Header(driver);
        header.verifyErrorMessages(expectedMessageUnderEmail, expectedMessageUnderPassword);
    }

    @And("I add to wishlist a {string}")
    public void iAddToWishlistA(String productName) {
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        Header header = new Header(driver);
        header.closeAnnouncement();
        searchResultsPage.addProductToWishlist(productName);
    }

    @Then("I remove product {string} from wishlist")
    public void iRemoveProductFromWishlist(String productName) {
        Header header = new Header(driver);
        header.openWishlist();
        header.removeFromWishlist(ProductNameDto.getName());
    }
}
