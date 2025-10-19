package catalogueTest;

import TestBase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CataloguePageTest extends TestBase {

    @BeforeMethod
    public void setup() {
        super.setup();
        driver.get("https://litecart.stqa.ru/en/rubber-ducks-c-1/");
    }

    @Test
    public void sortByNameTest() {
        CataloguePage cataloguePage = new CataloguePage(driver);
        List<RubberDuck> expectedRubberDucksOrder = RubberDuck.sortByName(cataloguePage.getRubberDucks());

        cataloguePage.chooseNameFilter();
        List<RubberDuck> sortedRubberDucks = cataloguePage.getRubberDucks();

        Assert.assertEquals(sortedRubberDucks, expectedRubberDucksOrder);
    }

    @Test
    public void sortByPriceTest() {
        CataloguePage cataloguePage = new CataloguePage(driver);
        List<RubberDuck> expectedRubberDucksOrder = RubberDuck.sortByPrice(cataloguePage.getRubberDucks());

        cataloguePage.choosePriceFilter();
        List<RubberDuck> sortedRubberDucks = cataloguePage.getRubberDucks();

        Assert.assertEquals(sortedRubberDucks, expectedRubberDucksOrder);
    }

    @Test(dataProvider = "rubberDucks")
    public void openRubberDuckCartTest(String rubberDuckLocator) {
        CataloguePage cataloguePage = new CataloguePage(driver);

        String title = cataloguePage.getRubberDuckAttributeTitle(rubberDuckLocator);
        cataloguePage.clickRubberDuck(rubberDuckLocator);

        Assert.assertEquals(driver.findElement(By.cssSelector(".title[itemprop]"))
                .getText(), title);
    }

    @DataProvider(name = "rubberDucks")
    public String[][] rubberDuckLocatorProvider() {
        return new String[][] {
                {".listing-wrapper.products .product [title='Blue Duck']"},
                {".listing-wrapper.products .product [title='Purple Duck']"},
                {".listing-wrapper.products .product [title='Yellow Duck']"},
                {".listing-wrapper.products .product [title='Red Duck']"},
                {".listing-wrapper.products .product [title='Green Duck']"}
        };
    }

    @Test
    public void successfulNavigationToSubcategoryPageTest() {
        CataloguePage cataloguePage = new CataloguePage(driver);
        cataloguePage.navigateToSubcategory();

        Assert.assertEquals(driver.getTitle(), "Subcategory | My Store");
    }

    @Test
    public void zoomedImageIsDisplayedTest(){
        CataloguePage cataloguePage = new CataloguePage(driver);
        cataloguePage.clickZoom();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(cataloguePage.getWrappedZoomedImage()));

        Assert.assertTrue(cataloguePage.getWrappedZoomedImage().isDisplayed(), "The image does not zoom");
    }
}
