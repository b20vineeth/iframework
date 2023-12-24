package com.web.framework.event;

import org.springframework.stereotype.Component;

import com.web.framework.exception.BusinessException;
import com.web.framework.vo.AbstractVo;

@Component("KafkaEvent")
public class KafkaEvent  extends Event {

	@Override
	public void execute(AbstractVo featureVo) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

}
