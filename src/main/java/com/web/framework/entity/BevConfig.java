package com.web.framework.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint; 
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BEVCONFIG", uniqueConstraints = { @UniqueConstraint(columnNames = "CONFIGCOD")})
public class BevConfig extends BaseEntity{
	
	
	public static final String BEV_CONFIG_DATA = "BEV_CONFIG_DATA";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
 
	@Column(name = "CONFIGNAM", length = 120)
	private String configName;
 
	@Column(name = "CONFIGCOD", length = 120)
	private String configCode;
 

	 

}
