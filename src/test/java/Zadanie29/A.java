package Zadanie29;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class A {

    @Test
    void xpathTest() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.manage().window().maximize();
            driver.get("https://www.dovoznakupov.sk/");

            WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"woocommerce-product-search-field-0\"]")));
            assert searchField.isDisplayed();

            WebElement categoriesHeader = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"woocommerce_product_categories-5\"]/h3")));
            assert categoriesHeader.isDisplayed();

            WebElement nonAlcoholicDrinksCategory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"woocommerce_product_categories-5\"]/ul/li[3]/a")));
            assert nonAlcoholicDrinksCategory.isDisplayed();

            WebElement cartElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menu-item-31\"]/a")));
            assert cartElement.isDisplayed();

            WebElement phoneNumberElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/footer/section/div[2]/div/div[2]/div/div/div[1]/div/p/span/a")));
            assert phoneNumberElement.isDisplayed();

            WebElement privacyPolicyElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/footer/section/div[2]/div/div[1]/div/div/div/div/a/img")));
            assert privacyPolicyElement.isDisplayed();

            WebElement logoElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/footer/section/div[2]/div/div[1]/div/div/div/div/a/img")));
            assert logoElement.isDisplayed();

            WebElement thirdProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/main/section[5]/div/div/div/div/div/div[2]/ul/li[3]/div")));
            assert thirdProduct.isDisplayed();

            WebElement sixthProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/main/section[5]/div/div/div/div/div/div[2]/ul/li[6]/div")));
            assert sixthProduct.isDisplayed();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}