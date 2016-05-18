package com.cmos.ipg.entity;

import java.util.Date;

/**
 * Created by jackl on 2016/5/18.
 */
public class Command {
    private  int id;
    private int eventId;
    private  short commandType;
    private  int num;
    private String action;
    private String param;
    private short commandStatus;
    private Date actionDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public short getCommandType() {
        return commandType;
    }

    public void setCommandType(short commandType) {
        this.commandType = commandType;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public short getCommandStatus() {
        return commandStatus;
    }

    public void setCommandStatus(short commandStatus) {
        this.commandStatus = commandStatus;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }
}
