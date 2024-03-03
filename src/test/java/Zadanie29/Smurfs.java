package Zadanie29;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Smurfs {

    @Test
    void xpathTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationtesting.sk/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on an element with class "fourth"
        driver.findElement(By.xpath("//*[@class='fourth']")).click();

        // Find an element with class "number-of-cars" and send keys "4" to it
        driver.findElement(By.className("number-of-cars")).sendKeys("4");

        // Click on a button
        driver.findElement(By.tagName("button")).click();

        // Wait until there are 4 elements with tag "img"
        wait.until(ExpectedConditions.numberOfElementsToBe(By.tagName("img"), 4));

        // Perform drag and drop from the first img to an element with class "bars"
        WebElement sourceElement = driver.findElements(By.tagName("img")).get(0);
        WebElement targetElement = driver.findElement(By.className("bars"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceElement, targetElement).perform();

        // Perform drag and drop from the second img to the same element with class "bars"
        WebElement sourceElement2 = driver.findElements(By.tagName("img")).get(1);
        actions.dragAndDrop(sourceElement2, targetElement).perform();

        // Close the browser window and quit the driver
        driver.quit();
    }
}
