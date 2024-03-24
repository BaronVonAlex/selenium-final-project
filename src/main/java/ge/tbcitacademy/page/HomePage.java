package ge.tbcitacademy.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;
    private final By CATEGORY_BUTTON_LOCATOR  = By.xpath("//p[@class='categoriesTitle']");
    private final By SWOOP_LOGO_LOCATOR = By.xpath("//a[@class='Newlogo']");

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
