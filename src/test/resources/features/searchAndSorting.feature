Feature: search and sorting results feature
  This feature deals with the search and sorting functionality of the application

  Scenario Outline: Search different queries
    Given User navigates to main page
    When User enters '<searchQuery>' to search bar
    Then Search result page with results title contains '<searchQuery>' is displayed
    Examples:
      | searchQuery |
      | dell        |
      | apple       |
      | acer aspire |

  Scenario Outline: Sort search results low to high
    Given User navigates to main page
    When User enters "<searchQuery>" to search bar
    Then Search result page with results title contains "<searchQuery>" is displayed
    When User sorts by price low to high
    Then Prices should be sorted low to high
    Examples:
      | searchQuery   |
      | acer aspire   |

  Scenario Outline: Sort search results high to low
    Given User navigates to main page
    When User enters "<searchQuery>" to search bar
    Then Search result page with results title contains "<searchQuery>" is displayed
    When User sorts by price high to low
    Then Prices should be sorted high to low
    Examples:
      | searchQuery   |
      | acer aspire   |