package com.app.report;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Yuvaraj on 20/07/2017.
 */
public class ReportMerger {

    public static void main(String[] args){
        File reportOutputDirectory = new File("target");
        List<String> jsonFiles = Arrays.stream(reportOutputDirectory.listFiles())
                .filter(file->file.getName().contains(".json"))
                .map(File::getAbsolutePath)
                .collect(Collectors.toList());
        String projectName = "cucumberProject";
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        Reportable result = reportBuilder.generateReports();
    }
}
