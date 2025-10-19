package loginTest;

import TestBase.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginPageTest extends TestBase {

    @BeforeMethod
    public void setup() {
        super.setup();
        driver.get("https://litecart.stqa.ru/en/");
    }

    @Test
    public void rightCredentialTest() {
        LoginBox loginBox = new LoginBox(driver);
        loginBox.attemptLogin("saladin@mail.com", "saladinAdmin@123");

        Assert.assertEquals(loginBox.getTitle(), "Account");
    }

    @Test
    public void emptyPasswordTest() {
        LoginBox loginBox = new LoginBox(driver);
        LoginPage loginPage = new LoginPage(driver);

        loginBox.enterEmailAddress("saladin@mail.com");
        loginBox.clickLoginButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getTitle(), "Login | My Store");
        softAssert.assertEquals(loginPage.getNoticeText(), "You must provide both email address and password.");
        softAssert.assertAll();
    }

    @Test
    public void emptyEmailAddressTest() {
        LoginBox loginBox = new LoginBox(driver);

        loginBox.enterPassword("saladinAdmin@123");
        loginBox.clickLoginButton();

        Assert.assertEquals(loginBox.getTitle(), "Login");


    }

    @Test
    public void wrongCredentialTest() {
        LoginBox loginBox = new LoginBox(driver);
        LoginPage loginPage = new LoginPage(driver);

        loginBox.attemptLogin("saladin@mail.com", "wrongPassword123!");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getTitle(), "Login | My Store");
        softAssert.assertEquals(loginPage.getNoticeText(),
                "Wrong password or the account is disabled, or does not exist");
        softAssert.assertAll();
    }

    @Test
    public void emailFieldXssInjectionTest() {
        LoginBox loginBox = new LoginBox(driver);
        LoginPage loginPage = new LoginPage(driver);

        loginBox.attemptLogin("<script>alert(1)</script>", "wrongPassword123!");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getTitle(), "Login | My Store");
        softAssert.assertEquals(loginPage.getNoticeText(),
                "Wrong password or the account is disabled, or does not exist");
        softAssert.assertAll();
    }
}
