package io.dh.spring.emailrender.domain;

import java.util.Date;

//public class EmailConfig implements Serializable {
public class EmailConfig  {
//	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private String emailConfigDesc;
	//
	private String fromAddress;
	//
	private String to;
	//
	private String emailTemplate;
	//
	private String createBy;
	//
	private Date createdTime;
	//
	private String updatedBy;
	//
	private Date updatedTime;
	//
	private Integer isActive;
	//
	private String contentSql;
	//
	private String columnMapping;

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
	public void setEmailConfigDesc(String emailConfigDesc) {
		this.emailConfigDesc = emailConfigDesc;
	}
	/**
	 * 获取：
	 */
	public String getEmailConfigDesc() {
		return emailConfigDesc;
	}
	/**
	 * 设置：
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	/**
	 * 获取：
	 */
	public String getFromAddress() {
		return fromAddress;
	}
	/**
	 * 设置：
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * 获取：
	 */
	public String getTo() {
		return to;
	}
	/**
	 * 设置：
	 */
	public void setEmailTemplate(String emailTemplate) {
		this.emailTemplate = emailTemplate;
	}
	/**
	 * 获取：
	 */
	public String getEmailTemplate() {
		return emailTemplate;
	}
	/**
	 * 设置：
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreatedTime() {
		return createdTime;
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
	/**
	 * 设置：
	 */
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdatedTime() {
		return updatedTime;
	}
	/**
	 * 设置：
	 */
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	/**
	 * 获取：
	 */
	public Integer getIsActive() {
		return isActive;
	}
	/**
	 * 设置：
	 */
	public void setContentSql(String contentSql) {
		this.contentSql = contentSql;
	}
	/**
	 * 获取：
	 */
	public String getContentSql() {
		return contentSql;
	}

	public String getColumnMapping() {
		return columnMapping;
	}

	public void setColumnMapping(String columnMapping) {
		this.columnMapping = columnMapping;
	}
	/**
	 * 设置：
	 */

}
