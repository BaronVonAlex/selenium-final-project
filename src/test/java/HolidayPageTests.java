import org.openqa.selenium.ElementClickInterceptedException;
import org.testng.annotations.Test;
import java.util.Collections;
import java.util.List;

import static ge.tbcitacademy.data.Constants.*;

public class HolidayPageTests extends TestUtil{

    @Test(priority = 1)
    public void descendingOrderTest() throws ElementClickInterceptedException {
        executor.executeScript("window.scrollTo(0, 0);");
        // Go to 'დასვენება' section.
        landingPage.clickRestCategoryButton();

        moviePage.waitForFreezeDiv();
        // sort offers from most expensive to least expensive on the website.
        holidayPage.sortByMostExpensive();
        // get first offer price
        double firstOfferPrice = holidayPage.getFirstOfferPrice();
        // get all offer prices
        List<Double> allOfferPrices = holidayPage.fetchAllOfferPrices();

        System.out.println(MOST_EXPENSIVE_OFFER_MSG + Collections.max(allOfferPrices) + GEL_CURRENCY_SYMBOL);

        softAssert.assertEquals(firstOfferPrice, Collections.max(allOfferPrices), DESC_SOFT_ASSERT_FAIL_MSG);
    }

    @Test(priority = 2)
    public void ascendingOrderTest() {
        executor.executeScript("window.scrollTo(0, 0);");
        // Go to 'დასვენება' section.
        landingPage.clickRestCategoryButton();

        moviePage.waitForFreezeDiv();
        // sort offers from most expensive to least expensive on the website.
        holidayPage.sortByMostCheap();
        // get first offer price
        double firstOfferPrice = holidayPage.getFirstOfferPrice();
        // get all offer prices
        List<Double> allOfferPrices = holidayPage.fetchAllOfferPrices();

        System.out.println(LEAST_EXPENSIVE_OFFER_MSG + Collections.min(allOfferPrices) + GEL_CURRENCY_SYMBOL);

        softAssert.assertEquals(firstOfferPrice, Collections.min(allOfferPrices), ASC_SOFT_ASSERT_FAIL_MSG);
    }

    @Test(priority = 3)
    public void filterTest() {
        // scroll at the top of page to access Dropdown menu
        executor.executeScript("window.scrollTo(0, 0);");

        moviePage.waitForFreezeDiv();
        // go to დასვენება
        landingPage.clickRestCategoryButton();
        // select cottages on sidebar
        moviePage.waitForFreezeDiv();
        holidayPage.clickCottageCheckbox();
        // scroll at the top of page to access Dropdown menu
        executor.executeScript("window.scrollTo(0, 0);");
        // for firefox, we need to wait until loading (freeze div) disappears
        moviePage.waitForFreezeDiv();
        //sort offers from most expensive to least expensive.
        holidayPage.sortByMostCheap();

        holidayPage.applyWaitForCottage();
        // get first offer price
        double firstOfferPrice = holidayPage.getFirstOfferPrice();
        // get offers that contain "koteji" with appropriate assertion
        List<String> allOffersContainWord = holidayPage.getAllOffersContainWord();
        // assert that all offers contain word
        for(String string : allOffersContainWord){
            softAssert.assertTrue(string.contains(word), FILTER_TEST_ASSERT_FAIL_MSG + string + FILTER_TEST_NAME);
        }
        // start from first page to collect offer prices.
        holidayPage.getToFirstPage();
        // get all offer prices, filter them and turn into Double to perform actions
        List<Double> allOfferPrices = holidayPage.fetchAllOfferPrices();
        System.out.println(LEAST_EXPENSIVE_OFFER_MSG + Collections.min(allOfferPrices) + GEL_CURRENCY_SYMBOL);
        softAssert.assertEquals(firstOfferPrice, Collections.min(allOfferPrices), ASC_SOFT_ASSERT_FAIL_MSG);
    }

    @Test(priority = 4)
    public void priceRangeTest() {
        holidayPage.scrollToTop();
        // Click on category rest button
        landingPage.clickRestCategoryButton();
        // scroll into View to select range.
        holidayPage.scrollIntoView();
        // Set price range
        holidayPage.setPriceRange(PRICE_RANGE_1, PRICE_RANGE_2);
        // Get offer prices within the range
        List<Double> offerPrices = holidayPage.getOfferPrices();
        // Assert that all offer prices are within the specified range
        for (Double offerPrice : offerPrices) {
            softAssert.assertTrue(offerPrice >= 45 && offerPrice <= 55, PRICE_RANGE_ASSERT_FAIL_MSG + offerPrice);
        }
    }
}
