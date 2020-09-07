import Base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class Tests extends TestBase {
    private final String searchQuery = "dell precision";
    private final String loginEmail = "testaccount@testmail.com";
    private final String loginPassword = "12345678";

    @Test
    public void searchTest() {
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.click();
        searchBar.sendKeys(searchQuery);
        searchBar.submit();
        List<WebElement> searchResultTitles = driver.findElements(By.xpath("//div[@class='white-wrap']/a[@class='model-name ga_card_mdl_title']"));
        for (WebElement titles : searchResultTitles) {
            System.out.println(titles.getText());
            assertThat(titles.getText().toLowerCase(), containsString(searchQuery));
        }
    }

    @Test
    public void sortByPriceLowToHighTest() {
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.click();
        searchBar.sendKeys(searchQuery);
        searchBar.submit();
        WebElement sortLowToHighButton = driver.findElement(By.xpath("//a[@class='link ga_cat_sort low-to-hight']"));
        sortLowToHighButton.click();
        List<WebElement> searchResultPrices = driver.findElements(By.xpath("//div[@class='price-wrap']/span[@class='price']"));
        ArrayList<Integer> integerPrices = new ArrayList();
        for (WebElement prices : searchResultPrices){
            int value = Integer.parseInt(prices.getText().replaceAll("[^0-9]", ""));
            System.out.println(value);
            integerPrices.add(value);
        }
        for (int i = 0; i < integerPrices.size(); i++) {
            if (i == integerPrices.size() - 1) break;
            Assert.assertTrue(integerPrices.get(i) < integerPrices.get(i + 1));
        }
    }


    @Test
    public void sortByPriceHighToLowTest() {
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.click();
        searchBar.sendKeys(searchQuery);
        searchBar.submit();
        WebElement sortHighToLowButton = driver.findElement(By.xpath("//a[@class='link ga_cat_sort hight-to-low']"));
        sortHighToLowButton.click();
        List<WebElement> searchResultPrices = driver.findElements(By.xpath("//div[@class='price-wrap']/span[@class='price']"));
        ArrayList<Integer> integerPrices = new ArrayList();
        for (WebElement prices : searchResultPrices) {
            int value = Integer.parseInt(prices.getText().replaceAll("[^0-9]", ""));
            System.out.println(value);
            integerPrices.add(value);
        }
        for (int i = 0; i < integerPrices.size(); i++) {
            if (i == integerPrices.size() - 1) break;
            Assert.assertTrue(integerPrices.get(i) > integerPrices.get(i + 1));
        }
    }

    @Test
    public void categorySelectionTest() {
        WebElement categoryMobileConnectionButton = driver.findElement(By.xpath("//a[@class='ga_cats_lateral'][@data-tracker-cid='6']"));
        WebElement closeAdBtn = new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='close announcement-acb']")));
        closeAdBtn.click();
        Actions action = new Actions(driver);
        action.moveToElement(categoryMobileConnectionButton).perform();
        WebElement categoryMobilePhonesButton = driver.findElement(By.xpath("//a[@class='ga_cats_lateral'][@data-tracker-cid='52']"));
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(categoryMobilePhonesButton)).click();
        WebElement breadcrumbsLast = driver.findElement(By.xpath("//span[@class='breadcrumbs-last']"));
        Assert.assertEquals(breadcrumbsLast.getText(), "Мобильные телефоны, смартфоны");
    }

    @Test
    public void filterProducerTest() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        categorySelectionTest();
        WebElement appleProducerFilterCheckbox = driver.findElement(By.xpath("//a[@data-filter-type='producer'][@data-producer-alias='apple']"));
        String appleProducer = appleProducerFilterCheckbox.getText();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click();", appleProducerFilterCheckbox);
        List<WebElement> modelNameTitles = driver.findElements(By.xpath("//a[@class='model-name ga_card_mdl_title']"));
        wait.until(ExpectedConditions.visibilityOfAllElements(modelNameTitles));
        for(WebElement modelTitle : modelNameTitles) {
            assertThat(modelTitle.getText().toLowerCase(), containsString(appleProducer.toLowerCase()));
        }
    }

    @Test
    public void filterPriceFixedTest() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        categorySelectionTest();
        WebElement priceMax1300FilterButton = driver.findElement(By.xpath("//a[@id='sum_range_0']"));
        priceMax1300FilterButton.click();
        WebElement activeFiltersHead = driver.findElement(By.xpath("//div[@class='applied-filters']"));
        wait.until(ExpectedConditions.visibilityOf(activeFiltersHead));
        wait.until(ExpectedConditions.urlContains("price[max]=1300"));
        List<WebElement> searchResultPrices = driver.findElements(By.xpath("//div[@class='price-wrap']/span[@class='price']"));
        wait.until(ExpectedConditions.elementToBeClickable(searchResultPrices.get(0)));
        for (WebElement prices : searchResultPrices){
            System.out.println(prices.getText());
            int value = Integer.parseInt(prices.getText().replaceAll("[^0-9]", ""));
            Assert.assertTrue(value < 1300);
        }
    }

    @Test
    public void filterPriceInputTest() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        categorySelectionTest();
        WebElement priceMinInput = driver.findElement(By.xpath("//input[@id='price_min_']"));
        WebElement priceMaxInput = driver.findElement(By.xpath("//input[@id='price_max_']"));
        WebElement priceInputOKButton = driver.findElement(By.xpath("//a[@class='btn btn-purple ga_cat_filter btn-filters-submit btn-ok']"));
        priceMinInput.click();
        priceMinInput.sendKeys("1000");
        priceMaxInput.click();
        priceMaxInput.sendKeys("2000");
        priceInputOKButton.click();
        wait.until(ExpectedConditions.urlContains("price[min]=1000&price[max]=2000"));
        List<WebElement> searchResultPrices = driver.findElements(By.xpath("//div[@class='price-wrap']/span[@class='price']"));
        wait.until(ExpectedConditions.elementToBeClickable(searchResultPrices.get(0)));
        for (WebElement prices : searchResultPrices){
            System.out.println(prices.getText());
            int value = Integer.parseInt(prices.getText().replaceAll("[^0-9]", ""));
            Assert.assertTrue(value >= 1000 && value <= 2000);
        }
    }

    @Test
    public void wishlistTest() {
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.click();
        searchBar.sendKeys(searchQuery);
        searchBar.submit();
        WebElement firstResult = driver.findElement(By.xpath("//a[@class='model-name ga_card_mdl_title']"));
        firstResult.click();
        WebElement addToWishlistButton = driver.findElement(By.cssSelector("#mod_4137928"));
        Actions action = new Actions(driver);
        action.moveToElement(addToWishlistButton).click().perform();
        WebElement wishlistLink = driver.findElement(By.xpath("//a[@class='wishlist-panel-link']"));
        wishlistLink.click();
        WebElement wishlistPriceLabel = driver.findElement(By.xpath("//span[@class='price_wish']"));
        int wishlistPrice = Integer.parseInt(wishlistPriceLabel.getText());
        System.out.println(wishlistPrice);
        Assert.assertTrue(wishlistPrice > 0);
    }

    @Test
    public void loginEmailNotConfirmedTest() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement header = driver.findElement(By.xpath("//header[@id='site-header']"));
        WebElement loginButton = driver.findElement(By.xpath("//a[@form-name='login']"));
        WebElement closeAdBtn = driver.findElement(By.xpath("//button[@class='close announcement-acb']"));
        wait.until(ExpectedConditions.elementToBeClickable(closeAdBtn));
        closeAdBtn.click();
        wait.until(ExpectedConditions.visibilityOf(header));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        WebElement loginForm = driver.findElement(By.xpath("//div[@class='form-content type-login']"));
        wait.until(ExpectedConditions.visibilityOf(loginForm));
        WebElement loginEmailInput = driver.findElement(By.xpath("//input[@name='LoginForm[username]']"));
        WebElement loginPasswordInput = driver.findElement(By.xpath("//input[@name='LoginForm[password]']"));
        loginEmailInput.click();
        loginEmailInput.sendKeys(loginEmail);
        loginPasswordInput.click();
        loginPasswordInput.sendKeys(loginPassword);
        loginPasswordInput.submit();
        wait.until(ExpectedConditions.invisibilityOf(loginForm));
        WebElement emailNotConfirmedMessage = driver.findElement(By.xpath("//div[@id='tab-not-confirmed']//p"));
        System.out.println(emailNotConfirmedMessage.getText());
        Assert.assertTrue(emailNotConfirmedMessage.getText().contains("Указанный e-mail не подтвержден."));
    }

    @Test
    public void registrationEmptyFieldsTest() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement header = driver.findElement(By.xpath("//header[@id='site-header']"));
        WebElement loginButton = driver.findElement(By.xpath("//a[@form-name='login']"));
        WebElement closeAdBtn = driver.findElement(By.xpath("//button[@class='close announcement-acb']"));
        wait.until(ExpectedConditions.elementToBeClickable(closeAdBtn));
        closeAdBtn.click();
        wait.until(ExpectedConditions.visibilityOf(header));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        WebElement loginForm = driver.findElement(By.xpath("//div[@class='form-content type-login']"));
        wait.until(ExpectedConditions.visibilityOf(loginForm));
        WebElement loginFormRegisterTab = driver.findElement(By.xpath("//div[@id='go-tab-userregister']"));
        loginFormRegisterTab.click();
        WebElement registerButton = driver.findElement(By.xpath("//a[@data-type='userregister']"));
        registerButton.click();
        WebElement errorMessageNeedToFillEmail = driver.findElement(By.xpath("//div[@class='error-text'][contains(text(),'Необходимо заполнить поле «Адрес электронной почты».')]"));
        WebElement errorMessageNeedToFillPassword = driver.findElement(By.xpath("//div[@class='error-text'][contains(text(),'Необходимо заполнить поле «Пароль».')]"));
        wait.until(ExpectedConditions.visibilityOf(errorMessageNeedToFillEmail));
        wait.until(ExpectedConditions.visibilityOf(errorMessageNeedToFillPassword));
    }
}