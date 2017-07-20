Feature: Make sure User can search the respective searched product

  @test
  Scenario: Validate the search results are displayed11
    Given I launch the amazon application
    Then I search the "laptop" in the search box
    And I click the first search result
    Then I display the price of the first laptop

  @test
  Scenario: Validate the search results are displayed21
    Given I launch the amazon application
    Then I search the "laptop" in the search box
    And I click the first search result
    Then I display the price of the first laptop


  @test
  Scenario: Validate the search results are displayed22
    Given I launch the amazon application
    Then I search the "laptop" in the search box
    And I click the first search result
    Then I display the price of the first laptop


  @test
  Scenario: Validate the search results are displayed23
    Given I launch the amazon application
    Then I search the "laptop" in the search box
    And I click the first search result
    Then I display the price of the first laptop