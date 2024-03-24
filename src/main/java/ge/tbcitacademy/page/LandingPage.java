package ge.tbcitacademy.page;

import ge.tbcitacademy.util.ColorUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LandingPage {
    private final WebDriver driver;

    // Sport category dropdown locator
    private final By sportDropdownLocator = By.xpath("//li[@cat_id='CatId-6']/child::a[contains(text(),'სპორტი')]");

    // Kart racing button locator
    private final By kartRacingButtonLocator = By.xpath("//li[@cat_id='CatId-6']//a[contains(text(),'კარტინგი')]");

    // Kart racing element locator
    private final By kartRacingElementLocator = By.xpath("//div[@class='category-filter-desk']//span[contains(text(),'კარტინგი')]");
    private final By restCategoryButtonLocator = By.xpath("//div[@class='Menus']//a[@href='/category/24/dasveneba']");

    // Constructor
    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void hoverOverSportCategory() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(sportDropdownLocator)).build().perform();
    }

    public void clickKartRacingButton() {
        driver.findElement(kartRacingButtonLocator).click();
    }

    public boolean isKartRacingPageUrlCorrect() {
        return driver.getCurrentUrl().equals("https://www.swoop.ge/category/2058/sporti/kartingi");
    }

    public String getKartElementColor() {
        WebElement kartRacingElement = driver.findElement(kartRacingElementLocator);
        String kartElementColor = kartRacingElement.getCssValue("color");
        return ColorUtil.rgbToHex(kartElementColor);
    }
    public void clickRestCategoryButton() {
        driver.findElement(restCategoryButtonLocator).click();
    }
}
