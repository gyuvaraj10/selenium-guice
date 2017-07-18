package com.app.configuration;


import com.google.inject.Provider;
import com.google.inject.spi.InjectionListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class PageFactoryInjectionListner implements InjectionListener{

   Provider<WebDriver> provider;

   PageFactoryInjectionListner(Provider<WebDriver> injector) {
       this.provider = injector;
   }

    @Override
    public void afterInjection(Object o) {
        WebDriver driver = this.provider.get();
        PageFactory.initElements(driver, o);
    }
}
