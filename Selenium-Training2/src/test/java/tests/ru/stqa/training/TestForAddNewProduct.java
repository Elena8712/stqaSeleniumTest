package tests.ru.stqa.training;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestForAddNewProduct {

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

    public void pause(Integer milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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

        var name = "Test Duck";

        driver.findElement(By.xpath("//span[contains(., 'Catalog')]")).click();
        driver.findElement(By.xpath("//a[@href='http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product']")).click();
        driver.findElement(By.xpath("//label[contains(., 'Enabled')]")).click();
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@name='code']")).sendKeys("testDuck01");
        driver.findElement(By.xpath("//input[@data-name='Root']")).click();
        driver.findElement(By.xpath("//input[@data-name='Rubber Ducks']")).click();
        driver.findElement(By.xpath("//input[@value='1-1']")).click();

        driver.findElement(By.xpath("//input[@name='quantity']")).clear();
        driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys("12");

        driver.findElement(By.xpath("//select[@name='sold_out_status_id']")).click();
        driver.findElement(By.xpath("//select[@name='sold_out_status_id']/option[contains(., '-- Select --')]")).click();

        String str = "src/main/resources/DuckCreative.jpg";
        Path path = Path.of(str).toAbsolutePath();
        //System.out.println(path);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(path.toString());

        driver.findElement(By.xpath("//input[@name='date_valid_from']")).sendKeys("10042024");
        driver.findElement(By.xpath("//input[@name='date_valid_to']")).sendKeys("10122024");

        driver.findElement(By.xpath("//a[contains(., 'Information')]")).click();
        pause(5000);

        driver.findElement(By.xpath("//select[@name='manufacturer_id']")).click();
        driver.findElement(By.xpath("//select[@name='manufacturer_id']/option[@value='1']")).click();
        driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("Test");
        driver.findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys("TestTest");
        driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys("TestTestTest");
        driver.findElement(By.xpath("//input[@name='head_title[en]']")).sendKeys("TestTitle");
        driver.findElement(By.xpath("//input[@name='meta_description[en]']")).sendKeys("TestMeta");

        driver.findElement(By.xpath("//a[contains(., 'Prices')]")).click();
        pause(5000);

        driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys("10000");
        driver.findElement(By.xpath("//select[@name='purchase_price_currency_code']")).click();
        driver.findElement(By.xpath("//select[@name='purchase_price_currency_code']/option[@value='USD']")).click();
        driver.findElement(By.xpath("//input[@name='prices[USD]']")).sendKeys("100");
        driver.findElement(By.xpath("//input[@name='prices[EUR]']")).sendKeys("100");

        driver.findElement(By.xpath("//button[@name='save']")).click();
        driver.findElement(By.xpath("//a[contains(., 'Rubber Ducks')]")).click();

        assertTrue(isElementPresent(By.xpath("//a[contains(., '" + name + "')]")));

    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}