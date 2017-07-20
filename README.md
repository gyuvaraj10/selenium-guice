**Instructions:**
**Run the tests from the command line**
1. Run the following command to execute the tests
   _mvn clean install -Dguice.injector-source=com.app.configuration.GuiceModule_
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
 
**Framework Features:**
1. Supports Parallel testing on Selenium Grid and on local execution
2. Supports SauceLabs, Browser Stack
   To run the tests on SauceLabs, simply set the saucelabs/browser stack url to the "test.grid.url" property in "src/main/resources/application.properties" file
   SauceLabs ex: http://username:access-key@ondemand.saucelabs.com/wd/hub
   Browser Stack ex: http://username:access-key@hub-cloud.browserstack.com/wd/hub
3. Supports inbuild reporting with the help of [serenityrunner](https://github.com/gyuvaraj10/serenityrunner) plugin
4. Can write less and do more
5. Can integrate any CI tools (Jenkins, Bamboo, Teamcity, Travis, etc.,)
