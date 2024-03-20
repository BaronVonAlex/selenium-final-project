package ge.tbcitacademy.util;

import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

public class offerListUtil {

    public static List<Double> extractPrices(List<WebElement> elements) {
        return elements.stream()
                .map(WebElement::getText)
                .map(rawText -> rawText.replaceAll("[^0-9.]", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }
}
