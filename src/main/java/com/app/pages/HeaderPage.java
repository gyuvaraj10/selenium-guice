package com.app.pages;


import com.app.annotations.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Yuvarej on 28/05/2016.
 */
@Page
public class HeaderPage  {


    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchBox;

    @FindBy(xpath = "*[@id='nav-search-submit-text']/..")
    public WebElement searchButton;


    /**
     * types into the search box and submits the search
     * @param searchTerm search term to be used for search
     */
    public void typeAndSubmitSearchBox(String searchTerm){
        typeIntoSearchBox(searchTerm);
        submitSearchButton();
    }

    /**
     * type into search box
     * @param searchTerm search term
     */
    private void typeIntoSearchBox(String searchTerm){
        searchBox.sendKeys(searchTerm);
    }

    /**
     * submits the search button
     */
    private void submitSearchButton(){
        searchBox.submit();
    }
}
