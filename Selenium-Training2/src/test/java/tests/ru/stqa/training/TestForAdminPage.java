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
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Appearence')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Template')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Logotype')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//a[@href='http://localhost/litecart/admin/?app=catalog&doc=catalog']/span[2]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//li[contains(@class, 'selected') and contains(@id, 'app-')]/a[1]/span[2]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Product Groups')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Option Groups')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Manufacturers')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Suppliers')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Delivery Statuses')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Sold Out Statuses')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Quantity Units')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'CSV Import/Export')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Countries')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Currencies')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//a[@href='http://localhost/litecart/admin/?app=customers&doc=customers']/span[2]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//li[contains(@class, 'selected') and contains(@id, 'app-')]/a[1]/span[2]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'CSV Import/Export')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Newsletter')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Geo Zones')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//a[@href='http://localhost/litecart/admin/?app=languages&doc=languages']/span[2]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Storage Encoding')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//li[contains(@class, 'selected') and contains(@id, 'app-')]/a[1]/span[2]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Modules')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//a[@href='http://localhost/litecart/admin/?app=modules&doc=customer']")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Background Jobs')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Shipping')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Payment')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Order Total')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Order Success')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Order Action')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//a[@href='http://localhost/litecart/admin/?app=orders&doc=orders']/span[2]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Order Statuses')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//li[contains(@class, 'selected') and contains(@id, 'app-')]/a[1]/span[2]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Pages')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Reports')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Monthly Sales')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Most Sold Products')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Most Shopping Customers')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Settings')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Store Info')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Defaults')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'General')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Listings')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Images')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Checkout')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Advanced')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Security')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Slides')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Tax')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Translations')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Search Translations')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Scan Files')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'CSV Import/Export')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//span[contains(@class,'name') and contains(.,'Users')]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//a[@href='http://localhost/litecart/admin/?app=vqmods&doc=vqmods']/span[2]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
        driver.findElement(By.xpath("//li[contains(@class, 'selected') and contains(@id, 'app-')]/a[1]/span[2]")).click();
        assertTrue(isElementPresent(By.xpath("//h1")));
    }
    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
