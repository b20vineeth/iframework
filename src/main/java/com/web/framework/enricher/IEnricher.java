package com.web.framework.enricher;

import com.web.framework.exception.BusinessException;
import com.web.framework.vo.AbstractVo;

public interface IEnricher<T extends AbstractVo> {
	public void execute(T featureVo) throws BusinessException;

}
