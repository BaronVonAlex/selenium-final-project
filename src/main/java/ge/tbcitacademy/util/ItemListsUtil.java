package ge.tbcitacademy.util;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ItemListsUtil {

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

    public static List<String> extractText(List<WebElement> elements) {
        return elements.stream()
                .map(element -> {
                    try {
                        return element.getText();
                    } catch (StaleElementReferenceException e) {
                        // Handle StaleElementReferenceException here
                        System.err.println("StaleElementReferenceException occurred: " + e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull) // Filter out null values caused by exceptions
                .filter(text -> !text.isEmpty()) // Filter out empty strings
                .collect(Collectors.toList());
    }

    public static List<Double> fetchAllOfferPrices(WebDriver driver, WebDriverWait wait, String offerElementXpath, String nextPageButtonXpath) {
        List<Double> allOfferPrices = new ArrayList<>();
        int offerCount = 0;
        while (true) {
            try {
                List<WebElement> offerElements = driver.findElements(By.xpath(offerElementXpath));
                offerCount += offerElements.size();
                // Iterate through each offer element to extract prices
                List<Double> pricesOnCurrentPage = extractPrices(offerElements);
                allOfferPrices.addAll(pricesOnCurrentPage);
                // Check if there's a next page
                WebElement nextPageButton = driver.findElement(By.xpath(nextPageButtonXpath));
                if (nextPageButton != null && nextPageButton.isEnabled()) {
                    nextPageButton.click();
                    // Wait for the next page to load
                    wait.until(ExpectedConditions.stalenessOf(offerElements.get(0)));
                }
            } catch (ElementClickInterceptedException e) {
                System.out.println("ElementClickInterceptedException, No more pages.");
                break;
            }
        }
        System.out.println("Total number of offers found: " + offerCount);
        return allOfferPrices;
    }

    public static List<String> verifyAllOffersContainWord(WebDriver driver, WebDriverWait wait, String offersXpath, String nextPageButtonXpath, String word) {
        List<String> offerUrls = new ArrayList<>();
        List<WebElement> currentOffers;
        while (true) {
            try {
                currentOffers = driver.findElements(By.xpath(offersXpath));
                // Extract href values from current offers and add them to list
                for (WebElement offer : currentOffers) {
                    try {
                        String href = offer.getAttribute("href");
                        offerUrls.add(href);
                    } catch (StaleElementReferenceException e) {
                        System.err.println("StaleElementReferenceException occurred while extracting href. Skipping element.");
                    }
                }

                // Verify that all offers contain the specified word
                for (String href : offerUrls) {
                    try {
                        if (!href.contains(word)) {
                            System.out.println("Offer with href " + href + " does not contain the specified word.");
                        }
                    } catch (StaleElementReferenceException e) {
                        System.err.println("StaleElementReferenceException occurred while checking href. Skipping element.");
                    }
                }
                // Check if there's a next page
                WebElement nextPageButton = driver.findElement(By.xpath(nextPageButtonXpath));
                if (nextPageButton != null && nextPageButton.isEnabled()) {
                    nextPageButton.click();
                    // Wait for the next page to load
                    wait.until(ExpectedConditions.stalenessOf(currentOffers.get(0)));
                }
            } catch (ElementClickInterceptedException e) {
                System.out.println("ElementClickInterceptedException, No more pages.");
                break; // Break out of the loop if ElementClickInterceptedException occurs
            }
        }
        return offerUrls;
    }
}
