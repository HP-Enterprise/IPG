package com.cmos.ipg.entity;

/**
 * Created by jackl on 2016/5/5.
 */
public class DeviceStatus {
    private  int id;
    private  int deviceId;
    private String deviceParaName;
    private String deviceParaValue;

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