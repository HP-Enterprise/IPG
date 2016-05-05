package com.cmos.ipg.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jackl on 2016/5/5.
 */
public class Device implements Serializable {
    private  int id;
    private String deviceName;
    private String deviceSn;
    private int deviceType;
    private String deviceLocate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceLocate() {
        return deviceLocate;
    }

    public void setDeviceLocate(String deviceLocate) {
        this.deviceLocate = deviceLocate;
    }
}
