Feature: Framework example without the steps layer

  @test
  Scenario: Validate the search results are displayed1
    Given I launch the amazon application
    Then I search the "laptop" in the search box
    And I click the first search result
    Then I display the price of the first laptop

  @test
  Scenario: Validate the search results are displayed1
    Given I launch the amazon application
    Then I search the "ipad" in the search box
    And I click the first search result
    Then I display the price of the first laptop

  @test
  Scenario: Validate the search results are displayed1
    Given I launch the amazon application
    Then I search the "tv" in the search box
    And I click the first search result
    Then I display the price of the first laptop


   @galen
  Scenario: Galen Test
    Given I Test Galen framework integration