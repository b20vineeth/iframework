package com.web.framework.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.web.framework.entity.Role;
import com.web.framework.model.EProvider;
import com.web.framework.util.CacheKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo extends AbstractVo {

	public static final String USER_DETAILS = "USER_DETAILS";
	public static final String USERNAME_SUGGESTIONS = "USERNAME_SUGGESTIONS";
	public static final String AUTH_SOURCE_LOCAL= "LOCAL";
	public static final String AUTH_SOURCE_GOOGLE= "GOOGLE";
	public static final String AUTH_SOURCE_FACEBOOK= "FACEBOOK"; 
	
	@CacheKey
	private Integer id;
	
	@JsonProperty("uname")
	private String uname;
	private String email;
  
	@JsonProperty("firstname")
	private String firstName;
	
	private String password;
	
	@JsonProperty("lastname")
	private String lastName;
	
	private String currentpassword;
	
	@CacheKey
	private String token;
	
	private EProvider provider;
 
	private Role role;
	
	

}
