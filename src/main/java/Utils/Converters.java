package Utils;

import java.util.ArrayList;
import java.util.List;

public class Converters {
    public static int stringCutAndParseToInt(String string) {
        return Integer.parseInt(string.replaceAll("[^0-9]", ""));
    }

    public static List<String> listToLowerCase(List<String> stringList) {
        List<String> lowerCaseList = new ArrayList<>();
        for(String listElement : stringList) lowerCaseList.add(listElement.toLowerCase());
        return lowerCaseList;
    }
}
