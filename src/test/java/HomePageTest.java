import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@Listeners(ExtentTestNGITestListener.class)
public class HomePageTest extends Hooks {

    public HomePage homePage;
    public WebDriverWait wait;

    @BeforeMethod
    public void SetupPageObject() {
        homePage = new HomePage(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @Test(description = "Test to verify that product names can be retrieved from the homepage.")
    public void getProductNamesTest() {
        List<String> productNames = homePage.getProductNames();

        assertFalse(productNames.isEmpty(), "The product names list should not be empty.");

        for (String name : productNames) {
            assertNotNull(name, "Product name should not be null.");
            System.out.println(name);
        }

        // List of expected product names
        List<String> expectedProductNames = List.of(
                "Awesome Granite Chips",
                "Awesome Metal Chair",
                "Awesome Soft Shirt",
                "Gorgeous Soft Pizza",
                "Incredible Concrete Hat",
                "Licensed Steel Gloves",
                "Practical Metal Mouse",
                "Practical Wooden Bacon",
                "Practical Wooden Bacon",
                "Refined Frozen Mouse"
        );

        // Assert that all expected product names are present
        assertTrue(productNames.containsAll(expectedProductNames), "All expected product names should be present.");
    }

    @Test(description = "Test to verify that products can be added to favorites.")
    public void addProductToFavoritesTest() {
        // Get the name of the first product
        List<String> productNames = homePage.getProductNames();
        String firstProductName = productNames.get(0);

        // Add the first product to favorites
        homePage.addProductToFavorites(0);

        // Navigate to the favorites page
        homePage.goToFavoritesPage();

        // Verify that the product is present in the favorites list
        List<String> favoritesProductNames = homePage.getFavoritesProductNames();
        assertTrue(favoritesProductNames.contains(firstProductName), "The product should be present in the favorites list.");
    }

    @Test(description = "Test to verify that products can be deleted from favorites.")
    public void removeProductFromFavoritesTest() {
        // Get the name of the first product
        List<String> productNames = homePage.getProductNames();
        String firstProductName = productNames.get(0);

        // Add the first product to favorites
        homePage.addProductToFavorites(0);

        // Navigate to the favorites page
        homePage.goToFavoritesPage();

        // Verify that the product is present in the favorites list
        List<String> favoritesProductNames = homePage.getFavoritesProductNames();
        assertTrue(favoritesProductNames.contains(firstProductName), "The product should be present in the favorites list.");

        homePage.removeProductFromFavorites(0);

        // Verify that the product is no longer present in the favorites list
        favoritesProductNames = homePage.getFavoritesProductNames();
        assertFalse(favoritesProductNames.contains(firstProductName), "The product should be removed from the favorites list.");
    }

    @Test(description = "Test to verify the search functionality.")
    public void searchProductTest() {
        // Search for a product
        String productName = "Gorgeous Soft Pizza";
        homePage.searchProduct(productName);

        // Verify that the search results contain the searched product
        List<String> productNames = homePage.getProductNames();
        assertTrue(productNames.contains(productName), "The product should be found in the search results.");
    }

    @Test(description = "Test to verify the sorting functionality.")
    public void sortProductsTest() {
        // Sort products by name (A to Z)
        homePage.sortProducts("az");
        List<String> productNamesAZ = homePage.getProductNames();
        List<String> sortedProductNamesAZ = productNamesAZ.stream().sorted().collect(Collectors.toList());
        assertEquals(productNamesAZ, sortedProductNamesAZ, "The products should be sorted by name (A to Z).");

        // Sort products by name (Z to A)
        homePage.sortProducts("za");
        List<String> productNamesZA = homePage.getProductNames();
        List<String> sortedProductNamesZA = productNamesZA.stream().sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());
        assertEquals(productNamesZA, sortedProductNamesZA, "The products should be sorted by name (Z to A).");

    }

    @Test(description = "Test to verify homepage functionality.")
    public void homepageFunctionalityTest() {
        // Verify that the homepage loads correctly
        List<String> productNames = homePage.getProductNames();
        assertFalse(productNames.isEmpty(), "The product names list should not be empty.");

        // Verify that the search bar is present
        WebElement searchInput = homePage.getSearchInput();
        assertNotNull(searchInput, "Search input should be present on the homepage.");

        // Verify that the search button is present
        WebElement searchButton = homePage.getSearchButton();
        assertNotNull(searchButton, "Search button should be present on the homepage.");

        // Verify that the sort dropdown is present
        WebElement sortDropdown = homePage.getSortDropdown();
        assertNotNull(sortDropdown, "Sort dropdown should be present on the homepage.");

        // Verify that all product links are present and clickable
        List<WebElement> productLinks = homePage.getProductLinks();
        for (WebElement productLink : productLinks) {
            assertTrue(productLink.isDisplayed(), "Product link should be visible.");
            assertTrue(productLink.isEnabled(), "Product link should be clickable.");
        }


    }


}

