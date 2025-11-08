package mySelenide.TestBase;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({ScreenshotListener.class})
public class TestBase {

    @BeforeMethod
    public void setup() {
        DesiredCapabilities caps = new DesiredCapabilities();
        Platform platform = Platform.valueOf(System.getProperty("oc","WIN10"));
        caps.setPlatform(platform);
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browserCapabilities = caps;
        Configuration.browser = System.getProperty("browser","chrome");
    }

    @AfterMethod
    public void teardown() {
        Selenide.closeWebDriver();
    }

}
