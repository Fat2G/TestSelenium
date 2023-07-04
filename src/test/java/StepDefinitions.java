import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Map;

public class StepDefinitions {
    private WebDriver driver;
    private LoginStepsDef loginSteps;
    private ItemStepsDef itemSteps;
    private BasketStepsDef basketSteps;


    @Before
    public void setup() {
        driver = new ChromeDriver();
        loginSteps = new LoginStepsDef(driver);
        basketSteps = new BasketStepsDef(driver);
        itemSteps = new ItemStepsDef(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    //teste de connexion
    @Given("Je suis sur la page de connexion du site")
    public void jeSuisSurLaPageDeConnexionDuSite() {
        loginSteps.jeSuisSurLaPageDeConnexionDuSite();
    }

    @When("Je saisis mes nom d'utilisateur et de mot de passe dans les champs de texte")
    public void jeSaisisMesNomDutilisateurEtDeMotDePasseDansLesChampsDeTexte(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String username = data.get(0).get("username");
        String password = data.get(0).get("password");
        loginSteps.jeSaisisMesNomDutilisateurEtDeMotDePasseDansLesChampsDeTexte(username, password);
    }

    @And("Je clique sur le bouton de connection")
    public void jeCliqueSurLeBoutonDeConnexion() throws InterruptedException {
        loginSteps.jeCliqueSurLeBoutonDeConnexion();
    }

    @Then("Je devrais voir la page d'accueil")
    public void jeDevraisVoirLaPageDaccueuil() {
        loginSteps.jeDevraisVoirLaPageDaccueuil();
    }

    @Then ("Je devrais recevoir un message d'erreur")
    public void JeDevraisRecevoirUnMessageDerreur() {
        loginSteps.JeDevraisRecevoirUnMessageDerreur();
    }

    // teste sur les items
    @When("Je clique sur un produit")
    public void jeCliqueSurUnProduit() {
        itemSteps.jeCliqueSurUnProduit();
    }

    @Then("Je devrais voir la page détail du produit")
    public void JeDevraisVoirLaPageDetailDuProduit() {
        itemSteps.JeDevraisVoirLaPageDetailDuProduit();
    }

    @Then("Je rentre l'url en dur sans l'id du produit")
    public void JeRentreLurlEnDurSansLidDuProduit() {
        itemSteps.JeRentreLurlEnDurSansLidDuProduit();
    }

    @Then("Je ne verrais pas la page détail du produit")
    public void JeNeVerraisPasLaPageDetailDuProduit() {
        itemSteps.JeNeVerraisPasLaPageDetailDuProduit();
    }

    // teste d'une commande a effectuer
    @When("Je choisis un produit à mettre au panier")
    public void jeChoisisUnProduitAMettreAuPanier() {
        basketSteps.jeChoisisUnProduitAMettreAuPanier();
    }

    @And("Je clique sur le logo panier")
    public void jeCliqueSurLeLogoPanier() {
        basketSteps.jeCliqueSurLeLogoPanier();
    }

    @Then("Je devrais voir la page panier")
    public void jeDevraisVoirLaPagePanier() {
        basketSteps.jeDevraisVoirLaPagePanier();
    }

    @And("Je vérifie que le produit est le bon")
    public void jeVerifieQueLeProduitEstLeBon() {
        basketSteps.jeVerifieQueLeProduitEstLeBon();
    }

    @And("Je clique sur le bouton pour payer")
    public void jeCliqueSurLeBoutonPourPayer() {
        basketSteps.jeCliqueSurLeBoutonPourPayer();
    }

    @When("Je rentre mes informations personnelles")
    public void jeRentreMesInformationsPersonnelles() {
        basketSteps.jeRentreMesInformationsPersonnelles();
    }

    @And("Je clique sur le bouton pour continuer")
    public void jeCliqueSurLeBoutonPourContinuer() {
        basketSteps.jeCliqueSurLeBoutonPourContinuer();
    }

    @Then("Je devrais voir la page de résumé de ma commande")
    public void jeDevraisVoirLaPageDeResumeDeMaCommande() {
        basketSteps.jeDevraisVoirLaPageDeResumeDeMaCommande();
    }

    @When("Je clique sur le bouton pour valider ma commande")
    public void jeCliqueSurLeBoutonPourValiderMaCommande() {
        basketSteps.jeCliqueSurLeBoutonPourValiderMaCommande();
    }

    @Then("Je devrais voir la page de confirmation de la commande")
    public void jeDevraisVoirLaPageDeConfirmationDeLaCommande() {
        basketSteps.jeDevraisVoirLaPageDeConfirmationDeLaCommande();
    }

    @And("Je clique sur le bouton retour")
    public void jeCliqueSurLeBoutonRetour() {
        basketSteps.jeCliqueSurLeBoutonRetour();
    }
}

