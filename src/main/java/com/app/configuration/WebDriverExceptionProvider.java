package com.app.configuration;

import com.google.inject.throwingproviders.CheckedProvider;

import java.net.MalformedURLException;

/**
 * Created by Yuvaraj on 28/07/2017.
 */
public interface WebDriverExceptionProvider<T> extends CheckedProvider<T> {
    T get() throws MalformedURLException;
}
