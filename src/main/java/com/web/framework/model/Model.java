package com.web.framework.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Model {

	
	@JsonProperty("valid_from")
	private Date validFrom;
	@JsonProperty("valid_to")
	private Date validTo;
	
	@JsonProperty("status")
	private String status;
}
