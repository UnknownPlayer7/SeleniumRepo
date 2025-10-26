package mySelenide.TestBase;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({ScreenshotListener.class, ReportPortalTestNGListener.class})
public class TestBase {

    @BeforeMethod
    public void setup() {
        Configuration.browser = System.getProperty("browser", "CHROME");
        Configuration.browserSize = "1366x768";
        Configuration.pageLoadTimeout = 5000;
    }

    @AfterMethod
    public void teardown() {
        Selenide.closeWebDriver();
    }

}
