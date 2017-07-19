**Instructions:**
1. Run the following command to execute the tests
 <h4>mvn test -Dguice.injector-source=com.app.configuration.GuiceModule</h4>
2. To apply the desired capabilities to the remote webdriver, prefix the desired capabilities key with "driver.remote.capability" in the properties
Example: 
_driver.remote.capability.browserName=chrome_
_driver.remote.capability.javascriptEnabled=true_
 