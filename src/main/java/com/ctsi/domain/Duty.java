package com.ctsi.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.ctsi.ssdc.admin.domain.util.ExcelColumn;

public class Duty {

    @ExcelColumn(value = "ip",col = 1)
    @Excel(name = "ip", orderNum = "0")
    private String ip;
    @ExcelColumn(value = "带班负责人" ,col=2)
    @Excel(name = "带班负责人", orderNum = "1")
    private String teamLeader;
    @ExcelColumn(value = "监控处置组（网络）" ,col=3)
    @Excel(name = "监控处置组（网络）", orderNum = "2")
    private String networkGroup;
    @ExcelColumn(value = "监控处置组(安全)" ,col=4)
    @Excel(name = "监控处置组(安全)", orderNum = "3")
    private String securityGroup;
    @ExcelColumn(value = "分析研判组" ,col=5)
    @Excel(name = "分析研判组", orderNum = "4")
    private String analysisGroup;
    @ExcelColumn(value = "钉钉群回复（客服）" ,col=6)
    @Excel(name = "钉钉群回复（客服）", orderNum = "5")
    private String customerServiceGroup;

    private String typeId;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public String getNetworkGroup() {
        return networkGroup;
    }

    public void setNetworkGroup(String networkGroup) {
        this.networkGroup = networkGroup;
    }

    public String getSecurityGroup() {
        return securityGroup;
    }

    public void setSecurityGroup(String securityGroup) {
        this.securityGroup = securityGroup;
    }

    public String getAnalysisGroup() {
        return analysisGroup;
    }

    public void setAnalysisGroup(String analysisGroup) {
        this.analysisGroup = analysisGroup;
    }

    public String getCustomerServiceGroup() {
        return customerServiceGroup;
    }

    public void setCustomerServiceGroup(String customerServiceGroup) {
        this.customerServiceGroup = customerServiceGroup;
    }
}
