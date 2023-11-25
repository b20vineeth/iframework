package com.web.framework.enricher;

import com.web.framework.exception.BussinessException;
import com.web.framework.vo.AbstractVo;

public interface IEnricher<T extends AbstractVo> {
	public void execute(T featureVo) throws BussinessException;

}
