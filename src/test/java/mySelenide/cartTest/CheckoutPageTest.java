package mySelenide.cartTest;

import io.qameta.allure.*;
import mySelenide.TestBase.Locator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import mySelenide.TestBase.TestBase;
import mySelenide.catalogueTest.CataloguePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Epic("Shopping flow")
@Feature("UI cart")
public class CheckoutPageTest extends TestBase {

    @BeforeMethod
    public void setup() {
        super.setup();
        open("https://litecart.stqa.ru/en/rubber-ducks-c-1/");
    }

    @Story("RemovalFlow")
    @Description("When the user removes all elements from the cart order summary form should become empty")
    @Test(description = "Order summary form empty after removing product")
    public void orderSummaryFormEmptyAfterRemovingProductTest() {
        CheckoutPage checkoutPage = simulateProductRemovalFlow();

        checkoutPage.getOrderSummaryForm().shouldBe(empty);
    }

    @Story("RemovalFlow")
    @Description("When the user removes all elements from the cart customer details form should become empty")
    @Test(description = "Customer details form empty after removing product")
    public void customerDetailsFormEmptyAfterRemovingProductTest() {
        CheckoutPage checkoutPage = simulateProductRemovalFlow();

        checkoutPage.getCustomerDetailsForm().shouldBe(empty);
    }

    @Story("RemovalFlow")
    @Description("When the user removes all elements from the cart it should become empty")
    @Test(description = "Cart empty after removing product")
    public void cartEmptyAfterRemovingProductTest() {
        CheckoutPage checkoutPage = simulateProductRemovalFlow();

        checkoutPage.getProductInCart().shouldNotBe(exist);
    }

    @Step("Simulate product removal flow")
    public CheckoutPage simulateProductRemovalFlow() {
        CataloguePage cataloguePage = new CataloguePage();
        CheckoutPage checkoutPage = new CheckoutPage();
        CartBox cartBox = new CartBox();

        cataloguePage.addRubberDuckToCart(Locator.getLocator("CataloguePageTest.RedDuck"),"12");
        cartBox.getQuantityText().shouldHave(text("12"));

        cartBox.clickLink();
        checkoutPage.clickRemoveButton();

        return checkoutPage;
    }



}
