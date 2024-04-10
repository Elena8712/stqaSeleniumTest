package tests.ru.stqa.training;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestForLinks {

    private WebDriver driver;
    private WebDriverWait wait;

    public ExpectedCondition<String>anyWindowOtherThan(Set<String> oldWindows){
        return driver -> {
            Set<String> handles = driver.getWindowHandles();
            handles.removeAll(oldWindows);
            return handles.size() > 0 ? handles.iterator().next():null;
        };
    }

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
    public void logIn() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.xpath("//a[contains(@href, 'http://localhost/litecart/admin/?app=countries&doc=edit_country&country_code=AF') and contains (@title, 'Edit')]")).click();

        var links = driver.findElements(By.xpath("//tr/td/a[contains(@href, 'http')]"));
        var linksSize = links.size();

        for (var i = 0; i < linksSize; i++) {
            String mainWindow = driver.getWindowHandle();
            Set<String> oldWindows = driver.getWindowHandles();
            links.get(i).click();
            var newWindow = wait.until(anyWindowOtherThan(oldWindows));
            System.out.println(newWindow);
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }
    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
