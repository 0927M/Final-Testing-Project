import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Listeners(ExtentTestNGITestListener.class)
public class LoginTest extends Hooks {

    public LoginPage loginPage;
    public WebDriverWait wait;

    @BeforeMethod
    public void SetupPageObject() {
        loginPage = new LoginPage(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @Test(description = "Valid login test with correct username and password.")
    public void validLoginTest() {
        // Click the toggle button to reveal login form
        loginPage.clickToggleLoginButton();

        // Perform login
        loginPage.enterUsername("dino");
        loginPage.enterPassword("choochoo");
        loginPage.clickLoginButton();

        // Verify successful login by checking if redirected to the correct page or checking for a specific element
        // This is a placeholder assertion; replace with actual validation logic
        String expectedUrl = "https://fasttrackit-test.netlify.app/#/products";
        assertEquals(driver.getCurrentUrl(), expectedUrl, "User was not redirected to the dashboard after login.");
    }

    @Test(description = "Invalid login test with incorrect username.")
    public void invalidLoginTestInvalidUsername() {
        // Click the toggle button to reveal login form
        loginPage.clickToggleLoginButton();

        // Perform login with invalid username
        loginPage.enterUsername("invalidUsername");
        loginPage.enterPassword("choochoo");
        loginPage.clickLoginButton();

        // Verify error message
        String expectedErrorMessage = "Incorrect username or password!";
        assertEquals(loginPage.getErrorMessage(), expectedErrorMessage, "The error message did not match.");
    }

    @Test(description = "Invalid login test with incorrect password.")
    public void invalidLoginTestInvalidPassword() {
        // Click the toggle button to reveal login form
        loginPage.clickToggleLoginButton();

        // Perform login with invalid password
        loginPage.enterUsername("dino");
        loginPage.enterPassword("invalidPassword");
        loginPage.clickLoginButton();

        // Verify error message
        String expectedErrorMessage = "Incorrect username or password!";
        assertEquals(loginPage.getErrorMessage(), expectedErrorMessage, "The error message did not match.");
    }

    @Test(description = "Invalid login test with empty credentials.")
    public void invalidLoginTestEmptyCredentials() {
        // Click the toggle button to reveal login form
        loginPage.clickToggleLoginButton();

        // Perform login with empty credentials
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();

        // Verify error message
        String expectedErrorMessage = "Please fill in the username!";
        assertEquals(loginPage.getErrorMessage(), expectedErrorMessage, "The error message did not match.");
    }
}
