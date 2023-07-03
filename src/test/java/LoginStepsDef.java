import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginStepsDef {
    private WebDriver driver;

    public LoginStepsDef(WebDriver driver) {
        this.driver = driver;
    }

    public void jeSuisSurLaPageDeConnectionDuSite() {
        driver.get("https://www.saucedemo.com/");
    }

    public void jeSaisisMesNomDutilisateurEtDeMotDePasseDansLesChampsDeTexte() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    public void jeCliqueSurLeBoutonDeConnection() {
        driver.findElement(By.id("login-button")).click();
    }

    public void jeDevraisVoirLaPageDaccueuil() {
        String expectedResults = "Swag Labs";
        Assert.assertEquals(expectedResults, driver.findElement(By.className("app_logo")).getText());

    }
}
