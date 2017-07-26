package com.app.contexts;

import com.google.inject.ProvidedBy;

/**
 * Created by Yuvaraj on 26/07/2017.
 */
public interface IScenarioContext {

     void putInfo(Object key, Object value);

     Object getInfo(Object key);

     void removeInfo(Object key);
}
