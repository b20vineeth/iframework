package com.web.util.bev.service;

import com.web.framework.exception.BussinessException;
import com.web.framework.feature.vo.FeatureVo;
import com.web.framework.vo.FeatureFilterVo;

public interface IBevService {

	FeatureVo fetchBevRules(FeatureFilterVo featureFilterVo) throws BussinessException;

}
