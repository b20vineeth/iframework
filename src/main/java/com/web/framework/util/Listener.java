package com.web.framework.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import com.google.gson.Gson;
import com.web.framework.event.IEvent;
import com.web.framework.exception.BusinessException;
import com.web.framework.vo.AbstractVo;
import com.web.framework.vo.EventVo;

@EnableAsync
@Configuration
public class Listener {
	
	@Autowired
	Map<String,IEvent> ievent;
 
	@Async 
    @EventListener
	void sendMsgEvent(EventVo eventVo) throws BusinessException {
		String[] events = eventVo.getEventName().split(",");
		try {
			Class classname = Class.forName(eventVo.getMsgClass());
			AbstractVo abstractVo=	(AbstractVo) new Gson().fromJson(eventVo.getMsg(), classname);
			for (String event : events) {

				ievent.get(event + "-EVENT").execute(abstractVo);

			}
		} catch (Exception e) {
		}
	}

}
