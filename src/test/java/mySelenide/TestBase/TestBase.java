package mySelenide.TestBase;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    @BeforeSuite
    public void setUpAllureSelenide() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @BeforeMethod
    public void setup() {
        DesiredCapabilities caps = new DesiredCapabilities();
        Platform platform = Platform.valueOf(System.getProperty("oc","WIN10"));
        caps.setPlatform(platform);
        Configuration.assertionMode = AssertionMode.STRICT;
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browserCapabilities = caps;
        Configuration.browser = System.getProperty("browser","chrome");
    }

    @AfterMethod
    public void teardown() {
        Selenide.closeWebDriver();
    }

}
