import org.testng.Assert;
import org.testng.annotations.Test;

public class LandingPageTests extends TestUtil{
    @Test(priority = 5)
    public void activeCategoryTest() {
        homePage.clickRestCategoryButton();
        // Hover on 'კატეგორიები', choose 'სპორტი'->'კარტინგი' from the dropdown.
        landingPage.hoverOverSportCategory();
        landingPage.clickKartRacingButton();
        // validate URL
        softAssert.assertTrue(landingPage.isKartRacingPageUrlCorrect());
        softAssert.assertEquals(landingPage.getKartElementColor(), "#6E7CFA");
    }

    @Test(priority = 6)
    public void logoTest() {
        // Go to 'დასვენება' section.
        landingPage.clickRestCategoryButton();
        // Click on Swoop logo
        homePage.clickSwoopLogo();
        // Validate that the logo takes the user back to landingPage
        softAssert.assertTrue(homePage.isHomePageUrlCorrect());
    }
}
