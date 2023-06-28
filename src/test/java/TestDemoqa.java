import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static java.time.Instant.now;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestDemoqa {
	private WebDriver driver;
	
	public TestDemoqa (WebDriver driver) {
		this.driver = driver;
	}
	
	@Parameterized.Parameters
	public static List<Object[]> data () {
		return Arrays.asList(new Object[][] {{new ChromeDriver()}, {new FirefoxDriver()}});
	}
	
	@AfterEach
	public void quit () {
		driver.quit();
	}
	
	@Test
	public void it_shoud_double_click () throws InterruptedException {
		driver.get("https://demoqa.com/buttons");
		
		Actions    actions    = new Actions(driver);
		WebElement btnDbleClk = driver.findElement(By.id("doubleClickBtn"));
		
		actions.doubleClick(btnDbleClk).perform();
		Thread.sleep(1500);
		
		WebElement dbleClkMsg = driver.findElement(By.id("doubleClickMessage"));
		assertEquals("You have done a double click", dbleClkMsg.getText());
	}
	
	@Test
	public void it_shoud_right_click () throws InterruptedException {
		driver.get("https://demoqa.com/buttons");
		
		Actions    actions     = new Actions(driver);
		WebElement btnRightClk = driver.findElement(By.id("rightClickBtn"));
		
		Thread.sleep(1500);
		actions.contextClick(btnRightClk).perform();
		Thread.sleep(1500);
		
		WebElement btnRightMsg = driver.findElement(By.id("rightClickMessage"));
		assertEquals("You have done a right click", btnRightMsg.getText());
	}
	
	@Ignore
	public void it_should_select_radio_yes () throws InterruptedException {
		driver.get("https://demoqa.com/radio-button");
		
		//            WebElement yesBtnRadio = driver.findElement(By.id("yesRadio"));
		WebElement yesBtnRadio = driver.findElement(By.name("like"));
		yesBtnRadio.click();
		
		Thread.sleep(1500);
		
		// vérification du message de succes
		WebElement msgSuccess = driver.findElement(By.className("text-success"));
		assertEquals("Yes", msgSuccess.getText());
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
	}
	
	@Test
	public void it_should_add_webTables () throws InterruptedException {
		driver.get("https://demoqa.com/webtables");
		
		WebElement addBtn = driver.findElement(By.id("addNewRecordButton"));
		addBtn.click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		Thread.sleep(500);
		WebElement firstName = driver.findElement(By.id("firstName"));
		firstName.sendKeys("Fat");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		Thread.sleep(500);
		WebElement lastName = driver.findElement(By.id("lastName"));
		lastName.sendKeys("Gassa");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		Thread.sleep(500);
		WebElement userEmail = driver.findElement(By.id("userEmail"));
		userEmail.sendKeys("test@test.fr");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		Thread.sleep(500);
		WebElement age = driver.findElement(By.id("age"));
		age.sendKeys("36");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		Thread.sleep(500);
		WebElement salary = driver.findElement(By.id("salary"));
		salary.sendKeys("500");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		Thread.sleep(500);
		WebElement department = driver.findElement(By.id("department"));
		department.sendKeys("Infotel");
		
		
		Thread.sleep(500);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		WebElement submitBtn = driver.findElement(By.id("submit"));
		submitBtn.click();
		
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		
		List<WebElement> elements     = driver.findElements(By.className("rt-tr"));
		boolean          contentFound = false;
		
		for(WebElement elem : elements) {
			String content = elem.getText();
			if (content.contains("Fat") && content.contains("Gassa") && content.contains("test@test.fr") && content.contains("36") && content.contains("500") && content.contains("Infotel")) {
				contentFound = true;
				break;
			}
		}
		
		assertTrue(contentFound);
	}
	
	@Ignore
	public void it_should_not_register () throws InterruptedException {
		driver.get("https://demoqa.com/register");
		
		WebElement firstName = driver.findElement(By.id("firstname"));
		WebElement lastName  = driver.findElement(By.id("lastname"));
		WebElement userName  = driver.findElement(By.id("userName"));
		WebElement password  = driver.findElement(By.id("password"));
		
		firstName.sendKeys("Fat");
		lastName.sendKeys("G");
		userName.sendKeys("FatG");
		password.sendKeys("Infotel1!");
		
		Thread.sleep(1500);
		
		Actions    actions         = new Actions(driver);
		WebElement captchaCheckbox = driver.findElement(By.className("recaptcha-checkbox-border"));
		//            captchaCheckbox.click();
		actions.click(captchaCheckbox).perform();
		Thread.sleep(1500);
		
		WebElement submitButton = driver.findElement(By.id("register"));
		submitButton.click();
		
		Thread.sleep(1500);
		WebElement userExists = driver.findElement(By.id("name"));
		assertEquals("User exists!", userExists.getText());
	}
	
	@Test
	public void it_should_login () throws InterruptedException {
		driver.get("https://demoqa.com/login");
		
		Thread.sleep(500);
		WebElement userName = driver.findElement(By.id("userName"));
		Thread.sleep(500);
		WebElement password = driver.findElement(By.id("password"));
		
		userName.sendKeys("FatG");
		password.sendKeys("Infotel1!");
		
		Instant startSleep = now();
		Thread.sleep(500);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		Instant endSleep = now();
		
		WebElement submitButton = driver.findElement(By.id("login"));
		submitButton.click();
		
		// vérification que la page profile soit bien ouverte
		Thread.sleep(500);
		WebElement profileHeader = driver.findElement(By.className("main-header"));
		assertEquals("Profile", profileHeader.getText());
		
		Thread.sleep(500);
		// vérification de l'url de la page de profil
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.contains("profile"));
	}
	
	@Test
	public void it_should_navigate () {
		driver.get("https://demoqa.com/broken");
		assertEquals("https://demoqa.com/broken", driver.getCurrentUrl());
	}
}


