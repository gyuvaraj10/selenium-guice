package com.app.galen;

import com.galenframework.api.Galen;
import com.galenframework.reports.TestReport;
import com.galenframework.reports.model.LayoutReport;
import com.galenframework.testng.GalenTestNgTestBase;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

/**
 * Created by Yuvaraj on 26/07/2017.
 */
public class StyleValidator {

    @Inject
    WebDriver driver;

    private ThreadLocal<TestReport> report = new ThreadLocal();

    /**
     * checks the layout
     * @param specPath
     * @param includedTags
     * @throws IOException
     */
    public LayoutReport checkLayout(String specPath, List<String> includedTags) throws IOException {
        return Galen.checkLayout(driver, specPath, includedTags);
    }

    public TestReport getReport() {
        TestReport report = (TestReport)this.report.get();
        if(report == null) {
            throw new RuntimeException("The report is not instantiated yet");
        } else {
            return report;
        }
    }
}
