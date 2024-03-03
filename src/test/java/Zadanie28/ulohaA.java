package Zadanie28;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class ulohaA {

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
    void clickNaHiddenStepper() throws InterruptedException {
        WebElement hoverElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.hover > a")));
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverElement).perform();

        Thread.sleep(3000);

        WebElement stepperClick = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li:nth-child(2) > a")));
        stepperClick.click();

        Thread.sleep(1000);
    }

    @Test(priority = 2, groups = {"Group 2"}, dependsOnMethods = "clickNaHiddenStepper")
    void stepperOverovanie() {
        WebElement elementOne = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input")));
        elementOne.sendKeys("Name");
        String actualValue = elementOne.getAttribute("value");
        Assert.assertEquals(actualValue, "Name", "The name was not entered into the input field as expected.");

        WebElement elementTwo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.next")));
        elementTwo.click();

        elementOne.clear();
        elementOne.sendKeys("Band");
        actualValue = elementOne.getAttribute("value");
        Assert.assertEquals(actualValue, "Band", "The name was not entered into the input field as expected.");
        elementTwo.click();

        elementOne.clear();
        elementOne.sendKeys("Actor");
        actualValue = elementOne.getAttribute("value");
        Assert.assertEquals(actualValue, "Actor", "The name was not entered into the input field as expected.");
        elementTwo.click();

        // Repeat the process for Actor2
        elementOne.clear();
        elementOne.sendKeys("Actor2");
        actualValue = elementOne.getAttribute("value");
        Assert.assertEquals(actualValue, "Actor2", "The name was not entered into the input field as expected.");
        elementTwo.click();
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
        driver.get("https://www.automationtesting.sk/");
        driver.manage().window().maximize();
    }
}
