package mySelenide.searchTest;

import io.qameta.allure.Step;
import mySelenide.TestBase.Locator;

import static com.codeborne.selenide.Selenide.$;

public class SearchPage {


    @Step("Enter text into search field")
    public void enterText(String text) {
        $(Locator.getLocator("SearchPage.searchField")).sendKeys(text);
    }

    @Step("Search")
    public void search() {
        $(Locator.getLocator("SearchPage.searchField")).pressEnter();
    }

    @Step("Choose name filter")
    public void chooseNameFilter() {
        $(Locator.getLocator("SearchPage.nameFilter")).click();
    }

}
