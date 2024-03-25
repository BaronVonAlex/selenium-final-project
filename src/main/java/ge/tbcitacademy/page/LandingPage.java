package ge.tbcitacademy.page;

import ge.tbcitacademy.util.ColorUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static ge.tbcitacademy.locators.LandingPageLocators.*;

public class LandingPage {
    private final WebDriver driver;


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
