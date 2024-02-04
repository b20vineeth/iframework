package com.web.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
public class SecurityConfiguration {
	
    
	@Autowired
	protected JwtAuthenticationFilter jwtAuthenticationFilter;
	
	
}
