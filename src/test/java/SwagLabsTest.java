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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    }
}
