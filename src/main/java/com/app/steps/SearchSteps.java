package com.app.steps;

import com.app.annotations.Step;
import com.app.pages.SearchResultsPage;
import com.google.inject.Inject;

/**
 * Created by Yuvarej on 28/05/2016.
 */
@Step
public class SearchSteps{

    @Inject
    private SearchResultsPage searchResultsPage;


    /**
     * clicks the first search item from the search results
     */
    public void clickSearchItemByIndex(int index){
        searchResultsPage.clickSearchItem(index);
    }

    /**
     * searches an item
     * @param searchTerm
     */
    public void searchAnItem(String searchTerm){
        searchResultsPage.typeAndSubmitSearchBox(searchTerm);
    }


}
