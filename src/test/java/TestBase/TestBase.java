package TestBase;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TestBase {

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
