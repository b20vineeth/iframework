package com.web.framework.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.framework.vo.Page;

import lombok.Getter;
import lombok.Setter;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter 
public class RequestModel {

	Model data;
	
	List<? extends Model>  datas;
	 
	Page page;
}
