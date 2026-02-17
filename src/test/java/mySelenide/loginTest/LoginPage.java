package mySelenide.loginTest;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import mySelenide.TestBase.Locator;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    @Step("Get notice text from the login page")
    public SelenideElement getNoticeText() {
        return $(Locator.getLocator("LoginPage.noticesWrapper"));
    }

    @Step("Get list of links")
    public List<String> getLinks(String baseUrl) {
        return  Selenide.$$(By.tagName("a"))
                .stream().map(element -> element.getDomAttribute("href"))
                .filter(link -> link != null && link.startsWith(baseUrl)).toList();
    }
}
