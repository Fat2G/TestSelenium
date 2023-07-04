package cucumber;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

public class CucumberStepsDefinitions {
    WebDriver driver;
    
    @Given("I am on the website login page with {string}")
    public void iAmOnTheWebsiteLoginPageWith (String drivername) {
        if (drivername.equals("chrome")){
            driver = new ChromeDriver();
        } else if (drivername.equals("firefox")){
            driver = new FirefoxDriver();
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
    public void iClickOnTheLoginButton () throws InterruptedException {
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1500);
    }
    
    @Then("I should be on the inventory page")
    public void iShouldBeOnTheInventoryPage () {
        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        driver.quit();
    }
}
