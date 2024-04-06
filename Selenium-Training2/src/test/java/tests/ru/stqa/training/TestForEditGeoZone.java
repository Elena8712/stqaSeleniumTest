package tests.ru.stqa.training;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestForEditGeoZone {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void checkCountriesZone() {
        var coutryCount = driver.findElements(By.xpath(".//tr[position()>1]/td[3]")).size() - 1;

        for (var i = 0; i <= coutryCount; i++) {
            var countryList = driver.findElements(By.xpath(".//tr[position()>1]/td[3]"));
            var country = countryList.get(i).getText();
            driver.findElement(By.xpath("//a[contains(.,'" + country + "')]")).click();

            var zonesCount = driver.findElements(By.xpath("//td[position()=3]/select/option[@selected='selected']")).size();
            var zonesList = driver.findElements(By.xpath("//td[position()=3]/select/option[@selected='selected']"));
            String[] zonesArray = new String[zonesCount];

            for (var x = 0; x < zonesCount; x++) {

                zonesArray[x] = zonesList.get(x).getText();
            }

            String[] zonesSort = new String[zonesArray.length];
            System.arraycopy(zonesArray, 0, zonesSort, 0, zonesArray.length);
            Arrays.sort(zonesArray);

            assertEquals(Arrays.toString(zonesSort), Arrays.toString(zonesArray));

            driver.findElement(By.cssSelector("a[href='http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones']")).click();
        }
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}