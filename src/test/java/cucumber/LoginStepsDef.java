package cucumber;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginStepsDef {
    private WebDriver driver;

    public LoginStepsDef(WebDriver driver) {
        this.driver = driver;
    }

    public void jeSuisSurLaPageDeConnexionDuSite() {
        driver.get("https://www.saucedemo.com/");
    }

    public void jeSaisisMesNomDutilisateurEtDeMotDePasseDansLesChampsDeTexte(String username, String password) throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void jeCliqueSurLeBoutonDeConnexion() throws InterruptedException {
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(500);
    }

    public void jeDevraisVoirLaPageDaccueuil() {
        String expectedResults = "Swag Labs";
        Assert.assertEquals(expectedResults, driver.findElement(By.className("app_logo")).getText());
    }

    public void JeDevraisRecevoirUnMessageDerreur() {
        String errorMsg = "Epic sadface: Sorry, this user has been locked out.";
        Assert.assertEquals(errorMsg, driver.findElement(By.cssSelector("div.error-message-container>h3")).getText());
    }

    public void jeCliqueSurLaBarreDeNavigation() throws InterruptedException {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(500);

    }
    public void jeCliqueSurLeBoutonLogout() throws InterruptedException {
        driver.findElement(By.id("logout_sidebar_link")).click();
        Thread.sleep(500);
    }
    public void jeDevraisVoirLaPageDeConnexion() {
        assertTrue(driver.getCurrentUrl().contains("https://www.saucedemo.com/"));
    }

    public void jeDevraisRecevoirUneErreur() {
        String errorMsg = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(errorMsg, driver.findElement(By.cssSelector("div .error-message-container > h3")).getText());
    }
}
