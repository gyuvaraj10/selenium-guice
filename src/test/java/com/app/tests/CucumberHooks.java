package com.app.tests;

import com.google.inject.Inject;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.openqa.selenium.WebDriver;

/**
 * Created by Yuvaraj on 08/10/2016.
 */
public class CucumberHooks {

    @Inject
    private WebDriver driver;

    @After(order = 1)
    public void tearDown(Scenario scenario){
        driver.close();
        driver.quit();
    }
}
