import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemStepsDef {
    private WebDriver driver;

    public ItemStepsDef(WebDriver driver) {
        this.driver = driver;
    }

    public void jeCliqueSurUnProduit() {
        WebElement itemLink = driver.findElement(By.id("item_4_img_link"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(itemLink));

        itemLink.click();
    }

    public void JeDevraisVoirLaPageDetailDuProduit() {
        assertTrue(driver.getCurrentUrl().contains("?id=4"));
    }

    public void JeRentreLurlEnDurSansLidDuProduit() {
        driver.get("https://www.saucedemo.com/inventory-item.html");
    }

    public void JeNeVerraisPasLaPageDetailDuProduit() {
        WebElement itemLink = driver.findElement(By.cssSelector(".inventory_details_name"));
        String msgError = itemLink.getText();

        assertEquals("ITEM NOT FOUND", msgError);
    }
}
