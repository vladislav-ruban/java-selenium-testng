Feature: Category selection and filtering feature
  This feature deals with the category selection and filtering products functonality of the application

  Scenario Outline: Category selection
    Given User navigates to main page
    When User hovers on category "<categoryToHover>"
    And User goes to category "<categoryToClick>"
    Then Remembered category is displayed
    Examples:
      | categoryToHover | categoryToClick               |
      | Мобильная связь | Мобильные телефоны, смартфоны |

  Scenario Outline: Filter by manufacturer
    Given User navigates to main page
    When User hovers on category "<categoryToHover>"
    And User goes to category "<categoryToClick>"
    And User filters products by manufacturer "<manufacturerName>"
    Then Products are filtered by remembered manufacturer
    Examples:
      | categoryToHover | categoryToClick               | manufacturerName |
      | Мобильная связь | Мобильные телефоны, смартфоны | Apple            |
      | Мобильная связь | Мобильные телефоны, смартфоны | Xiaomi           |
      | Мобильная связь | Мобильные телефоны, смартфоны | Samsung          |

  Scenario Outline: Filter by input price
    Given User navigates to main page
    When User hovers on category '<categoryToHover>'
    And User goes to category '<categoryToClick>'
    And User filters products by input price from '<minPrice>' to '<maxPrice>'
    Then Products with prices in range from '<minPrice>' to '<maxPrice>' displayed
    Examples:
      | categoryToHover | categoryToClick               | minPrice | maxPrice |
      | Мобильная связь | Мобильные телефоны, смартфоны | 1000     | 2000     |
      | Мобильная связь | Мобильные телефоны, смартфоны | 2000     | 3000     |
      | Мобильная связь | Мобильные телефоны, смартфоны | 3000     | 4000     |

  Scenario Outline: Filter by price fixed
    Given User navigates to main page
    When User hovers on category '<categoryToHover>'
    And User goes to category '<categoryToClick>'
    And User filters products by fixed price "<maxPriceFixed>"
    Then Products with prices up to "<maxPriceFixed>" displayed
    Examples:
      | categoryToHover | categoryToClick               | maxPriceFixed |
      | Мобильная связь | Мобильные телефоны, смартфоны | 1300          |
      | Мобильная связь | Мобильные телефоны, смартфоны | 2500          |
      | Мобильная связь | Мобильные телефоны, смартфоны | 46000          |
