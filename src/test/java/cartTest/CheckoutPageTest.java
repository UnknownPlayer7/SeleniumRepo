package cartTest;

import TestBase.TestBase;
import catalogueTest.CataloguePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutPageTest extends TestBase {

    @BeforeMethod
    public void setup() {
        super.setup();
        driver.get("https://litecart.stqa.ru/en/rubber-ducks-c-1/");
    }

    @Test
    public void orderSummaryFormEmptyAfterRemovingProductTest() {
        CheckoutPage checkoutPage = simulateProductRemovalFlow();

        Assert.assertTrue(checkoutPage.isOrderSummaryFormEmpty());
    }

    @Test
    public void customerDetailsFormEmptyAfterRemovingProductTest() {
        CheckoutPage checkoutPage = simulateProductRemovalFlow();

        Assert.assertTrue(checkoutPage.isCustomerDetailsFormEmpty());
    }
    @Test
    public void cartEmptyAfterRemovingProductTest() {
        CheckoutPage checkoutPage = simulateProductRemovalFlow();

        Assert.assertTrue(checkoutPage.isCartEmpty());
    }

    public CheckoutPage simulateProductRemovalFlow() {
        CataloguePage cataloguePage = new CataloguePage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        CartBox cartBox = new CartBox(driver);

        cataloguePage.addRubberDuckToCart(".listing-wrapper.products .product [title='Blue Duck']"
                ,"12", cartBox);
        cartBox.clickLink();
        checkoutPage.clickRemoveButton();

        return checkoutPage;
    }



}
