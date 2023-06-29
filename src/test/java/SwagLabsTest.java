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
        Thread.sleep(1500);


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
    @Test
    public void it_should_add_basket() throws InterruptedException {
        for(WebDriver driver : drivers) {
            driver.get("https://www.saucedemo.com/inventory.html");

            connect(driver);

            WebElement addToCartBtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
            addToCartBtn.click();

            Thread.sleep(500);

            String articleNumber = driver.findElement(By.className("shopping_cart_badge")).getText();

            assertEquals("1", articleNumber);
        }
    }
    @Test
    public void it_should_remove() throws InterruptedException {
        for (WebDriver driver : drivers){
            driver.get("https://www.saucedemo.com/inventory.html");

            connect(driver);

            WebElement addToCartBtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
            addToCartBtn.click();

            Thread.sleep(500);

            WebElement addToCartBtn2 = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
            addToCartBtn2.click();

            Thread.sleep(500);

            WebElement addToCartBtn3 = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
            addToCartBtn3.click();

            Thread.sleep(500);

            WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-bike-light"));
            removeBtn.click();

            Thread.sleep(500);

            WebElement removeBtn2 = driver.findElement(By.id("remove-sauce-labs-backpack"));
            removeBtn2.click();

            Thread.sleep(500);

            String articleNumber = driver.findElement(By.className("shopping_cart_badge")).getText();

            assertEquals("1",articleNumber);

        }
    }

    @Test
    public void it_should_not_access_pages_if_not_logged() throws InterruptedException {
        for(WebDriver driver : drivers) {
            driver.get("https://www.saucedemo.com/");

            WebElement userName = driver.findElement(By.id("user-name"));
            WebElement password = driver.findElement(By.id("password"));

            userName.sendKeys("standard_user1");
            password.sendKeys("secret_sauce");

            WebElement loginBtn = driver.findElement(By.id("login-button"));
            loginBtn.click();

            Thread.sleep(1000);

           //String error =  driver.findElement(By.className("error-button")).getText();

            WebElement errorNotif = driver.findElement(By.cssSelector("div .error-message-container > h3"));

           assertEquals("Epic sadface: Username and password do not match any user in this service", errorNotif.getText());
        }
    }

}