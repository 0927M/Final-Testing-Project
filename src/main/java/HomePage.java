import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage extends BasePage {

    public WebDriverWait wait;

    public HomePage(WebDriver driver) {

        super(driver);
        wait = new WebDriverWait(driver, 30);
    }
    public void selectOption(WebElement element, String option) {
        Select optionSelect = new Select(element);
        optionSelect.selectByVisibleText(option);
    }
    //place for locators and methods
}

