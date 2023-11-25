package com.web.framework.enricher;

import org.springframework.stereotype.Component;

import com.web.framework.exception.BussinessException;
import com.web.framework.vo.AbstractVo;

@Component("TestEnricher")
public class TestEnricher extends Enricher{

	@Override
	public void execute(AbstractVo featureVo) throws BussinessException {
		// TODO Auto-generated method stub
		
	}

}
