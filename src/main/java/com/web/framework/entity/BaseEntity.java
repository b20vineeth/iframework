package com.web.framework.entity;

import java.io.Serializable;
import java.util.Date;
 
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable {


//	@ManyToOne
//	@JoinColumn(name="usrid", referencedColumnName = "id")    
//	private User user;
//	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "CRTDAT")  
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	
	
	@Column(name = "LSTUPD",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")  
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastupdate;
	
	@ManyToOne
	@JoinColumn(name="LSTUPDUSR", referencedColumnName = "ID")    
	private User lastupdatedUser;

	@Column(name = "FRMDAT",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")  
	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;
	
	@Column(name = "TODAT",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")  
	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;
	
	@Column(name = "STATUS",length=1)
	@ColumnDefault(value="'Y'")
	@NonNull 
	private String status="Y";

  
	
	
}
