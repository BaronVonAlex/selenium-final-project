package ge.tbcitacademy.page;

import org.openqa.selenium.By;

public class MoviePage {
    public static final By MOVIE_EVENT_LOCATOR = By.xpath("//div[@class='Menus']//a[@href='/events']");
    public static final By FIRST_MOVIE_LOCATOR = By.xpath("//div[@class='movies-deal'][1]/a");
    public static final By BUY_MOVIE_LOCATOR = By.xpath("//div[@class='movies-deal'][1]");
    public static final By CAVEA_BUTTON_LOCATOR = By.xpath("//div[@class='container choose-seanse']//a[contains(text(),'კავეა ისთ ფოინ')]");
    public static final By CAVEA_SESSIONS_LOCATOR = By.xpath("//div[@id='384933']//p[@class='cinema-title']");
    public static final By DATE_TABS_LOCATOR = By.xpath("//div[@aria-labelledby='ui-id-5']//li");
    public static final By MOVIE_NAME_LOCATOR = By.cssSelector("p.name");
    public static final By MOVIE_TITLE_POPUP_LOCATOR = By.cssSelector("p.movie-title");
    public static final By CINEMA_NAME_POPUP_LOCATOR = By.cssSelector("p.movie-title");
    public static final By MOVIE_DATE_POPUP_LOCATOR = By.cssSelector("p.movie-title");
    public static final By VACANT_SEAT_LOCATOR = By.xpath("//div[@class='seat free']");
    public static final By REGISTER_BUTTON_LOCATOR = By.xpath("//a[contains(text(),'შექმენი')]");

    // Email input locator
    public static final By EMAIL_INPUT = By.id("email");

    // Password input locator
    public static final By PASSWORD_INPUT = By.id("password");

    // Retype Password input locator
    public static final By PASSWORD_RETYPE_INPUT = By.id("PasswordRetype");

    // Gender Male radio button locator
    public static final By GENDER_MALE_RADIO = By.id("Gender1");

    // Name input locator
    public static final By NAME_INPUT = By.id("name");

    // Surname input locator
    public static final By SURNAME_INPUT = By.id("surname");

    // Birthday dropdown locator
    public static final By BIRTHDAY_DROPDOWN = By.xpath("//span[@class='select2-selection__placeholder']");

    // Year in Birthday dropdown locator
    public static final By BIRTHDAY_YEAR_OPTION = By.xpath("//li[contains(text(), '2003')]");

    // Phone number input locator
    public static final By PHONE_NUMBER_INPUT = By.id("Phone");

    // Phone code input locator
    public static final By PHONE_CODE_INPUT = By.id("PhoneCode");

    // Registration button locator
    public static final By REGISTRATION_BUTTON = By.id("registrationBtn");

    // Checkbox 1 locator
    public static final By CHECKBOX_1 = By.xpath("//input[@id='test']/following-sibling::span[1]");

    // Checkbox 2 locator
    public static final By CHECKBOX_2 = By.xpath("//input[@id='tbcAgreement']/following-sibling::span[1]");

    // Email error message locator
    public static final By EMAIL_ERROR_MESSAGE = By.id("input-error-email");
}
