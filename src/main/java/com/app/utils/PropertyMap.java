package com.app.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertyMap {

    public Properties getProperties(){
        try{
            InputStream stream = this.getClass().getResourceAsStream("/application.properties");
            Properties properties = new Properties();
            properties.load(stream);
            return properties;
        }
        catch (Exception ex){
           ex.printStackTrace();
        }
        return null;
    }

}
