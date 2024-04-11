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

public class TestForBrowserLog {

    private WebDriver driver;
    private WebDriverWait wait;

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
    }

    @Test
    public void checkBrowserLogs() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get(" http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.xpath("//a[contains(., 'Subcategory')]")).click();

        var arrayLinks = driver.findElements(By.xpath("//td[3]/a"));
        var arrayLinksSize = driver.findElements(By.xpath("//td[3]/a")).size();
        String[] arrayLinksHref = new String[arrayLinksSize];


        for (var i = 0; i < arrayLinksSize; i++ ){
            arrayLinksHref[i] = arrayLinks.get(i).getAttribute("href");
        }

        for (var x = 0; x < arrayLinksSize; x++) {
            var href = arrayLinksHref[x];
            driver.findElement(By.xpath("//td[3]/a[@href='"+ href + "']")).click();
            driver.navigate().back();
            var log = driver.manage().logs().get("browser").getAll();
            assertTrue(log.isEmpty());

        }
    }
    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
