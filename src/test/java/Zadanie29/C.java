package Zadanie29;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class C {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.manage().window().maximize();
            driver.get("https://www.dovoznakupov.sk/");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"agy-accept\"]"))).click();

            driver.findElement(By.xpath("//*[@id=\"woocommerce-product-search-field-0\"]"))
                    .sendKeys("Smädný mních 10 0,5l fľaša");
            driver.findElement(By.xpath("//*[@id=\"woocommerce_product_search-3\"]/form/button")).click();
            driver.findElement(By.xpath("//*[starts-with(@id, 'quantity_')]"))
                    .sendKeys("5");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[contains(@class, 'add_to_cart_button')]")).click();


            driver.findElement(By.xpath("//*[@id=\"woocommerce-product-search-field-0\"]"))
                    .sendKeys("Šampanské Mionetto Prosecco 0.75l");
            driver.findElement(By.xpath("//*[@id=\"woocommerce_product_search-3\"]/form/button")).click();
            driver.findElement(By.xpath("//*[starts-with(@id, 'quantity_')]"))
                    .sendKeys("6");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[contains(@class, 'add_to_cart_button')]")).click();

            driver.findElement(By.xpath("//*[@id=\"woocommerce-product-search-field-0\"]"))
                    .sendKeys("Bake rolls pizza 80g ");
            driver.findElement(By.xpath("//*[@id=\"woocommerce_product_search-3\"]/form/button")).click();
            driver.findElement(By.xpath("//*[starts-with(@id, 'quantity_')]"))
                    .sendKeys("7");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[contains(@class, 'add_to_cart_button')]")).click();

            driver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div/div[3]/div[2]/div/a/i")).click();

            List<WebElement> quantityElements = driver.findElements(By.xpath("//*[starts-with(@id, 'quantity_')]"));
            Thread.sleep(1500);

            String[] expectedValues = {"51", "61", "71"};
            for (int i = 0; i < quantityElements.size(); i++) {

                WebElement quantityElement = quantityElements.get(i);
                String actualValue = quantityElement.getAttribute("value");
                Assertions.assertEquals(expectedValues[i], actualValue);
            }

            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/main/section/div/div/div[2]/div/div/a")).click();

            driver.findElement(By.xpath("//*[@id=\"billing_first_name\"]"))
                    .sendKeys("Name");
            driver.findElement(By.xpath("//*[@id=\"billing_last_name\"]"))
                    .sendKeys("Last Name");
            driver.findElement(By.xpath("//*[@id=\"billing_address_1\"]"))
                    .sendKeys("Street");
            driver.findElement(By.xpath("//*//*[@id=\"billing_postcode\"]"))
                    .sendKeys("PSC");
            driver.findElement(By.xpath("//*[@id=\"billing_city\"]"))
                    .sendKeys("Town");
            driver.findElement(By.xpath("//*[@id=\"billing_phone\"]"))
                    .sendKeys("Phone number");

            System.out.println("Here the Test ends");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}