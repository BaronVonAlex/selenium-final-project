import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;

public class TestUtil {
    protected WebDriver driver;
    protected static WebDriverWait wait;
    protected static JavascriptExecutor executor;
    protected static SoftAssert softAssert;
    protected static Actions actions;

    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser){
        if (browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addExtensions(new File("./extensions/adblock.crx"));
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            // Set marionette capability
            options.setCapability("marionette", true);

            driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        TestUtil.wait = new WebDriverWait(driver, 5);
        TestUtil.executor = (JavascriptExecutor) driver;
        TestUtil.softAssert = new SoftAssert();
        TestUtil.actions = new Actions(driver);

        driver.get("https://www.swoop.ge/");
        // accepts cookies
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='acceptCookie']"))).click();
    }

    @AfterClass
    public void tearDown(){
        try{
            softAssert.assertAll(getClass().toString());
        }finally {
            driver.close();
        }
    }
}
