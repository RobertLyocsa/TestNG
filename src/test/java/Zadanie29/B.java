package Zadanie29;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class B {

    @Test
    void xpathTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationtesting.sk/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.xpath("//*[@class='third']")).click();

        driver.findElements(By.xpath("//*[@class='small']")).get(1).click();
        Thread.sleep(1500);
        driver.findElements(By.xpath("//*[@class='small']")).get(2).click();
        Thread.sleep(1500);
        driver.findElements(By.xpath("//*[@class='small']")).get(3).click();
        Thread.sleep(1500);
        driver.findElements(By.xpath("//*[@class='small']")).get(4).click();
        Thread.sleep(1500);

        driver.findElement(By.xpath("//*[@class='big']")).click();
        Thread.sleep(1500);


        driver.findElements(By.xpath("//*[@class='pokemons']")).get(2).click();
        Thread.sleep(5000);

        driver.findElements(By.xpath("//*[@class='pokemons']")).get(2).click();
        Thread.sleep(5000);

        driver.findElements(By.xpath("//*[@class='pokemons']")).get(2).click();
        Thread.sleep(5000);

        System.out.println("Visible pokemons are Bulbasaur, Charizzard and Pikachu. Have a great day!");

        driver.quit();
    }
}
