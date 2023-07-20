package com.ssi.devicemonitor.entity;

public abstract class Device {
    public enum  DeviceType{
        Hardwate,
        Software
    }
    private String name;
    private String status;
    private String manufacturer;
    private String version;
    private DeviceType deviceType;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    protected void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public Device(String name) {
        this.name = name;
        this.status = "Offline"; // Set initial status to Offline
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
