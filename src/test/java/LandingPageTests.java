import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import ge.tbcitacademy.util.ColorUtil;

public class LandingPageTests extends TestUtil{
    @Test
    public void activeCategoryTest() {
        driver.get("https://www.swoop.ge/");

        WebElement categoryDDButton = driver.findElement(By.xpath("//p[@class='categoriesTitle']"));
        // click on category button
        categoryDDButton.click();

        WebElement sportDropDownButton = driver.findElement(By.xpath("//li[@cat_id='CatId-6']/child::a[contains(text(),'სპორტი')]"));
        // hover over spots drop down button
        actions.moveToElement(sportDropDownButton).build().perform();

        WebElement kartRacingButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@cat_id='CatId-6']//a[contains(text(),'კარტინგი')]")));
        // click on kart racing button
        kartRacingButton.click();

        WebElement kartRacingElement = driver.findElement(By.xpath("//div[@class='category-filter-desk']//span[contains(text(),'კარტინგი')]"));

        // validate that page url matches to desired url
        softAssert.assertEquals(driver.getCurrentUrl(),"https://www.swoop.ge/category/2058/sporti/kartingi");
        // get kart element color in RGB
        String kartElementColor = kartRacingElement.getCssValue("color");
        // turn RBG color code into HEX code
        String elementColorHEX = ColorUtil.rgbToHex(kartElementColor);
        // validate that color matches to desired HEX Color
        softAssert.assertEquals(elementColorHEX, "#6E7CFA");
        softAssert.assertAll();
    }

    @Test
    public void logoTest() {
        driver.get("https://www.swoop.ge/");
        // Go to 'დასვენება' section.
        WebElement categoryRestButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Menus']//a[@href='/category/24/dasveneba']")));
        categoryRestButton.click();

        WebElement swoopLogo = driver.findElement(By.xpath("//a[@class='Newlogo']"));
        swoopLogo.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.swoop.ge/");
    }
}
