import ge.tbcitacademy.page.MoviePage;
import ge.tbcitacademy.util.ItemListsUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static ge.tbcitacademy.data.Constants.*;
import static org.bouncycastle.cms.RecipientId.*;

public class MoviePageTests extends TestUtil{
    @Test(priority = 7)
    public void movieTest() throws ElementClickInterceptedException {
        // Go to 'კინო'
        moviePage.navigateToMovieEvents();
        // Select the first movie in the returned list and click on ‘ყიდვა’ button
        moviePage.selectFirstMovie();
        // scroll to Cavea
        moviePage.chooseCaveaSession();
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
        //click on any vacant seat
        moviePage.clickOnVacantSeat();
        // go to registration tab
        moviePage.clickOnRegisterButton();
        // register but with wrong EMAIL
        moviePage.registerWithWrongEmail(EMAIL, PASSWORD, PASSWORD_RETYPE, NAME, SURNAME, PHONE, PHONE_CODE);
        // error message
        String ErrorMessageText = moviePage.getEmailErrorTest();
        softAssert.assertEquals(ErrorMessageText, MOVIE_ASSERT_EXPECTED_TEXT);
    }
}
