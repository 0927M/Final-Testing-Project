import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    public WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 30);
    }

    // Locators
    @FindBy(xpath = "/html/body/div[1]/div/div[1]/nav/div/div[2]/span/span/span/button")
    private WebElement toggleLoginButton;

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

//    @FindBy(css = ".svg-inline--fa.fa-sign-in-alt.fa-w-16")
    @FindBy(xpath = "/html/body/div[3]/div/div/div[2]/div/form/button")
    private WebElement loginButton;

    @FindBy(css = ".error[data-test='error']")
    private WebElement errorMessage;

    // Methods
    public void clickToggleLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(toggleLoginButton)).click();
    }

    public void enterUsername(String username) {
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameElement.clear();
        usernameElement.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }
}
