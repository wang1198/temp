package com.ctsi.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 *    任务公司架构实体
 * @author qinyx
 *2020年4月13日
 */
@Table(name="t_task_company")
@ApiModel(value = "任务公司架构实体", description = "任务公司架构实体")
public class TaskCompany {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 公司ID
	 */
	@ApiModelProperty(value = "公司ID", name = "companyId", example = "1", required = true)
	@Id
	private String companyId;
	
	/**
	 * 公司全称
	 */
	@Excel(name="公司全称",orderNum = "1",width = 35)
	@NotNull
	@ApiModelProperty(value = "公司全称", name = "companyFullName", example = "4254sda423131", required = true)
	private String companyFullName;
	
	/**
	 * 公司简称
	 */
	@Excel(name="公司简称",orderNum = "2",width = 25)
	@NotNull
	@ApiModelProperty(value = "公司简称", name = "companySimpleName", example = "4254sd", required = true)
	private String companySimpleName;
	
	/**
	 * 护网行动任务ID
	 */
	@ApiModelProperty(value = "任务ID", name = "taskId", example = "2", required = true)
	private String taskId;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyFullName() {
		return companyFullName;
	}

	public void setCompanyFullName(String companyFullName) {
		this.companyFullName = companyFullName;
	}

	public String getCompanySimpleName() {
		return companySimpleName;
	}

	public void setCompanySimpleName(String companySimpleName) {
		this.companySimpleName = companySimpleName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	
}
