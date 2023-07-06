Feature: Je fais un achat

  Scenario: Je choisis un article et l'achète
    Given Je suis sur la page de connexion du site
    When Je saisis mes nom d'utilisateur et de mot de passe dans les champs de texte
      | username       | password      |
      | standard_user  | secret_sauce  |
    And Je clique sur le bouton de connexion
    Then Je devrais voir la page d'accueil

    When Je choisis un produit à mettre au panier
    And Je clique sur le logo panier
    Then Je devrais voir la page panier
    And Je vérifie que le produit est le bon
    And Je clique sur le bouton pour payer

    When Je rentre mes informations personnelles
    And Je clique sur le bouton pour continuer
    Then Je devrais voir la page de résumé de ma commande

    When Je clique sur le bouton pour valider ma commande
    Then Je devrais voir la page de confirmation de la commande
    And Je clique sur le bouton retour

    Scenario: J'ajoute un article dans le panier
      Given Je suis sur la page de connexion du site
      When Je saisis mes nom d'utilisateur et de mot de passe dans les champs de texte
        | username       | password      |
        | standard_user  | secret_sauce  |
      And Je clique sur le bouton de connexion
      Then Je devrais voir la page d'accueil

      When je choisis un article à mettre dans le panier
      And je clique sur le bouton Add to cart
      Then une notification d'ajout a été ajouté

  Scenario: Je supprime un article dans le panier
    Given Je suis sur la page de connexion du site
    When Je saisis mes nom d'utilisateur et de mot de passe dans les champs de texte
      | username       | password      |
      | standard_user  | secret_sauce  |
    And Je clique sur le bouton de connexion
    Then Je devrais voir la page d'accueil

    When je choisis un article à supprimer du panier
    And je clique sur le bouton remove
    Then la notification d'ajout sera decremente