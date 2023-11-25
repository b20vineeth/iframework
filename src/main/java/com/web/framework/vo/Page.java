package com.web.framework.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {

 
	
	@JsonProperty("current_page")
	int currentPage=1;
	
	
	@JsonProperty("total_page")
	int totalPage=1;
	
	
	@JsonProperty("per_page")
	int perPage=10;
	
	@JsonProperty("max_result")
	int maxResult=10;
	
}
