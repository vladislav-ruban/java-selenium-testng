Feature: Products actions feature
  This feature deals with the actions can be done to products

  Scenario Outline: Add product to wishlist from search results
    Given User navigates to main page
    When User enters "<product>" to search bar
    And User adds to wishlist a '<product>'
    Then User removes remembered product from wishlist
    Examples:
      | product                   |
      | Apple iPhone 7 Plus 128Gb |