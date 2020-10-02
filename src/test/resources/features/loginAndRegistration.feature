Feature: Login and registration feature
  This feature deals with the login and registration functionality of the application

  Scenario: Login with valid credentials
    Given User navigates to main page
    When User logins with email "yoxanov792@mail7d.com" and password "12345678"
    Then Logged state instead of login button is displayed
    And User logs out

  Scenario: Login with unconfirmed email
    Given User navigates to main page
    When User logins with email "testaccount@testmail.com" and password "12345678"
    Then Message informing that email unconfirmed is displayed

  Scenario: Login with empty fields
    Given User navigates to main page
    When User logins with email "" and password ""
    Then Errors under inputs are displayed