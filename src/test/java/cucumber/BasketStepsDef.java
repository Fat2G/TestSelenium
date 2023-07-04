package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasketStepsDef {
    private WebDriver driver;

    public BasketStepsDef(WebDriver driver) {
        this.driver = driver;
    }

    public void jeChoisisUnProduitAMettreAuPanier() {
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
    }

    public void jeCliqueSurLeLogoPanier() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    public void jeDevraisVoirLaPagePanier() {
        assertTrue(driver.getCurrentUrl().contains("cart.html"));

        WebElement cartTitle = driver.findElement(By.className("title"));
        assertEquals("Your Cart", cartTitle.getText());
    }

    public void jeVerifieQueLeProduitEstLeBon() {
        WebElement quantity = driver.findElement(By.className("cart_quantity"));
        assertEquals("1", quantity.getText());

        WebElement item = driver.findElement(By.className("inventory_item_name"));
        assertEquals("Sauce Labs Bike Light", item.getText());

        WebElement price = driver.findElement(By.className("inventory_item_price"));
        assertEquals("$9.99", price.getText());
    }

    public void jeCliqueSurLeBoutonPourPayer() {
        driver.findElement(By.id("checkout")).click();
    }

    public void jeRentreMesInformationsPersonnelles() {
        driver.findElement(By.id("first-name")).sendKeys("Fat");
        driver.findElement(By.id("last-name")).sendKeys("G");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
    }

    public void jeCliqueSurLeBoutonPourContinuer() {
        driver.findElement(By.id("continue")).click();
    }

    public void jeDevraisVoirLaPageDeResumeDeMaCommande() {
        assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"));

        WebElement cartTitle = driver.findElement(By.className("title"));
        assertEquals("Checkout: Overview", cartTitle.getText());
    }

    public void jeCliqueSurLeBoutonPourValiderMaCommande() {
        driver.findElement(By.id("finish")).click();
    }

    public void jeDevraisVoirLaPageDeConfirmationDeLaCommande() {
        WebElement checkoutTitle = driver.findElement(By.className("title"));
        assertEquals("Checkout: Complete!", checkoutTitle.getText());

        WebElement checkoutSuccess = driver.findElement(By.className("complete-header"));
        assertEquals("Thank you for your order!", checkoutSuccess.getText());

    }

    public void jeCliqueSurLeBoutonRetour() {
        driver.findElement(By.id("back-to-products")).click();
    }
}
