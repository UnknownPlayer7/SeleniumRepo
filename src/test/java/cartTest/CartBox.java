package cartTest;

import TestBase.Locator;
import org.openqa.selenium.WebDriver;

public class CartBox {

    private WebDriver driver;

    public CartBox(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLink() {
        driver.findElement(Locator.getLocator("CartBox.cartBoxLink")).click();
    }



    public String getMoneyTextWithoutSeparators() {
        return driver.findElement(Locator.getLocator("CartBox.money")).getText().replace(",","");
    }

    public String getQuantityText() {
        return driver.findElement(Locator.getLocator("CartBox.quantity")).getText();
    }
}
