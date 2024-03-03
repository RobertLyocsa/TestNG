package Zadanie27;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.time.Duration;

public class Abc27 {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeSuite(alwaysRun = true)
    void setUp() {
        initializeDriver();
        goToHomePage();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test(priority = 1, groups = {"Group 1"})
    void acceptAge() {
        WebElement ageAcceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#agy-accept")));
        ageAcceptButton.click();
    }

    @Test(priority = 2, groups = {"Group 2"})
    void performActions() {
        WebElement elementOne = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#ui-id-1")));
        elementOne.click();

        WebElement elementTwo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ui-id-2")));
        Assert.assertTrue(elementTwo.isDisplayed(), "Element #ui-id-2 is not displayed after clicking on #ui-id-1");

        WebElement elementThree = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#ui-id-3")));
        elementThree.click();

        WebElement elementFour = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ui-id-4")));
        Assert.assertTrue(elementFour.isDisplayed(), "Element #ui-id-4 is not displayed after clicking on #ui-id-3");

        WebElement elementFive = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#ui-id-5")));
        elementFive.click();

        WebElement elementSix = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ui-id-6")));
        Assert.assertTrue(elementSix.isDisplayed(), "Element #ui-id-6 is not displayed after clicking on #ui-id-5");
    }

    @Test(priority = 3, groups = {"Group 3"})         // This website was written by Satan
    void leftPanelTest() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".overlay-verify")));
        } catch (Exception e) {
            System.out.println("Overlay did not disappear within the specified timeout.");
        }


        selectCategory("li.cat-item.cat-item-18.cat-parent"); // Piwo
        selectCategory("li.cat-item.cat-item-19.cat-parent"); // Wino
        selectCategory("li.cat-item.cat-item-39.cat-parent"); // Alcohol
        selectCategory("li.cat-item.cat-item-112"); // Champanske
        selectCategory("li.cat-item.cat-item-34.cat-parent"); // Non-alcoholp
        selectCategory("li.cat-item.cat-item-106"); // Snacks
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
        driver.get("https://www.dovoznakupov.sk/");
        driver.manage().window().maximize();
    }

    private void selectCategory(String selector) {
        WebElement category = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
        if (category != null) {
            category.click();
        } else {
            Assert.fail("Element not clickable within the specified time.");
        }
    }
}


// 9:35 test presiel
// 9:36 test presiel 2x
//