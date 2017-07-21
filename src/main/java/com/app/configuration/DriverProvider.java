package com.app.configuration;

import com.app.utils.PropertyMap;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.youiengine.YouiEngineDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

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
    @Named("test.grid.url")
    private String gridUrl;

    private static final String DESIRED_CAPABILITIES_KEY = "driver.remote.capability.";

    public WebDriver get() {
        DesiredCapabilities capability;
        Properties properties = PropertyMap.getInstance().getProperties();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        properties.entrySet().forEach(entry -> {
            if(entry.getKey().toString().contains(DESIRED_CAPABILITIES_KEY)) {
                String key = entry.getKey().toString().split(DESIRED_CAPABILITIES_KEY)[1];
                desiredCapabilities.setCapability(key, entry.getValue());
            }
        });
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
            case "android": {
                try {
                    return new AndroidDriver(new URL(gridUrl), desiredCapabilities);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            case "ios" : {
                try {
                    return new IOSDriver<>(new URL(gridUrl), desiredCapabilities);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            case "youi" : {
                try {
                    return new YouiEngineDriver<>(new URL(gridUrl), desiredCapabilities);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            default: {
                capability = DesiredCapabilities.chrome();
                return new ChromeDriver(capability);
            }
        }
    }

}
