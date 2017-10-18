package com.app.configuration;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementListHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Yuvaraj on 18/10/2017.
 */
public class CustomLocatingElementListHandler extends LocatingElementListHandler {
    private ElementLocator locator;

    public CustomLocatingElementListHandler(ElementLocator locator) {
        super(locator);
        this.locator = locator;
    }

    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        int maxTry = 3;
        Object obj= null;
        for(int trail = 1; trail <= maxTry; trail++) {
            List elements = this.locator.findElements();
            try {
                obj = method.invoke(elements, objects);
            } catch (InvocationTargetException var6) {
                throw var6.getCause();
            } catch (StaleElementReferenceException ex) {
                if(trail == 3) {
                    throw ex;
                }
            }
        }
        return obj;
    }

}
