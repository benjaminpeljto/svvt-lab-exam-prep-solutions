package ba.ibu.edu.task5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhpTravels {
    private static WebDriver webDriver;
    private static JavascriptExecutor js;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Benjamin Peljto/web_engineering/selenium/chromedriver.exe"); // specify the path to chromedriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        js = (JavascriptExecutor) webDriver;
    }

    @Test
    void travelsTest() throws InterruptedException {
        webDriver.get("https://phptravels.net");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement thailandLink = webDriver.findElement(By.xpath("//div[contains(@class, \"col-lg-8\")]/div[1]/a[last()]"));
        js.executeScript("arguments[0].scrollIntoView(true);", thailandLink);
        Thread.sleep(500);
        thailandLink.click();
        Thread.sleep(1500);

        Select adultNumber = new Select(webDriver.findElement(By.id("adults")));
        adultNumber.selectByValue("4");
        Thread.sleep(1500);

        Select childrenNumber = new Select(webDriver.findElement(By.id("childs")));
        childrenNumber.selectByValue("3");
        Thread.sleep(1500);

        WebElement datepickerInput = webDriver.findElement(By.xpath("//input[contains(@name, \"date\")]"));
        js.executeScript("arguments[0].value = '23-1-2025';", datepickerInput);
        Thread.sleep(1500);

        WebElement totalPrice = webDriver.findElement(By.className("total"));
        assertEquals("502", totalPrice.getText(), "Price not good.");

        WebElement bookNowBtn = webDriver.findElement(By.xpath("//button[@type='submit']"));
        bookNowBtn.click();
        Thread.sleep(1500);

        WebElement fNameInput = webDriver.findElement(By.xpath("//input[contains(@name, \"user[first_name]\")]"));
        fNameInput.sendKeys("Julius");
        Thread.sleep(1000);

        WebElement lNameInput = webDriver.findElement(By.xpath("//input[contains(@name, \"user[last_name]\")]"));
        lNameInput.sendKeys("Meinl");
        Thread.sleep(1000);


        WebElement emailInput = webDriver.findElement(By.xpath("//input[contains(@name, \"user[email]\")]"));
        emailInput.sendKeys("julius@meinl.com");
        Thread.sleep(1000);

        WebElement phoneInput = webDriver.findElement(By.xpath("//input[contains(@name, \"user[phone]\")]"));
        phoneInput.sendKeys("033 225 883");
        Thread.sleep(1000);

        WebElement addressInput = webDriver.findElement(By.xpath("//input[contains(@name, \"user[address]\")]"));
        addressInput.sendKeys("Schtrossmeier's 43");
        Thread.sleep(1000);

        Select traveller1 = new Select(webDriver.findElement(By.xpath("//select[contains(@name, \"title_1\")]")));
        traveller1.selectByValue("Mrs");
        Thread.sleep(1000);

        WebElement traveller1FirstName = webDriver.findElement(By.xpath("//input[contains(@name, \"firstname_1\")]"));
        traveller1FirstName.sendKeys("Annete");

        WebElement traveller1LastName = webDriver.findElement(By.xpath("//input[contains(@name, \"lastname_1\")]"));
        traveller1LastName.sendKeys("Bradford");


    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
