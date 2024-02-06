# VVS Selenium Test UI

## Introduction
The web application that you are going to test is located at https://www.saucedemo.com/.


2. **Download WebDriver executables:**

   - Download the geckodriver executable for Firefox from https://github.com/mozilla/geckodriver/releases and place it in the project directory.
   - Download the chromedriver executable for Chrome from https://sites.google.com/a/chromium.org/chromedriver/downloads and place it in the project directory.

3. **Import the project into your preferred Java IDE:**

   - Open your Java IDE (e.g., IntelliJ IDEA, Eclipse).
   - Import the project as a Maven project.

## Running the Tests

1. **Configure WebDriver paths:**

   - Open the `SauceDemoTest.java` file.
   - Replace `"path/to/geckodriver"` with the actual path to the geckodriver executable.
   - If you're using Chrome, replace `"webdriver.gecko.driver"` with `"webdriver.chrome.driver"` and specify the path to the chromedriver executable.

2. **Run the test cases:**

   - Open the `SauceDemoTest.java` file.
   - Right-click inside the file and select "Run" or "Run As" > "JUnit Test".

## Test Cases

1. **Valid User Login**
   - Logs in with valid user credentials.
   - Asserts successful login.

2. **Locked Out User Login**
   - Attempts login with a locked-out user account.
   - Asserts the error message for locked-out user.

3. **Sort Products by Price**
   - Logs in with valid user credentials.
   - Sorts the products by price from low to high.
   - Asserts the first product after sorting.

4. **Add Products to Cart**
   - Logs in with valid user credentials.
   - Adds three different products to the cart.
   - Asserts the number of items in the cart.

5. **Complete Checkout Flow**
   - Logs in with valid user credentials.
   - Adds a product to the cart.
   - Completes the checkout flow.
   - Asserts the completion message.
