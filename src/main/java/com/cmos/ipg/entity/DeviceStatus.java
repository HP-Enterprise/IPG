package com.cmos.ipg.entity;

/**
 * Created by jackl on 2016/5/5.
 */
public class DeviceStatus {
    private  int id;
    private  int deviceId;
    private String deviceName;
    private String deviceCode;
    private String deviceLocation;
    private String deviceParaName;
    private String deviceParaValue;
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

    public String getParkCode() {
        return parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }
}
