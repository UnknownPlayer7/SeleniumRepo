package mySelenide.cartTest;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverConditions;
import io.qameta.allure.*;
import mySelenide.TestBase.Locator;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import mySelenide.TestBase.TestBase;
import mySelenide.catalogueTest.CataloguePage;
import mySelenide.catalogueTest.RubberDuck;

import java.text.NumberFormat;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@Epic("Shopping flow")
@Feature("UI cart box")
public class CartBoxTest extends TestBase {

    @BeforeMethod
    public void setup() {
        super.setup();
        open("https://litecart.stqa.ru/en/rubber-ducks-c-1/");
    }

    @Story("Cart box quantity update")
    @Description("""
            When the user adds a duck to cart with a specified quantity
            the quantity in the cart box should be updated accordingly
            """)
    @Test(description = "Quantity change",dataProvider = "rubberDucks")
    public void quantityChangeTest(By rubberDuckLocator, String enteredQuantity) {
        CartBox cartBox = new CartBox();
        CataloguePage cataloguePage = new CataloguePage();

        cataloguePage.addRubberDuckToCart(rubberDuckLocator, enteredQuantity);

        cartBox.getQuantityText().shouldHave(text(enteredQuantity));
    }

    @Story("Cart box money update")
    @Description("""
            When the user adds a duck to cart with a specified quantity
            the total price in the cart box should be updated accordingly
            """)
    @Test(description = "Total price change",dataProvider = "rubberDucks")
    public void totalPriceChangeTest(By rubberDuckLocator, String enteredQuantity) {
        CartBox cartBox = new CartBox();
        CataloguePage cataloguePage = new CataloguePage();

        String expectedMoney = calculateExpectedMoney($(rubberDuckLocator), enteredQuantity) + 20; // BUG HERE

        cataloguePage.addRubberDuckToCart(rubberDuckLocator, enteredQuantity);

        cartBox.getMoneyText().shouldHave(text(expectedMoney));
    }

    @Step("Calculate expected money")
    public String calculateExpectedMoney(SelenideElement selenideElement, String enteredQuantity) {
        String priceForOne = RubberDuck.getPrice(selenideElement).replace("$","");
        String result = toExpectedFormat(Integer.parseInt(priceForOne) * Integer.parseInt(enteredQuantity));
        return "$" + result;
    }

    @Step("Convert money to expected format")
    public String toExpectedFormat(Integer number) {
        return NumberFormat.getNumberInstance(Locale.US).format(number);
    }

    @DataProvider(name = "rubberDucks")
    public Object[][] rubberDuckLocatorProvider() {
        return new Object[][] {
                {Locator.getLocator("CataloguePageTest.GreenDuck"), "21"},
                {Locator.getLocator("CataloguePageTest.BlueDuck"), "12"},
                {Locator.getLocator("CataloguePageTest.PurpleDuck"), "1"},
                {Locator.getLocator("CataloguePageTest.RedDuck"), "99"}
        };
    }

    @Story("Navigation")
    @Description("When user click cart box link they must be redirected to checkout page")
    @Test(description = "Successful navigation to checkout page by link")
    public void successfulNavigationToCheckoutPageByLinkTest() {
        CartBox cartBox = new CartBox();
        cartBox.clickLink();

        webdriver().shouldHave(WebDriverConditions.title("Checkout | My Store"));
    }
}
