package loginTest;

import TestBase.Locator;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getNoticeText() {
        return driver.findElement(Locator.getLocator("LoginPage.noticesWrapper")).getText();
    }
}
