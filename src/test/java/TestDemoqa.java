import org.junit.Ignore;
import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDemoqa {
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

    @Test
    public void it_shoud_double_click() throws InterruptedException {
        for (WebDriver driver : drivers) {

            driver.get("https://demoqa.com/buttons");

            Actions actions = new Actions(driver);
            WebElement btnDbleClk = driver.findElement(By.id("doubleClickBtn"));

            actions.doubleClick(btnDbleClk).perform();
            Thread.sleep(1000);

            WebElement dbleClkMsg = driver.findElement(By.id("doubleClickMessage"));
            assertEquals("You have done a double click", dbleClkMsg.getText());

            System.out.println("Test double click passé avec " + driver.getClass().getSimpleName());
        }
    }

    @Test
    public void it_shoud_right_click() throws InterruptedException {
        for (WebDriver driver : drivers) {

            driver.get("https://demoqa.com/buttons");

            Actions actions = new Actions(driver);
            WebElement btnRightClk = driver.findElement(By.id("rightClickBtn"));

            actions.contextClick(btnRightClk).perform();
            Thread.sleep(500);

            WebElement btnRightMsg = driver.findElement(By.id("rightClickMessage"));
            assertEquals("You have done a right click", btnRightMsg.getText());

            System.out.println("Test click droit passé avec " + driver.getClass().getSimpleName());
        }

    }

    @Ignore
    public void it_should_select_radio_yes() throws InterruptedException {
        for (WebDriver driver : drivers) {

            driver.get("https://demoqa.com/radio-button");

//            WebElement yesBtnRadio = driver.findElement(By.id("yesRadio"));
            WebElement yesBtnRadio = driver.findElement(By.name("like"));
            yesBtnRadio.click();

            Thread.sleep(1000);

            // vérification du message de succes
            WebElement msgSuccess = driver.findElement(By.className("text-success"));
            assertEquals("Yes", msgSuccess.getText());

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            System.out.println("Test radio \"Yes\" passé avec " + driver.getClass().getSimpleName());
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
            System.out.println("Test web table passé avec " + driver.getClass().getSimpleName());
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

            Actions actions = new Actions(driver);
            WebElement captchaCheckbox = driver.findElement(By.className("recaptcha-checkbox-border"));
//            captchaCheckbox.click();
            actions.click(captchaCheckbox).perform();
            Thread.sleep(1000);

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
            System.out.println("Test login passé avec " + driver.getClass().getSimpleName());
        }
    }

}


