import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SiteTest {

    @Test
    public void clickOnTenthLink() {
        WebDriver chrome = new ChromeDriver();
        chrome.get("https://the-internet.herokuapp.com");

        List<WebElement> links = chrome.findElements(By.xpath("//ul/li/a"));
        links.get(9).click();

        String expectedUrl = "https://the-internet.herokuapp.com/drag_and_drop";
        Assert.assertEquals(chrome.getCurrentUrl(), expectedUrl);
    }


}
