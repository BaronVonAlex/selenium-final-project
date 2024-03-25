package ge.tbcitacademy.page;

import org.openqa.selenium.WebDriver;

import static ge.tbcitacademy.locators.HomePageLocators.*;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRestCategoryButton() {
        driver.findElement(CATEGORY_BUTTON_LOCATOR).click();
    }

    public void clickSwoopLogo() {
        driver.findElement(SWOOP_LOGO_LOCATOR).click();
    }

    public boolean isHomePageUrlCorrect() {
        return driver.getCurrentUrl().equals("https://www.swoop.ge/");
    }
}
