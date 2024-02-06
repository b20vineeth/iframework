package com.web.framework.httpcontroller;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
import com.web.framework.config.ServerConfiguration;
import com.web.framework.model.HttpRequestModel;
import com.web.framework.vo.Server;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class HttpPost implements IHttpPost {

	@Autowired
    RestTemplate restTemplate;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	ServerConfiguration serverconfig;
	 
	@Override
	public JsonObject getDetails(HttpRequestModel model) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", request.getHeader("Authorization")); // Add Authorization token
		headers.set("isAuthorized", model.getIsAuthorized()); // Add Authorization token
		RequestEntity<?> requestEntity = new RequestEntity<>(model.getData(), 
				 headers, model.getRequestType(), 
				 getUrl(model.getService().toLowerCase()));
		  ResponseEntity<?> responseEntity = restTemplate.exchange(requestEntity, String.class);
		 
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			JsonElement element = JsonParser.parseString((String) responseEntity.getBody());
	        JsonObject jsonObject = element.getAsJsonObject();
	        return jsonObject;
		 } else {
			return null;
		}
	}

	private URI getUrl(String url) {
		
		StringBuilder builder=new StringBuilder();
		Optional<Server> server = findServerByName(serverconfig.getServer(),url);
		Optional<Server> urls = findUrlByName(serverconfig.getUrl(),url);
		if(!server.isEmpty()) {
			builder.append(server.get().getUrl());
		}
		if(!urls.isEmpty()) {
			builder.append(urls.get().getUrl());
		}
		
		return URI.create(builder.toString());
	}

	 
	private Optional<Server> findUrlByName(List<Server> server, String url) {
		 String[] parts = url.split("_");
	        if (parts.length > 0) {
	            String searchName = parts[1];
	            return findDetails(server, searchName);
	        }
	        return Optional.empty();
	}

	public Optional<Server> findServerByName(List<Server> servers, String searchString) {
        String[] parts = searchString.split("_");
        if (parts.length > 0) {
            String searchName = parts[0];
            return findDetails(servers, searchName);
        }
        return Optional.empty();
    }

	private Optional<Server> findDetails(List<Server> servers, String searchName) {
		return servers.stream()
		              .filter(server -> server.getName().equals(searchName))
		              .findFirst();
	}
	 
	

}
