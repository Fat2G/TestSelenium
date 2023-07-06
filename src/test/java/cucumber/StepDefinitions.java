package cucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {
    private WebDriver driver;
    private LoginStepsDef loginSteps;
    private ItemStepsDef itemSteps;
    private BasketStepsDef basketSteps;
    private FilterStepsDef filterSteps;


    @Before
    public void setup() {
        driver = new ChromeDriver();
        loginSteps = new LoginStepsDef(driver);
        basketSteps = new BasketStepsDef(driver);
        itemSteps = new ItemStepsDef(driver);
        filterSteps = new FilterStepsDef(driver);
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

    @And("Je clique sur le bouton de connexion")
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

    // filtrage par prix croissant

    @When("Je sélectionne l'option de filtrage par prix croissant")
    public void jeSelectionneLOptionDeFiltrageParPrixCroissant() throws InterruptedException {
        filterSteps.jeSelectionneLOptionDeFiltrageParPrixCroissant();
    }

    @Then("Les produits sont affichés par prix croissant")
    public void lesProduitsSontAffichesParPrixCroissant() {
        filterSteps.lesProduitsSontAffichesParPrixCroissant();
    }

    //Test Logout

    @And("Je clique sur la barre de navigation")
    public void jeCliqueSurLaBarreDeNavigation() throws InterruptedException {
        loginSteps.jeCliqueSurLaBarreDeNavigation();
    }

    @And("Je clique sur le bouton Logout")
    public void jeCliqueSurLeBoutonLogout() throws InterruptedException {
        loginSteps.jeCliqueSurLeBoutonLogout();
    }

    @Then("Je devrais voir la page de connexion")
    public void jeDevraisVoirLaPageDeConnexion() {
        loginSteps.jeDevraisVoirLaPageDeConnexion();
    }


    @When("je choisis un article à mettre dans le panier")
    public void jeChoisisUnArticleÀMettreDansLePanier() {
        basketSteps.jeChoisisUnProduitAMettreAuPanier();
    }

    @And("je clique sur le bouton Add to cart")
    public void jeCliqueSurLeBoutonAddToCart() throws InterruptedException {
        basketSteps.jeCliqueSurLeBoutonAddToCart();
    }


    @Then("une notification d'ajout a été ajouté")
    public void uneNotificationDAjoutAeteAjoute() {
        basketSteps.uneNotificationDAjoutAeteAjoute();
    }

    @When("je choisis un article à supprimer du panier")
    public void jeChoisisUnArticleÀSupprimerDuPanier() throws InterruptedException {
        basketSteps.jeChoisisUnArticleÀSupprimerDuPanier();
    }

    @And("je clique sur le bouton remove")
    public void jeCliqueSurLeBoutonRemove() throws InterruptedException {
        basketSteps.jeCliqueSurLeBoutonRemove();
    }

    @Then("la notification d'ajout sera decremente")
    public void laNotificationDAjoutSeraDecremente() {
        basketSteps.laNotificationDAjoutSeraDecremente();
    }

    @Then("Je devrais recevoir une erreur")
    public void jeDevraisRecevoirUneErreur() {
        loginSteps.jeDevraisRecevoirUneErreur();
    }
}

