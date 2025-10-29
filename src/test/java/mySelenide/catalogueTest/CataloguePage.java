package mySelenide.catalogueTest;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import mySelenide.TestBase.Locator;
import mySelenide.duckCard.DuckCardPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CataloguePage {

    @Step("Click on rubber duck")
    public void clickRubberDuck(By locator) {
        $(locator).click();
    }

    @Step("Get rubber duck attribute title")
    public String getRubberDuckAttributeTitle(By locator) {
        return $(locator).getAttribute("title");
    }

    @Step("Retrieve rubber duck list")
    public List<SelenideElement> getRubberDucks() {
        return $$(Locator.getLocator("CataloguePage.rubberDucks")).stream().toList();
    }

    @Step("Display zoomed element")
    public void clickZoom() {
        $(Locator.getLocator("CataloguePage.zoom")).click();
    }

    @Step("Get zoomed image")
    public SelenideElement getWrappedZoomedImage() {
        return $(Locator.getLocator("CataloguePage.wrappedZoomedImage"));
    }

    @Step("Navigate to subcategory")
    public void navigateToSubcategory() {
        $(Locator.getLocator("CataloguePage.subcategory")).click();
    }

    @Step("Choose name filter")
    public void chooseNameFilter() {
        $$(Locator.getLocator("CataloguePage.filters")).get(0).click();
    }

    @Step("Choose price filter")
    public void choosePriceFilter() {
        $$(Locator.getLocator("CataloguePage.filters")).get(1).click();
    }

    @Step("Add rubber duck to cart")
    public void addRubberDuckToCart(By rubberDuckLocator, String enteredQuantity) {
        CataloguePage cataloguePage = new CataloguePage();
        DuckCardPage duckCardPage = new DuckCardPage();

        cataloguePage.clickRubberDuck(rubberDuckLocator);
        duckCardPage.addToCart(enteredQuantity);
    }
}
