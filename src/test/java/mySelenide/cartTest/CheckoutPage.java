package mySelenide.cartTest;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import mySelenide.TestBase.Locator;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {

    @Step("Get order summary form")
    public SelenideElement getOrderSummaryForm() {
        return $(Locator.getLocator("CheckoutPage.orderSummaryForm"));
    }

    @Step("Get customer details form")
    public SelenideElement getCustomerDetailsForm() {
        return $(Locator.getLocator("CheckoutPage.customerDetailsForm"));
    }

    @Step("Get product from cart")
    public SelenideElement getProductInCart() {
        return $(Locator.getLocator("CheckoutPage.productInCart"));
    }

    @Step("Click remove")
    public void clickRemoveButton() {
        $(Locator.getLocator("CheckoutPage.removeButton")).click();
    }

}
