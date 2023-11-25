package com.web.framework.vo;

public class SecureVo {

	 public static final String[] AUTH_WHITELIST = {
	            // -- swagger ui
	            "/v2/api-docs", 
	            "/swagger-resources/**", 
	            "/configuration/ui",
	            "/configuration/security", 
	            "/swagger-ui.html",
	            "/webjars/**",
	            "/swagger-ui",
	            "/swagger-ui/**"
	    };
}
