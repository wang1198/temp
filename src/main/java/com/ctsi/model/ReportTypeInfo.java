package com.ctsi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * ClassName ReportTypeInfo
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/8 10:14
 * Version v1.0
 **/
public class ReportTypeInfo {
    @JsonIgnore
    private String fid;
    @JsonProperty("value")
    private String typeId;
    @JsonProperty("label")
    private String typeName;
    private List<CycleInfo> children;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<CycleInfo> getChildren() {
        return children;
    }

    public void setChildren(List<CycleInfo> children) {
        this.children = children;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
}
