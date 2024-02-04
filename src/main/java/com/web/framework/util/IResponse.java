package com.web.framework.util;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.web.framework.exception.BusinessException;
import com.web.framework.model.ResponseModel;

public interface IResponse {

	ResponseEntity<?> ok(String json, MediaType applicationJson);

	ResponseEntity<?> ok(ResponseModel reponse, MediaType applicationJson);

	ResponseEntity<?> fail(BusinessException e);
}
