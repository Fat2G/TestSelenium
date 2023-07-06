package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CucumberStepsDefinitions {
    WebDriver driver;
    
    @After
    public void closeDriverAfterEachScenario () {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Given("I am on the website login page with {string}")
    public void iAmOnTheWebsiteLoginPageWith (String drivername) {
        switch (drivername) {
            case "chrome" -> driver = new ChromeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            default -> throw new IllegalStateException("Unexpected value: " + drivername);
        }
        driver.get("https://www.saucedemo.com");
    }
    
    @When("I enter correct login information")
    public void iEnterCorrectLoginInformation () {
        String username = "standard_user";
        
        String password = "secret_sauce";
        
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }
    
    @And("I click on the login button")
    public void iClickOnTheLoginButton () {
        driver.findElement(By.id("login-button")).click();
    }
    
    @Then("I should be on the inventory page")
    public void iShouldBeOnTheInventoryPage () {
        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }
    
    @And("I click on the image of product with {int}")
    public void iClickOnTheImageOfProductWith (int id) {
        WebElement itemLink = driver.findElement(By.id("item_" + id + "_img_link"));
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(itemLink));
        
        itemLink.click();
    }
    
    @And("I click on the name of product with {int}")
    public void iClickOnTheNameOfProductWithId (int id) {
        WebElement itemLink = driver.findElement(By.cssSelector("#item_" + id + "_title_link > div"));
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(itemLink));
        
        itemLink.click();
    }
    
    @Then("I should be on the item detail page of product with {int}")
    public void iShouldBeOnTheItemDetailPageOfProductWithId (int id) {
        assertTrue(driver.getCurrentUrl().contains("?id=" + id));
    }
}
