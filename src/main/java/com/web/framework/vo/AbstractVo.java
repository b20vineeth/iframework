package com.web.framework.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbstractVo {

	public static final String YES = "Y";
	public static final String NO = "N";

	public String operationFlag;

	public String status;

	@JsonProperty("user")
	public UserVo userVo;

	@JsonProperty("last_update")
	private Date lastupdate;

	@JsonProperty("valid_from")
	private Date validFrom;

	@JsonProperty("valid_to")
	private Date validTo;

	@JsonProperty("created_date")
	private Date createdDate;

	@JsonProperty("created_from_date")
	private Date fromDate;
	
	private HeaderVo header;
	
	
	public String component;
	
	
	@JsonProperty("created_to_date")
	private Date toDate;

	Page page;

	
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getOperationFlag() {
		return operationFlag;
	}

	public void setOperationFlag(String operationFlag) {
		this.operationFlag = operationFlag;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	public String getClassName() {
		
		return this.getClass().getCanonicalName();
	}

	public HeaderVo getHeader() {
		return header;
	}

	public void setHeader(HeaderVo header) {
		this.header = header;
	}

}
