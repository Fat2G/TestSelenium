import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDemoqa {
    //    private WebDriver driver;
    List<WebDriver> drivers = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        drivers.add(new ChromeDriver());
        drivers.add(new FirefoxDriver());
//        drivers.add(new EdgeDriver());
    }

    @AfterEach
    public void quit() {
        for (WebDriver driver : drivers) {
            driver.quit();
        }
    }

    @Ignore
    public void it_should_not_register() throws InterruptedException {
        for (WebDriver driver : drivers) {

            driver.get("https://demoqa.com/register");

            WebElement firstName = driver.findElement(By.id("firstname"));
            WebElement lastName = driver.findElement(By.id("lastname"));
            WebElement userName = driver.findElement(By.id("userName"));
            WebElement password = driver.findElement(By.id("password"));

            firstName.sendKeys("Fat");
            lastName.sendKeys("G");
            userName.sendKeys("FatG");
            password.sendKeys("Infotel1!");

            Thread.sleep(3000);

            WebElement captchaCheckbox = driver.findElement(By.xpath("//span[@class='recaptcha-checkbox-borderAnimation']"));
            captchaCheckbox.click();
            Thread.sleep(3000);

            WebElement submitButton = driver.findElement(By.id("register"));
            submitButton.click();

            Thread.sleep(500);
            WebElement userExists = driver.findElement(By.id("name"));
            assertEquals("User exists!", userExists.getText());

        }
    }

    @Test
    public void it_should_login() throws InterruptedException {

        for (WebDriver driver : drivers) {

            driver.get("https://demoqa.com/login");

            WebElement userName = driver.findElement(By.id("userName"));
            WebElement password = driver.findElement(By.id("password"));

            userName.sendKeys("FatG");
            password.sendKeys("Infotel1!");

            WebElement submitButton = driver.findElement(By.id("login"));
            submitButton.click();
            Thread.sleep(500);

            // vérification que la page profile soit bien ouverte
            WebElement profileHeader = driver.findElement(By.className("main-header"));
            assertEquals("Profile", profileHeader.getText());

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
            // vérification de l'url de la page de profil
            String currentUrl = driver.getCurrentUrl();
            assertTrue(currentUrl.contains("profile"));
            System.out.println("Test passé avec " + driver.getClass().getSimpleName());
        }
    }

    @Test
    public void it_should_add_webTables() throws InterruptedException {
        for (WebDriver driver : drivers) {

            driver.get("https://demoqa.com/webtables");

            WebElement addBtn = driver.findElement(By.id("addNewRecordButton"));
            addBtn.click();
            Thread.sleep(500);

            WebElement firstName = driver.findElement(By.id("firstName"));
            WebElement lastName = driver.findElement(By.id("lastName"));
            WebElement userEmail = driver.findElement(By.id("userEmail"));
            WebElement age = driver.findElement(By.id("age"));
            WebElement salary = driver.findElement(By.id("salary"));
            WebElement department = driver.findElement(By.id("department"));

            firstName.sendKeys("Fat");
            lastName.sendKeys("Gassa");
            userEmail.sendKeys("test@test.fr");
            age.sendKeys("36");
            salary.sendKeys("500");
            department.sendKeys("Infotel");

            WebElement submitBtn = driver.findElement(By.id("submit"));
            submitBtn.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            List<WebElement> elements = driver.findElements(By.className("rt-tr"));
            boolean contentFound = false;

            for (WebElement elem : elements) {
                String content = elem.getText();


                if (content.contains("Fat") &&
                        content.contains("Gassa") &&
                        content.contains("test@test.fr") &&
                        content.contains("36") &&
                        content.contains("500") &&
                        content.contains("Infotel")) {
                    contentFound = true;
                    break;
                }
            }

            assertTrue(contentFound);
        }
    }
}


