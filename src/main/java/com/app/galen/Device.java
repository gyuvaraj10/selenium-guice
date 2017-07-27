package com.app.galen;

/**
 * Created by Yuvaraj on 26/07/2017.
 */
public enum Device {
    MOBILE("mobile"),
    TABLET("tablet"),
    DESKTOP("desktop");

    String device;

    private Device(String device) {
        this.device = device;
    }

    public String getDevice(){
        return device;
    }
}
