Feature: Products actions feature
  This feature deals with the actions can be done to products

  Scenario Outline: Add product to wishlist from search results
    Given I navigate to main page
    When I enter "<productName>" to search bar
    And I add to wishlist a "<productName>"
    Then I remove product "<productName>" from wishlist
    Examples:
      | productName               |
      | Apple iPhone 7 Plus 128Gb |