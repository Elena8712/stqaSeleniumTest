package tests.ru.stqa.training;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

public class TestForMainPage {

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
    public void checkStickers() {
        driver.get("http://localhost/litecart/");
        var prodPop = driver.findElements(By.xpath("//div[@id='box-most-popular']/div/ul/li")).size();
        for (var i = 1; i <= prodPop; i++){
            String subStr = "//div[@id='box-most-popular']/div/ul/li[" + i + "]/a/div[1]/div[starts-with(@class, 'sticker')]";
            assertTrue(isElementPresent (
                    By.xpath(subStr)));
        }

        var prodCamp = driver.findElements(By.xpath("//div[@id='box-campaigns']/div/ul/li")).size();
        for (var i = 1; i <= prodCamp; i++){
            String subStr = "//div[@id='box-campaigns']/div/ul/li[" + i + "]/a/div[1]/div[starts-with(@class, 'sticker')]";
            assertTrue(isElementPresent (
                    By.xpath(subStr)));
        }

        var prodLatest = driver.findElements(By.xpath("//div[@id='box-latest-products']/div/ul/li")).size();
        for (var i = 1; i <= prodLatest; i++){
            String subStr = "//div[@id='box-latest-products']/div/ul/li[" + i + "]/a/div[1]/div[starts-with(@class, 'sticker')]";
            assertTrue(isElementPresent (
                    By.xpath(subStr)));
        }

    }
    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
