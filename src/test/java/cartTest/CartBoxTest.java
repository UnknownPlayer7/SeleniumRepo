package cartTest;

import TestBase.TestBase;
import catalogueTest.CataloguePage;
import catalogueTest.RubberDuck;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CartBoxTest extends TestBase {

    @BeforeMethod
    public void setup() {
        super.setup();
        driver.get("https://litecart.stqa.ru/en/rubber-ducks-c-1/");
    }

    @Test(dataProvider = "rubberDucks")
    public void quantityChangeTest(String rubberDuckLocator, String enteredQuantity) {
        CartBox cartBox = new CartBox(driver);
        CataloguePage cataloguePage = new CataloguePage(driver);

        cataloguePage.addRubberDuckToCart(rubberDuckLocator, enteredQuantity, cartBox);

        Assert.assertEquals(cartBox.getQuantityText(), enteredQuantity);
    }

    @Test(dataProvider = "rubberDucks")
    public void moneyChangeTest(String rubberDuckLocator, String enteredQuantity) {
        CartBox cartBox = new CartBox(driver);
        CataloguePage cataloguePage = new CataloguePage(driver);

        String expectedMoney = calculateExpectedMoney(driver.findElement(By.cssSelector(rubberDuckLocator))
                , enteredQuantity);

        cataloguePage.addRubberDuckToCart(rubberDuckLocator, enteredQuantity, cartBox);

        Assert.assertEquals(cartBox.getMoneyTextWithoutSeparators(), expectedMoney);
    }

    public String calculateExpectedMoney(WebElement webElement, String enteredQuantity) {
        String priceForOne = new RubberDuck(webElement).getPrice().replace("$","");

        return "$" + Integer.parseInt(priceForOne) * Integer.parseInt(enteredQuantity);
    }

    @DataProvider(name = "rubberDucks")
    public String[][] rubberDuckLocatorProvider() {
        return new String[][] {
                {".listing-wrapper.products .product [title='Blue Duck']", "12"},
                {".listing-wrapper.products .product [title='Purple Duck']", "5"},
                {".listing-wrapper.products .product [title='Red Duck']", "1"},
                {".listing-wrapper.products .product [title='Green Duck']", "99"}
        };
    }

    @Test
    public void successfulNavigationToCheckoutPageByLinkTest() {
        CartBox cartBox = new CartBox(driver);
        cartBox.clickLink();

        Assert.assertEquals(driver.getTitle(), "Checkout | My Store");
    }
}
