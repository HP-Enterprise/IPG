package com.cmos.ipg.entity;

import java.util.Date;

/**
 * 门禁控制表实体类
 * @author ybb
 * Created by Administrator on 2016/7/18 0018.
 */
public class AccessCtrl {
    private  int id;
    private String aAlias;//门禁别名
    private String aArea;//门禁区域
    private String aNum;
    private String ip;//门禁ip
    private String interfaceAddr;//门禁接口面板地址
    private String interfacePosition;//门禁接口面板左右
    private String aType ;//门禁设备类型
    private String taskCode;//任务代码
    private String eventCode;//时间代码
    private String msgTypeId;//消息类型id
    private String description;//告警描述
    private String ioMsgCode;//IO消息代码
    private Date eventTime;//时间发生时间
    private String controllerName;//控制器名称
    private String panelName;//接口面板名称
    private String cardReaderName;//读卡器名称
    private String eventName;//事件名称
    private String ioDescription;//IO事件描述
    private String cardNum;//卡号
    private String jobNum;//人员工号
    private String staffName;//人员姓名
    private String parkCode;//园区编号

    public String getaAlias() {
        return aAlias;
    }

    public void setaAlias(String aAlias) {
        this.aAlias = aAlias;
    }

    public String getaArea() {
        return aArea;
    }

    public void setaArea(String aArea) {
        this.aArea = aArea;
    }

    public String getaNum() {
        return aNum;
    }

    public void setaNum(String aNum) {
        this.aNum = aNum;
    }

    public String getaType() {
        return aType;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardReaderName() {
        return cardReaderName;
    }

    public void setCardReaderName(String cardReaderName) {
        this.cardReaderName = cardReaderName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public String getIoDescription() {
        return ioDescription;
    }

    public void setIoDescription(String ioDescription) {
        this.ioDescription = ioDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInterfaceAddr() {
        return interfaceAddr;
    }

    public void setInterfaceAddr(String interfaceAddr) {
        this.interfaceAddr = interfaceAddr;
    }

    public String getInterfacePosition() {
        return interfacePosition;
    }

    public void setInterfacePosition(String interfacePosition) {
        this.interfacePosition = interfacePosition;
    }

    public String getIoMsgCode() {
        return ioMsgCode;
    }

    public void setIoMsgCode(String ioMsgCode) {
        this.ioMsgCode = ioMsgCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public String getMsgTypeId() {
        return msgTypeId;
    }

    public void setMsgTypeId(String msgTypeId) {
        this.msgTypeId = msgTypeId;
    }

    public String getPanelName() {
        return panelName;
    }

    public void setPanelName(String panelName) {
        this.panelName = panelName;
    }

    public String getParkCode() {
        return parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }
}
