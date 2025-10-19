package cartTest;

import TestBase.Locator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOrderSummaryFormEmpty() {
        return isElementEmpty(driver.findElement(Locator.getLocator("CheckoutPage.orderSummaryForm")));
    }

    public boolean isCustomerDetailsFormEmpty() {
        return isElementEmpty(driver.findElement(Locator.getLocator("CheckoutPage.customerDetailsForm")));
    }

    private boolean isElementEmpty(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try{
            wait.until(driver -> element.findElements(By.xpath("./*")).isEmpty());
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isCartEmpty() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    Locator.getLocator("CheckoutPage.productInCart")));

        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void clickRemoveButton() {
        driver.findElement(Locator.getLocator("CheckoutPage.removeButton")).click();
    }

}
