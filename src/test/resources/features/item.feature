Feature: Je cherche un produit
  Scenario: Je me connecte au site et je clique sur un produit
    Given Je suis sur la page de connexion du site
    When Je saisis mes nom d'utilisateur et de mot de passe dans les champs de texte
      | username       | password      |
      | standard_user  | secret_sauce  |
    And Je clique sur le bouton de connexion
    Then Je devrais voir la page d'accueil
    When Je clique sur un produit
    Then Je devrais voir la page détail du produit

  Scenario: Je rentre une url de produit en omettant l'id
    Given Je suis sur la page de connexion du site
    When Je saisis mes nom d'utilisateur et de mot de passe dans les champs de texte
      | username       | password      |
      | standard_user  | secret_sauce  |
    And Je clique sur le bouton de connexion
    Then Je devrais voir la page d'accueil
    When Je rentre l'url en dur sans l'id du produit
    Then Je ne verrais pas la page détail du produit