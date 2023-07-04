Feature: Connection sur le site web

  Scenario: Je me connecte au site
    Given Je suis sur la page de connexion du site
    When Je saisis mes nom d'utilisateur et de mot de passe dans les champs de texte
      | username       | password      |
      | standard_user  | secret_sauce  |
    And Je clique sur le bouton de connection
    Then Je devrais voir la page d'accueil

  Scenario: Mon compte est bloqué
    Given Je suis sur la page de connexion du site
    When Je saisis mes nom d'utilisateur et de mot de passe dans les champs de texte
      | username           | password      |
      | locked_out_user    | secret_sauce  |
    And Je clique sur le bouton de connection
    Then Je devrais recevoir un message d'erreur