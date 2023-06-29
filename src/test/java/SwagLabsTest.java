import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.AfterEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Enclosed.class)
public class SwagLabsTest {

    @RunWith(Parameterized.class)
    public static class Login {
        private WebDriver driver;
        private String username;
        private String password;

        public Login(WebDriver driver, String username) {
            this.driver = driver;
            this.username = username;
            this.password = "secret_sauce";
        }
        
        @AfterEach
        public void quit() {
            driver.quit();
            driver.close();
        }

        public void printMsg(String name) {
                System.out.println("Test " + name + " passé avec " + driver.getClass().getSimpleName());
        }

        @Parameterized.Parameters
        public static List<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {new ChromeDriver(), "standard_user"},
                    {new ChromeDriver(), "problem_user"},
                    {new ChromeDriver(), "performance_glitch_user"},
                    {new FirefoxDriver(), "standard_user"},
                    {new FirefoxDriver(), "problem_user"},
                    {new FirefoxDriver(), "performance_glitch_user"}
            });
        }

        @Test
        public void it_should_login() throws InterruptedException {

                driver.get("https://www.saucedemo.com/");

                driver.findElement(By.id("user-name")).sendKeys(this.username);
                driver.findElement(By.id("password")).sendKeys(this.password);

                driver.findElement(By.id("login-button")).click();
                Thread.sleep(1000);


                assertTrue(driver.getCurrentUrl().contains("inventory.html"));
                WebElement loginHeader = driver.findElement(By.className("app_logo"));
                assertEquals("Swag Labs", loginHeader.getText());

                printMsg("login");

        }

        //TODO: externaliser dans une classe
        @Test
        public void it_should_not_login() throws InterruptedException {

            driver.get("https://www.saucedemo.com/");

            driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
            driver.findElement(By.id("password")).sendKeys(this.password);

            driver.findElement(By.id("login-button")).click();
            Thread.sleep(1000);

            String errorMsg = "Epic sadface: Sorry, this user has been locked out.";

            assertFalse(driver.getCurrentUrl().contains("inventory.html"));
            assertEquals(errorMsg, driver.findElement(By.cssSelector("div.error-message-container.error>h3")).getText());

            printMsg("error login");

        }
    }

    @RunWith(Parameterized.class)
    public static class Item {
        private WebDriver driver;

        public Item(WebDriver driver) {
            this.driver = driver;
        }

        public void connect(){
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
        }

        @Parameterized.Parameters
        public static List<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {new ChromeDriver()},
                    {new FirefoxDriver()}
            });
        }

        @Test
        public void it_should_open_item_detail_by_img() throws InterruptedException {
            driver.get("https://www.saucedemo.com/");
            connect();

            WebElement itemLink = driver.findElement(By.id("item_4_img_link"));

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(itemLink));

            itemLink.click();
            Thread.sleep(1000);

            assertTrue(driver.getCurrentUrl().contains("?id=4"));
        }
    }

}
