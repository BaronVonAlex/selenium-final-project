package ge.tbcitacademy.locators;

import org.openqa.selenium.By;

public class HolidayPageLocators {
    public static final By SORT_SELECTOR_LOCATOR = By.id("sort");
    public static final By COTTAGE_CHECKBOX_LOCATOR = By.xpath("//div[@class='category-filter-desk']//label[contains(text(),'კოტეჯი')]/input");
    public static final By FIRST_PAGE_ELEMENT_LOCATOR = By.xpath("//a[@class='pagination__link']/img[@src='/Images/NewDesigneImg/categoryIn/arrows2-04.png']");
    public static final By SUBMIT_BUTTON_LOCATOR = By.xpath("//div[@class='category-filter-desk']//div[@class='submit-button']");
    public static final By MIN_PRICE_INPUT_LOCATOR = By.xpath("//div[@class='category-filter-desk']//input[@name='minprice']");
    public static final By MAX_PRICE_INPUT_LOCATOR = By.xpath("//div[@class='category-filter-desk']//input[@name='maxprice']");
    public static final By OFFER_PRICES_LOCATOR = By.xpath("//div[@class='discounted-prices']/child::p[1]");
    public static final String offerPricesXpathExpression = "//div[@class='discounted-prices']/child::p[1]";
    public static final String nextPageElementXpathExpression = "//a[@class='pagination__link']/img[@src='/Images/NewDesigneImg/categoryIn/arrow-01.png']";
    public static final String cottageXpathExpression = "//div[@class='special-offer-title']//a[contains(@href, 'koteji')]";

}
