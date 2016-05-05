package com.cmos.ipg.entity;

/**
 * Created by jackl on 2016/5/5.
 */
public class Agent {
    private  int id;
    private String agentName;
    private String ip;
    private String port;
    private String contable;

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

    public String getContable() {
        return contable;
    }

    public void setContable(String contable) {
        this.contable = contable;
    }
}
