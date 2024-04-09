package tests.ru.stqa.training;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Arrays;
import java.util.Random;

public class TestForUserRegistration {
    private WebDriver driver;
    private WebDriverWait wait;

    public int generateRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public String generateRandomHexStringMail(int length){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < length){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        var random = new SecureRandom();
        var list = Arrays.asList("@mail.ru", "@gmail.ru", "@yandex.ru");
        var randomElement = list.get(random.nextInt(list.size()));
        return sb.toString().substring(0, length) + randomElement;
    }

    public String generateRandomHexString(int length){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < length){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, length);
    }
    

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
    }

    @Test
    public void userRegistrationCheck() {
        var eMail = generateRandomHexStringMail(10);
        var postcode = Integer.toString(generateRandomInt(10000, 99999));
        var password = generateRandomHexString(10);
        driver.get("http://localhost/litecart/");
        driver.findElement(By.xpath("//a[contains(.,'New customers click here')]")).click();
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Иван");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Тестовый");
        driver.findElement(By.xpath("//input[@name='address1']")).sendKeys("Тестовый город, ул.Тестовая, д.3");
        driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys(postcode);
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Siattle");
        driver.findElement(By.xpath("//b[@role='presentation']")).click();
        driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys("United States");
        driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(eMail);
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("555555");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys(password);

        driver.findElement(By.xpath("//button[@name='create_account']")).click();
        driver.findElement(By.xpath("//li/a[contains(., 'Logout')]")).click();

        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(eMail);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@name='login']")).click();
        driver.findElement(By.xpath("//li/a[contains(., 'Logout')]")).click();


    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
    }
