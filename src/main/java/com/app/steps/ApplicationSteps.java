package com.app.steps;

import com.app.annotations.Step;
import com.app.pages.HomePage;
import com.google.inject.Inject;

/**
 * Created by Yuvarej on 29/05/2016.
 */
@Step
public class ApplicationSteps{

    @Inject
    HomePage homePage;

    /**
     * goes to the application home page by launching the browser
     * if the browser is not launched
     */
    public void goToHomePage(){
        homePage.goToHomePage();
    }

}
