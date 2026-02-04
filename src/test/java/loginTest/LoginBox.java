package loginTest;

import TestBase.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginBox {

    private WebDriver driver;

    public LoginBox(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmailAddress(String emailAddress) {
        driver.findElement(Locator.getLocator("LoginBox.emailAddress")).sendKeys(emailAddress);
    }

    public void enterPassword(String password) {
        driver.findElement(Locator.getLocator("LoginBox.password")).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(Locator.getLocator("LoginBox.loginButton")).click();
    }

    public void attemptLogin(String emailAddress, String password) {
        enterEmailAddress(emailAddress);
        enterPassword(password);
        clickLoginButton();
    }

    public String getTitle() {
        return driver.findElement(By.cssSelector("[id*=box-account] .title")).getText();
    }

}
