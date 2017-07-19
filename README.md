**Instructions:**
1. Run the following command to execute the tests
   _mvn test -Dguice.injector-source=com.app.configuration.GuiceModule_
2. To apply the desired capabilities to the remote webdriver, prefix the desired capabilities key with "driver.remote.capability" in the properties
   Example: 
    _driver.remote.capability.browserName=chrome_
    _driver.remote.capability.javascriptEnabled=true_
3. Write Scenarios in src/test/resources/features folder
4. Write Step definitions in src/test/java folder  
 