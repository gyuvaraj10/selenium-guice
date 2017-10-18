package pages;

import com.app.annotations.Page;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import org.openqa.selenium.WebDriver;

/**
 * Created by Yuvarej on 29/05/2016.
 */
@Page
public class HomePage extends HeaderPage {

    @Inject
    WebDriver driver;

    @Inject
    private Provider<WebDriver> injector;

    @Inject
    @Named("app.url")
    String applicationUrl;
    /**
     * goes to the application home page by launching the browser
     * if the browser is not launched
     */
    public void goToHomePage(){
        driver.get(applicationUrl);
        String handle = driver.getWindowHandle();
        driver.quit();
        driver = injector.get();
        driver.get(applicationUrl);
    }
}
