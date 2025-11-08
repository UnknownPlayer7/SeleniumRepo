package mySelenide.loginTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import mySelenide.TestBase.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


@Epic("Authentication")
@Feature("UI Login form")
public class LoginPageTest extends TestBase {

    @BeforeMethod
    public void setup() {
        super.setup();
        open("https://litecart.stqa.ru/en/");
    }

    @Story("Login with right credentials")
    @Description("When user enters credentials and log in the title of the login box changes from 'Login' to 'Account'")
    @Test(description = "Login with right credentials")
    public void rightCredentialTest() {
        LoginBox loginBox = new LoginBox();
        loginBox.attemptLogin("saladin@mail.com", "saladinAdmin@123");

        loginBox.getTitle().shouldHave(text("BUG"));
    }

    @Story("Login with wrong credentials")
    @Description("""
            When user enters only password and leaves the email field empty and attempts to log in
            authentication does not occur."
            """)
    @Test(description = "Attempt to log in with empty email field")
    public void emptyEmailAddressTest() {
        LoginBox loginBox = new LoginBox();

        loginBox.enterPassword("saladinAdmin@123");
        loginBox.clickLoginButton();

        loginBox.getTitle().shouldHave(text("Login"));
    }
}
