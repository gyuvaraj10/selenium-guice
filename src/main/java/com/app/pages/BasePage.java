package com.app.pages;

import com.app.annotations.Page;
import com.galenframework.api.Galen;
import com.galenframework.reports.model.LayoutReport;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.security.Credentials;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by Yuvaraj on 19/07/2017.
 */
@Page
public class BasePage {

    @Inject
    private WebDriver driver;

    @Inject
    @Named("test.element.wait.seconds")
    private int elementWait;

    /**
     * switches to frame
      * @param element
     */
    protected void switchToFrame(WebElement element) {
       driver.switchTo().frame(element);
    }

    /**
     * switches to frame based on the frame index
     * @param index
     */
    protected void switchToFrame(int index) {
        driver.switchTo().frame(index);
    }

    /**
     * switches to frame based on the frame name
     * @param frameName
     */
    protected void switchToFrame(String frameName) {
        driver.switchTo().frame(frameName);
    }

    /**
     * switches to default content
     */
    protected void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    /**
     * switch to parent frame
     */
    protected void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    /**
     * switch to window
     * @param windowHandleOrName
     */
    protected void switchToWindow(String windowHandleOrName) {
        driver.switchTo().window(windowHandleOrName);
    }

    /**
     * get current window handle
     * @return
     */
    protected String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    /**
     * get window handles
     * @return
     */
    protected List<String> getWindowHandles() {
        return driver.getWindowHandles().stream().collect(Collectors.toList());
    }

    /**
     * go to url
     * @param url
     */
    protected void goTo(String url) {
        driver.get(url);
    }

    /**
     * backward navigation
     */
    protected void back() {
        driver.navigate().back();
    }

    /**
     * forward navigation
     */
    protected void forward() {
        driver.navigate().forward();
    }

    /**
     * refresh the page
     */
    protected void refresh() {
        driver.navigate().refresh();
    }

    /**
     * navigate to the page
     * @param url
     */
    protected void navigateTo(String url) {
        driver.navigate().to(url);
    }

    /**
    * resizes the browser window
    * @param width
    * @param height
    */
    protected void resize(int width, int height) {
       driver.manage().window().setSize(new Dimension(width, height));
    }

    /**
      * move to Element
      * @param element
      */
    protected void moveToElement(WebElement element) {
       getActions().moveToElement(element).build().perform();
    }

    /**
      * move to Element with an offset
      * @param element
      */
    protected void moveToElement(WebElement element, int xOffset, int yOffset) {
      getActions().moveToElement(element, xOffset, yOffset).build().perform();
    }

    /**
      * move to Element with an offset
      * @param element
      */
    protected void actionsClick(WebElement element) {
      getActions().click(element).build().perform();
    }

    /**
      * move to Element with an offset
      * @param element
      */
    protected void releaseElement(WebElement element) {
      getActions().release(element).build().perform();
    }

    /**
     * click and Holds an Element
     * @param element
     */
    protected void clickAndHold(WebElement element) {
        getActions().clickAndHold(element).build().perform();
    }

    /**
     * context click on an element
     * @param element
     */
    protected void contextClick(WebElement element) {
        getActions().contextClick(element).build().perform();
    }

    /**
     * double click on an element
     * @param element
     */
    protected void doubleClick(WebElement element) {
        getActions().doubleClick(element).build().perform();
    }

    /**
     * context click on an element
     * @param sourceElement
     * @param target
     */
    protected void doubleClick(WebElement sourceElement, WebElement target) {
       getActions().dragAndDrop(sourceElement, target).build().perform();
    }

    /**
     * context click on an element
     * @param element
     * @param keys
     */
    protected void keyDown(WebElement element, Keys keys) {
        getActions().keyDown(element, keys).build().perform();
    }

    /**
     * context click on an element
     * @param element
     * @param keys
     */
    protected void keyUp(WebElement element, Keys keys) {
        getActions().keyUp(element, keys).build().perform();
    }

    /**
     * send Keys to an element
     * @param element
     * @param sequence
     */
    protected void sendKeys(WebElement element, CharSequence sequence) {
        getActions().sendKeys(element, sequence).build().perform();
    }

    /**
     * clicks
     */
    protected void click() {
       getActions().click().build().perform();
    }

    /**
     * double clicks
     */
    protected void doubleClick() {
        getActions().doubleClick().build().perform();
    }

    /**
     * click and Hold
     */
    protected void clickAndHold() {
      getActions().clickAndHold().build().perform();
    }

    /**
     * context Click
     */
    protected void contextClick() {
      getActions().contextClick().build().perform();
    }

    /**
     * release
     */
    protected void release() {
        getActions().release().build().perform();
    }

    /**
     * release
     */
    protected void sendKeys(CharSequence sequence) {
        getActions().sendKeys(sequence).build().perform();
    }


    /**
     * switch to full screen
     */
    protected void fullScreen() {
        driver.manage().window().fullscreen();
    }

    /**
     * maximize Window
     */
    protected void maximizeWindow() {
        driver.manage().window().maximize();
    }

    /**
     * switches to alert
     */
    protected Alert switchToAlert() {
        return driver.switchTo().alert();
    }

    /**
     * accept alert
     */
    protected void acceptAlert(Alert alert) {
        alert.accept();
    }

    /**
     * dismiss alert
     */
    protected void dismissAlert(Alert alert) {
        alert.dismiss();
    }

    /**
     * get alert text
     */
    protected String getAlertText(Alert alert) {
        return alert.getText();
    }

    /**
     * dismiss alert
     */
    protected void sendKeysToAlert(Alert alert, String keys) {
        alert.sendKeys(keys);
    }

    /**
     * dismiss alert
     */
    protected void authenticate(Alert alert, String username, String password) {
        Credentials credentials = new UserAndPassword(username, password);
        alert.authenticateUsing(credentials);
    }

    /**
     * dismiss alert
     */
    protected void setCredentials(Alert alert, String username, String password) {
        Credentials credentials = new UserAndPassword(username, password);
        alert.setCredentials(credentials);
    }

    /**
    * injects the java script
    * @param script
    * @return
    */
  protected String injectJavaScript(String script) {
      return ((JavascriptExecutor)driver)
              .executeScript(script).toString();
  }

  /**
    * injects the java script
    * @param script
    * @return
    */
  protected String injectJavaScript(String script, WebElement element) {
      return ((JavascriptExecutor)driver)
              .executeScript(script, element).toString();
  }


  /**
   * waits for the element to be clickable
   * @param element
   */
  protected void waitForElementToBeClickable(WebElement element) {
      WebDriverWait wait = getElementWait();
      wait.until(ExpectedConditions.elementToBeClickable(element));
  }

  /**
   * returns the webdriver wait object for the element wait
   * @return
   */
  private WebDriverWait getElementWait() {
      return new WebDriverWait(driver, elementWait);
  }

  /**
    * returns actions object
    * @return
    */
  private Actions getActions() {
      return new Actions(driver);
  }

}
