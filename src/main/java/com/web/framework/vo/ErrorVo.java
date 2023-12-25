package com.web.framework.vo;

import com.web.framework.model.EErrorType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorVo {
	String code;
	String message;
	EErrorType type;


}
