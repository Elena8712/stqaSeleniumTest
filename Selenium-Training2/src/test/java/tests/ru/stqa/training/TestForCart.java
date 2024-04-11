package tests.ru.stqa.training;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class TestForCart {

    private WebDriver driver;
    private WebDriverWait wait;

    public int isElementPresent(By locator) {
        var x = driver.findElements(locator).size();
        return x;
    }

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
    }

    @Test
    public void checkCart() {
        driver.get("http://localhost/litecart/");

        for (var i = 1; i <= 3; i++) {
            driver.findElement(By.xpath("//li[@class='product column shadow hover-light']/a[1]")).click();


            if (isElementPresent(By.xpath("//strong[contains(., 'Size')]")) > 0) {
                driver.findElement(By.xpath("//select[@name='options[Size]']")).click();
                driver.findElement(By.xpath("//option[@value='Medium']")).click();
            }
            driver.findElement(By.xpath("//button[@value='Add To Cart']")).click();

            var iStr = Integer.toString(i);
            wait.until(presenceOfElementLocated(By.xpath(
                    "//span[contains(., '" + iStr + "') and contains(@class, 'quantity')]")));
            driver.findElement(By.xpath("//a[contains(., 'Home')]")).click();
        }

        driver.findElement(By.xpath("//a[contains(., 'Checkout')]")).click();

        var arrayButtonRemove = driver.findElements(By.xpath("//button[@value='Remove']"));
        var arrayButtonRemSize = arrayButtonRemove.size();


            for(var i = 0; i < 3; i++) {
                var tr = arrayButtonRemSize + 1;
                var tableElement = driver.findElement(By.xpath("//table[@class='dataTable rounded-corners']/tbody/tr[" + tr + "]"));
                driver.findElement(By.xpath("//button[@value='Remove']")).click();
                wait.until(ExpectedConditions.stalenessOf(tableElement));
            }

    }
    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
