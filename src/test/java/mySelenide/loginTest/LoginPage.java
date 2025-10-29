package mySelenide.loginTest;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import mySelenide.TestBase.Locator;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    @Step("Get notice text from the login page")
    public SelenideElement getNoticeText() {
        return $(Locator.getLocator("LoginPage.noticesWrapper"));
    }
}
