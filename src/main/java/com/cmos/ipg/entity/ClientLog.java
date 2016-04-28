package com.cmos.ipg.entity;

/**
 * Created by jackl on 2016/4/28.
 */
public class ClientLog {

    private  int id;
    private String client;
    private String action;

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
