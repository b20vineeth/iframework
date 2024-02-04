package com.web.framework.util;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.web.framework.exception.BusinessException;
import com.web.framework.model.ResponseModel;

@Component
public class ResponseImpl implements IResponse{

	@Override
	public ResponseEntity<?> ok(String response, MediaType applicationJson) {
		 
		return ResponseEntity.ok()
                .contentType(applicationJson) // Set content type to JSON
                .body(response);
	}

	@Override
	public ResponseEntity<?> ok(ResponseModel reponse, MediaType applicationJson) {
		
				return ResponseEntity.ok()
		                .contentType(applicationJson) // Set content type to JSON
		                .body(new Gson().toJson(reponse));
	}
	public ResponseEntity<?> fail(BusinessException e) {
		
		 String errorResponse = new Gson().toJson(e.getErrors());
	        return ResponseEntity.badRequest()
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(errorResponse);
	 }
}
 