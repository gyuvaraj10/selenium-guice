package com.app.configuration;

import com.app.utils.PropertyMap;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.youiengine.YouiEngineDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumOptions;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.StoreAppsOptions;
import org.openqa.selenium.winium.SilverlightOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

@Singleton
public class DriverProvider implements Provider<WebDriver> {

    private static final String DESIRED_CAPABILITIES_KEY = "driver.remote.capability.";

    private static final Logger logger = Logger.getLogger(DriverProvider.class.getName());

    @Inject
    @Named("test.browser.name")
    private String browser;

    @Inject
    @Named("test.grid.url")
    private String gridUrl;

    public WebDriver get() {
        WebDriver driver = null;
        try {
            switch (browser.toLowerCase()) {
                case "edge":
                    driver = new EdgeDriver(getEdgeCapabilities());
                    break;

                case "chrome":
                    driver = new ChromeDriver(getChromeCapabilities());
                    break;

                case "firefox":
                    driver = new FirefoxDriver(getFirefoxCapabilities());
                    break;

                case "grid":
                    driver = new RemoteWebDriver(new URL(gridUrl), getRemoteDriverCapabilities());
                    break;

                case "safari":
                    driver = new SafariDriver(getSafariCapabilities());
                    break;

                case "ie":
                    driver = new InternetExplorerDriver(getIECapabilities());
                    break;

                case "android":
                    driver = new AndroidDriver(new URL(gridUrl), getRemoteDriverCapabilities());
                    break;

                case "ios":
                    driver = new IOSDriver<>(new URL(gridUrl), getRemoteDriverCapabilities());
                    break;

                case "youi":
                    driver = new YouiEngineDriver<>(new URL(gridUrl), getRemoteDriverCapabilities());
                    break;

                case "windesktop":
                    driver = new WiniumDriver(getWiniumOptions(browser.toLowerCase()));
                    break;

                case "winremotedesktop":
                    driver = new WiniumDriver(new URL(gridUrl), getWiniumOptions(browser.toLowerCase()));
                    break;

                case "winstoreapp":
                    driver = new WiniumDriver(getWiniumOptions(browser.toLowerCase()));
                    break;

                case "winremotestoreapp":
                    driver = new WiniumDriver(new URL(gridUrl), getWiniumOptions(browser.toLowerCase()));
                    break;

                case "winsilverslight":
                    driver = new WiniumDriver(getWiniumOptions(browser.toLowerCase()));
                    break;

                case "winremotesilverslight":
                    driver = new WiniumDriver(new URL(gridUrl), getWiniumOptions(browser.toLowerCase()));
                    break;

                default:
                    driver = new ChromeDriver(getChromeCapabilities());
                    break;

            }
        } catch (MalformedURLException ex) {
            logger.severe(ex.getMessage());
        }
        return driver;
    }


    /**
     * implement a strategy to read the chrome capabilities
     * @return
     */
    private Capabilities getRemoteDriverCapabilities(){
        Properties properties = PropertyMap.getInstance().getProperties();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        properties.entrySet().forEach(entry -> {
            if(entry.getKey().toString().contains(DESIRED_CAPABILITIES_KEY)) {
                String key = entry.getKey().toString().split(DESIRED_CAPABILITIES_KEY)[1];
                desiredCapabilities.setCapability(key, entry.getValue());
            }
        });
        return desiredCapabilities;
    }

    /**
     * implement a strategy to read the chrome capabilities
     * @return
     */
    private Capabilities getChromeCapabilities(){
        Capabilities capabilities = DesiredCapabilities.chrome();
        return capabilities;
    }

    /**
     * implement a strategy to read the safari capabilities
     * @return
     */
    private Capabilities getSafariCapabilities(){
        Capabilities capabilities = DesiredCapabilities.safari();
        return capabilities;
    }

    /**
     * implement a strategy to read the IE capabilities
     * @return
     */
    private Capabilities getIECapabilities(){
        Capabilities capabilities = DesiredCapabilities.internetExplorer();
        return capabilities;
    }

    /**
     * implement a strategy to read the Firefox capabilities
     * @return
     */
    private Capabilities getFirefoxCapabilities(){
        Capabilities capabilities = DesiredCapabilities.firefox();
        return capabilities;
    }

    /**
     * implement a strategy to read the Edge capabilities
     * @return
     */
    private Capabilities getEdgeCapabilities(){
        Capabilities capabilities = DesiredCapabilities.edge();
        return capabilities;
    }

    private WiniumOptions getWiniumOptions(String wintype) {
        WiniumOptions options = null;
        switch (wintype) {
            case "winDesktop":
                options = new DesktopOptions();
                break;
            case "winStoreApp":
                options = new StoreAppsOptions();
                break;
            default:
                options = new SilverlightOptions();
                break;
        }
        return options;
    }

}
