Feature: Make sure User can search the respective searched product

  @test
  Scenario: Validate the search results are displayed
    Given I launch the amazon application
    Then I search the "laptop" in the search box
    And I click the first search result
    Then I display the price of the first laptop

  @test
  Scenario: Validate the search results are displayed1
    Given I launch the amazon application
    Then I search the "laptop" in the search box
    And I click the first search result
    Then I display the price of the first laptop


  @test
  Scenario: Validate the search results are displayed2
    Given I launch the amazon application
    Then I search the "laptop" in the search box
    And I click the first search result
    Then I display the price of the first laptop


  @test
  Scenario: Validate the search results are displayed3
    Given I launch the amazon application
    Then I search the "laptop" in the search box
    And I click the first search result
    Then I display the price of the first laptop