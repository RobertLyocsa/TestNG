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

public class ulohaC {

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
    void clickNaPost() throws InterruptedException {
        WebElement actions = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.hover > a")));
        actions.click();

        WebElement addText = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#textForAdd")));
        addText.sendKeys(
                "Once upon a midnight dreary, while I pondered, weak and weary,\n" +
                "Over many a quaint and curious volume of forgotten lore—\n" +
                "While I nodded, nearly napping, suddenly there came a tapping,\n" +
                "As of some one gently rapping, rapping at my chamber door.\n" +
                "’Tis some visitor,” I muttered, “tapping at my chamber door—\n" +
                "Only this and nothing more.");

        Thread.sleep(1000);

        WebElement actions2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#addButton")));
        actions2.click();

        addText.sendKeys(
                "Achilles' wrath, to Greece the direful spring Of woes unnumber'd, \n" +
                " heavenly goddess, sing! That wrath which hurl'd to Pluto's gloomy reign The souls of mighty chiefs untimely slain; \n" +
                "Whose limbs unburied on the naked shore, Devouring dogs and hungry vultures tore.");

        WebElement actions3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#waitingButton")));
        actions3.click();

        Thread.sleep(3000);
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