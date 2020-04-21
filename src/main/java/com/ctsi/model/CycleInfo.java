package com.ctsi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ClassName CycleInfo
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/8 15:31
 * Version v1.0
 **/
public class CycleInfo {
    @JsonIgnore
    private Integer id;
    @JsonIgnore
    private Integer typeId;
    @JsonProperty("value")
    private String cycle;
    @JsonProperty("label")
    private String cycleDesc;

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getCycleDesc() {
        return cycleDesc;
    }

    public void setCycleDesc(String cycleDesc) {
        this.cycleDesc = cycleDesc;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
