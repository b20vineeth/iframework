package com.web.framework.vo;

import java.util.Date;

import com.web.framework.model.EProvider;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo extends AbstractVo {

	public static final String USER_DETAILS = "USER_DETAILS";
	public static final String USERNAME_SUGGESTIONS = "USERNAME_SUGGESTIONS";
	private Integer id;
	private String uname;
	private String email;

	private String password;
	private Date validFrom;
	private Date validTo;

	private String firstName;
	private String lastName;
	
	private EProvider provider;
	

}
