package com.web.framework.model;

import java.util.List;

import com.web.framework.vo.ErrorVo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorModel extends Model{

	List<ErrorVo> errors;
	
	public ErrorModel(List<ErrorVo> errors) {
		 this.errors=errors;
	}
	public ErrorModel() { 
	}

}
