package com.cmos.ipg.entity;

import java.io.Serializable;

/**
 * Created by jackl on 2016/5/5.
 */
public class Agent implements Serializable{
    private  int id;
    private String agentName;
    private short agentType;
    private int num;
    private String ip;
    private String port;
    private int contable;
    private int conProtocol;
    private String description;
    private short agentOnline;
    private String parkCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getContable() {
        return contable;
    }

    public void setContable(int contable) {
        this.contable = contable;
    }

    public int getConProtocol() {
        return conProtocol;
    }

    public void setConProtocol(int conProtocol) {
        this.conProtocol = conProtocol;
    }

    public short getAgentType() {
        return agentType;
    }

    public void setAgentType(short agentType) {
        this.agentType = agentType;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getAgentOnline() {
        return agentOnline;
    }

    public void setAgentOnline(short agentOnline) {
        this.agentOnline = agentOnline;
    }

    public String getParkCode() {
        return parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }
}
