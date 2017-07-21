**Framework Features:**
1. Supports Parallel testing on Selenium Grid and on local execution
2. Supports SauceLabs, Browser Stack, Testing Bot
   To run the tests on SauceLabs, simply set the saucelabs/browser stack url to the "test.grid.url" property in "src/main/resources/application.properties" file
   * SauceLabs: test.grid.url = http://username:access-key@ondemand.saucelabs.com/wd/hub
   * Browser Stack: test.grid.url = http://username:access-key@hub-cloud.browserstack.com/wd/hub
   * Testing Bot: test.grid.url = http://username:access-key@hub.testingbot.com/wd/hub
   * Selenium Grid: test.grid.url = http://selenium-grid-server:4444/wd/hub
3. Supports reporting with the help of [serenityrunner](https://github.com/gyuvaraj10/serenityrunner) plugin
4. Can write less and do more
5. Can integrate any CI tools (Jenkins, Bamboo, Teamcity, Travis, etc.,)

**Requirements:**
1. Maven version 3
2. Java version 8
3. Cucumber Java plugin for the IntelliJ IDEA

**How to write a test:**
1. To create a new test/scenario, simply add a new feature if the scenario belongs to new feature or add the scenario in the existing 
feature files if the scenario belongs to the existing feature.
2. Scenario/Feature must be written under src/test/resource
3. Add the step definitions for the corresponding scenarios in src/test/java
4. Add pages, step class in src/main/java/pages and src/main/java/steps respectively
5. It is not mandatory to write the step classes under src/main/java/steps and use them in the step definition classes. Instead
can directly use the page classes/page objects in the step definition classes
_Example:_

path: src/test/resources/features
```java
  @test
  Scenario: Validate the search results are displayed
    Given I launch the amazon application
    Then I search the "laptop" in the search box
    And I click the first search result
    Then I display the price of the first laptop
 ```
 path: src/test/java/com/app/tests
 ```java 
  public class SearchResultsStepDef {
  
      @Inject
      SearchSteps searchSteps;
  
      @Inject
      ApplicationSteps appSteps;
  
      @Given("^I launch the amazon application$")
      public void iLaunchTheAmazonApplication() throws Throwable {
          appSteps.goToHomePage();
      }
  
      @Then("^I search the \"([^\"]*)\" in the search box$")
      public void iSearchTheInTheSearchBox(String searchTerm) throws Throwable {
          searchSteps.searchAnItem(searchTerm);
      }
  
      @And("^I click the first search result$")
      public void iClickTheFirstSearchResult() throws Throwable {
          searchSteps.clickSearchItemByIndex(0);
      }
  
      @Then("^I display the price of the first laptop$")
      public void iDisplayThePriceOfTheFirstLaptop() throws Throwable {
  
      }
  }  
```


**Instructions:**
**Run the tests from the command line**
1. Run the following command to execute the tests
   _mvn clean verify_
2. To apply the desired capabilities to the remote webdriver, prefix the desired capabilities key with "driver.remote.capability" in the properties
   Example: 
    _driver.remote.capability.browserName=chrome_
    _driver.remote.capability.javascriptEnabled=true_
3. Write Scenarios in src/test/resources/features folder
4. Write Step definitions in src/test/java folder
5. Reports are generated in target/cucumber-html-reports folder  
 
**Run the tests from the Intellij IDEA**
1. To run the tests from the Intellij IDEA, click on "Run" and then "Edit Configurations"
2. Select "Cucumber Java" in the default section of the left side page
3. Set _-Dguice.injector-source=com.app.configuration.GuiceModule_ in the VM options and save the configurations
4. Choose any scenario you want to run and click the "Run <scenario name>" in the context menu
