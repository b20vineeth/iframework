package com.web.framework.util;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.web.framework.vo.EventVo;

@Component
public class Publisher {

	 private final ApplicationEventPublisher eventPublisher;

	    Publisher(ApplicationEventPublisher publisher) {
	        this.eventPublisher = publisher;
	    }

	    public void publish (EventVo eventVo) { 
	    	eventPublisher.publishEvent(eventVo);
	    }
 
}
