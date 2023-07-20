package com.ssi.devicemonitor.controller;

import com.ssi.devicemonitor.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimerTask;


public class DeviceMonitorController {
    @FXML
    private ListView<Device> deviceListView;

    @FXML
    private TextField deviceNameTextField;
    @FXML
    private RadioButton devHardware;
    @FXML
    private RadioButton devSoftware;
    @FXML
    private Button addDeviceButton;
    @FXML
    private Label deviceTypeInfo;
    @FXML
    TextField nameInfo;
    @FXML
    TextField versionInfo;
    @FXML
    TextField manufacturerInfo;

    @FXML
    TextField dateInfo;

    @FXML
    TextField locationInfo;
    @FXML
    TextField MACAddressInfo;

    private DeviceMonitor deviceMonitor;


    private void deviceShow(Device device){

        deviceTypeInfo.setText("Device Type : "+ device.getDeviceType().toString()) ;
        nameInfo.setText(device.getName());
        versionInfo.setText(device.getVersion());

        if(device.getDeviceType() == Device.DeviceType.Software){
            softwareShow((SoftwareDevice) device);
        }else {
            hardwareShow((HardwareDevice)device);
        }
    }
    private void softwareShow(SoftwareDevice device){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(device.getDate());
        dateInfo.setText(strDate);

    }
    private void hardwareShow(HardwareDevice device){
        MACAddressInfo.setText(device.getMACCAddress());
    }
    public void initialize() {
        deviceMonitor = new DeviceMonitor();

        deviceMonitor.addDevice(new HardwareDevice("Device 1"));
        deviceMonitor.addDevice(new HardwareDevice("Device 2"));
        deviceMonitor.addDevice(new HardwareDevice("Device 3"));

        deviceListView.setItems(FXCollections.observableList(deviceMonitor.getDevices()));
        deviceListView.setCellFactory(deviceListView -> new DeviceListCell());

        // Add context menu to ListView
        ContextMenu contextMenu = new ContextMenu();
        MenuItem removeItem = new MenuItem("Remove");
        MenuItem informationItem = new MenuItem("Information");

        removeItem.setOnAction(event -> {
            Device selectedDevice = deviceListView.getSelectionModel().getSelectedItem();
            if (selectedDevice != null) {
                deviceMonitor.removeDevice(selectedDevice);
            }
        });
        informationItem.setOnAction(event -> {
            Device selectedDevice = deviceListView.getSelectionModel().getSelectedItem();
            if (selectedDevice != null) {
               deviceShow(selectedDevice);
            }
        });
        devHardware.setSelected(true);
        contextMenu.getItems().addAll(removeItem);
        contextMenu.getItems().addAll(informationItem);
        deviceListView.setContextMenu(contextMenu);

    }

    private class DataUpdateTask extends TimerTask {
        private Random random = new Random();

        @Override
        public void run() {
            refreshListView();
        }
    }

    @FXML
    private void addDevice() {
        String deviceName = deviceNameTextField.getText();
        Device newDevice = (devHardware.isSelected()) ? new HardwareDevice(deviceName) : new SoftwareDevice(deviceName);

        deviceMonitor.addDevice(newDevice);
        deviceNameTextField.clear();
    }

    @FXML
    private void editDevice(){
        Device selectedDevice = deviceListView.getSelectionModel().getSelectedItem();
        Device deviceNew = null;
        if(selectedDevice.getDeviceType() == Device.DeviceType.Software){
            deviceNew = CreateSoftwareDevice();
        }else{
            deviceNew = CreateHardwareDevice();
        }
         deviceMonitor.editDevice(selectedDevice, deviceNew);
    }
    private Device CreateHardwareDevice(){
        Device device = new HardwareDevice(nameInfo.getText());
        return device;
    }
    private Device CreateSoftwareDevice(){
        Device device = new SoftwareDevice(nameInfo.getText());
        return device;
    }
    public void refreshListView() {
        deviceListView.refresh();
    }

    private class DeviceListCell extends ListCell<Device> {
        @Override
        protected void updateItem(Device device, boolean empty) {
            super.updateItem(device, empty);

            if (device == null || empty) {
                setText(null);
                setGraphic(null);
                setStyle(""); // Reset the cell style
            } else {
                setText(device.getName() + " - " + device.getStatus());

                // Set the cell style based on the device status
                if (device.getStatus().equals("Online")) {
                    setStyle("-fx-text-fill: green;");
                } else if (device.getStatus().equals("Offline")) {
                    setStyle("-fx-text-fill: red;");
                } else {
                    setStyle(""); // Reset the cell style
                }
            }
        }
    }
}
