package com.ctsi.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * ClassName ReportInfoDTO
 * Description //TODO
 * Author tongliwei
 * Date 2020/4/9 14:32
 * Version v1.0
 **/
public class ReportInfoDTO {
    private Integer id;
    @NotBlank
    @Length(max = 15,message = "标题不能超过15个汉字")
    private String title;
    private String creator;
    private String orgId;
    private Integer scope;
    private String cycle;
    private String typeId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
