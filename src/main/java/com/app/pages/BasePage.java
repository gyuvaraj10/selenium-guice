package com.app.pages;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Yuvaraj on 19/07/2017.
 */
class BasePage {

  @Inject
  private WebDriver driver;

  @Inject
  @Named("test.element.wait.seconds")
  private int elementWait;

  public void waitForElement(WebElement element) {
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
}
