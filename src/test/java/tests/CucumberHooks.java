package tests;

import com.google.inject.Inject;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

/**
 * Created by Yuvaraj on 08/10/2016.
 */
public class CucumberHooks {

    @Inject
    private WebDriver driver;

    @Before
    public void beforeHook(Scenario scenario) {

    }
    @After(order = 1)
    public void tearDown(Scenario scenario) throws Exception{
        captureScreenShotOnFailure(scenario);
        driver.close();
        driver.quit();
    }

    /**
     * captures the screenshot on failure
     * @param scenario
     */
    private void captureScreenShotOnFailure(Scenario scenario) {
        if(scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
    }
}
