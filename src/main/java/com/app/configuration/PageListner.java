package com.app.configuration;

import com.app.annotations.Page;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * Created by Yuvaraj on 24/05/2017.
 */
public class PageListner implements TypeListener {

    @Override
    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
        Annotation[] annotations = typeLiteral.getRawType().getDeclaredAnnotations();
        if(annotations.length > 0 && Arrays.stream(annotations)
                    .anyMatch(x->x.annotationType().isAssignableFrom(Page.class))) {
                typeEncounter.register(new PageFactoryInjectionListner(typeEncounter.getProvider(WebDriver.class)));
            }
    }
}
