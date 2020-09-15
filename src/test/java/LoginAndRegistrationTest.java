import Pages.Header;
import Pages.MainPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginAndRegistrationTest extends BaseTest {
    private final String email = "testaccount@testmail.com";
    private final String password = "12345678";
    private final String expectedMessage = "Email не подтвержден";
    private final String expectedMessageUnderEmail = "Необходимо заполнить поле «Адрес электронной почты».";
    private final String expectedMessageUnderPassword = "Необходимо заполнить поле «Пароль».";

    Header header;
    MainPage mainPage;

    @BeforeClass
    public void initializePage() {
        header = new Header(getDriver());
        mainPage = new MainPage(getDriver());
    }

    @Test
    public void loginWithUnconfirmedEmail() {
        header.closeAnnouncement();
        header.openLoginForm();
        header.loginWithCredentials(email, password);
        header.verifyEmailNotConfirmedMessage(expectedMessage);
    }

    @Test
    public void registrationWithEmptyFields() {
        header.closeAnnouncement();
        header.openLoginForm();
        header.GoToLoginFormRegisterTab();
        header.registerWithCredentials("","");
        header.verifyErrorMessages(expectedMessageUnderEmail, expectedMessageUnderPassword);
    }

}
