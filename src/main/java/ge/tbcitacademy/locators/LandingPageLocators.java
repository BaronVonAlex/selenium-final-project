package ge.tbcitacademy.locators;

import org.openqa.selenium.By;

public class LandingPageLocators {
    public static final By sportDropdownLocator = By.xpath("//li[@cat_id='CatId-6']/child::a[contains(text(),'სპორტი')]");

    // Kart racing button locator
    public static final By kartRacingButtonLocator = By.xpath("//li[@cat_id='CatId-6']//a[contains(text(),'კარტინგი')]");

    // Kart racing element locator
    public static final By kartRacingElementLocator = By.xpath("//div[@class='category-filter-desk']//span[contains(text(),'კარტინგი')]");
    public static final By restCategoryButtonLocator = By.xpath("//div[@class='Menus']//a[@href='/category/24/dasveneba']");
}
