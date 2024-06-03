
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    public WebDriverWait wait;

    public HomePage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 30);
    }

    // Method to get product links
    // Locators
    @FindBy(css = "a.card-link")
    private List<WebElement> productLinks;

    // Method to get search button
    @FindBy(xpath = "//button[@type='button' and contains(text(),'Search')]")
    private WebElement searchButton;

    // Method to get search input
    @FindBy(className = "form-control")
    private WebElement searchInput;

    // Method to get sort dropdown
    @FindBy(css = ".sort-products-select.form-control.form-control-sm")
    private WebElement sortDropdown;

    @FindBy(css = ".svg-inline--fa.fa-heart.fa-w-16.fa-3x")
    private WebElement addToFavoritesButtons;

    @FindBy(css = ".svg-inline--fa.fa-heart-broken.fa-w-16.fa-3x")
    private WebElement removeFromFavoritesButtons;

    @FindBy(css = "a[href='#/wishlist']")
    private WebElement favoriteLink;

    @FindBy(css = "a.card-link")
    private List<WebElement> favoritesProductLinks;

    // Method to get the names of all products
    public List<String> getProductNames() {
        return productLinks.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    // Method to search for a product
    public void searchProduct(String productName) {
        searchInput.sendKeys(productName);
        searchButton.click();
    }

    // Method to sort products
    public void sortProducts(String sortBy) {
        Select select = new Select(sortDropdown);
        select.selectByValue(sortBy);
    }

    // Method to add a product to favorites
    public void addProductToFavorites(int productIndex) {
        productLinks.get(productIndex).click();
        addToFavoritesButtons.click();
    }

    // Method to remove a product from favorites
    public void removeProductFromFavorites(int productIndex) {
        productLinks.get(productIndex).click();
        removeFromFavoritesButtons.click();
    }

    public void goToFavoritesPage() {
        favoriteLink.click();
    }

    public List<String> getFavoritesProductNames() {
        return favoritesProductLinks.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    // Method to get search input
    public WebElement getSearchInput() {
        return searchInput;
    }

    // Method to get search button
    public WebElement getSearchButton() {
        return searchButton;
    }

    // Method to get sort dropdown
    public WebElement getSortDropdown() {
        return sortDropdown;
    }

    // Method to get product links
    public List<WebElement> getProductLinks() {
        return productLinks;
    }

    }

