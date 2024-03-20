import ge.tbcitacademy.util.offerListUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HolidayPageTests extends TestUtil{

    @Test
    public void descendingOrderTest() {
        driver.get("https://www.swoop.ge/");

        // Go to 'დასვენება' section.
        WebElement categoryRestButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Menus']//a[@href='/category/24/dasveneba']")));
        categoryRestButton.click();

        // sort offers from most expensive to least expensive.
        Select sortSelector = new Select(driver.findElement(By.id("sort")));
        sortSelector.getOptions();
        sortSelector.selectByValue("1");

        String offerPricesXpathExpression = "//div[@class='discounted-prices']/child::p[1]"; // can be sent in Constants

        if (driver instanceof FirefoxDriver) {
            // Apply Firefox-specific waits
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(offerPricesXpathExpression)));
        } else {
            // Apply generic waits for other browsers
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(offerPricesXpathExpression))));
        }
        // get all offer prices, filter them and turn into Double to perform actions
        List<WebElement> offerPrices = driver.findElements(By.xpath(offerPricesXpathExpression));
        List<Double> offerFilteredPrices = offerListUtil.extractPrices(offerPrices);
        // find most expensive item and get price, turn into double, so we can compare to our collections most expensive item
        WebElement expensiveOffer = driver.findElement(By.xpath("//section[@class='container deal-container category-offers ']/child::div[1]"));
        WebElement expensiveOfferPrice = expensiveOffer.findElement(By.xpath(offerPricesXpathExpression));

        Double expensivePrice = Double.parseDouble(expensiveOfferPrice.getText().replaceAll("[^0-9.]", ""));
        Assert.assertEquals(expensivePrice, Collections.max(offerFilteredPrices));
        System.out.println("Most expensive offer: " + Collections.max(offerFilteredPrices) + "₾");
    }

    @Test
    public void ascendingOrderTest() {
        driver.get("https://www.swoop.ge/");
        // Go to 'დასვენება' section.
        WebElement categoryRestButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Menus']//a[@href='/category/24/dasveneba']")));
        categoryRestButton.click();
        // sort offers from most expensive to least expensive.
        Select sortSelector = new Select(driver.findElement(By.id("sort")));
        sortSelector.getOptions();
        sortSelector.selectByValue("2");

        String offerPricesXpathExpression = "//div[@class='discounted-prices']/child::p[1]"; // can be sent in Constants

        if (driver instanceof FirefoxDriver) {
            // Apply Firefox-specific waits
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(offerPricesXpathExpression)));
        } else {
            // Apply generic waits for other browsers
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(offerPricesXpathExpression))));
        }
        // get all offer prices, filter them and turn into Double to perform actions
        List<WebElement> offerPrices = driver.findElements(By.xpath(offerPricesXpathExpression));
        List<Double> offerFilteredPrices = offerListUtil.extractPrices(offerPrices);
        // find most expensive item and get price, turn into double, so we can compare to our collections most expensive item
        WebElement cheapestOffer = driver.findElement(By.xpath("//section[@class='container deal-container category-offers ']/child::div[1]"));
        WebElement cheapestOfferPrice = cheapestOffer.findElement(By.xpath(offerPricesXpathExpression));

        Double cheapPrice = Double.parseDouble(cheapestOfferPrice.getText().replaceAll("[^0-9.]", ""));
        Assert.assertEquals(cheapPrice, Collections.min(offerFilteredPrices));
        System.out.println("Least expensive offer: " + Collections.min(offerFilteredPrices) + "₾");
    }

    @Test
    public void filterTest(){
        driver.get("https://www.swoop.ge/");
        WebElement categoryRestButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Menus']//a[@href='/category/24/dasveneba']")));
        categoryRestButton.click();

        WebElement cottageCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='category-filter-desk']//label[contains(text(),'კოტეჯი')]/input")));
        cottageCheckbox.click();

        String cottageCss = "div.special-offer-title a[href*=koteji]:last-child";

        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(cottageCss))));

        List<WebElement> offersWithCottage = driver.findElements(By.cssSelector(cottageCss));
        for (WebElement offer : offersWithCottage){
            String offerAttribute = offer.getAttribute("href");
            if(offerAttribute.contains("koteji")){
                return;
            }else {
                Assert.fail("All offers do not include target word" + offerAttribute);
            }
        }
        // sort offers from most expensive to least expensive.
        Select sortSelector = new Select(driver.findElement(By.id("sort")));
        sortSelector.getOptions();
        sortSelector.selectByValue("2");

        String offerPricesXpathExpression = "//div[@class='discounted-prices']/child::p[1]";
        // get all offer prices, filter them and turn into Double to perform actions
        List<WebElement> offerPrices = driver.findElements(By.xpath(offerPricesXpathExpression));
        List<Double> offerFilteredPrices = offerListUtil.extractPrices(offerPrices);
        // find most expensive item and get price, turn into double, so we can compare to our collections most expensive item
        WebElement cheapestOffer = driver.findElement(By.xpath("//section[@class='container deal-container category-offers ']/child::div[1]"));
        WebElement cheapestOfferPrice = cheapestOffer.findElement(By.xpath(offerPricesXpathExpression));
        // Validate that the least expensive offer is displayed first in the list.
        Double cheapPrice = Double.parseDouble(cheapestOfferPrice.getText().replaceAll("[^0-9.]", ""));
        Assert.assertEquals(cheapPrice, Collections.min(offerFilteredPrices));
        System.out.println("Least expensive offer: " + Collections.min(offerFilteredPrices) + "₾");
    }
}
