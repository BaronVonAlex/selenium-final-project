package ge.tbcitacademy.util;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class offerListUtil {

    public static List<Double> extractPrices(List<WebElement> elements) {
        return elements.stream()
                .map(element -> {
                    try {
                        return Double.parseDouble(element.getText().replaceAll("[^0-9.]", ""));
                    } catch (StaleElementReferenceException e) {
                        // Handle StaleElementReferenceException here
                        System.err.println("StaleElementReferenceException occurred: " + e.getMessage());
                        return 0.0;
                    } catch (NumberFormatException e) {
                        // Handle NumberFormatException here
                        System.err.println("NumberFormatException occurred: " + e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull) // Filter out null values caused by exceptions
                .collect(Collectors.toList());
    }
}
