package com.app.configuration;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

@Singleton
public class DriverProvider implements Provider<WebDriver> {


    @Inject
    @Named("test.browser.name")
    private String browser;

    @Inject
    @Named("test.grid.url")
    private String gridUrl;

    public WebDriver get() {
        switch (browser.toLowerCase()) {
            case "chrome":  {
                System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
                DesiredCapabilities capability = DesiredCapabilities.chrome();
                return new ChromeDriver(capability);
            }
            case "firefox": {
                return new FirefoxDriver();
            }
            case "grid": {
                try {
                    return new RemoteWebDriver(new URL(gridUrl), new DesiredCapabilities());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            case "safari": {
                return new SafariDriver();
            }
            case "ie": {
                return new InternetExplorerDriver();
            }
            default: {
                return new ChromeDriver();
            }
        }

    }
}
