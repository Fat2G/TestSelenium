import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

@RunWith(Enclosed.class)
public class SwagLabsTest {
    private static final int TAX_PERCENT = 8;
    
    
    
    @RunWith(Parameterized.class)
    public static class TestNetworkButtons {
        private WebDriver driver;
        private String    socialNetwork;
        
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
            new FluentWait<>(driver).until(numberOfWindowsToBe(2));
            
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
    
    
    
    @RunWith(Parameterized.class)
    public static class TestRecapCheckout {
        private WebDriver driver;
        
        public TestRecapCheckout (WebDriver driver) {
            this.driver = driver;
        }
        
        @Parameterized.Parameters
        public static List<Object[]> data () {
            return Arrays.asList(new Object[][] {{new ChromeDriver()}, {new FirefoxDriver()}});
        }
        
        public void connect () {
            driver.get("https://www.saucedemo.com/");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
        }
        
        @Test
        public void it_should_calculate_total () throws InterruptedException {
            connect();
            
            List<WebElement>    elementItems   = driver.findElements(By.className("inventory_item"));
            List<InventoryItem> inventoryItems = new ArrayList<>();
            for(WebElement item : elementItems) {
                inventoryItems.add(new InventoryItem(item));
            }
            
            double totalPrice = 0;
            
            // Add random items
            for(InventoryItem item : inventoryItems) {
                if (new Random().nextBoolean()) {
                    item.getAddToCartButton().click();
                    Thread.sleep(200);
                    totalPrice += item.getPrice();
                }
            }
            
            // Go to checkout page
            driver.findElement(By.id("shopping_cart_container")).click();
            Thread.sleep(200);
            driver.findElement(By.id("checkout")).click();
            Thread.sleep(200);
            
            // Enter Checkout information
            driver.findElement(By.id("first-name")).sendKeys("first name");
            driver.findElement(By.id("last-name")).sendKeys("last name");
            driver.findElement(By.id("postal-code")).sendKeys("77777");
            
            driver.findElement(By.id("continue")).click();
            Thread.sleep(500);
            
            String summary_subtotal_label     = driver.findElement(By.className("summary_subtotal_label")).getText();
            String summary_subTotal_formatted = summary_subtotal_label.split("Item total: \\$")[1];
            double summary_subtotal = Double.parseDouble(summary_subTotal_formatted);
            
            // subtotal // WARNING ! The site may show values such as 59.980000000000004
            assertEquals(totalPrice, summary_subtotal, 0.006);
            
            // tax
            String summary_tax_label     = driver.findElement(By.className("summary_tax_label")).getText();
            String summary_tax_formatted = summary_tax_label.split("Tax: \\$")[1];
            double expectedTax           = totalPrice * TAX_PERCENT / 100;
            assertEquals(expectedTax, Double.parseDouble(summary_tax_formatted), 0.006);
            
            // total
            String summary_total_label     = driver.findElement(By.className("summary_total_label")).getText();
            String summary_total_formatted = summary_total_label.split("Total: \\$")[1];
            double summary_total           = Double.parseDouble(summary_total_formatted);
            assertEquals(totalPrice + expectedTax, summary_total, 0.006);
            
            driver.quit();
        }
    }
}
