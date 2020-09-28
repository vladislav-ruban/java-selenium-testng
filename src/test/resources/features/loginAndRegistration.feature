Feature: Login and registration feature
  This feature deals with the login and registration functionality of the application

  Scenario: Login with valid credentials
    Given I navigate to main page
    When I login with email "yoxanov792@mail7d.com" and password "12345678"
    Then I see logged state instead of login button
    And I log out

  Scenario: Login with unconfirmed email
    Given I navigate to main page
    When I login with email "testaccount@testmail.com" and password "12345678"
    Then I see message informing me email unconfirmed

  Scenario: Login with empty fields
    Given I navigate to main page
    When I login with email "" and password ""
    Then I see errors under inputs