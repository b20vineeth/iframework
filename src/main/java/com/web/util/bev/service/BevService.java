package com.web.util.bev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.web.framework.exception.BusinessException;
import com.web.framework.feature.vo.FeatureVo;
import com.web.framework.vo.FeatureFilterVo;
import com.web.util.bev.feature.BevFeature;

@Service
@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
public class BevService implements IBevService{
	
	@Autowired
	BevFeature feature;

	@Override
	public FeatureVo fetchBevRules(FeatureFilterVo featureFilterVo) throws BusinessException {
		 
		return (FeatureVo) feature.execute(featureFilterVo);
	}

}
