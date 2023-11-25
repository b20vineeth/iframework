package com.web.framework.event;

import com.web.framework.exception.BussinessException;
import com.web.framework.vo.AbstractVo;

public interface IEvent<T extends AbstractVo> {
	
	public void execute(T featureVo) throws BussinessException;

}
