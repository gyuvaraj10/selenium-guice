package com.app.tests.web;

import com.app.pages.HomePage;
import com.app.pages.SearchResultsPage;
import com.google.inject.Inject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


/**
 * Created by Yuvarej on 28/05/2016.
 */
public class SearchResultsStepDef {

    @Inject
    HomePage homePage;

    @Inject
    private SearchResultsPage searchResultsPage;

    @Given("^I launch the amazon application$")
    public void iLaunchTheAmazonApplication() throws Throwable {
        homePage.goToHomePage();
    }

    @Then("^I search the \"([^\"]*)\" in the search box$")
    public void iSearchTheInTheSearchBox(String searchTerm) throws Throwable {
        searchResultsPage.typeAndSubmitSearchBox(searchTerm);
    }

    @And("^I click the first search result$")
    public void iClickTheFirstSearchResult() throws Throwable {
        searchResultsPage.clickSearchItem(0);
    }

    @Then("^I display the price of the first laptop$")
    public void iDisplayThePriceOfTheFirstLaptop() throws Throwable {

    }
}
