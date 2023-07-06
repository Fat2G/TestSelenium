Feature: Connexion sur le site web

  Scenario: Je me connecte au site
    Given Je suis sur la page de connexion du site
    When Je saisis mes nom d'utilisateur et de mot de passe dans les champs de texte
      | username       | password      |
      | standard_user  | secret_sauce  |
    And Je clique sur le bouton de connexion
    Then Je devrais voir la page d'accueil

  Scenario: Mon compte est bloqué
    Given Je suis sur la page de connexion du site
    When Je saisis mes nom d'utilisateur et de mot de passe dans les champs de texte
      | username           | password      |
      | locked_out_user    | secret_sauce  |
    And Je clique sur le bouton de connexion
    Then Je devrais recevoir un message d'erreur

    Scenario: Je me déconnecte du site
      Given Je suis sur la page de connexion du site
      When Je saisis mes nom d'utilisateur et de mot de passe dans les champs de texte
        | username       | password      |
        | standard_user  | secret_sauce  |
      And Je clique sur le bouton de connexion
      Then Je devrais voir la page d'accueil
      And Je clique sur la barre de navigation
      And Je clique sur le bouton Logout
      Then Je devrais voir la page de connexion

      Scenario: J'essaye de me connecter avec un mauvais mail et mot de passe
        Given Je suis sur la page de connexion du site
        When Je saisis mes nom d'utilisateur et de mot de passe dans les champs de texte
          | username           | password      |
          | standard_user1    | secret_sauce  |
        And Je clique sur le bouton de connexion
        Then Je devrais recevoir une erreur