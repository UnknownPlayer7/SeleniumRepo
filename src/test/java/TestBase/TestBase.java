package TestBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

public class TestBase {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = chooseDriver();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    private WebDriver chooseDriver() {
        Browser browser = Browser.valueOf(System.getProperty("browser", "chrome"));

        return switch (browser) {
            case chrome -> new ChromeDriver();
            case edge -> new EdgeDriver();
        };
    }

    public List<String> getNotFoundElements(String[][] requiredPageElements, List<WebElement> webElementList){
        List<String> notFoundElementsList = new ArrayList<>();
        boolean isMatch;

        for (String[] requiredPageElement : requiredPageElements) {
            isMatch = false;

            for (WebElement webElement : webElementList) {
                if (requiredPageElement[0].equals(webElement.getAttribute(requiredPageElement[1]))) {
                    isMatch = true;
                    break;
                }
            }

            if(!isMatch) {
                String element = String.format(
                        "The element with attribute: %s and value: %s was not found on the page.",
                        requiredPageElement[1], requiredPageElement[0]);

                notFoundElementsList.add(element);
            }
        }
        return notFoundElementsList;
    }

}
