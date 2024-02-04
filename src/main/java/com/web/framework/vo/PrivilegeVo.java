package com.web.framework.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrivilegeVo extends AbstractVo {
	

	public static final String PRIVILEGE_DETAILS = "PRIVILEGE_DETAILS";
	private List<String> privilegeCodes; 
	
	private Integer  usrid;
	
	
	

}
