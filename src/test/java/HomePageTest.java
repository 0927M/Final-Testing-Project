

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentTestNGITestListener.class)

public class HomePageTest extends Hooks {

    public HomePage homePage;
    public WebDriverWait wait;


    @BeforeMethod
    public void SetupPageObject() {
        homePage = new HomePage(driver);
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void homePageTest() throws InterruptedException {

    }


}
