Feature: search and sorting results feature
  This feature deals with the search and sorting functionality of the application

  Scenario Outline: Search different queries
    Given I navigate to main page
    When I enter "<searchQuery>" to search bar
    Then I should see search result page with results title contains "<searchQuery>"
    Examples:
      | searchQuery |
      | dell        |
      | apple       |
      | acer aspire |

  Scenario Outline: Sort search results low to high
    Given I navigate to main page
    When I enter "<searchQuery>" to search bar
    Then I should see search result page with results title contains "<searchQuery>"
    When I sort by price low to high
    Then I should see prices sorted low to high
    Examples:
      | searchQuery   |
      | acer aspire   |

  Scenario Outline: Sort search results high to low
    Given I navigate to main page
    When I enter "<searchQuery>" to search bar
    Then I should see search result page with results title contains "<searchQuery>"
    When I sort by price high to low
    Then I should see prices sorted high to low
    Examples:
      | searchQuery   |
      | acer aspire   |

