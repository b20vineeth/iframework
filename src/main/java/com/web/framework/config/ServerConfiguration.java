package com.web.framework.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.web.framework.vo.Server;

@Component
@ConfigurationProperties(prefix = "modules")
public class ServerConfiguration {
	
	private List<Server> server;
	
	private List<Server> url;

    public List<Server> getServer() {
        return server;
    }

    public void setServer(List<Server> server) {
        this.server = server;
    }

	public List<Server> getUrl() {
		return url;
	}

	public void setUrl(List<Server> url) {
		this.url = url;
	}
    

   
}
