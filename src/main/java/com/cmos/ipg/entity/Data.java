package com.cmos.ipg.entity;

import java.io.Serializable;

/**
 * Created by jackl on 2016/4/28.
 */
public class Data implements Serializable {
    private  int id;
    private String client;
    private String bytes;

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
}
