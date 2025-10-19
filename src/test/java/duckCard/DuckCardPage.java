package duckCard;

import TestBase.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DuckCardPage {

    private WebDriver driver;

    public DuckCardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setQuantity(String number) {
        WebElement quantity = driver.findElement(Locator.getLocator("DuckCardPage.quantity"));

        quantity.clear();
        quantity.sendKeys(number);
    }

    public void clickAddToCartButton() {
        driver.findElement(Locator.getLocator("DuckCardPage.addToCartButton")).click();
    }

    public void addToCart(String amount) {
        setQuantity(amount);
        clickAddToCartButton();
    }




}
