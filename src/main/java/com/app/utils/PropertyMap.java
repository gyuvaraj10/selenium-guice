package com.app.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyMap {

    private Properties properties;
    private static PropertyMap propertyMap;

    private PropertyMap() {
        properties = new Properties();
    }

    public Properties getProperties() {
        return properties;
    }

    public Properties getLoadedProperties() {
        try {
            properties.load(new FileInputStream(new File(PropertyMap.class.getClass()
                    .getResource("/application.properties").getPath())));
            properties.putAll(System.getProperties());
            return properties;
        }
        catch (Exception ex) {
            return properties;
        }
    }

    public static PropertyMap getInstance() {
        if(propertyMap == null) {
            propertyMap = new PropertyMap();
        }
        return propertyMap;
    }
}
