package com.web.framework.httpcontroller;

import java.net.URI;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.web.framework.model.HttpRequestModel;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class HttpPost implements IHttpPost {

	@Autowired
    RestTemplate restTemplate;
	
	@Autowired
	 HttpServletRequest request;
	 
	@Override
	public JsonObject getDetails(HttpRequestModel model) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", request.getHeader("Authorization")); // Add Authorization token
		headers.set("isAuthorized", "T"); // Add Authorization token
		RequestEntity<?> requestEntity = new RequestEntity<>(model.getData(), 
				 headers, model.getRequestType(), 
				 URI.create("http://localhost:1003/0auth2/api/v1/auth/loadusername"));
		  ResponseEntity<?> responseEntity = restTemplate.exchange(requestEntity, String.class);
		 
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			JsonElement element = JsonParser.parseString((String) responseEntity.getBody());
	        JsonObject jsonObject = element.getAsJsonObject();
	        return jsonObject;
		 } else {
			return null;
		}
	}

	 

	 
	

}
