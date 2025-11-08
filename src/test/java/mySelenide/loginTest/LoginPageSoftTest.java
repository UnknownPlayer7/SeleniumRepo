package mySelenide.loginTest;
import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.SoftAsserts;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import mySelenide.TestBase.TestBase;

import static com.codeborne.selenide.WebDriverConditions.title;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


@Epic("Authentication")
@Feature("UI Login form")
@Listeners({SoftAsserts.class})
public class LoginPageSoftTest extends TestBase {
    @BeforeMethod
    public void setup() {
        super.setup();
        open("https://litecart.stqa.ru/en/");
    }

    @Story("Login with wrong credentials")
    @Description("""
            When user enters only email and leaves the password field empty and attempts to log in
            the user is redirected to Login page
            There they will see error message: "You must provide both email address and password."
            """)
    @Test(description = "Attempt to log in with empty password field")
    public void emptyPasswordTest() {
        LoginBox loginBox = new LoginBox();
        LoginPage loginPage = new LoginPage();

        loginBox.enterEmailAddress("saladin@mail.com");
        loginBox.clickLoginButton();

        Configuration.assertionMode = AssertionMode.SOFT;

        loginPage.getNoticeText().shouldHave(text("You must provide both email address and password."));
        webdriver().shouldHave(title("Login | My Store"));
    }

    @Story("Login with wrong credentials")
    @Description("""
            When user enters right email and wrong password and attempts to log in
            the user is redirected to Login page
            There they will see error message: "Wrong password or the account is disabled, or does not exist"
            """)
    @Test(description = "Attempt to log in with wrong credentials")
    public void wrongCredentialTest() {
        LoginBox loginBox = new LoginBox();
        LoginPage loginPage = new LoginPage();

        loginBox.attemptLogin("saladin@mail.com", "wrongPassword123!");

        Configuration.assertionMode = AssertionMode.SOFT;

        loginPage.getNoticeText().shouldHave(text("Wrong password or the account is disabled, or does not exist"));
        webdriver().shouldHave(title("Login | My Store"));
    }

    @Story("Login with wrong credentials")
    @Description("""
            When user enters into email field XSS and attempts to log in
            the user is redirected to Login page
            There they will see error message: "Wrong password or the account is disabled, or does not exist"
            """)
    @Test(description = "Attempt to log in with XSS in the email field")
    public void emailFieldXssInjectionTest() {
        LoginBox loginBox = new LoginBox();
        LoginPage loginPage = new LoginPage();

        loginBox.attemptLogin("<script>alert(1)</script>", "wrongPassword123!");


        Configuration.assertionMode = AssertionMode.SOFT;

        loginPage.getNoticeText().shouldHave(text("Wrong password or the account is disabled, or does not exist"));
        webdriver().shouldHave(title("Login | My Store"));
    }
}

