Feature: Je cherche un produit

  Scenario: Je rentre une url de produit en omettant l'id
    Given Je suis sur la page de connexion du site
    When Je saisis mes nom d'utilisateur et de mot de passe dans les champs de texte
      | username      | password     |
      | standard_user | secret_sauce |
    And Je clique sur le bouton de connexion
    Then Je devrais voir la page d'accueil
    When Je rentre l'url en dur sans l'id du produit
    Then Je ne verrais pas la page détail du produit


  Scenario Outline: Open item detail page by clicking image
    Given I am on the website login page with "<driver>"
    When I enter correct login information
    And I click on the login button
    And I click on the image of product with <id>
    Then I should be on the item detail page of product with <id>

    Examples:
      | driver  | id |
      | chrome  | 3  |
      | chrome  | 4  |
      | chrome  | 2  |
      | firefox | 3  |
      | firefox | 2  |
      | firefox | 5  |


  Scenario Outline: Open item detail page by clicking title
    Given I am on the website login page with "<driver>"
    When I enter correct login information
    And I click on the login button
    And I click on the name of product with <id>
    Then I should be on the item detail page of product with <id>

    Examples:
      | driver  | id |
      | chrome  | 3  |
      | chrome  | 4  |
      | chrome  | 2  |
      | firefox | 3  |
      | firefox | 2  |
      | firefox | 5  |
