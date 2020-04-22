package com.ctsi.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel(value = "系统实体", description = "系统实体")
public class ProjectsSystem {

    @ApiModelProperty(value = "项目ID", name = "projectsId", example = "12354652313")
    private String projectsId;


    @ApiModelProperty(value = "项目系统ID", name = "personnelId", example = "12354652313")
    private String systemId;


    @ApiModelProperty(value = "系统名称", name = "name", example = "OA系统")
    private String systemName;

    @ApiModelProperty(value = "系统年份", name = "year")
    @DateTimeFormat(pattern = "yyyy")
    @JsonFormat(pattern = "yyyy", timezone = "GMT+8")
    private Date year;

    @ApiModelProperty(value = "系统负责人", name = "manager")
    private String manager;

    @ApiModelProperty(value = "系统编号", name = "businessNo")
    private String businessNo;

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getProjectsId() {
        return projectsId;
    }

    public void setProjectsId(String projectsId) {
        this.projectsId = projectsId;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    @Override
    public String toString() {
        return "ProjectsSystem{" +
                "projectsId='" + projectsId + '\'' +
                ", systemId='" + systemId + '\'' +
                ", systemName='" + systemName + '\'' +
                ", year=" + year +
                ", manager='" + manager + '\'' +
                ", businessNo='" + businessNo + '\'' +
                '}';
    }
}
