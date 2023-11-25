package com.web.framework.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.web.framework.model.EBevConfigType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BEVCONFIGMAP")
public class BevConfigMap extends BaseEntity{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

 
	@Column(name = "bean_name", length = 120)
	private String beanName;
	 
	
	@Enumerated(EnumType.STRING)
	@Column(name = "bean_type", length = 15,nullable = false)
	private EBevConfigType configType;
	
	@Column(name = "bean_des")
	private String beanDes;
  
	@ManyToOne
	@JoinColumn(name="bevid", referencedColumnName = "id")    
	private BevConfig bevConfig;
	

}
