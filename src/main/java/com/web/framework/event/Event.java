package com.web.framework.event;

import org.springframework.stereotype.Component;

import com.web.framework.vo.AbstractVo;

@Component
public abstract class Event<T extends AbstractVo> implements IEvent {

	 
}
