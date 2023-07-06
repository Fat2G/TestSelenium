import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

@RunWith(Enclosed.class)
public class SwagLabsTest {
    private static final int TAX_PERCENT = 8;
    
    
    @RunWith(Parameterized.class)
    public static class Login {
        private WebDriver driver;
        private String    username;
        private String    password;
        
        public Login (WebDriver driver, String username) {
            this.driver = driver;
            this.username = username;
            this.password = "secret_sauce";
        }
        
        @Parameterized.Parameters
        public static List<Object[]> data () {
            return Arrays.asList(new Object[][] {{new ChromeDriver(), "standard_user"}, {new ChromeDriver(),
                                                                                         "problem_user"},
                                                 {new ChromeDriver(), "performance_glitch_user"},
                                                 {new FirefoxDriver(), "standard_user"}, {new FirefoxDriver(),
                                                                                          "problem_user"},
                                                 {new FirefoxDriver(), "performance_glitch_user"}});
        }
        
        @AfterEach
        public void quit () {
            driver.quit();
            driver.close();
        }
        
        public void printMsg (String name) {
            System.out.println("Test " + name + " passé avec " + driver.getClass().getSimpleName());
        }
        
        @Test
        public void it_should_login () throws InterruptedException {
            
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
        public void it_should_not_login () throws InterruptedException {
            
            driver.get("https://www.saucedemo.com/");
            
            driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
            driver.findElement(By.id("password")).sendKeys(this.password);
            
            driver.findElement(By.id("login-button")).click();
            Thread.sleep(1000);
            
            String errorMsg = "Epic sadface: Sorry, this user has been locked out.";
            
            assertFalse(driver.getCurrentUrl().contains("inventory.html"));
            assertEquals(errorMsg,
                driver.findElement(By.cssSelector("div.error-message-container.error>h3")).getText());
            
            printMsg("error login");
        }
    }
    
    
    @RunWith(Parameterized.class)
    public static class Item {
        private WebDriver driver;
        
        public Item (WebDriver driver) {
            this.driver = driver;
        }
        
        @Parameterized.Parameters
        public static List<Object[]> data () {
            return Arrays.asList(new Object[][] {{new ChromeDriver()}, {new FirefoxDriver()}});
        }
        
        public void connect () {
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
        }
        
        @Test
        public void it_should_open_item_detail_by_img () throws InterruptedException {
            driver.get("https://www.saucedemo.com/");
            connect();
            
            WebElement itemLink = driver.findElement(By.id("item_4_img_link"));
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(itemLink));
            
            itemLink.click();
            Thread.sleep(1000);
            
            assertTrue(driver.getCurrentUrl().contains("?id=4"));
        }
        
        @Test
        public void it_should_open_item_detail_by_title () throws InterruptedException {
            driver.get("https://www.saucedemo.com/");
            connect();
            
            WebElement itemLink = driver.findElement(By.cssSelector("#item_4_title_link > div"));
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(itemLink));
            
            itemLink.click();
            Thread.sleep(1000);
            
            assertTrue(driver.getCurrentUrl().contains("?id=4"));
        }
        
        @Test
        public void it_should_show_item_not_found () throws InterruptedException {
            driver.get("https://www.saucedemo.com/");
            connect();
            driver.get("https://www.saucedemo.com/inventory-item.html");
            
            WebElement itemLink = driver.findElement(By.cssSelector(".inventory_details_name"));
            String     msgError = itemLink.getText();
            
            assertEquals("ITEM NOT FOUND", msgError);
        }
        
        @Test
        public void it_should_logout () throws InterruptedException {
            driver.get("https://www.saucedemo.com/");
            
            connect();
            
            WebElement burgerBtn = driver.findElement(By.id("react-burger-menu-btn"));
            burgerBtn.click();
            
            Thread.sleep(500);
            WebElement logoutBtn = driver.findElement(By.id("logout_sidebar_link"));
            logoutBtn.click();
            
            Thread.sleep(1000);
            
            String currentUrl = driver.getCurrentUrl();
            assertEquals("https://www.saucedemo.com/", currentUrl);
        }
        
        
        @Test
        public void it_should_add_basket () throws InterruptedException {
            driver.get("https://www.saucedemo.com/");
            
            connect();
            
            WebElement addToCartBtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
            addToCartBtn.click();
            
            Thread.sleep(500);
            
            String articleNumber = driver.findElement(By.className("shopping_cart_badge")).getText();
            
            assertEquals("1", articleNumber);
        }
        
        @Test
        public void it_should_remove () throws InterruptedException {
            driver.get("https://www.saucedemo.com/");
            
            connect();
            
            WebElement addToCartBtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
            addToCartBtn.click();
            
            Thread.sleep(500);
            
            WebElement addToCartBtn2 = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
            addToCartBtn2.click();
            
            Thread.sleep(500);
            
            WebElement addToCartBtn3 = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
            addToCartBtn3.click();
            
            Thread.sleep(500);
            
            WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-bike-light"));
            removeBtn.click();
            
            Thread.sleep(500);
            
            WebElement removeBtn2 = driver.findElement(By.id("remove-sauce-labs-backpack"));
            removeBtn2.click();
            
            Thread.sleep(500);
            
            String articleNumber = driver.findElement(By.className("shopping_cart_badge")).getText();
            
            assertEquals("1", articleNumber);
        }
        
        @Test
        public void it_should_not_access_pages_if_not_logged () throws InterruptedException {
            driver.get("https://www.saucedemo.com/");
            
            WebElement userName = driver.findElement(By.id("user-name"));
            WebElement password = driver.findElement(By.id("password"));
            
            userName.sendKeys("standard_user1");
            password.sendKeys("secret_sauce");
            
            WebElement loginBtn = driver.findElement(By.id("login-button"));
            loginBtn.click();
            
            Thread.sleep(1000);
            
            WebElement errorNotif = driver.findElement(By.cssSelector("div .error-message-container > h3"));
            
            assertEquals("Epic sadface: Username and password do not match any user in this service",
                errorNotif.getText());
        }
    }
    
    
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
                                                 {new FirefoxDriver(), "facebook"}, {new FirefoxDriver(), "linkedin"}});
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
            double summary_subtotal           = Double.parseDouble(summary_subTotal_formatted);
            
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
