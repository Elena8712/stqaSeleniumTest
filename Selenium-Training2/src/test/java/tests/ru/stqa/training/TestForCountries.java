package tests.ru.stqa.training;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestForCountries {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void checkCountries() {
        var coutryCount = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr[@class='row']")).size();
        String[] tableGrid = new String[coutryCount];
        var x = 0;
        for (var i = 1; i <= coutryCount; i++) {
            String subStr = "//table[@class='dataTable']/tbody/tr[@class='row'][" + i + "]/td[5]/a";
            tableGrid[x] = driver.findElement(By.xpath
                    (subStr)).getText();
            x++;
        }

        String[] countryS = new String[tableGrid.length];
        System.arraycopy(tableGrid, 0, countryS, 0, tableGrid.length);
        Arrays.sort(tableGrid);

        assertEquals(Arrays.toString(countryS), Arrays.toString(tableGrid));

    }

}