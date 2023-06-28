import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwagLabsTest {

    List<WebDriver> drivers = new ArrayList<>();

    @BeforeEach
    public void setUp () {
        drivers.add(new ChromeDriver());
        drivers.add(new FirefoxDriver());
    }

    @AfterEach
    public void quit () {
        for(WebDriver driver : drivers) {
            driver.quit();
        }
    }

    public void connect(WebDriver driver) throws InterruptedException {

        String username = "standard_user";

        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.id("login-button")).click();

        System.out.println("URL APRES CONNECT AVANT SLEEP " + driver.getCurrentUrl());
        Thread.sleep(1500);
        System.out.println("URL APRES CONNECT " + driver.getCurrentUrl());

    }

    public void printMsg (String name) {
        for(WebDriver driver : drivers) {
            System.out.println("Test " + name + " passé avec " + driver.getClass().getSimpleName());
        }
    }

    @Test
    public void it_should_logout() throws InterruptedException {

        for(WebDriver driver : drivers) {
            driver.get("https://www.saucedemo.com/inventory.html");

            connect(driver);

            WebElement burgerBtn = driver.findElement(By.id("react-burger-menu-btn"));
            burgerBtn.click();

            Thread.sleep(500);
            WebElement logoutBtn = driver.findElement(By.id("logout_sidebar_link"));
            logoutBtn.click();

            Thread.sleep(1000);

            String currentUrl = driver.getCurrentUrl();
            assertEquals("https://www.saucedemo.com/", currentUrl);
        }
        printMsg("de logout");
    }

}