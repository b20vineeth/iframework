package com.web.util.bev.component;

import com.web.framework.exception.BusinessException;
import com.web.framework.feature.vo.FeatureVo;
import com.web.framework.vo.FeatureFilterVo;

public interface IBevComponent {

	FeatureVo fetchBevRules(FeatureFilterVo featureFilterVo) throws BusinessException ;

}
