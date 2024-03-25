import ge.tbcitacademy.util.ItemListsUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static ge.tbcitacademy.data.Constants.*;

public class MoviePageTests extends TestUtil{
    @Test(priority = 7)
    public void movieTest() throws ElementClickInterceptedException {
        WebElement movieEventButton = driver.findElement(By.xpath("//div[@class='Menus']//a[@href='/events']"));
        movieEventButton.click();
        WebElement firstMovieElement = driver.findElement(By.xpath("//div[@class='movies-deal'][1]/a"));
        // hover mouse over movie to display buy option
        actions.moveToElement(firstMovieElement).build().perform();
        driver.findElement(By.xpath("//div[@class='movies-deal'][1]")).click();

        // find Cavea option and check that only Cavea options are returned.
        WebElement caveaButton = driver.findElement(By.xpath("//div[@class='container choose-seanse']//a[contains(text(),'კავეა ისთ ფოინ')]"));
        executor.executeScript("arguments[0].scrollIntoView({block: 'center'});", caveaButton);
        caveaButton.click();

        // get list of cinemas
        List<WebElement> caveaSessions = driver.findElements(By.xpath("//div[@id='384933']//p[@class='cinema-title']"));
        // filter list into String list
        List<String> cinemaNames = ItemListsUtil.extractText(caveaSessions);
        for (String cinemaName : cinemaNames) {
            Assert.assertEquals(cinemaName, CINEMA_TARGET_NAME);
        }
        // click on last date
        List<WebElement> dateTabs = driver.findElements(By.xpath("//div[@aria-labelledby='ui-id-5']//li"));
        WebElement lastDate = dateTabs.get(dateTabs.size()-1);
        lastDate.click();
        // click on last movie option
        WebElement lastCinema = caveaSessions.get(caveaSessions.size() - 1);
        // scroll into view of last cinema element and click
        executor.executeScript("arguments[0].scrollIntoView({block: 'center'});", lastCinema);
        lastCinema.click();
        // movie date, separate date and time
        String lastDateString = lastDate.getText();
        String[] movieDate =  lastDateString.split(" ");
        // name of the cinema
        String activeCinemaName = lastCinema.getText();
        // movie name before popup -> getText as String.
        WebElement movieName = driver.findElement(By.cssSelector("p.name"));
        String movieNameString = movieName.getText();
        // movie Title on purchase popup
        WebElement movieTitlePopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.movie-title")));
        // cinema name on purchase popup
        WebElement cinemaNamePopup = driver.findElements(By.cssSelector("p.movie-cinema")).get(0);
        // movie date on purchase popup
        WebElement movieDatePopup = driver.findElements(By.cssSelector("p.movie-cinema")).get(1);
        // popup movie date, separate date and time
        String[] movieDateLast =  movieDatePopup.getText().split(" ");
        // do assertion
        Assert.assertEquals(movieDateLast[0], movieDate[0]);
        Assert.assertEquals(cinemaNamePopup.getText(), activeCinemaName);
        Assert.assertEquals(movieTitlePopup.getText(), movieNameString);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='freeze']")));
        WebElement vacantSeat = driver.findElement(By.xpath("//div[@class='seat free']"));
        vacantSeat.click();

        WebElement registerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'შექმენი')]")));
        registerButton.click();

        // register with wrong email
        driver.findElement(By.id("email")).sendKeys(EMAIL);
        // password
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        // retype password
        driver.findElement(By.id("PasswordRetype")).sendKeys(PASSWORD_RETYPE);
        // div "radio" selector
        WebElement chooseGenderMale = driver.findElement(By.id("Gender1"));
        chooseGenderMale.click();
        // name
        driver.findElement(By.id("name")).sendKeys(NAME);
        // lastname
        driver.findElement(By.id("surname")).sendKeys(SURNAME);
        // date of birth
        WebElement birthdayBar = driver.findElement(By.xpath("//span[@class='select2-selection__placeholder']"));
        birthdayBar.click();
        WebElement birthdayYearSelector = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(), '2003')]")));

        birthdayYearSelector.click();
        // phone number
        driver.findElement(By.id("Phone")).sendKeys(PHONE);
        // phone code
        driver.findElement(By.id("PhoneCode")).sendKeys(PHONE_CODE);
        //register button -> scroll into view.
        WebElement registrationBtn = driver.findElement(By.id("registrationBtn"));
        executor.executeScript("arguments[0].scrollIntoView({block: 'center'});", registrationBtn);
        // agree on website rules
        WebElement checkbox1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='test']/following-sibling::span[1]")));
        WebElement checkbox2 = driver.findElement(By.xpath("//input[@id='tbcAgreement']/following-sibling::span[1]"));
        // using Javascript to force click on checkboxes due to some error
        executor.executeScript("arguments[0].click();", checkbox2);
        executor.executeScript("arguments[0].click();", checkbox1);
        executor.executeScript("arguments[0].click();", registrationBtn);
        // error message
        WebElement emailErrorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-error-email")));
        String ErrorMessageText = emailErrorMsg.getText();
        softAssert.assertEquals(ErrorMessageText, MOVIE_ASSERT_EXPECTED_TEXT);
    }
}
