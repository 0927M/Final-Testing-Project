import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage {

    public WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 30);
    }

    @FindBy(linkText = "Awesome Granite Chips")
    private WebElement addProductGraniteChipsToCartLink;

    @FindBy(css = ".svg-inline--fa.fa-cart-plus.fa-w-18.fa-3x")
    private WebElement addProductGraniteChipsToCartButton;

    @FindBy(css = ".svg-inline--fa.fa-shopping-cart.fa-w-18")
    private WebElement cartButton;

    @FindBy(xpath = "/html/body/div/div/div[2]/div[2]/div[1]/div/div/div/div[3]/div")
    private WebElement productPriceElement;
    @FindBy(css = ".svg-inline--fa.fa-plus-circle.fa-w-16 ")
    private WebElement plusQuantityButton;

    public void clickAddProductGraniteChipsToCartLink() {
        wait.until(ExpectedConditions.elementToBeClickable(addProductGraniteChipsToCartLink)).click();
    }

    public void clickAddProductGraniteChipsToCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addProductGraniteChipsToCartButton)).click();
    }

    public void clickCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
    }

    public double getProductPrice() {
        wait.until(ExpectedConditions.visibilityOf(productPriceElement));
        System.out.println("Extracted Price: " + productPriceElement.getText());
        return Double.parseDouble(productPriceElement.getText().replace("$", ""));

    }

    public void clickPlusQuantity() {
        wait.until(ExpectedConditions.elementToBeClickable(plusQuantityButton)).click();
    }
}
