import TestBase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SiteMenuTest extends TestBase {

    private ChromeDriver chrome;

    @BeforeMethod
    public void setup() {
        chrome = new ChromeDriver();
        chrome.get("https://litecart.stqa.ru/en/");
    }

    @AfterMethod
    public void teardown() {
        chrome.quit();
    }

    @Test(groups = "homePage")
    public void homePageLinkTestByTitle() {
        WebElement homePageLink = chrome.findElement(By.cssSelector("#site-menu .general-0"));
        homePageLink.click();

        String title = chrome.getTitle();

        Assert.assertEquals(title, "Online Store | My Store");

    }

    @Test(groups = "homePage")
    public void homePageLinkTestByElements() {
        WebElement homePageLink = chrome.findElement(By.cssSelector("#site-menu .general-0"));
        homePageLink.click();

        String[][] requiredPageElements = {
                {"box-most-popular", "id"},
                {"box-campaigns", "id"},
                {"box-latest-products", "id"}
        };
        List<WebElement> webElementList = chrome.findElements(By.cssSelector("#main .middle>.content .box"));

        List<String> notFoundElements = getNotFoundElements(requiredPageElements, webElementList);
        Assert.assertTrue(notFoundElements.isEmpty(), notFoundElements.toString());
    }

    @Test(groups = "rubberDucksPage")
    public void rubberDucksLinkTestByTitle() {
        WebElement homePageLink = chrome.findElement(By.cssSelector("#site-menu .category-1"));
        homePageLink.click();

        String title = chrome.getTitle();

        Assert.assertEquals(title, "Rubber Ducks | My Store");

    }

    @Test(groups = "rubberDucksPage")
    public void rubberDucksLinkTestByElements() {
        WebElement homePageLink = chrome.findElement(By.cssSelector("#site-menu .category-1"));
        homePageLink.click();

        String[][] requiredPageElements = {
                {"listing-wrapper categories", "class"},
                {"listing-wrapper products", "class"}
        };
        List<WebElement> webElementList = chrome.findElements(By.cssSelector("#box-category ul"));

        List<String> notFoundElements = getNotFoundElements(requiredPageElements, webElementList);
        Assert.assertTrue(notFoundElements.isEmpty(), notFoundElements.toString());
    }

    @Test(groups = "subcategoryPage")
    public void subcategoryPageLinkTestByTitle() {
        Actions builder = new Actions(chrome);

        WebElement rubberDucksLink = chrome.findElement(By.cssSelector("#site-menu .category-1"));
        WebElement subcategoryLink = chrome.findElement(By.cssSelector("#site-menu .category-2"));
        builder.moveToElement(rubberDucksLink).click(subcategoryLink).perform();

        String title = chrome.getTitle();

        Assert.assertEquals(title, "Subcategory | My Store");

    }

    @Test(groups = "subcategoryPage")
    public void subcategoryPageLinkTestByElements() {
        Actions builder = new Actions(chrome);

        WebElement rubberDucksLink = chrome.findElement(By.cssSelector("#site-menu .category-1"));
        WebElement subcategoryLink = chrome.findElement(By.cssSelector("#site-menu .category-2"));
        builder.moveToElement(rubberDucksLink).click(subcategoryLink).perform();

        WebElement navigationPointer = chrome.findElement(By.cssSelector("#breadcrumbs li:last-of-type"));
        String actualNavigationName = navigationPointer.getText();

        Assert.assertEquals(actualNavigationName, "» Subcategory");
    }

}
