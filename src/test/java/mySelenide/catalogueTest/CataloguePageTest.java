package mySelenide.catalogueTest;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import mySelenide.TestBase.Locator;
import mySelenide.duckCard.DuckCardPage;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import mySelenide.TestBase.TestBase;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverConditions.title;
import static com.codeborne.selenide.Selenide.*;

@Epic("Shopping flow")
@Feature("UI catalogue and sorting")
public class CataloguePageTest extends TestBase {

    @BeforeMethod
    public void setup() {
        super.setup();
        open("https://litecart.stqa.ru/en/rubber-ducks-c-1/");
    }

    @Story("Sorting")
    @Description("When user sort by name elements must be sorted in ascending order by name")
    @Test(description = "Sort by name")
    public void sortByNameTest() {
        CataloguePage cataloguePage = new CataloguePage();
        List<String> expectedNamesOrder = RubberDuck.getSortedNames(cataloguePage.getRubberDucks());

        cataloguePage.chooseNameFilter();
        List<SelenideElement> sortedRubberDucks = cataloguePage.getRubberDucks();

        for (int i = 0; i < sortedRubberDucks.size(); i++) {
            sortedRubberDucks.get(i).shouldHave(innerText(expectedNamesOrder.get(i)));
        }
    }

    @Story("Sorting")
    @Description("When user sort by price element must be sorted in ascending order by price")
    @Test(description = "Sort by price")
    public void sortByPriceTest() {
        CataloguePage cataloguePage = new CataloguePage();
        List<String> expectedPricesOrder = RubberDuck.getSortedPrices(cataloguePage.getRubberDucks());

        cataloguePage.choosePriceFilter();
        List<SelenideElement> sortedRubberDucks = cataloguePage.getRubberDucks();

        for (int i = 0; i < sortedRubberDucks.size(); i++) {
            sortedRubberDucks.get(i).shouldHave(innerText(expectedPricesOrder.get(i)));
        }
    }

    @Story("Interaction with elements")
    @Description("""
            When user clicks on any rubber duck
            they must be redirected to the card page of the selected duck
            """)
    @Test(description = "Open rubber duck card",dataProvider = "rubberDucks")
    public void openRubberDuckCardTest(By rubberDuckLocator) {
        CataloguePage cataloguePage = new CataloguePage();
        DuckCardPage duckCardPage = new DuckCardPage();

        String title = cataloguePage.getRubberDuckAttributeTitle(rubberDuckLocator);
        cataloguePage.clickRubberDuck(rubberDuckLocator);

        duckCardPage.getRubberDuckTitle().shouldHave(text(title));
    }

    @DataProvider(name = "rubberDucks")
    public By[][] rubberDuckLocatorProvider() {
        return new By[][] {
                {Locator.getLocator("CataloguePageTest.BlueDuck")},
                {Locator.getLocator("CataloguePageTest.YellowDuck")},
                {Locator.getLocator("CataloguePageTest.PurpleDuck")},
                {Locator.getLocator("CataloguePageTest.RedDuck")},
                {Locator.getLocator("CataloguePageTest.GreenDuck")}
        };
    }

    @Story("Navigation")
    @Description("When user click subcategory they must be redirected to subcategory page")
    @Test(description = "Successful navigation to subcategory page")
    public void successfulNavigationToSubcategoryPageTest() {
        CataloguePage cataloguePage = new CataloguePage();
        cataloguePage.navigateToSubcategory();

        webdriver().shouldHave(title("Subcategory | My Store"));
    }

    @Story("Interaction with elements")
    @Description("""
            When user clicks the zoom icon a zoomed version of the image must be displayed.
            Zoom icon is located in the upper-left corner of the product image.
            """)
    @Test(description = "Zoomed image is displayed")
    public void zoomedImageIsDisplayedTest(){
        CataloguePage cataloguePage = new CataloguePage();
        cataloguePage.clickZoom();

        cataloguePage.getWrappedZoomedImage().should(be(visible));
    }
}
