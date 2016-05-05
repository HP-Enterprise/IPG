package com.cmos.ipg.entity;

import java.util.Date;

/**
 * Created by jackl on 2016/5/5.
 */
public class AlarmConf {
    private  int id;
    private int deviceId;
    private  String deviceName;
    private String deviceParaName;
    private String deviceParaValue;
    private String alarmTitle;
    private String alarmContent;
    private int alarmLevel;
    private Date alarmDate;

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
}
