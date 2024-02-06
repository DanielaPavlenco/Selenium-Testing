import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceDemoTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Set the path to the geckodriver executable
        System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");

        // Create a new instance of FirefoxDriver
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);

        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @Test
    public void testValidUserLogin() {
        // Navigate to the SauceDemo website
        driver.get("https://www.saucedemo.com/");

        // Login for a valid user
        login("standard_user", "secret_sauce");

        // Assert successful login
        WebElement inventoryElement = driver.findElement(By.className("product_label"));
        Assert.assertTrue(inventoryElement.isDisplayed());
    }

    @Test
    public void testLockedOutUserLogin() {
        // Navigate to the SauceDemo website
        driver.get("https://www.saucedemo.com/");

        // Login for a user that is locked out
        login("locked_out_user", "secret_sauce");

        // Assert error message for locked out user
        WebElement errorMessage = driver.findElement(By.cssSelector("h3"));
        Assert.assertEquals("Epic sadface: Sorry, this user has been locked out.", errorMessage.getText());
    }

    @Test
    public void testSortProductsByPrice() {
        // Navigate to the SauceDemo website
        driver.get("https://www.saucedemo.com/");

        // Login for a valid user
        login("standard_user", "secret_sauce");

        // Sort the products by price from low to high
        sortProductsByPrice();

        // Assert Sauce Labs Onesie is the first product on the page
        WebElement firstProduct = driver.findElement(By.cssSelector(".inventory_item_name"));
        Assert.assertEquals("Sauce Labs Onesie", firstProduct.getText());
    }

    @Test
    public void testAddProductsToCart() {
        // Navigate to the SauceDemo website
        driver.get("https://www.saucedemo.com/");

        // Login for a valid user
        login("standard_user", "secret_sauce");

        // Add three different products to the cart
        addProductsToCart();

        // Assert the cart contains 3 products
        WebElement cartBadge = driver.findElement(By.cssSelector(".shopping_cart_badge"));
        Assert.assertEquals("3", cartBadge.getText());
    }

    @Test
    public void testCompleteCheckoutFlow() {
        // Navigate to the SauceDemo website
        driver.get("https://www.saucedemo.com/");

        // Login for a valid user
        login("standard_user", "secret_sauce");

        // Add a product to the cart
        addProductsToCart();

        // Complete checkout flow
        buyProduct();

        // Assert checkout complete message
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement checkoutCompleteMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".complete-header")));
        Assert.assertEquals("THANK YOU FOR YOUR ORDER", checkoutCompleteMessage.getText());
    }

    private void login(String username, String password) {
        // Enter username
        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys(username);

        // Enter password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        // Click login button
        WebElement loginButton = driver.findElement(By.cssSelector(".btn_action"));
        loginButton.click();
    }

    private void sortProductsByPrice() {
        // Click on the sort dropdown
        WebElement sortDropdown = driver.findElement(By.cssSelector(".product_sort_container"));
        sortDropdown.click();

        // Select 'Price (low to high)' option
        WebElement priceLowToHighOption = driver.findElement(By.cssSelector("option[value='lohi']"));
        priceLowToHighOption.click();
    }

    private void addProductsToCart() {
        // Add the first three products to the cart
        for (int i = 0; i < 3; i++) {
            WebElement addToCartButton = driver.findElement(By.xpath("(//button[text()='ADD TO CART'])[" + (i + 1) + "]"));
            addToCartButton.click();
        }

        // Click on the shopping cart icon to view the cart
        WebElement cartIcon = driver.findElement(By.cssSelector(".shopping_cart_link"));
        cartIcon.click();
    }

    private void buyProduct() {
        // Click on checkout button
        WebElement checkoutButton = driver.findElement(By.cssSelector(".checkout_button"));
        checkoutButton.click();

        // Fill in checkout information
        WebElement firstNameField = driver.findElement(By.id("first-name"));
        firstNameField.sendKeys("John");

        WebElement lastNameField = driver.findElement(By.id("last-name"));
        lastNameField.sendKeys("Doe");

        WebElement postalCodeField = driver.findElement(By.id("postal-code"));
        postalCodeField.sendKeys("12345");

        // Click on continue button
        WebElement continueButton = driver.findElement(By.cssSelector(".cart_button"));
        continueButton.click();

        // Click on finish button
        WebElement finishButton = driver.findElement(By.cssSelector(".cart_button"));
        finishButton.click();
    }

    @After
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
