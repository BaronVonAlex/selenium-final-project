package ge.tbcitacademy.page;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ge.tbcitacademy.data.Constants.*;
import static ge.tbcitacademy.locators.HomePageLocators.*;
import static ge.tbcitacademy.locators.MoviePageLocators.*;

public class MoviePage {

    private final WebDriver driver;
    private final JavascriptExecutor executor;
    private final Actions actions;
    private final WebDriverWait wait;

    public MoviePage(WebDriver driver, Actions actions, JavascriptExecutor executor, WebDriverWait wait) {
        this.driver = driver;
        this.executor = executor;
        this.actions = actions;
        this.wait = wait;
    }
    // Go to 'კინო'
    public void navigateToMovieEvents() {
        driver.findElement(MOVIE_EVENT_LOCATOR).click();
    }
    // Select the first movie in the returned list and click on ‘ყიდვა’ button
    public void selectFirstMovie() {
        WebElement firstMovieElement = driver.findElement(FIRST_MOVIE_LOCATOR);
        actions.moveToElement(firstMovieElement).build().perform();
        driver.findElement(BUY_MOVIE_LOCATOR).click();
    }
    // scroll to Cavea
    public void chooseCaveaSession() {
        WebElement caveaButton = driver.findElement(CAVEA_BUTTON_LOCATOR);
        executor.executeScript(JS_SCROLL_MIDDLE, caveaButton);
        caveaButton.click();
    }

    public void clickOnVacantSeat(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='freeze']")));
        driver.findElement(VACANT_SEAT_LOCATOR).click();
    }
    public void clickOnRegisterButton(){
        driver.findElement(REGISTER_BUTTON_LOCATOR).click();
    }

    public void registerWithWrongEmail(String email, String password, String retypedPassword, String name, String surname, String phone, String phoneCode) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(PASSWORD_RETYPE_INPUT).sendKeys(retypedPassword);

        WebElement chooseGenderMale = driver.findElement(GENDER_MALE_RADIO);
        chooseGenderMale.click();

        driver.findElement(NAME_INPUT).sendKeys(name);
        driver.findElement(SURNAME_INPUT).sendKeys(surname);

        WebElement birthdayBar = driver.findElement(BIRTHDAY_DROPDOWN);
        birthdayBar.click();
        WebElement birthdayYearSelector = wait.until(ExpectedConditions.visibilityOfElementLocated(BIRTHDAY_YEAR_OPTION));
        birthdayYearSelector.click();

        driver.findElement(PHONE_NUMBER_INPUT).sendKeys(phone);
        driver.findElement(PHONE_CODE_INPUT).sendKeys(phoneCode);

        WebElement registrationBtn = driver.findElement(REGISTRATION_BUTTON);
        executor.executeScript(JS_SCROLL_MIDDLE, registrationBtn);

        WebElement checkbox1 = wait.until(ExpectedConditions.elementToBeClickable(CHECKBOX_1));
        WebElement checkbox2 = driver.findElement(CHECKBOX_2);

        executor.executeScript(JS_CLICK, checkbox2);
        executor.executeScript(JS_CLICK, checkbox1);
        executor.executeScript(JS_CLICK, registrationBtn);
    }
    public String getEmailErrorTest(){
        WebElement emailErrorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_ERROR_MESSAGE));
        return emailErrorMsg.getText();
    }

    public void waitForFreezeDiv(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(FREEZE_DIV_WAIT));
    }
}
