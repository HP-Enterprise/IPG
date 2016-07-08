package com.cmos.ipg.entity;

import java.util.Date;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class EnergyRecord {
    private int id;
    private String deviceName;
    private Date deviceSy;
    private int deviceType;
    private String deviceLocate;
    private String deviceCode;
    private String parkCode;

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

    public Date getDeviceSy() {
        return deviceSy;
    }

    public void setDeviceSy(Date deviceSy) {
        this.deviceSy = deviceSy;
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

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getParkCode() {
        return parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }
}
