package com.ssi.devicemonitor.entity;

import java.text.DateFormat;
import java.util.Date;

public class SoftwareDevice extends Device {

    public DateFormat getDate() {
        return date;
    }

    public void setDate(DateFormat date) {
        this.date = date;
    }

    private DateFormat date;

    public SoftwareDevice(String name) {
        super(name);
        super.setDeviceType(DeviceType.Software);
    }
}