package com.web.framework.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
	
	String token;
	String userName;
	String userId;
	String email; 
	String status;
	String message;
	String displayName; 
	  
	 
	

}
