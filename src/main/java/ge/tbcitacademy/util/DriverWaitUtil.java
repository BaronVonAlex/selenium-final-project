package ge.tbcitacademy.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverWaitUtil {
    public static void applyWaitCssPath(WebDriver driver, WebDriverWait wait, String cssSelector) {
        if (driver instanceof FirefoxDriver) {
            // Apply Firefox-specific waits
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
        } else {
            // Apply generic waits for other browsers
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(cssSelector))));
        }
    }
    public static void applyWaitXpath(WebDriver driver, WebDriverWait wait, String xpath) {
        if (driver instanceof FirefoxDriver) {
            // Apply Firefox-specific waits
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        } else {
            // Apply generic waits for other browsers
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(xpath))));
        }
    }
}
