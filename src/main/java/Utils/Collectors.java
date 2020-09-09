package Utils;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Collectors {
    public static ArrayList<Integer> collectAndParseToIntResultPrices(List<WebElement> resultPricesList) {
        ArrayList<Integer> integerPrices = new ArrayList();
        for (WebElement price : resultPricesList) integerPrices.add(Converters.stringCutAndParseToInt(price.getText()));
        return integerPrices;
    }

    public static List<String> collectModelNames(List<WebElement> modelTitlesList) {
        List<String> modelNames = new ArrayList<>();
        for(WebElement title : modelTitlesList) modelNames.add(title.getText());
        return modelNames;
    }
}
