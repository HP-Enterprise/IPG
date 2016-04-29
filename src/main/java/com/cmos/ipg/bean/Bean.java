package com.cmos.ipg.bean;

import com.cmos.ipg.utils.DataTool;

/**
 * Created by jackl on 2016/4/29.
 */
public abstract class Bean {
    private Short startCode=9252;
    private Short messageSize;
    private Byte messageType;
    private Byte mId;
    private Integer sendingTime;
    private Integer eventId;
    private Byte agentNum;
    private Byte checkSum;

    public static final int BUFFER_SIZE = 1024;
    public DataTool dataTool = new DataTool();

    public Short getStartCode() {
        return startCode;
    }

    public void setStartCode(Short startCode) {
        this.startCode = startCode;
    }

    public Short getMessageSize() {
        return messageSize;
    }

    public void setMessageSize(Short messageSize) {
        this.messageSize = messageSize;
    }

    public Byte getMessageType() {
        return messageType;
    }

    public void setMessageType(Byte messageType) {
        this.messageType = messageType;
    }

    public Byte getmId() {
        return mId;
    }

    public void setmId(Byte mId) {
        this.mId = mId;
    }

    public Integer getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(Integer sendingTime) {
        this.sendingTime = sendingTime;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Byte getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(Byte agentNum) {
        this.agentNum = agentNum;
    }

    public Byte getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(Byte checkSum) {
        this.checkSum = checkSum;
    }
}
