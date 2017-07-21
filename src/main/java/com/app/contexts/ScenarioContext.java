package com.app.contexts;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yuvaraj on 21/07/2017.
 */
public class ScenarioContext {

    private Map<Object, Object> sessionInfo;

    public ScenarioContext() {
        sessionInfo = new HashMap<>();
    }

    public void putInfo(Object key, Object value) {
        sessionInfo.put(key, value);
    }

    public Object getInfo(Object key) {
        return sessionInfo.get(key);
    }

    public void removeInfo(Object key) {
        sessionInfo.remove(key);
    }

}
