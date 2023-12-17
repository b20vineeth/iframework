package com.web.util.bev.feature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.framework.exception.BussinessException;
import com.web.framework.feature.Feature;
import com.web.framework.feature.vo.FeatureVo;
import com.web.framework.vo.AbstractVo;
import com.web.util.bev.feature.persistor.BevEvaluatorPersistor;

@Component("bevfeature")
public class BevFeature extends Feature {

	@Autowired
	BevEvaluatorPersistor persistor;
	
	@Override
	protected FeatureVo perform(AbstractVo featureVo) throws BussinessException {
		  
	 
		return persistor.perform(featureVo);
	}

	@Override
	protected FeatureVo getBussinessConfiguration() throws BussinessException {
		return new FeatureVo();
	}

}
