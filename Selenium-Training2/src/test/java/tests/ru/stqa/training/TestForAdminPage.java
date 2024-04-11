package tests.ru.stqa.training;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestForAdminPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public boolean isElementPresent(By locator){
        try{
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }


    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
    }
    @Test
    public void logIn() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        var arrayMainListSize = driver.findElements(By.xpath("//li[@id='app-']")).size();

        for (var i = 1; i <= arrayMainListSize; i++){
            driver.findElement(By.xpath("//li[@id='app-'][" + i + "]")).click();
            if (isElementPresent(By.xpath("//li[@class='selected']/ul/li/a/span"))){
                var arraySubListSize = driver.findElements(By.xpath("//li[@class='selected']/ul/li/a/span")).size();

                for (var x = 1; x <= arraySubListSize; x++){

                    driver.findElement(By.xpath("//li[@class='selected']/ul/li[" + x + "]")).click();

                }
            }
        }
    }
    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}