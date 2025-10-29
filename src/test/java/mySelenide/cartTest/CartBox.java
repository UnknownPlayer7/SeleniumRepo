package mySelenide.cartTest;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import mySelenide.TestBase.Locator;

import static com.codeborne.selenide.Selenide.$;

public class CartBox {

    @Step("Click cart box link")
    public void clickLink() {
        $(Locator.getLocator("CartBox.cartBoxLink")).click();
    }

    @Step("Retrieve money")
    public SelenideElement getMoneyText() {
        return $(Locator.getLocator("CartBox.money"));
    }

    @Step("Retrieve quantity")
    public SelenideElement getQuantityText() {
        return $(Locator.getLocator("CartBox.quantity"));
    }
}
