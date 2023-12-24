package com.web.util.bev.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.framework.exception.BusinessException;
import com.web.framework.feature.vo.FeatureVo;
import com.web.framework.vo.FeatureFilterVo;
import com.web.util.bev.service.IBevService;

@Component
public class BevComponent implements IBevComponent {

	@Autowired
	IBevService service;
	
	@Override
	public FeatureVo fetchBevRules(FeatureFilterVo featureFilterVo) throws BusinessException {
		// TODO Auto-generated method stub
		return service.fetchBevRules(featureFilterVo);
	}

}
