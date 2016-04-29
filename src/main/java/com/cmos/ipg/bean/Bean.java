package com.cmos.ipg.bean;

/**
 * Created by jackl on 2016/4/29.
 */
public abstract class Bean {
    private Short startCode=9252;
    private Short length;
    private Byte messageType;
    private Integer sendingTime;
    private Byte agentNum;
    private Byte checkSum;

    public Short getStartCode() {
        return startCode;
    }

    public void setStartCode(Short startCode) {
        this.startCode = startCode;
    }

    public Short getLength() {
        return length;
    }

    public void setLength(Short length) {
        this.length = length;
    }

    public Byte getMessageType() {
        return messageType;
    }

    public void setMessageType(Byte messageType) {
        this.messageType = messageType;
    }

    public Integer getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(Integer sendingTime) {
        this.sendingTime = sendingTime;
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
