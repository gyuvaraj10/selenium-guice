package com.app.configuration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by Yuvaraj on 18/10/2017.
 */
public class CustomFieldDecorator extends DefaultFieldDecorator {
    public CustomFieldDecorator(ElementLocatorFactory factory) {
        super(factory);
    }

    protected WebElement proxyForLocator(ClassLoader loader, ElementLocator locator) {
        CustomLocatingElementHandler handler = new CustomLocatingElementHandler(locator);
        WebElement proxy = (WebElement) Proxy.newProxyInstance(loader, new Class[]{WebElement.class, WrapsElement.class, Locatable.class}, handler);
        return proxy;
    }

    protected List<WebElement> proxyForListLocator(ClassLoader loader, ElementLocator locator) {
        CustomLocatingElementListHandler handler = new CustomLocatingElementListHandler(locator);
        List proxy = (List)Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        return proxy;
    }
}
