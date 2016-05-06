package com.cmos.ipg.entity;

/**
 * Created by jackl on 2016/5/5.
 */
public class Agent {
    private  int id;
    private String agentName;
    private String ip;
    private String port;
    private int contable;
    private int conProtocol;

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
}
