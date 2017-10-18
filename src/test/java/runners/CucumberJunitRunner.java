package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Yuvaraj on 27/07/2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "../webapps/webapp/features", tags = "@test1", glue = "tests")
public class CucumberJunitRunner {
}
