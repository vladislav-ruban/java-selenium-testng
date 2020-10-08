package steps;

import Base.BaseUtil;
import Dto.CategoryNameDto;
import Dto.ProductNameDto;
import Pages.AnyCategoryPage;
import Pages.Header;
import Pages.MainPage;
import Pages.SearchResultsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class StepsDefinition extends BaseUtil {

    String searchQuery;
    BaseUtil base;

    public StepsDefinition(BaseUtil base) {
        super();
        this.base=base;
    }

    public WebDriver getDriver() {
        return base.getDriver();
    }

    @Step
    @Given("User navigates to main page")
    public void userNavigatesToMainPage() {
        openMainPage();
        Header header = new Header(getDriver());
        header.closeAnnouncement();
    }

    @Step
    @When("User enters {string} to search bar")
    public void userEntersSearchQueryToSearchBar(String searchQuery) {
        Header header = new Header(getDriver());
        header.searchFor(searchQuery);
    }

    @Step
    @When("User sorts by price low to high")
    public void userSortsByPriceLowToHigh() {
        SearchResultsPage searchResultsPage = new SearchResultsPage(getDriver());
        searchResultsPage.sortByPriceLowToHigh();
    }

    @Step
    @When("User sorts by price high to low")
    public void userSortsByPriceHighToLow() {
        SearchResultsPage searchResultsPage = new SearchResultsPage(getDriver());
        searchResultsPage.sortByPriceHighToLow();
    }

    @Step
    @Then("Prices should be sorted low to high")
    public void pricesShouldBeSortedLowToHigh() {
        SearchResultsPage searchResultsPage = new SearchResultsPage(getDriver());
        searchResultsPage.verifySortResultsLowToHigh();
    }

    @Step
    @Then("Prices should be sorted high to low")
    public void pricesShouldBeSortedHighToLow() {
        SearchResultsPage searchResultsPage = new SearchResultsPage(getDriver());
        searchResultsPage.verifySortResultsHighToLow();
    }

    @Step
    @Then("Search result page with results title contains {string} is displayed")
    public void searchResultPageWithResultsTitleContainsSearchQueryIsDisplayed(String searchQuery) {
        SearchResultsPage searchResultsPage = new SearchResultsPage(getDriver());
        searchResultsPage.verifySearchResults(searchQuery);
    }

    @Step
    @And("User adds to wishlist a {string}")
    public void userAddsToWishlistAProduct(String productName) {
        SearchResultsPage searchResultsPage = new SearchResultsPage(getDriver());
        searchResultsPage.isAppliedFiltersShown();
        searchResultsPage.addProductToWishlist(productName);
    }

    @Step
    @Then("User removes remembered product from wishlist")
    public void userRemovesRememberedProductFromWishlist() {
        Header header = new Header(getDriver());
        header.openWishlist();
        header.removeFromWishlist(ProductNameDto.getName());
    }

    @Step
    @When("User logins with email {string} and password {string}")
    public void userLoginsWithEmailAndPassword(String email, String password) {
        Header header = new Header(getDriver());
        header.openLoginForm();
        header.loginWithCredentials(email, password);
    }

    @Step
    @Then("Logged state instead of login button is displayed")
    public void loggedStateInsteadOfLoginButtonIsDisplayed() {
        Header header = new Header(getDriver());
        header.verifyUserLogged();
    }

    @Step
    @And("User logs out")
    public void userLogsOut() {
        Header header = new Header(getDriver());
        header.openUserpanel();
        header.logOut();
    }

    @Step
    @Then("Message informing that email unconfirmed is displayed")
    public void messageInformingThatEmailUnconfirmedIsDisplayed() {
        Header header = new Header(getDriver());
        header.verifyEmailNotConfirmedMessage("Email не подтвержден");
    }

    @Step
    @Then("Errors under inputs are displayed")
    public void errorsUnderInputsAreDisplayed() {
        final String expectedMessageUnderEmail = "Необходимо заполнить поле «Адрес электронной почты».";
        final String expectedMessageUnderPassword = "Необходимо заполнить поле «Пароль».";
        Header header = new Header(getDriver());
        header.verifyErrorMessages(expectedMessageUnderEmail, expectedMessageUnderPassword);
    }

    @Step
    @When("User hovers on category {string}")
    public void userHoversOnCategory(String categoryToHover) {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.hoverOnCategory(categoryToHover);
    }

    @Step
    @And("User goes to category {string}")
    public void userGoesToCategory(String categoryToClick) {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.goToCategory(categoryToClick);
        CategoryNameDto.setCategory(categoryToClick);
    }

    @Step
    @Then("Remembered category is displayed")
    public void rememberedCategoryIsDisplayed() {
        AnyCategoryPage anyCategoryPage = new AnyCategoryPage(getDriver());
        anyCategoryPage.verifyCategoryName(CategoryNameDto.getCategory());
    }

    @Step
    @And("User filters products by manufacturer {string}")
    public void userFiltersProductsByManufacturer(String manufacturerName) {
        AnyCategoryPage anyCategoryPage = new AnyCategoryPage(getDriver());
        anyCategoryPage.filterByManufacturer(manufacturerName);
        ProductNameDto.setName(manufacturerName);
    }

    @Step
    @Then("Products are filtered by remembered manufacturer")
    public void productsAreFilteredByRememberedManufacturer() {
        AnyCategoryPage anyCategoryPage = new AnyCategoryPage(getDriver());
        anyCategoryPage.verifyFilteringByManufacturer(ProductNameDto.getName());
    }

    @Step
    @And("User filters products by input price from {string} to {string}")
    public void userFiltersProductsByInputPriceFromMinPriceToMaxPrice(String minPrice, String maxPrice) {
        AnyCategoryPage anyCategoryPage = new AnyCategoryPage(getDriver());
        anyCategoryPage.filterByPriceInput(minPrice, maxPrice);
    }

    @Step
    @Then("Products with prices in range from {string} to {string} displayed")
    public void productsWithPricesInRangeFromMinPriceToMaxPriceDisplayed(String minPrice, String maxPrice) {
        AnyCategoryPage anyCategoryPage = new AnyCategoryPage(getDriver());
        anyCategoryPage.verifyFilteringByPriceInput(minPrice, maxPrice);
    }

    @Step
    @And("User filters products by fixed price {string}")
    public void userFiltersProductsByFixedPrice(String fixedPriceMax) {
        AnyCategoryPage anyCategoryPage = new AnyCategoryPage(getDriver());
        anyCategoryPage.filterByPriceMaxFixed(Integer.parseInt(fixedPriceMax));
    }

    @Step
    @Then("Products with prices up to {string} displayed")
    public void productsWithPricesUpToDisplayed(String fixedPriceMax) {
        AnyCategoryPage anyCategoryPage = new AnyCategoryPage(getDriver());
        anyCategoryPage.verifyFilteringByPriceMaxFixed(Integer.parseInt(fixedPriceMax));
    }
}
