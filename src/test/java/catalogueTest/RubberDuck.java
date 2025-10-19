package catalogueTest;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
public class RubberDuck {

    private String name;
    private String price;

    public RubberDuck(WebElement wrappedElement) {
        name = wrappedElement.findElement(By.className("name")).getText();
        price = wrappedElement.findElement(By.cssSelector(".price, .campaign-price")).getText();

    }

    public static List<RubberDuck> getListOfDucks (List<WebElement> wrappedElementsList) {
        List<RubberDuck> rubberDucks = new ArrayList<>();

        for (WebElement wrappedElement : wrappedElementsList) {
            rubberDucks.add(new RubberDuck(wrappedElement));
        }
        return rubberDucks;
    }

    public static List<RubberDuck> sortByName (List<RubberDuck> rubberDucks) {
        List<RubberDuck> sortedByName = new ArrayList<>(rubberDucks);

        sortedByName.sort(Comparator.comparing(RubberDuck::getName));
        return sortedByName;
    }

    public static List<RubberDuck> sortByPrice (List<RubberDuck> rubberDucks) {
        List<RubberDuck> sortedByPrice = new ArrayList<>(rubberDucks);

        sortedByPrice.sort(Comparator.comparing(RubberDuck::getPrice));
        return rubberDucks;
    }

}
