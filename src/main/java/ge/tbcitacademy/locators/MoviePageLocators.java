package ge.tbcitacademy.locators;

import org.openqa.selenium.By;

public class MoviePageLocators {
    public static final By MOVIE_EVENT_LOCATOR = By.xpath("//div[@class='Menus']//a[@href='/events']");
    public static final By FIRST_MOVIE_LOCATOR = By.xpath("//div[@class='movies-deal'][1]/a");
    public static final By BUY_MOVIE_LOCATOR = By.xpath("//div[@class='movies-deal'][1]");
    public static final By CAVEA_BUTTON_LOCATOR = By.xpath("//div[@class='container choose-seanse']//a[contains(text(),'კავეა ისთ ფოინ')]");
    public static final By VACANT_SEAT_LOCATOR = By.xpath("//div[@class='seat free']");
    public static final By REGISTER_BUTTON_LOCATOR = By.xpath("//a[contains(text(),'შექმენი')]");
    public static final By EMAIL_INPUT = By.id("email");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By PASSWORD_RETYPE_INPUT = By.id("PasswordRetype");
    public static final By GENDER_MALE_RADIO = By.id("Gender1");
    public static final By NAME_INPUT = By.id("name");
    public static final By SURNAME_INPUT = By.id("surname");
    public static final By BIRTHDAY_DROPDOWN = By.xpath("//span[@class='select2-selection__placeholder']");
    public static final By BIRTHDAY_YEAR_OPTION = By.xpath("//li[contains(text(), '2003')]");
    public static final By PHONE_NUMBER_INPUT = By.id("Phone");
    public static final By PHONE_CODE_INPUT = By.id("PhoneCode");
    public static final By REGISTRATION_BUTTON = By.id("registrationBtn");
    public static final By CHECKBOX_1 = By.xpath("//input[@id='test']/following-sibling::span[1]");
    public static final By CHECKBOX_2 = By.xpath("//input[@id='tbcAgreement']/following-sibling::span[1]");
    public static final By EMAIL_ERROR_MESSAGE = By.id("input-error-email");
}
