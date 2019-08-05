package com.ppdai.bdtp.admin.performance.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author bdtester
 * @email bdtester@ppdai.com
 * @date 2018-08-28 19:18:07
 */
public class PerfJobDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String desc;
	//
	private String perfScenario;
	//
	private String apiDefinition;
	//
	private String jmeterScriptContent;
	//
	private String jobStatus;
	//
	private String runMachines;
	//
	private String jmeterFileLocation;
	//
	private String csvFileLocation;
	//
	private String csvConfig;
	//
	private String jmeterParamConfig;
	//
	private Date createTime;
	//
	private Date updateTime;
	//
	private String createdBy;
	//
	private String updatedBy;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * 设置：
	 */
	public void setPerfScenario(String perfScenario) {
		this.perfScenario = perfScenario;
	}
	/**
	 * 获取：
	 */
	public String getPerfScenario() {
		return perfScenario;
	}
	/**
	 * 设置：
	 */
	public void setApiDefinition(String apiDefinition) {
		this.apiDefinition = apiDefinition;
	}
	/**
	 * 获取：
	 */
	public String getApiDefinition() {
		return apiDefinition;
	}
	/**
	 * 设置：
	 */
	public void setJmeterScriptContent(String jmeterScriptContent) {
		this.jmeterScriptContent = jmeterScriptContent;
	}
	/**
	 * 获取：
	 */
	public String getJmeterScriptContent() {
		return jmeterScriptContent;
	}
	/**
	 * 设置：
	 */
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	/**
	 * 获取：
	 */
	public String getJobStatus() {
		return jobStatus;
	}
	/**
	 * 设置：
	 */
	public void setRunMachines(String runMachines) {
		this.runMachines = runMachines;
	}
	/**
	 * 获取：
	 */
	public String getRunMachines() {
		return runMachines;
	}
	/**
	 * 设置：
	 */
	public void setJmeterFileLocation(String jmeterFileLocation) {
		this.jmeterFileLocation = jmeterFileLocation;
	}
	/**
	 * 获取：
	 */
	public String getJmeterFileLocation() {
		return jmeterFileLocation;
	}
	/**
	 * 设置：
	 */
	public void setCsvFileLocation(String csvFileLocation) {
		this.csvFileLocation = csvFileLocation;
	}
	/**
	 * 获取：
	 */
	public String getCsvFileLocation() {
		return csvFileLocation;
	}
	/**
	 * 设置：
	 */
	public void setCsvConfig(String csvConfig) {
		this.csvConfig = csvConfig;
	}
	/**
	 * 获取：
	 */
	public String getCsvConfig() {
		return csvConfig;
	}
	/**
	 * 设置：
	 */
	public void setJmeterParamConfig(String jmeterParamConfig) {
		this.jmeterParamConfig = jmeterParamConfig;
	}
	/**
	 * 获取：
	 */
	public String getJmeterParamConfig() {
		return jmeterParamConfig;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * 获取：
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * 设置：
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * 获取：
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
}
