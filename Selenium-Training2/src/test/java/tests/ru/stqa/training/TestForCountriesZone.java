package tests.ru.stqa.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestForCountriesZone {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void checkCountriesZone() {
        var coutryCount = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr[@class='row']")).size();
        for (var i = 1; i <= coutryCount; i++) {
            String subStr = "//table[@class='dataTable']/tbody/tr[@class='row'][" + i + "]/td[6]";
            if (driver.findElement(By.xpath(subStr)).getText().equals("0")) {

            } else {
                driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr[@class='row'][" + i + "]/td[5]/a")).click();
                var zoneCount = driver.findElements(By.xpath(".//tr[position()>1]/td[3]")).size() - 1;
                var zoneList = driver.findElements(By.xpath(".//tr[position()>1]/td[3]"));
                String[] tableGrid = new String[zoneCount];
                var x = 0;

                for (var y = 0; y < zoneCount; y++) {

                    tableGrid[y] = zoneList.get(y).getText();

                }

                String[] zonesS = new String[tableGrid.length];
                System.arraycopy(tableGrid, 0, zonesS, 0, tableGrid.length);
                Arrays.sort(tableGrid);

                assertEquals(Arrays.toString(zonesS), Arrays.toString(tableGrid));
                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

            }
        }

    }
}