package com.ctsi.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class Intranet {
    private String id;
    @Excel(name = "ip", orderNum = "1")
    private String ip;
    @Excel(name = "MAC", orderNum = "2")
    private String macAddress;
    @Excel(name = "办公区", orderNum = "0")
    private String officeZone;

    public String getProhibitReasonDesc() {
        return prohibitReasonDesc;
    }

    public void setProhibitReasonDesc(String prohibitReasonDesc) {
        this.prohibitReasonDesc = prohibitReasonDesc;
    }

    @Excel(name = "封禁原因", replace = {"高危漏洞_1", "疑似攻击行为_2","服务器非法接入_3","违禁的操作系统_4"}
            ,orderNum = "3")
    private String prohibitReason;
    private String prohibitReasonDesc;
    @Excel(name = "设备所属部门", orderNum = "6")
    private String equipmentDepartment;
    @Excel(name = "设备所有人", orderNum = "5")
    private String equipmentPerson;
    @Excel(name = "漏洞名称", orderNum = "4")
    private String leakName;
    @Excel(name = "处理人员", orderNum = "8")
    private String handlePerson;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "封禁日期", exportFormat = "yyyy-MM-dd HH:mm:ss",orderNum = "7")
    private String prohibitTime;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "解封日期", exportFormat = "yyyy-MM-dd",orderNum = "9")
    private String unsealTime;
    private String comment;

    public String getProhibitReason() {
        return prohibitReason;
    }

    public void setProhibitReason(String prohibitReason) {
        this.prohibitReason = prohibitReason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getOfficeZone() {
        return officeZone;
    }

    public void setOfficeZone(String officeZone) {
        this.officeZone = officeZone;
    }


    public String getEquipmentDepartment() {
        return equipmentDepartment;
    }

    public void setEquipmentDepartment(String equipmentDepartment) {
        this.equipmentDepartment = equipmentDepartment;
    }

    public String getEquipmentPerson() {
        return equipmentPerson;
    }

    public void setEquipmentPerson(String equipmentPerson) {
        this.equipmentPerson = equipmentPerson;
    }

    public String getLeakName() {
        return leakName;
    }

    public void setLeakName(String leakName) {
        this.leakName = leakName;
    }

    public String getHandlePerson() {
        return handlePerson;
    }

    public void setHandlePerson(String handlePerson) {
        this.handlePerson = handlePerson;
    }

    public String getProhibitTime() {
        return prohibitTime;
    }

    public void setProhibitTime(String prohibitTime) {
        this.prohibitTime = prohibitTime;
    }

    public String getUnsealTime() {
        return unsealTime;
    }

    public void setUnsealTime(String unsealTime) {
        this.unsealTime = unsealTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
