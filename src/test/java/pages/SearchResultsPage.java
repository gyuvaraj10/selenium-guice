package pages;

import com.app.annotations.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Yuvarej on 28/05/2016.
 */
@Page
public class SearchResultsPage extends HeaderPage {

     @FindBy(id = "s-results-list-atf")
     public WebElement searchResults;

     @FindBy(xpath = "//*[@id='s-results-list-atf']/li")
     public List<WebElement> results;

    /**
     * clicks the search item based on the index passed
     * @param index item to be clicked
     */
    public void clickSearchItem(int index){
        results.get(index).click();
    }

}
