package com.web.framework.model;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel extends Model {
	
	private String id; 
	private String username;
	@JsonProperty("firstname")
	private String firstname;
	@JsonProperty("lastname")
	private String lastname;
	@JsonProperty("email")
	private String  email;
	@JsonProperty("validfrom")
	private Date validFrom;
	@JsonProperty("validto")
	private Date validTo;
	
	Collection<? extends GrantedAuthority> authorities; 

}
