import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
}
