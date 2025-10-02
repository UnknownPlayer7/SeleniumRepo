package TestBase;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestBase {

    public boolean haveAllElementsOnPage(String[][] requiredPageElements, List<WebElement> webElementList) throws NoSuchElementException {
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
                String message = String.format(
                        "The element with attribute: %s and value: %s was not found on the page.",
                        requiredPageElement[1], requiredPageElement[0]);

                throw new NoSuchElementException(message);
            }
        }
        return true;
    }

}
