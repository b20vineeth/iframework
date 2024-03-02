package com.web.framework.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.web.framework.util.CacheKey;
import java.lang.reflect.Field;

public class AbstractVo {

	public static final String YES = "Y";
	public static final String NO = "N";
	private static final String FLAG_V = "V"; 

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
	
	public String ipaddress;
	
	public String component;
	
	@JsonProperty("source")
	public String source;

	
	@JsonProperty("created_to_date")
	private Date toDate;

	Page page;

	private long lastActivityTime;
	private Date  expiryDate;
	
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

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	

    public long getLastActivityTime() {
		return lastActivityTime;
	}

	public void setLastActivityTime(long lastActivityTime) {
		this.lastActivityTime = lastActivityTime;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
	
	
 
	public String getCacheName() {
		 return getClass().getSimpleName();
	}
	public String generateCacheKey() {
		StringBuilder keyBuilder = new StringBuilder();
		// Get all fields declared in the class, including inherited fields
		Field[] fields = getClass().getDeclaredFields();
		for (Field field : fields) {
			// Check if the field is annotated with the @CacheableField annotation
			if (field.isAnnotationPresent(CacheKey.class)) {
				try {
					// Access the field's value and append it to the cache key
					field.setAccessible(true);
					Object value = field.get(this);
					if (value != null) {
						keyBuilder.append(value.toString()).append("_");
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		// Remove the trailing underscore, if any
		if (keyBuilder.length() > 0) {
			keyBuilder.setLength(keyBuilder.length() - 1);
		}
		return keyBuilder.toString();
	}


  
}
