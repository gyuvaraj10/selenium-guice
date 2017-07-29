package java;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Created by Yuvaraj on 27/07/2017.
 */
@RunWith(CucumberGalenRunner.class)
@CucumberOptions(features = "src/test/resources/features",
        tags = "@test1", glue = "tests", plugin = "com.app.test.formatter.JSONScenarioFormatter:target/report.json")
public class CucumberJunitRunner {
}
