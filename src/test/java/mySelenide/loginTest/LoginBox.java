package mySelenide.loginTest;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import mySelenide.TestBase.Locator;

import static com.codeborne.selenide.Selenide.$;

public class LoginBox {

    @Step("Enter email address into login field")
    public void enterEmailAddress(String emailAddress) {
        $(Locator.getLocator("LoginBox.emailAddress")).sendKeys(emailAddress);
    }

    @Step("Enter password into login field")
    public void enterPassword(String password) {
        $(Locator.getLocator("LoginBox.password")).sendKeys(password);
    }

    @Step("Click login")
    public void clickLoginButton() {
        $(Locator.getLocator("LoginBox.loginButton")).click();
    }

    @Step("Enter credentials and log in")
    public void attemptLogin(String emailAddress, String password) {
        enterEmailAddress(emailAddress);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Get title from login box")
    public SelenideElement getTitle() {
        return $(By.cssSelector("[id*=box-account] .title"));
    }

}
