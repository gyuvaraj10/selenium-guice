package com.app.configuration;

import com.app.utils.PropertyMap;
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
import java.util.Map;
import java.util.Properties;

@Singleton
public class DriverProvider implements Provider<WebDriver> {

    @Inject
    @Named("test.browser.name")
    private String browser;

    @Inject
    @Named("webdriver.chrome.driver")
    private String chromeDriverPath;

    @Inject
    @Named("test.grid.url")
    private String gridUrl;

    public WebDriver get() {
        DesiredCapabilities capability;
        switch (browser.toLowerCase()) {
            case "chrome":  {
                capability = DesiredCapabilities.chrome();
                return new ChromeDriver(capability);
            }
            case "firefox": {
                capability = DesiredCapabilities.firefox();
                return new FirefoxDriver(capability);
            }
            case "grid": {
                try {
                    Properties properties = PropertyMap.getInstance().getProperties();
                    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                    properties.entrySet().forEach(entry -> {
                        if(entry.getKey().toString().contains("driver.remote.capability")) {
                            desiredCapabilities.setCapability((String) entry.getKey(), entry.getValue());
                        }
                    });
                    return new RemoteWebDriver(new URL(gridUrl), desiredCapabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            case "safari": {
                capability = DesiredCapabilities.safari();
                return new SafariDriver(capability);
            }
            case "ie": {
                capability = DesiredCapabilities.internetExplorer();
                return new InternetExplorerDriver(capability);
            }
            default: {
                return new ChromeDriver();
            }
        }
    }

}
