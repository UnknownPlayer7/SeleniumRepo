package mySelenide.TestBase;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        File screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.FILE);

        try{
            Allure.addAttachment(result.getMethod().getMethodName() + "screenshot",
                    new FileInputStream(screenshot));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
