package com.ctsi.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Extranet {
    private String id;

    private String fromIP;

    private String fromPort;

    @Excel(name = "目的IP地址", orderNum = "0")
    private String dstIP;
    @Excel(name = "目的端口", orderNum = "1")
    private String dstPort;

    private String ipPort;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date discoveryTime;
    private String attackedSystem;
    private String attackSystemDesc;

    private String resportCompany;
    @Excel(name = "信息来源", orderNum = "10")
    private String resportCompanyDesc;



    @Excel(name = "协议类型", orderNum = "2")
    private String protocolType;

    private String fromIPLocation;
    private String fromIPLocationDesc;
    private String fromIPCity;
    private String fromIPCityDesc;
    private String detailLocation;




    private String fromIPType;
    @Excel(name = "风险级别", replace = {"高级_1", "中级_2"},
            orderNum = "6")
    private String riskLevel;
    private String riskLevelDesc;



    @Excel(name = "攻击类型", replace = {"远程代码/命令执行攻击_1", "Sql注入攻击_2","Cookie攻击_3",
            "扫描攻击_4","任意文件上传_5","下载与利用_6","口令破解攻击_7","溢出攻击_8","逻辑漏洞攻击_9"},
            orderNum = "3")
    private String attackType;
    private String attackTypeDesc;
    @Excel(name = "攻击说明", orderNum = "4")
    private String attackDescription;
    @Excel(name = "攻击详情", orderNum = "5")
    private String attackDetails;
    @Excel(name = "潜在影响", orderNum = "7")
    private String potentialImpact;
    @Excel(name = "是否已处置", replace = {"是_1", "否_2"},
            orderNum = "8")
    private String isHandle;
    private String isHandleDesc;
    @Excel(name = "处置措施", orderNum = "9")
    private String disposalMeasures;
    private String comment;

    private String typeId;

    public String getAttackSystemDesc() {
        return attackSystemDesc;
    }

    public void setAttackSystemDesc(String attackSystemDesc) {
        this.attackSystemDesc = attackSystemDesc;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }


    public String getDetailLocation() {
        return detailLocation;
    }

    public void setDetailLocation(String detailLocation) {
        this.detailLocation = detailLocation;
    }

    public String getIpPort() {
        return ipPort;
    }

    public void setIpPort(String ipPort) {
        this.ipPort = ipPort;
    }


    public String getFromIPLocationDesc() {
        return fromIPLocationDesc;
    }

    public void setFromIPLocationDesc(String fromIPLocationDesc) {
        this.fromIPLocationDesc = fromIPLocationDesc;
    }

    public String getFromIPCity() {
        return fromIPCity;
    }

    public void setFromIPCity(String fromIPCity) {
        this.fromIPCity = fromIPCity;
    }

    public String getFromIPCityDesc() {
        return fromIPCityDesc;
    }

    public void setFromIPCityDesc(String fromIPCityDesc) {
        this.fromIPCityDesc = fromIPCityDesc;
    }

    public String getResportCompanyDesc() {
        return resportCompanyDesc;
    }

    public void setResportCompanyDesc(String resportCompanyDesc) {
        this.resportCompanyDesc = resportCompanyDesc;
    }


    public String getFromIPType() {
        return fromIPType;
    }

    public void setFromIPType(String fromIPType) {
        this.fromIPType = fromIPType;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public String getRiskLevelDesc() {
        return riskLevelDesc;
    }

    public void setRiskLevelDesc(String riskLevelDesc) {
        this.riskLevelDesc = riskLevelDesc;
    }

    public String getAttackTypeDesc() {
        return attackTypeDesc;
    }

    public void setAttackTypeDesc(String attackTypeDesc) {
        this.attackTypeDesc = attackTypeDesc;
    }

    public String getIsHandleDesc() {
        return isHandleDesc;
    }

    public void setIsHandleDesc(String isHandleDesc) {
        this.isHandleDesc = isHandleDesc;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromIP() {
        return fromIP;
    }

    public void setFromIP(String fromIP) {
        this.fromIP = fromIP;
    }

    public String getFromPort() {
        return fromPort;
    }

    public void setFromPort(String fromPort) {
        this.fromPort = fromPort;
    }

    public String getDstIP() {
        return dstIP;
    }

    public void setDstIP(String dstIP) {
        this.dstIP = dstIP;
    }

    public String getDstPort() {
        return dstPort;
    }

    public void setDstPort(String dstPort) {
        this.dstPort = dstPort;
    }

    public Date getDiscoveryTime() {
        return discoveryTime;
    }

    public void setDiscoveryTime(Date discoveryTime) {
        this.discoveryTime = discoveryTime;
    }

    public String getAttackedSystem() {
        return attackedSystem;
    }

    public void setAttackedSystem(String attackedSystem) {
        this.attackedSystem = attackedSystem;
    }

    public String getResportCompany() {
        return resportCompany;
    }

    public void setResportCompany(String resportCompany) {
        this.resportCompany = resportCompany;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public String getFromIPLocation() {
        return fromIPLocation;
    }

    public void setFromIPLocation(String fromIPLocation) {
        this.fromIPLocation = fromIPLocation;
    }


    public String getAttackDescription() {
        return attackDescription;
    }

    public void setAttackDescription(String attackDescription) {
        this.attackDescription = attackDescription;
    }

    public String getAttackDetails() {
        return attackDetails;
    }

    public void setAttackDetails(String attackDetails) {
        this.attackDetails = attackDetails;
    }

    public String getPotentialImpact() {
        return potentialImpact;
    }

    public void setPotentialImpact(String potentialImpact) {
        this.potentialImpact = potentialImpact;
    }

    public String getIsHandle() {
        return isHandle;
    }

    public void setIsHandle(String isHandle) {
        this.isHandle = isHandle;
    }

    public String getDisposalMeasures() {
        return disposalMeasures;
    }

    public void setDisposalMeasures(String disposalMeasures) {
        this.disposalMeasures = disposalMeasures;
    }
}
