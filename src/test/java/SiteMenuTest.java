import TestBase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SiteMenuTest extends TestBase {

    @Test(groups = "homePage")
    public void homePageLinkTestByTitle() {
        ChromeDriver chrome = new ChromeDriver();
        chrome.get("https://litecart.stqa.ru/en/");

        WebElement homePageLink = chrome.findElement(By.cssSelector("#site-menu .general-0"));
        homePageLink.click();

        String title = chrome.getTitle();
        chrome.close();

        Assert.assertEquals(title, "Online Store | My Store");

    }

    @Test(groups = "homePage")
    public void homePageLinkTestByElements() {
        ChromeDriver chrome = new ChromeDriver();
        chrome.get("https://litecart.stqa.ru/en/");

        WebElement homePageLink = chrome.findElement(By.cssSelector("#site-menu .general-0"));
        homePageLink.click();

        String[][] requiredPageElements = {
                {"box-most-popular", "id"},
                {"box-campaigns", "id"},
                {"box-latest-products", "id"}
        };
        List<WebElement> webElementList = chrome.findElements(By.cssSelector("#main .middle>.content .box"));

        try{
            haveAllElementsOnPage(requiredPageElements, webElementList);
        } catch (NoSuchElementException e) {
            Assert.fail(e.getMessage());
        } finally {
            chrome.close();
        }
    }

    @Test(groups = "rubberDucksPage")
    public void rubberDucksLinkTestByTitle() {
        ChromeDriver chrome = new ChromeDriver();
        chrome.get("https://litecart.stqa.ru/en/");

        WebElement homePageLink = chrome.findElement(By.cssSelector("#site-menu .category-1"));
        homePageLink.click();

        String title = chrome.getTitle();
        chrome.close();

        Assert.assertEquals(title, "Rubber Ducks | My Store");

    }

    @Test(groups = "rubberDucksPage")
    public void rubberDucksLinkTestByElements() {
        ChromeDriver chrome = new ChromeDriver();
        chrome.get("https://litecart.stqa.ru/en/");

        WebElement homePageLink = chrome.findElement(By.cssSelector("#site-menu .category-1"));
        homePageLink.click();

        String[][] requiredPageElements = {
                {"listing-wrapper categories", "class"},
                {"listing-wrapper products", "class"}
        };
        List<WebElement> webElementList = chrome.findElements(By.cssSelector("#box-category ul"));

        try{
            haveAllElementsOnPage(requiredPageElements, webElementList);
        } catch (NoSuchElementException e) {
            Assert.fail(e.getMessage());
        } finally {
            chrome.close();
        }
    }

    @Test(groups = "subcategoryPage")
    public void subcategoryPageLinkTestByTitle() {
        ChromeDriver chrome = new ChromeDriver();
        chrome.get("https://litecart.stqa.ru/en/");
        Actions builder = new Actions(chrome);

        WebElement rubberDucksLink = chrome.findElement(By.cssSelector("#site-menu .category-1"));
        WebElement subcategoryLink = chrome.findElement(By.cssSelector("#site-menu .category-2"));
        builder.moveToElement(rubberDucksLink).click(subcategoryLink).perform();

        String title = chrome.getTitle();
        chrome.close();

        Assert.assertEquals(title, "Subcategory | My Store");

    }

    @Test(groups = "subcategoryPage")
    public void subcategoryPageLinkTestByElements() {
        ChromeDriver chrome = new ChromeDriver();
        chrome.get("https://litecart.stqa.ru/en/");
        Actions builder = new Actions(chrome);

        WebElement rubberDucksLink = chrome.findElement(By.cssSelector("#site-menu .category-1"));
        WebElement subcategoryLink = chrome.findElement(By.cssSelector("#site-menu .category-2"));
        builder.moveToElement(rubberDucksLink).click(subcategoryLink).perform();

        WebElement navigationPointer = chrome.findElement(By.cssSelector("#breadcrumbs li:last-of-type"));
        String actualNavigationName = navigationPointer.getText();
        chrome.close();

        Assert.assertEquals(actualNavigationName, "» Subcategory");
    }

}
