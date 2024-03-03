package com.web.framework.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.web.framework.model.EErrorType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorVo extends AbstractVo{
	
	@JsonProperty("code")
	private String code;
	@JsonProperty("message")
	private String message;
	@JsonProperty("type")
	private EErrorType type;


}
