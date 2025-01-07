package ba.ibu.edu.task4;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TheInternetPageTest {
    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Benjamin Peljto/web_engineering/selenium/chromedriver.exe"); // specify the path to chromedriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://the-internet.herokuapp.com/";
    }

    @Test
    @Order(1)
    void loginLogoutTest() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(1500);
        WebElement formAuthLink = webDriver.findElement(By.xpath("//ul/li[21]/a"));
        formAuthLink.click();
        Thread.sleep(1000);
        System.out.println(webDriver.getCurrentUrl());
        WebElement usernameInput = webDriver.findElement(By.id("username"));
        usernameInput.click();
        Thread.sleep(500);
        usernameInput.sendKeys("tomsmith");
        Thread.sleep(500);

        WebElement passwordInput = webDriver.findElement(By.id("password"));
        passwordInput.click();
        Thread.sleep(500);
        passwordInput.sendKeys("SuperSecretPassword!");
        Thread.sleep(500);

        WebElement submitBtn = webDriver.findElement(By.xpath("//button"));
        submitBtn.click();
        Thread.sleep(1500);

        assertEquals("https://the-internet.herokuapp.com/secure", webDriver.getCurrentUrl());

        WebElement logoutBtn = webDriver.findElement(By.xpath("//a[contains(@href, \"/logout\")]"));
    }

    @Test
    @Order(2)
    void checkboxesTest() throws InterruptedException {
        webDriver.get(baseUrl);

        WebElement checkboxesLink = webDriver.findElement(By.xpath("//ul/li[6]/a"));
        checkboxesLink.click();
        Thread.sleep(1500);

        WebElement secondCheckbox = webDriver.findElement(By.xpath("//input[2]"));
        secondCheckbox.click();
        Thread.sleep(1000);

        WebElement firstCheckbox = webDriver.findElement(By.xpath("//input[1]"));
        firstCheckbox.click();
        Thread.sleep(1000);
    }

    @Test
    @Order(3)
    void dropdownTest() throws InterruptedException {
        webDriver.navigate().back();

        WebElement dropdownLink = webDriver.findElement(By.xpath("//ul/li[11]/a"));
        dropdownLink.click();
        Thread.sleep(1500);

        Select dropdown = new Select(webDriver.findElement(By.id("dropdown")));
        dropdown.selectByValue("2");
        Thread.sleep(1000);
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
