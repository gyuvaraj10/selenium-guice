package com.app.configuration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Yuvaraj on 18/10/2017.
 */
public class CustomLocatingElementHandler extends LocatingElementHandler {
    private final ElementLocator locator;

    public CustomLocatingElementHandler(ElementLocator locator) {
        super(locator);
        this.locator = locator;
    }

    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        WebElement element = null;
        int maxTry = 3;
        for(int trial=1; trial <=maxTry; trial ++) {
            try {
                element = this.locator.findElement();
            } catch (NoSuchElementException var7) {
                if ("toString".equals(method.getName())) {
                    return "Proxy element for: " + this.locator.toString();
                }

                throw var7;
            } catch (StaleElementReferenceException ex) {
                if(trial == maxTry) {
                    throw ex;
                }
            }
        }

        if("getWrappedElement".equals(method.getName())) {
            return element;
        } else {
            try {
                return method.invoke(element, objects);
            } catch (InvocationTargetException var6) {
                throw var6.getCause();
            }
        }
    }
}
