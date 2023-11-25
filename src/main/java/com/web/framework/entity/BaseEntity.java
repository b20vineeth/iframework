package com.web.framework.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {


//	@ManyToOne
//	@JoinColumn(name="usrid", referencedColumnName = "id")    
//	private User user;
//	
	
	
	@Column(name = "crtdat")  
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	
	
	@Column(name = "lstupd")  
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastupdate;
	
	

	@Column(name = "frmdat")  
	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;
	
	@Column(name = "todat")  
	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;
	
	@Column(name = "status",length=1)
	@NonNull
	@JsonProperty("status")
	private String status="Y";

  
	
	
}
