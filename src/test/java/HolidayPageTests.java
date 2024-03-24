import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import java.util.Collections;
import java.util.List;

public class HolidayPageTests extends TestUtil{

    @Test(priority = 1)
    public void descendingOrderTest() throws ElementClickInterceptedException, InterruptedException {
        executor.executeScript("window.scrollTo(0, 0);");
        // Go to 'დასვენება' section.
        landingPage.clickRestCategoryButton();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='freeze']")));
        // sort offers from most expensive to least expensive on the website.
        holidayPage.sortByMostExpensive();
        // get first offer price
        double firstOfferPrice = holidayPage.getFirstOfferPrice();
        // get all offer prices
        List<Double> allOfferPrices = holidayPage.fetchAllOfferPrices();

        System.out.println("Most expensive offer: " + Collections.max(allOfferPrices) + "₾");

        softAssert.assertEquals(firstOfferPrice, Collections.max(allOfferPrices), "Most expensive offer is not displayed in : [descendingOrderTest]");
    }

    @Test(priority = 2)
    public void ascendingOrderTest() {
        executor.executeScript("window.scrollTo(0, 0);");
        // Go to 'დასვენება' section.
        landingPage.clickRestCategoryButton();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='freeze']")));
        // sort offers from most expensive to least expensive on the website.
        holidayPage.sortByMostCheap();
        // get first offer price
        double firstOfferPrice = holidayPage.getFirstOfferPrice();
        // get all offer prices
        List<Double> allOfferPrices = holidayPage.fetchAllOfferPrices();

        System.out.println("Most expensive offer: " + Collections.min(allOfferPrices) + "₾");

        softAssert.assertEquals(firstOfferPrice, Collections.min(allOfferPrices), "Most cheapest offer is not displayed in : [ascendingOrderTest]");
    }

    @Test(priority = 3)
    public void filterTest() {
        // scroll at the top of page to access Dropdown menu
        executor.executeScript("window.scrollTo(0, 0);");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='freeze']")));
        // go to დასვენება
        landingPage.clickRestCategoryButton();
        // select cottages on sidebar
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='freeze']")));
        holidayPage.clickCottageCheckbox();
        // scroll at the top of page to access Dropdown menu
        executor.executeScript("window.scrollTo(0, 0);");
        // for firefox, we need to wait until loading (freeze div) disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='freeze']")));
        //sort offers from most expensive to least expensive.
        holidayPage.sortByMostCheap();

        holidayPage.applyWaitForCottage();
        // get first offer price
        double firstOfferPrice = holidayPage.getFirstOfferPrice();
        // get offers that contain "koteji" with appropriate assertion
        List<String> allOffersContainWord = holidayPage.getAllOffersContainWord();
        // assert that all offers contain word
        for(String string : allOffersContainWord){
            softAssert.assertTrue(string.contains("koteji"), "String does not contain the word 'koteji': " + string + "[filterTest]");
        }
        // start from first page to collect offer prices.
        holidayPage.getToFirstPage();
        // get all offer prices, filter them and turn into Double to perform actions
        List<Double> allOfferPrices = holidayPage.fetchAllOfferPrices();
        System.out.println("Least Expensive offer: " + Collections.min(allOfferPrices) + "₾");
        softAssert.assertEquals(firstOfferPrice, Collections.min(allOfferPrices), "Least expensive offer is not displayed 1st in : [filterTest]");
    }

    @Test(priority = 4)
    public void priceRangeTest() {
        holidayPage.scrollToTop();
        // Click on category rest button
        landingPage.clickRestCategoryButton();
        // scroll into View to select range.
        holidayPage.scrollIntoView();
        // Set price range
        holidayPage.setPriceRange("45", "55");
        // Get offer prices within the range
        List<Double> offerPrices = holidayPage.getOfferPrices();
        // Assert that all offer prices are within the specified range
        for (Double offerPrice : offerPrices) {
            softAssert.assertTrue(offerPrice >= 45 && offerPrice <= 55, "Offer price is not within the expected range: " + offerPrice);
        }
    }
}
