package com.ctsi.model;

import java.util.List;

/**
 * ClassName CscpPackDTO
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/25 13:03
 * Version v1.0
 **/
public class CscpPackDTO {
    private String id;
    private String packName;
    private String description;
    private String orgId;
    private String orgName;
    private String createTime;
    private List<String> funcIDs;
    private String funcNames;
    private String begin;
    private String end;

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

    public List<String> getFuncIDs() {
        return funcIDs;
    }

    public void setFuncIDs(List<String> funcIDs) {
        this.funcIDs = funcIDs;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getFuncNames() {
        return funcNames;
    }

    public void setFuncNames(String funcNames) {
        this.funcNames = funcNames;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
