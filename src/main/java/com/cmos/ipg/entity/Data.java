package com.cmos.ipg.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jackl on 2016/4/28.
 */
public class Data implements Serializable {
    private  int id;
    private String client;
    private String bytes;
    private Date  actionDate;
    private String parkCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getBytes() {
        return bytes;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public String getParkCode() {
        return parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }
}
