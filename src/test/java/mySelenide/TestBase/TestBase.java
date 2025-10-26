package mySelenide.TestBase;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({ScreenshotListener.class, ReportPortalTestNGListener.class})
public class TestBase {

    @BeforeMethod
    public void setup() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setPlatform(Platform.WIN10);
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browserCapabilities = caps;
    }

    @AfterMethod
    public void teardown() {
        Selenide.closeWebDriver();
    }

}
