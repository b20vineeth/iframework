package com.web.framework.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
 
@ComponentScan(basePackages = "com")
@EnableAutoConfiguration()
@ComponentScan(basePackages = "com")
@EntityScan(basePackages = "com")  
public class Application {

	
	
	
}
