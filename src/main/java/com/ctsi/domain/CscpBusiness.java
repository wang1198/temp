package com.ctsi.domain;

import com.ctsi.ssdc.admin.domain.util.ExcelColumn;

import java.util.List;

/**
 * ClassName CscpBusiness
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/1 21:02
 * Version v1.0
 **/
public class CscpBusiness {
    private String id;
    @ExcelColumn(value = "系统名称", col = 1)
    private String businessName;
    private Integer businessType;
    @ExcelColumn(value = "系统描述", col = 4)
    private String description;
    private String orgId;
    private String orgName;
    private String createTime;
    @ExcelColumn(value = "系统负责人", col = 5)
    private String manager;
    @ExcelColumn(value = "系统编号", col = 3)
    private String businessNo;
    @ExcelColumn(value = "建设年份", col = 6)
    private String year;
    @ExcelColumn(value = "系统类型", col = 2)
    private String typeDesc;
    private List<String> url;
    private Integer show;
    @ExcelColumn(value = "URL", col = 7)
    private String urlDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public Integer getShow() {
        return show;
    }

    public void setShow(Integer show) {
        this.show = show;
    }

    public String getUrlDesc() {
        return urlDesc;
    }

    public void setUrlDesc(String urlDesc) {
        this.urlDesc = urlDesc;
    }
}
