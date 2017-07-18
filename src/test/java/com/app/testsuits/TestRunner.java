package com.app.testsuits;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
/**
 * Created by Yuvaraj on 18/07/2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = "com.app.tests",
        plugin = {"json:target/report.json", "html:target/report.html"})
public class TestRunner {
}
