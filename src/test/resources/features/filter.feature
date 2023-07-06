Feature: Utilisation du filtre
  Scenario: Je filtre par prix croissant
    Given Je suis sur la page de connexion du site
    When Je saisis mes nom d'utilisateur et de mot de passe dans les champs de texte
      | username       | password      |
      | standard_user  | secret_sauce  |
    And Je clique sur le bouton de connexion
    And Je devrais voir la page d'accueil
    When Je sélectionne l'option de filtrage par prix croissant
    Then Les produits sont affichés par prix croissant