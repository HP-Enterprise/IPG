package com.cmos.ipg.entity;

import java.util.Date;

/**
 * Created by jackl on 2016/5/5.
 */
public class AlarmHistory {
    private  int id;
    private int deviceId;
    private  String alarmDeviceName;
    private String alarmDeviceCode;
    private String alarmDeviceLocate;
    private String alarmTitle;
    private String alarmContent;
    private int alarmLevel;
    private Date alarmDate;
    private String parkCode;

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

    public String getAlarmDeviceName() {
        return alarmDeviceName;
    }

    public void setAlarmDeviceName(String alarmDeviceName) {
        this.alarmDeviceName = alarmDeviceName;
    }

    public String getAlarmDeviceCode() {
        return alarmDeviceCode;
    }

    public void setAlarmDeviceCode(String alarmDeviceCode) {
        this.alarmDeviceCode = alarmDeviceCode;
    }

    public String getAlarmDeviceLocate() {
        return alarmDeviceLocate;
    }

    public void setAlarmDeviceLocate(String alarmDeviceLocate) {
        this.alarmDeviceLocate = alarmDeviceLocate;
    }

    public String getAlarmTitle() {
        return alarmTitle;
    }

    public void setAlarmTitle(String alarmTitle) {
        this.alarmTitle = alarmTitle;
    }

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    public int getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(int alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public Date getAlarmDate() {
        return alarmDate;
    }

    public void setAlarmDate(Date alarmDate) {
        this.alarmDate = alarmDate;
    }

    public String getParkCode() {
        return parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }
}
