import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class C {

    public static void main(String[] args) {
        WebDriver driver = initializeChromeDriver();
        try {
            // User 1
            fillOutMultiStepForm(driver, "John Doe", "john.doe@example.com", "2024-03-03", "Thank you Metis!");
            verifyFormData(driver, "Fixtures");

            // User 2
            fillOutMultiStepForm(driver, "Alice Smith", "alice.smith@example.com", "2024-03-04", "Awesome website!");
            verifyFormData(driver, "Each");

            // User 3
            fillOutMultiStepForm(driver, "Bob Johnson", "bob.johnson@example.com", "2024-03-05", "Great job!");
            verifyFormData(driver, "Each");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }

    private static WebDriver initializeChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        return new ChromeDriver();
    }

    private static void fillOutMultiStepForm(WebDriver driver, String name, String email, String date, String message) throws InterruptedException {
        driver.get("https://web.automationtesting.sk/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement multiStepFormLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Multi Step Form")));
        multiStepFormLink.click();

        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("msf-text-your-name")));
        nameInput.sendKeys(name);

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("msf-mail-your-email")));
        emailInput.sendKeys(email);

        WebElement dateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("msf-date-select-date-of-order")));
        dateInput.sendKeys(date);

        WebElement termsAndConditions = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#multi-step-form > div.fw-wizard-step-container > div > div.fw-wizard-step.fw-current > div.fw-step-part > div.fw-step-part-body > div:nth-child(4) > span > label")));
        termsAndConditions.click();
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"multi-step-form\"]/div[4]/div/div/button[2]")));
        nextButton.click();

        Thread.sleep(1000);

        WebElement whatLike1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"multi-step-form\"]/div[3]/div/div[2]/div[1]/div[2]/div[1]/div[1]/label")));
        whatLike1.click();

        WebElement whatLike2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"multi-step-form\"]/div[3]/div/div[2]/div[1]/div[2]/div[1]/div[3]/label")));
        whatLike2.click();

        WebElement newsLetter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"multi-step-form\"]/div[3]/div/div[2]/div[1]/div[2]/div[2]/span/span[1]/span")));
        newsLetter.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newsLetter);
        newsLetter.sendKeys(Keys.ENTER);
        WebElement nextOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"multi-step-form\"]/div[4]/div/div/button[2]")));
        nextOption.click();
        WebElement messageInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"msf-textarea-write-us-a-message\"]")));
        messageInput.sendKeys(message);
    }

    private static void verifyFormData(WebDriver driver, String userType) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        WebElement summary = driver.findElement(By.xpath("//*[@id=\"multi-step-form\"]/div[3]/div/div[3]/div[2]/button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", summary);

        String nameValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wizard-summary\"]/div[1]/div[1]/div[2]"))).getText();
        String emailValue = driver.findElement(By.xpath("//*[@id=\"wizard-summary\"]/div[1]/div[2]/div[2]")).getText();
        String dateValue = driver.findElement(By.xpath("//*[@id=\"wizard-summary\"]/div[1]/div[3]/div[2]")).getText();
        String messageValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wizard-summary\"]/div[1]/div[4]/div[2]"))).getText();

        System.out.println(userType + " Name: " + nameValue);
        System.out.println(userType + " Email: " + emailValue);
        System.out.println(userType + " Date: " + dateValue);
        System.out.println(userType + " Message: " + messageValue);

        if (messageValue.equals("Thank you Metis!") || messageValue.equals("Awesome website!") || messageValue.equals("Great job!")) {
            System.out.println(userType + " Message verification successful.");
        } else {
            System.out.println(userType + " Message verification failed. Expected: '" + messageValue + "'");
        }
    }
}
