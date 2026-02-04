package catalogueTest;

import TestBase.Locator;
import cartTest.CartBox;
import duckCard.DuckCardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CataloguePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CataloguePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRubberDuck(String cssLocator) {
        driver.findElement(By.cssSelector(cssLocator)).click();
    }

    public String getRubberDuckAttributeTitle(String cssLocator) {
        return driver.findElement(By.cssSelector(cssLocator)).getAttribute("title");
    }

    public List<RubberDuck> getRubberDucks() {
        return RubberDuck.getListOfDucks(driver.findElements(Locator.getLocator("CataloguePage.rubberDucks")));
    }

    public void clickZoom() {
        driver.findElement(Locator.getLocator("CataloguePage.zoom")).click();
    }

    public WebElement getWrappedZoomedImage() {
        return driver.findElement(Locator.getLocator("CataloguePage.wrappedZoomedImage"));
    }

    public void navigateToSubcategory() {
        driver.findElement(Locator.getLocator("CataloguePage.subcategory")).click();
    }

    public void chooseNameFilter() {
        driver.findElements(Locator.getLocator("CataloguePage.filters")).get(0).click();
    }

    public void choosePriceFilter() {
        driver.findElements(Locator.getLocator("CataloguePage.filters")).get(1).click();
    }

    public void addRubberDuckToCart(String rubberDuckLocator, String enteredQuantity, CartBox cartBox) {
        CataloguePage cataloguePage = new CataloguePage(driver);
        DuckCardPage duckCardPage = new DuckCardPage(driver);

        cataloguePage.clickRubberDuck(rubberDuckLocator);
        duckCardPage.addToCart(enteredQuantity);

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> {
            String initialText = cartBox.getQuantityText();
            return initialText.equals(enteredQuantity);
        });
    }
}
