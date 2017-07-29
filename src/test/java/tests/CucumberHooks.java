package tests;

import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.TestReport;
import com.galenframework.reports.model.LayoutReport;
import com.galenframework.reports.nodes.LayoutReportNode;
import com.galenframework.support.GalenReportsContainer;
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
    TestReport testReport;
    GalenTestInfo ti;

    @Before
    public void beforeHook(Scenario scenario) {
//        ti = new GalenTestInfo(scenario.getName(), null);
//        testReport = new TestReport();
//        ti.setReport(testReport);
//        GalenReportsContainer.get().registerTest(scenario.getName(), null);
    }
    @After(order = 1)
    public void tearDown(Scenario scenario) throws Exception{
        driver.close();
        driver.quit();
//        LayoutReport report1 = Report.getReport().getLayoutReport();
//        TestReport report = GalenReportsContainer.get().getAllTests().get(0).getReport();
//        report.addNode(new LayoutReportNode(report.getFileStorage(), report1, scenario.getName()));
//        ti.setReport(report);
//        HtmlReportBuilder builder = new HtmlReportBuilder();
//        builder.build(Arrays.asList(ti),"target/");
//        captureScreenShotOnFailure(scenario);
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
