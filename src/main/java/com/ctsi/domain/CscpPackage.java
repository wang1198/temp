package com.ctsi.domain;

import java.util.List;

/**
 * ClassName CscpPackage
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/1 21:07
 * Version v1.0
 **/
public class CscpPackage {
    private String id;
    private String packName;
    private String description;
    private String orgId;
    private String orgName;
    private List<CscpFunc> funcs;
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
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

    public List<CscpFunc> getFuncs() {
        return funcs;
    }

    public void setFuncs(List<CscpFunc> funcs) {
        this.funcs = funcs;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
