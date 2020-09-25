import Pages.Header;
import Pages.MainPage;
import Pages.UserpanelProfilePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginAndRegistrationTest extends BaseTest {
    private final String emailUnconfirmed = "testaccount@testmail.com";
    private final String emailRegistered = "yoxanov792@mail7d.com";
    private final String password = "12345678";
    private final String expectedMessage = "Email не подтвержден";
    private final String expectedMessageUnderEmail = "Необходимо заполнить поле «Адрес электронной почты».";
    private final String expectedMessageUnderPassword = "Необходимо заполнить поле «Пароль».";

    Header header;
    MainPage mainPage;
    UserpanelProfilePage userpanelProfilePage;

    @BeforeClass
    public void initializePage() {
        header = new Header(getDriver());
        mainPage = new MainPage(getDriver());
        userpanelProfilePage = new UserpanelProfilePage(getDriver());
    }

    @BeforeMethod
    public void openLoginForm() {
        header.closeAnnouncement();
        header.openLoginForm();
    }

    @Test
    public void loginWithUnconfirmedEmail() {
        header.loginWithCredentials(emailUnconfirmed, password);
        header.verifyEmailNotConfirmedMessage(expectedMessage);
    }

    @Test
    public void loginWithValidCredentials() {
        header.loginWithCredentials(emailRegistered, password);
        header.openUserpanel();
        userpanelProfilePage.checkIfYouLogged();
        header.closeAnnouncement();
        header.logOut();
    }

    @Test
    public void registrationWithEmptyFields() {
//        header.GoToLoginFormRegisterTab();
        header.registerWithCredentials("","");
        header.verifyErrorMessages(expectedMessageUnderEmail, expectedMessageUnderPassword);
    }
}
