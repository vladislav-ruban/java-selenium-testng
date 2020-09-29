import Pages.Header;
import Pages.UserpanelProfilePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginAndRegistrationTest extends BaseTest {
    private final String emailUnconfirmed = "testaccount@testmail.com";
    private final String emailRegistered = "yoxanov792@mail7d.com";
    private final String password = "12345678";
    private final String expectedMessage = "Email не подтвержден";
    private final String expectedMessageUnderEmail = "Необходимо заполнить поле «Адрес электронной почты».";
    private final String expectedMessageUnderPassword = "Необходимо заполнить поле «Пароль».";

    @BeforeMethod
    public void openLoginForm() {
        Header header = new Header(driver);
        header.closeAnnouncement();
        header.openLoginForm();
    }

    @Test
    public void loginWithUnconfirmedEmail() {
        Header header = new Header(driver);
        header.loginWithCredentials(emailUnconfirmed, password);
        header.verifyEmailNotConfirmedMessage(expectedMessage);
    }

    @Test
    public void loginWithValidCredentials() {
        Header header = new Header(driver);
        header.loginWithCredentials(emailRegistered, password);
        header.openUserpanel();
        UserpanelProfilePage userpanelProfilePage = new UserpanelProfilePage(driver);
        userpanelProfilePage.checkIfYouLogged();
        header.closeAnnouncement();
        header.logOut();
    }

    @Test
    public void registrationWithEmptyFields() {
        Header header = new Header(driver);
        header.GoToLoginFormRegisterTab();
        header.registerWithCredentials("","");
        header.verifyErrorMessages(expectedMessageUnderEmail, expectedMessageUnderPassword);
    }
}
