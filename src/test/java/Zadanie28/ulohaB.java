package Zadanie28;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class ulohaB {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeSuite(alwaysRun = true)
    void setUp() {
        initializeDriver();
        goToHomePage();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test(dataProvider = "loginData", priority = 1, groups = {"Group 1"})
    void logInTest(String username, String password) {
        performLogin(username, password);
        if (isLoginSuccess()) {
            System.out.println("Login successful for username: " + username);
        } else {
            System.out.println("Login failed for username: " + username);
        }
        try {
            performLogOut();
        } catch (Exception e) {
            System.out.println("Logout failed: " + e.getMessage());
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][]{
                {"tomsmith", "SuperSecretPassword!"},
                {"invaliduser", "SuperSecretPassword!"},
                {"tomsmith", "InvalidPassword!"}
        };
    }

    @AfterSuite(alwaysRun = true)
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void initializeDriver() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        actions = new Actions(driver);
    }

    private void goToHomePage() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize();
    }

    private void performLogin(String username, String password) {
        WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#username")));
        usernameElement.sendKeys(username);

        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#password")));
        passwordElement.sendKeys(password);

        WebElement logInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#login > button")));
        logInButton.click();
    }

    private void performLogOut() {
        WebElement logOut = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#content > div > a")));
        logOut.click();
    }

    private boolean isLoginSuccess() {
        try {
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#flash.success")));
            return successMessage.getText().contains("You logged into a secure area!");
        } catch (Exception e) {
            return false;
        }
    }
}