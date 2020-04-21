package com.ctsi.domain;

/**
 * ClassName CscpServiceInfo
 * Description //TODO
 * Author tongliwei
 * Date 2020/3/20 15:44
 * Version v1.0
 **/
public class CscpServiceInfo {
    private String id;
    private String ip;
    private Integer port;
    private String serviceName;
    private String status;
    private String checkTime;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
