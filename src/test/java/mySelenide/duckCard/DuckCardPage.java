package mySelenide.duckCard;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import mySelenide.TestBase.Locator;

import static com.codeborne.selenide.Selenide.$;

public class DuckCardPage {

    @Step("Set quantity of a product")
    public void setQuantity(String number) {
        WebElement quantity = $(Locator.getLocator("DuckCardPage.quantity"));

        quantity.clear();
        quantity.sendKeys(number);
    }

    @Step("Click \"Add to cart\"")
    public void clickAddToCartButton() {
        $(Locator.getLocator("DuckCardPage.addToCartButton")).click();
    }

    @Step("Enter product quantity and add to cart")
    public void addToCart(String amount) {
        setQuantity(amount);
        clickAddToCartButton();
    }

    @Step("Get rubber duck name")
    public SelenideElement getRubberDuckTitle() {
        return $(Locator.getLocator("DuckCardPage.rubberDuckTitle"));
    }




}
