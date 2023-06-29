import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.AfterEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

@RunWith(Enclosed.class)
public class SwagLabsTest {
	
	@RunWith(Parameterized.class)
	public static class TestNetworkButtons {
		private       WebDriver driver;
		private final String    socialNetwork;
		
		public TestNetworkButtons (WebDriver driver, String socialNetwork) {
			this.driver = driver;
			this.socialNetwork = socialNetwork;
		}
		
		@Parameterized.Parameters
		public static List<Object[]> data () {
			return Arrays.asList(new Object[][] {{new ChromeDriver(), "twitter"}, {new ChromeDriver(), "facebook"},
												 {new ChromeDriver(), "linkedin"}, {new FirefoxDriver(), "twitter"},
												 {new FirefoxDriver(), "facebook"}, {new FirefoxDriver(),
																					 "linkedin"}});
		}
		
		public void connect () {
			driver.get("https://www.saucedemo.com/");
			driver.findElement(By.id("user-name")).sendKeys("standard_user");
			driver.findElement(By.id("password")).sendKeys("secret_sauce");
			driver.findElement(By.id("login-button")).click();
		}
		
		@Test
		public void it_should_redirect_to_social_network () throws InterruptedException {
			connect();
			
			// Store the ID of the original window
			String originalWindow = driver.getWindowHandle();
			
			// Check we don't have other windows open already
			assert driver.getWindowHandles().size() == 1;
			
			driver.findElement(By.cssSelector(".social_" + socialNetwork + " a")).click();
			
			// Wait until there are 2 tabs
			new FluentWait<WebDriver>(driver).until(numberOfWindowsToBe(2));
			
			// Loop through until we find a new window handle
			for(String windowHandle : driver.getWindowHandles()) {
				if (!originalWindow.contentEquals(windowHandle)) {
					driver.switchTo().window(windowHandle);
					break;
				}
			}
			
			Thread.sleep(1500);
			String url = driver.getCurrentUrl();
			assertTrue(url.contains(socialNetwork + ".com"));
			assertTrue(url.contains("saucelabs") || url.contains("sauce-labs"));
			
			this.driver.quit();
		}
	}
}
