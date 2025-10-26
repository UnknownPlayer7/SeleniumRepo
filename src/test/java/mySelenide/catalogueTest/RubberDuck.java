package mySelenide.catalogueTest;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import mySelenide.TestBase.Locator;
import org.openqa.selenium.By;

import java.util.Comparator;
import java.util.List;

public class RubberDuck {

    private static By nameLocator = Locator.getLocator("RubberDuck.name");
    private static By priceLocator = Locator.getLocator("RubberDuck.price");

    @Step("Retrieve rubber duck list sorted by name ")
    public static List<String> getSortedNames(List<SelenideElement> rubberDucks) {
        return sortBy(rubberDucks, nameLocator).stream().map(RubberDuck::getName).toList();
    }

    @Step("Retrieve rubber duck list sorted by price")
    public static List<String> getSortedPrices(List<SelenideElement> rubberDucks) {
        return sortBy(rubberDucks, priceLocator).stream().map(RubberDuck::getPrice).toList();
    }

    private static List<SelenideElement> sortBy (List<SelenideElement> rubberDucks, By locator) {
        return rubberDucks
                .stream()
                .sorted(Comparator.comparing(duck -> duck.findElement(locator).getText()))
                .toList();
    }

    @Step("Get element name")
    public static String getName(SelenideElement element) {
        return element.findElement(nameLocator).getText();
    }

    @Step("Get element price")
    public static String getPrice(SelenideElement element) {
        return element.findElement(priceLocator).getText();
    }



}
