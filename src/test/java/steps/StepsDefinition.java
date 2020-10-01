package steps;

import Base.BaseTest;
import Dto.CategoryNameDto;
import Dto.ProductNameDto;
import Pages.AnyCategoryPage;
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

    @Given("User navigates to main page")
    public void userNavigatesToMainPage() {
        openMainPage();
        Header header = new Header(driver);
        header.closeAnnouncement();
    }

    @When("User enters {string} to search bar")
    public void userEntersSearchQueryToSearchBar(String searchQuery) {
        Header header = new Header(driver);
        header.searchFor(searchQuery);
    }

    @When("User sorts by price low to high")
    public void userSortsByPriceLowToHigh() {
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.sortByPriceLowToHigh();
    }

    @When("User sorts by price high to low")
    public void userSortsByPriceHighToLow() {
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.sortByPriceHighToLow();
    }

    @Then("Prices should be sorted low to high")
    public void pricesShouldBeSortedLowToHigh() {
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.verifySortResultsLowToHigh();
    }

    @Then("Prices should be sorted high to low")
    public void pricesShouldBeSortedHighToLow() {
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.verifySortResultsHighToLow();
    }

    @Then("Search result page with results title contains {string} is displayed")
    public void searchResultPageWithResultsTitleContainsSearchQueryIsDisplayed(String searchQuery) {
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.verifySearchResults(searchQuery);
    }

    @And("User adds to wishlist a {string}")
    public void userAddsToWishlistAProduct(String productName) {
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        Header header = new Header(driver);
        header.closeAnnouncement();
        searchResultsPage.addProductToWishlist(productName);
    }

    @Then("User removes remembered product from wishlist")
    public void userRemovesRememberedProductFromWishlist() {
        Header header = new Header(driver);
        header.openWishlist();
        header.removeFromWishlist(ProductNameDto.getName());
    }

    @When("User logins with email {string} and password {string}")
    public void userLoginsWithEmailAndPassword(String email, String password) {
        Header header = new Header(driver);
        header.openLoginForm();
        header.loginWithCredentials(email, password);
    }

    @Then("Logged state instead of login button is displayed")
    public void loggedStateInsteadOfLoginButtonIsDisplayed() {
        Header header = new Header(driver);
        header.verifyUserLogged();
    }

    @And("User logs out")
    public void userLogsOut() {
        Header header = new Header(driver);
        header.openUserpanel();
        header.logOut();
    }

    @Then("Message informing that email unconfirmed is displayed")
    public void messageInformingThatEmailUnconfirmedIsDisplayed() {
        Header header = new Header(driver);
        header.verifyEmailNotConfirmedMessage("Email не подтвержден");
    }

    @Then("Errors under inputs are displayed")
    public void errorsUnderInputsAreDisplayed() {
        final String expectedMessageUnderEmail = "Необходимо заполнить поле «Адрес электронной почты».";
        final String expectedMessageUnderPassword = "Необходимо заполнить поле «Пароль».";
        Header header = new Header(driver);
        header.verifyErrorMessages(expectedMessageUnderEmail, expectedMessageUnderPassword);
    }

    @When("User hovers on category {string}")
    public void userHoversOnCategory(String categoryToHover) {
        MainPage mainPage = new MainPage(driver);
        mainPage.hoverOnCategory(categoryToHover);
    }

    @And("User goes to category {string}")
    public void userGoesToCategory(String categoryToClick) {
        MainPage mainPage = new MainPage(driver);
        mainPage.goToCategory(categoryToClick);
        CategoryNameDto.setCategory(categoryToClick);
    }

    @Then("Remembered category is displayed")
    public void rememberedCategoryIsDisplayed() {
        AnyCategoryPage anyCategoryPage = new AnyCategoryPage(driver);
        anyCategoryPage.verifyCategoryName(CategoryNameDto.getCategory());
    }

    @And("User filters products by manufacturer {string}")
    public void userFiltersProductsByManufacturer(String manufacturerName) {
        AnyCategoryPage anyCategoryPage = new AnyCategoryPage(driver);
        anyCategoryPage.filterByManufacturer(manufacturerName);
        ProductNameDto.setName(manufacturerName);
    }

    @Then("Products are filtered by remembered manufacturer")
    public void productsAreFilteredByRememberedManufacturer() {
        AnyCategoryPage anyCategoryPage = new AnyCategoryPage(driver);
        anyCategoryPage.verifyFilteringByManufacturer(ProductNameDto.getName());
    }

    @And("User filters products by input price from {string} to {string}")
    public void userFiltersProductsByInputPriceFromMinPriceToMaxPrice(String minPrice, String maxPrice) {
        AnyCategoryPage anyCategoryPage = new AnyCategoryPage(driver);
        anyCategoryPage.filterByPriceInput(minPrice, maxPrice);
    }

    @Then("Products with prices in range from {string} to {string} displayed")
    public void productsWithPricesInRangeFromMinPriceToMaxPriceDisplayed(String minPrice, String maxPrice) {
        AnyCategoryPage anyCategoryPage = new AnyCategoryPage(driver);
        anyCategoryPage.verifyFilteringByPriceInput(minPrice, maxPrice);
    }

    @And("User filters products by fixed price {string}")
    public void userFiltersProductsByFixedPrice(String fixedPriceMax) {
        AnyCategoryPage anyCategoryPage = new AnyCategoryPage(driver);
        anyCategoryPage.filterByPriceMaxFixed(Integer.parseInt(fixedPriceMax));
    }

    @Then("Products with prices up to {string} displayed")
    public void productsWithPricesUpToDisplayed(String fixedPriceMax) {
        AnyCategoryPage anyCategoryPage = new AnyCategoryPage(driver);
        anyCategoryPage.verifyFilteringByPriceMaxFixed(Integer.parseInt(fixedPriceMax));
    }
}
