package tests.ru.stqa.training;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestForProductsChrome {
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
    public void checkProductsPage() {
        driver.get("http://localhost/litecart/");

        var boxCount = driver.findElements(By.xpath("//div[@class='middle']/div[@class='content' and position()>1]/div[h3]")).size();
        var box = driver.findElements(By.xpath("//div[@class='middle']/div[@class='content' and position()>1]/div[h3]"));
        String[] boxArray = new String[box.size()];

        for (var y = 0; y < boxCount; y++) {

            boxArray[y] = box.get(y).getAttribute("id");
        }

        for (var y = 0; y < boxArray.length; y++) {
            var boxId = boxArray[y];
            var prodBoxCount = driver.findElements(By.xpath("//div[@id='" + boxId + "']/div/ul/li/a[@class='link']")).size();
            var prodBox = driver.findElements(By.xpath("//div[@id='" + boxId + "']/div/ul/li/a[@class='link']"));

            String[] prodBoxArray = new String[prodBox.size()];

            for (var i = 0; i < prodBoxCount; i++) {
                prodBoxArray[i] = prodBox.get(i).getAttribute("title");

            }


            for (var i = 0; i < prodBoxArray.length; i++) {
                var title = prodBoxArray[i];

                var priceStr = "//div[@id='" + boxId + "']/div/ul/li/a[@title='" + title + "' and @class='link']/div[@class='price-wrapper']/span";
                if (isElementPresent(By.xpath(priceStr)) == 0) {
                    var priceReg = driver.findElement(By.xpath("//div[@id='" + boxId + "']/div/ul/li/a[@title='" + title + "' and @class='link']/div[@class='price-wrapper']/s")).getText();
                    var campPrice = driver.findElement(By.xpath("//div[@id='" + boxId + "']/div/ul/li/a[@title='" + title + "' and @class='link']/div[@class='price-wrapper']/strong")).getText();

                    var colorReg = driver.findElement(By.xpath("//div[@id='" + boxId + "']/div/ul/li/a[@title='" + title + "' and @class='link']/div[@class='price-wrapper']/s")).getCssValue("color");
                    var colorRegSub = colorReg.substring(5).split(", ");
                    assertEquals(colorRegSub[0], colorRegSub[1], colorRegSub[2]);

                    var decorReg = driver.findElement(By.xpath("//div[@id='" + boxId + "']/div/ul/li/a[@title='" + title + "' and @class='link']/div[@class='price-wrapper']/s")).getCssValue("text-decoration");
                    var decorRegSub = decorReg.substring(0, 12);

                    assertEquals(decorRegSub, "line-through");

                    var colorCamp = driver.findElement(By.xpath("//div[@id='" + boxId + "']/div/ul/li/a[@title='" + title + "' and @class='link']/div[@class='price-wrapper']/strong")).getCssValue("color");
                    var colorCampSub = colorCamp.substring(5).split(", ");
                    assertEquals("0", colorCampSub[1], colorCampSub[2]);

                    var decorCamp = driver.findElement(By.xpath("//div[@id='" + boxId + "']/div/ul/li/a[@title='" + title + "' and @class='link']/div[@class='price-wrapper']/strong")).getCssValue("font-weight");
                    assertEquals(decorCamp, "700");

                    var sizeReg = driver.findElement(By.xpath("//div[@id='" + boxId + "']/div/ul/li/a[@title='" + title + "' and @class='link']/div[@class='price-wrapper']/s")).getCssValue("font-size").split("px");
                    var sizeCamp = driver.findElement(By.xpath("//div[@id='" + boxId + "']/div/ul/li/a[@title='" + title + "' and @class='link']/div[@class='price-wrapper']/strong")).getCssValue("font-size").split("px");

                    double sizeRegDouble = Double.parseDouble(sizeReg[0]);
                    double sizeCampDouble = Double.parseDouble(sizeCamp[0]);
                    assertTrue(sizeCampDouble > sizeRegDouble);


                    driver.findElement
                                    (By.xpath("//div[@id='" + boxId + "']/div/ul/li/a[@title='" + title + "' and @class='link']"))
                            .click();

                    var colorRegProdPage = driver.findElement(By.xpath("//div[position()=2]/s[@class='regular-price']")).getCssValue("color");
                    var colorRegProdPageSub = colorRegProdPage.substring(5).split(", ");
                    assertEquals(colorRegProdPageSub[0], colorRegProdPageSub[1], colorRegProdPageSub[2]);

                    var decorRegProdPage = driver.findElement(By.xpath("//div[position()=2]/s[@class='regular-price']")).getCssValue("text-decoration");
                    var decorRegProdPageSub = decorRegProdPage.substring(0, 12);
                    assertEquals(decorRegProdPageSub, "line-through");

                    var colorCampProdPage = driver.findElement(By.xpath("//div[position()=2]/strong[@class='campaign-price']")).getCssValue("color");
                    var colorCampProdPageSub = colorCampProdPage.substring(5).split(", ");
                    assertEquals("0", colorCampProdPageSub[1], colorCampProdPageSub[2]);

                    var decorCampProdPage = driver.findElement(By.xpath("//div[position()=2]/strong[@class='campaign-price']")).getCssValue("font-weight");
                    assertEquals(decorCampProdPage, "700");

                    var sizeRegProdPage = driver.findElement(By.xpath("//div[position()=2]/s[@class='regular-price']")).getCssValue("font-size").split("px");
                    var sizeCampProdPage = driver.findElement(By.xpath("//div[position()=2]/strong[@class='campaign-price']")).getCssValue("font-size").split("px");

                    double sizeRegProdPageDouble = Double.parseDouble(sizeRegProdPage[0]);
                    double sizeCampProdPageDouble = Double.parseDouble(sizeCampProdPage[0]);
                    assertTrue(sizeCampProdPageDouble > sizeRegProdPageDouble);


                    assertEquals(priceReg, driver.findElement(By.xpath("//div[position()=2]/s[@class='regular-price']")).getText());
                    assertEquals(campPrice, driver.findElement(By.xpath("//div[position()=2]/strong[@class='campaign-price']")).getText());
                    assertEquals(title, driver.findElement(By.xpath("//h1")).getText());
                } else {
                    var price = driver.findElement(By.xpath("//div[@id='" + boxId + "']/div/ul/li/a[@title='" + title + "' and @class='link']/div[@class='price-wrapper']/span")).getText();
                    driver.findElement
                                    (By.xpath("//div[@id='" + boxId + "']/div/ul/li/a[@title='" + title + "' and @class='link']"))
                            .click();


                    assertEquals(title, driver.findElement(By.xpath("//h1")).getText());
                    assertEquals(price, driver.findElement(By.xpath("//div[position()=2]/span[@class='price']")).getText());
                }
                driver.findElements(By.xpath("//a[contains(., 'Home')]")).get(0).click();

            }

        }

    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }

}


