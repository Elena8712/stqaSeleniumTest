package tests.ru.stqa.training;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestForLoging {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
    }
    @Test
    public void logIn() {
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.name("email")).sendKeys("elena.kleymenova@raiffeisen.ru");
        driver.findElement(By.name("password")).sendKeys("12345");
        driver.findElement(By.name("login")).click();

    }
    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
