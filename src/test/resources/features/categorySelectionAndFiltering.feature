Feature: Category selection and filtering feature
  This feature deals with the category selection and filtering products functonality of the application

  Scenario: Category selection
    Given I navigate to main page
    When I open category mobile connection and subcategory mobile phones
    Then I should see category mobile phones page

  Scenario Outline: Filter by manufacturer
    Given I navigate to main page
    When I open category mobile connection and subcategory mobile phones
    And I filter products by manufacturer "<manufacturerAlias>"
    Then I should see products by chosen manufacturer
    Examples:
      | manufacturerAlias |
      | apple             |

  Scenario Outline: Filter by input price
    Given I navigate to main page
    When I open category mobile connection and subcategory mobile phones
    And I filter products by input price from "<minPrice>" to "<maxPrice>"
    Then I should see products with price in range from "<minPrice>" to "<maxPrice>"
    Examples:
      | minPrice | maxPrice |
      | 1000     | 2000     |
      | 2000     | 3000     |
      | 3000     | 4000     |

  Scenario Outline: Filter by price fixed
    Given I navigate to main page
    When I open category mobile connection and subcategory mobile phones
    And I filter products by "<maxPriceFixed>"
    Then I should see products with prices up to "<maxPriceFixed>"
    Examples:
      | maxPriceFixed |
      | 1300          |
      | 2500          |
      | 46000         |