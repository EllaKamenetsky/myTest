package com.ssi.devicemonitor.entity;

public class HardwareDevice extends Device {
    private String Location;
    private String  MACCAddress;

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getMACCAddress() {

        return MACCAddress;
    }

    public void setMACCAddress(String MACCAddress) {
       //validatetion
        if(  MACCAddress.length() != 12){
           System.out.println("MACCAddress is not valid format");
           return;
        }
        this.MACCAddress = MACCAddress;
    }

    public HardwareDevice(String name) {
          super(name);
          super.setDeviceType(DeviceType.Hardwate);
    }
}
