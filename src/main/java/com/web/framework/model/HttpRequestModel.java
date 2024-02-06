package com.web.framework.model;

import java.util.List;

import org.springframework.http.HttpMethod;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter 
public class HttpRequestModel {

	Model data;
	
	List<? extends Model>  datas;
	 
	HttpMethod requestType; 
	
	EReturnType returnType; 
	
	String service;
	 
	String isAuthorized="F";
}
 