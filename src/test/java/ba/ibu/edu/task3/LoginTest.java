package ba.ibu.edu.task3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginTest {
    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Benjamin Peljto/web_engineering/selenium/chromedriver.exe"); // specify the path to chromedriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://demo.guru99.com/test/login.html";
    }

    @Test
    void testLoginForm() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(500);
        WebElement emailField = webDriver.findElement(By.id("email"));
        emailField.click();
        Thread.sleep(500);
        emailField.sendKeys("svvt@test.com");
        Thread.sleep(500);

        WebElement passwordField = webDriver.findElement(By.id("passwd"));
        passwordField.click();
        Thread.sleep(500);
        passwordField.sendKeys("testing");
        Thread.sleep(500);

        WebElement submitButton = webDriver.findElement(By.id("SubmitLogin"));
        submitButton.click();
        Thread.sleep(1000);

        WebElement successText = webDriver.findElement(By.xpath("//h3[1]"));
        assertEquals("Successfully Logged in...", successText.getText());

        assertEquals("https://demo.guru99.com/test/success.html", webDriver.getCurrentUrl());


    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
