package com.cmos.ipg.entity;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class EnergyRecordDetail {
    private  int id;
    private  int deviceId;
    private  String deviceName;
    private  String deviceCode;
    private  String deviceLocation;
    private  String deviceParaName;
    private  String deviceParaValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceLocation() {
        return deviceLocation;
    }

    public void setDeviceLocation(String deviceLocation) {
        this.deviceLocation = deviceLocation;
    }

    public String getDeviceParaName() {
        return deviceParaName;
    }

    public void setDeviceParaName(String deviceParaName) {
        this.deviceParaName = deviceParaName;
    }

    public String getDeviceParaValue() {
        return deviceParaValue;
    }

    public void setDeviceParaValue(String deviceParaValue) {
        this.deviceParaValue = deviceParaValue;
    }
}
