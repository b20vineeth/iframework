package com.web.framework.model.request;

import com.web.framework.model.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter 
public class AuthRequest extends Model {
 
	  
	 private String uname;
	 private int id;
	 private String token;

	 

	 

}



 
