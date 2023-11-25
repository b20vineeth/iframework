package com.web.framework.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel {
	
	private String id;
	@JsonProperty("user_name")
	private String username;
	@JsonProperty("first_name")
	private String firstname;
	@JsonProperty("last_name")
	private String lastname;
	@JsonProperty("email")
	private String  email;
	@JsonProperty("valid_from")
	private Date validFrom;
	@JsonProperty("valid_to")
	private Date validTo;

}
