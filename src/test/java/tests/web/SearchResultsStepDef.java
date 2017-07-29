package tests.web;

//import com.app.galen.StyleValidator;

import com.google.inject.Inject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.SearchResultsPage;


/**
 * Created by Yuvarej on 28/05/2016.
 */
public class SearchResultsStepDef {

    @Inject
    HomePage homePage;

    @Inject
    private SearchResultsPage searchResultsPage;

//    @Inject
//    private StyleValidator validator;

    @Inject
    private WebDriver driver;

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

    @Given("^I Test Galen framework integration$")
    public void iTestGalenFrameworkIntegration() throws Throwable {
//        driver.get("http://testapp.galenframework.com");
//        LayoutReport report = validator.checkLayout("/specs/welcomePage.spec", java.util.Arrays.asList("mobile"));
//        Report.getReport().setReport(report);
    }
}
