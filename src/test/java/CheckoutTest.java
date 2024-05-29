import com.aventstack.extentreports.Status;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Listeners(ExtentTestNGITestListener.class)
public class CheckoutTest extends Hooks {

    private CheckoutPage checkoutPage;
    private WebDriverWait wait;

    @BeforeMethod
    public void setupPageObject() {
        checkoutPage = new CheckoutPage(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @Test(description = "Add product to cart and verify the price update with quantity change.")
    public void addProductAndVerifyPriceTest() {
        // Add product to cart
        checkoutPage.clickAddProductGraniteChipsToCartLink();
        checkoutPage.clickAddProductGraniteChipsToCartButton();
        checkoutPage.clickCartButton();

        // Log initial price
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The initial price of the product is: $" + checkoutPage.getProductPrice());

        // Calculate expected total price after changing quantity
        double initialPrice = checkoutPage.getProductPrice();
        double expectedTotalPrice = initialPrice * 2;
        ExtentTestNGITestListener.getTest().log(Status.INFO, "Expected price after quantity update: $" + expectedTotalPrice);

        // Change quantity
        checkoutPage.clickPlusQuantity();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The updated price of the product is: $" + checkoutPage.getProductPrice());

        // Verify price update
        assertEquals(checkoutPage.getProductPrice(), expectedTotalPrice, "The price did not update correctly.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The price of the product matches the expected price after quantity update.");
    }
}
