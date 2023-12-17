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
	private Integer id;

	@Column(name = "crtdat")  
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	
	
	@Column(name = "lstupd",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")  
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastupdate;
	
	

	@Column(name = "frmdat",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")  
	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;
	
	@Column(name = "todat",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")  
	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;
	
	@Column(name = "status",length=1)
	@ColumnDefault(value="'Y'")
	@NonNull
	@JsonProperty("status")
	private String status="Y";

  
	
	
}
