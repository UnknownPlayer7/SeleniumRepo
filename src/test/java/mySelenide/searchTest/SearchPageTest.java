package mySelenide.searchTest;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import mySelenide.TestBase.TestBase;
import mySelenide.catalogueTest.CataloguePage;
import mySelenide.catalogueTest.RubberDuck;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Condition.innerText;
import static com.codeborne.selenide.Selenide.open;

@Epic("Search")
@Feature("Search field behaviour")
public class SearchPageTest extends TestBase {

    @BeforeMethod
    public void setup() {
        super.setup();
        open("https://litecart.stqa.ru/en/rubber-ducks-c-1/");
    }

    @Story("Ignore irrelevant elements")
    @Description("Leading/trailing spaces and an empty string should be ignored")
    @Test(description = "Handle a search field with spaces or an empty string",dataProvider = "textWithSpaces")
    public void spacesEnteringTest(String text) {
        SearchPage searchPage = new SearchPage();
        CataloguePage cataloguePage = new CataloguePage();
        List<String> expectedRubberDuckNames = RubberDuck.getSortedNames(cataloguePage.getRubberDucks());

        searchPage.enterText(text);
        searchPage.search();

        searchPage.chooseNameFilter();
        List<SelenideElement> actualRubberDucks = cataloguePage.getRubberDucks();

        try{
            for(int i = 0; i < expectedRubberDuckNames.size(); i++) {
                actualRubberDucks.get(i).shouldHave(innerText(expectedRubberDuckNames.get(i)));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Actual amount of rubber ducks doesn't match the expected one");
        }

    }

    @DataProvider(name = "textWithSpaces")
    public Object[][] textWithSpacesProvider() {
        return new Object[][] {
                {""},
                {"  "},
                {"Duck "},
                {" Duck "},
                {"\n"},
                {"\r"},
                {"\n\r"}
        };
    }
}
