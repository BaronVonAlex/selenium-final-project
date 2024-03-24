import ge.tbcitacademy.util.ItemListsUtil;
import ge.tbcitacademy.util.DriverWaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class HolidayPageTests extends TestUtil{

    @Test(priority = 3)
    public void descendingOrderTest() throws ElementClickInterceptedException {
        executor.executeScript("window.scrollTo(0, 0);");
        // Go to 'დასვენება' section.
        WebElement categoryRestButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Menus']//a[@href='/category/24/dasveneba']")));
        categoryRestButton.click();
        // sort offers from most expensive to least expensive on the website.
        Select sortSelector = new Select(driver.findElement(By.id("sort")));
        sortSelector.getOptions();
        sortSelector.selectByValue("1");

        String offerPricesXpathExpression = "//div[@class='discounted-prices']/child::p[1]"; // can be sent in Constants
        // wait until all elements are updated after applying filter
        DriverWaitUtil.applyWaitXpath(driver, wait, offerPricesXpathExpression);
        // find most expensive item and get price and turn into String for Assert.
        WebElement expensiveOfferPrice = driver.findElement(By.xpath(offerPricesXpathExpression));
        String expensivePriceText = expensiveOfferPrice.getText().replaceAll("[^0-9.]", "");
        Double expensivePriceDouble = Double.parseDouble(expensivePriceText);

        String nextPageElementXpathExpression = "//a[@class='pagination__link']/img[@src='/Images/NewDesigneImg/categoryIn/arrow-01.png']";

        List<Double> allOfferPrices = ItemListsUtil.fetchAllOfferPrices(driver, wait, offerPricesXpathExpression, nextPageElementXpathExpression);
        // Print or process the most expensive offer
        System.out.println("Most expensive offer: " + Collections.max(allOfferPrices) + "₾");
        softAssert.assertEquals(expensivePriceDouble, Collections.max(allOfferPrices), "Most expensive offer is not displayed in : [descendingOrderTest]");
    }

    @Test(priority = 2)
    public void ascendingOrderTest() {
        // scroll at the top of page to access Dropdown menu
        executor.executeScript("window.scrollTo(0, 0);");
        // Go to 'დასვენება' section.
        WebElement categoryRestButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Menus']//a[@href='/category/24/dasveneba']")));
        categoryRestButton.click();
        // sort offers from most expensive to least expensive on the website.
        Select sortSelector = new Select(driver.findElement(By.id("sort")));
        sortSelector.getOptions();
        sortSelector.selectByValue("2");

        String offerPricesXpathExpression = "//div[@class='discounted-prices']/child::p[1]"; // can be sent in Constants
        // wait until all elements are updated after applying filter
        DriverWaitUtil.applyWaitXpath(driver, wait, offerPricesXpathExpression);
        // find most expensive item and get price and turn into String for Assert.
        WebElement cheapOfferPrice = driver.findElement(By.xpath(offerPricesXpathExpression));
        String cheapPrice = cheapOfferPrice.getText().replaceAll("[^0-9.]", "");
        Double cheapPriceDouble = Double.parseDouble(cheapPrice);

        String nextPageElementXpathExpression = "//a[@class='pagination__link']/img[@src='/Images/NewDesigneImg/categoryIn/arrow-01.png']";
        // get all offer prices, filter them and turn into Double to perform actions
        List<Double> allOfferPrices = ItemListsUtil.fetchAllOfferPrices(driver,wait,offerPricesXpathExpression, nextPageElementXpathExpression);
        System.out.println("Least Expensive offer: " + Collections.min(allOfferPrices) + "₾");
        softAssert.assertEquals(cheapPriceDouble, Collections.min(allOfferPrices), "Least expensive offer is not displayed 1st in : [ascendingOrderTest]");
    }

    @Test(priority = 3)
    public void filterTest() {
        // scroll at the top of page to access Dropdown menu
        executor.executeScript("window.scrollTo(0, 0);");
        WebElement categoryRestButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Menus']//a[@href='/category/24/dasveneba']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='freeze']")));
        categoryRestButton.click();

        WebElement cottageCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='category-filter-desk']//label[contains(text(),'კოტეჯი')]/input")));
        cottageCheckbox.click();
        // xpath expressions for my methods
        String cottageXpath = "//div[@class='special-offer-title']//a[contains(@href, 'koteji')]";
        String word = "koteji";
        // scroll at the top of page to access Dropdown menu
        executor.executeScript("window.scrollTop;");
        // for firefox, we need to wait until loading (freeze div) disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='freeze']")));
        //sort offers from most expensive to least expensive.
        Select sortSelector = new Select(driver.findElement(By.id("sort")));
        sortSelector.getOptions();
        sortSelector.selectByValue("2");

        String offerPricesXpathExpression = "//div[@class='discounted-prices']/child::p[1]";
        DriverWaitUtil.applyWaitXpath(driver, wait, cottageXpath);
        String updatedOfferXpath = "//div[@class='discounted-prices']/child::p[1]";
        // get all offer prices, filter them and turn into Double to perform actions
        WebElement cheapOfferPrice = driver.findElement(By.xpath(updatedOfferXpath));
        String cheapPrice = cheapOfferPrice.getText().replaceAll("[^0-9.]", "");
        Double cheapPriceDouble = Double.parseDouble(cheapPrice);

        String nextPageElementXpathExpression = "//a[@class='pagination__link']/img[@src='/Images/NewDesigneImg/categoryIn/arrow-01.png']";

        // get offers that contain "koteji" with appropriate assertion
        List<String> allOffersContainWord = ItemListsUtil.verifyAllOffersContainWord(driver, wait, cottageXpath, nextPageElementXpathExpression, word);
        for(String string : allOffersContainWord){
            softAssert.assertTrue(string.contains("koteji"), "String does not contain the word 'koteji': " + string + "[filterTest]");
        }
        // start from first page to collect offer prices.
        WebElement firstPage = driver.findElement(By.xpath("//a[@class='pagination__link']/img[@src='/Images/NewDesigneImg/categoryIn/arrows2-04.png']"));
        firstPage.click();
        // get all offer prices, filter them and turn into Double to perform actions
        List<Double> allOfferPrices = ItemListsUtil.fetchAllOfferPrices(driver,wait,offerPricesXpathExpression, nextPageElementXpathExpression);
        System.out.println("Least Expensive offer: " + Collections.min(allOfferPrices) + "₾");
        softAssert.assertEquals(cheapPriceDouble, Collections.min(allOfferPrices), "Least expensive offer is not displayed 1st in : [filterTest]");
    }

    @Test(priority = 4)
    public void priceRangeTest() {
        // scroll at the top of page to access Dropdown menu
        executor.executeScript("window.scrollTo(0, 0);");
        WebElement categoryRestButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Menus']//a[@href='/category/24/dasveneba']")));

        executor.executeScript("arguments[0].click();", categoryRestButton);

        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='category-filter-desk']//div[@class='submit-button']")));

        executor.executeScript("arguments[0].scrollIntoView({block: 'center'});", submitButton);

        WebElement minPriceInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='category-filter-desk']//input[@name='minprice']")));
        minPriceInput.sendKeys("45");

        WebElement maxPriceInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='category-filter-desk']//input[@name='maxprice']")));
        maxPriceInput.sendKeys("55");

        WebElement submitButtonUpdated = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='category-filter-desk']//div[@class='submit-button']")));
        submitButtonUpdated.click();

        String offerPricesXpathExpression = "//div[@class='discounted-prices']/child::p[1]";

        DriverWaitUtil.applyWaitXpath(driver, wait, offerPricesXpathExpression);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='freeze']")));
        List<WebElement> offerPrices = driver.findElements(By.xpath(offerPricesXpathExpression));
        List<Double> offerFilteredPrices = ItemListsUtil.extractPrices(offerPrices);
        for(Double offer : offerFilteredPrices){
            softAssert.assertTrue(offer >= 45 && offer <= 55, "target prices are not within range : [priceRangeTest]");
        }
    }
}
