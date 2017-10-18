package com.app.configuration;


import com.app.annotations.Page;
import com.app.utils.PropertyMap;
import com.google.inject.Provider;
import com.google.inject.spi.InjectionListener;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;


public class PageFactoryInjectionListner implements InjectionListener{

   private final Provider<WebDriver> provider;

   PageFactoryInjectionListner(Provider<WebDriver> injector) {
       this.provider = injector;
   }

    @Override
    public void afterInjection(Object o) {
        if(o.getClass().getDeclaredAnnotation(Page.class) != null) {
            WebDriver driver = this.provider.get();
            String browserV = StringUtils.defaultString(System.getProperty("test.browser.name"), "");
            if (browserV.contains("android") || browserV.contains("ios") || browserV.contains("youi")) {
                PageFactory.initElements(new AppiumFieldDecorator(driver) , o);
            } else {
                ElementLocatorFactory elementLocatorFactory = new DefaultElementLocatorFactory(driver);
                CustomFieldDecorator customFieldDecorator = new CustomFieldDecorator(elementLocatorFactory);
                PageFactory.initElements(customFieldDecorator, o);
            }
        }
    }
}
