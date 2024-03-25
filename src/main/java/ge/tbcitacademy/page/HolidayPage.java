package ge.tbcitacademy.page;

import ge.tbcitacademy.util.DriverWaitUtil;
import ge.tbcitacademy.util.ItemListsUtil;
import static ge.tbcitacademy.data.Constants.*;
import static ge.tbcitacademy.locators.HolidayPageLocators.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HolidayPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor executor;
    // ascendingOrderTest and descendingOrderTest
    public HolidayPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor executor) {
        this.driver = driver;
        this.wait = wait;
        this.executor = executor;
    }

    public void sortByMostExpensive() {
        Select sortSelector = new Select(driver.findElement(SORT_SELECTOR_LOCATOR));
        sortSelector.selectByValue("1");
    }

    public void sortByMostCheap() {
        Select sortSelector = new Select(driver.findElement(SORT_SELECTOR_LOCATOR));
        sortSelector.selectByValue("2");
    }

    public List<Double> fetchAllOfferPrices() {
        return ItemListsUtil.fetchAllOfferPrices(driver, wait, offerPricesXpathExpression, nextPageElementXpathExpression);
    }

    public List<String> getAllOffersContainWord(){
        return ItemListsUtil.verifyAllOffersContainWord(driver, wait, cottageXpathExpression, nextPageElementXpathExpression, word);
    }

    public double getFirstOfferPrice(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='freeze']")));
        WebElement expensiveOfferPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(offerPricesXpathExpression)));
        String expensivePriceText = expensiveOfferPriceElement.getText().replaceAll("[^0-9.]", "");
        return Double.parseDouble(expensivePriceText);
    }
    //filterTest
    public void clickCottageCheckbox(){
        driver.findElement(COTTAGE_CHECKBOX_LOCATOR).click();
    }

    public void applyWaitForCottage(){
        DriverWaitUtil.applyWaitXpath(driver, wait, cottageXpathExpression);
    }

    public void getToFirstPage(){
        driver.findElement(FIRST_PAGE_ELEMENT_LOCATOR).click();
    }

    // priceRangeTest
    public void scrollToTop() {
        executor.executeScript("window.scrollTo(0, 0);");
    }

    public void scrollIntoView() {
        WebElement minPriceInput = driver.findElement(MIN_PRICE_INPUT_LOCATOR);
        executor.executeScript("arguments[0].scrollIntoView({block: 'center'});", minPriceInput);
    }

    public void setPriceRange(String minPrice, String maxPrice) {
        WebElement minPriceInput = wait.until(ExpectedConditions.visibilityOfElementLocated(MIN_PRICE_INPUT_LOCATOR));
        minPriceInput.sendKeys(minPrice);

        WebElement maxPriceInput = wait.until(ExpectedConditions.visibilityOfElementLocated(MAX_PRICE_INPUT_LOCATOR));
        maxPriceInput.sendKeys(maxPrice);

        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BUTTON_LOCATOR));
        submitButton.click();
    }

    public List<Double> getOfferPrices() {
        DriverWaitUtil.applyWaitXpath(driver, wait, offerPricesXpathExpression);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='freeze']")));
        List<WebElement> offerPrices = driver.findElements(OFFER_PRICES_LOCATOR);
        return ItemListsUtil.extractPrices(offerPrices);
    }
}
