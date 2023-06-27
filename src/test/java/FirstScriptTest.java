import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstScriptTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https:/www.selenium.dev/selenium/web/web-form.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }

    @Test
    public void eightComponents() {
        // looking for website title
        String title = driver.getTitle();
        assertEquals("Web form", title);

        // find element
        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        // action on element
        textBox.sendKeys("Selenium");
        submitButton.click();

        // request element information
        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);

    }

    @Test
    public void what_is_my_color() {
        WebElement colorPicker = driver.findElement(By.name("my-colors"));
        String colorValue = colorPicker.getAttribute("value");
        assertEquals("#563d7c", colorValue);
    }

}
