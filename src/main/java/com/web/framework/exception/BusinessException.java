package com.web.framework.exception;

import java.util.List;

import com.web.framework.vo.ErrorVo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends Exception {

	private List<ErrorVo> errors;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BusinessException() {
		
	}
	
	public BusinessException(String message)
    {
       super(message);
    }
	
	public BusinessException(List<ErrorVo> error)
    {
		this.errors=error;
    }

	
}
