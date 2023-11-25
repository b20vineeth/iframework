package com.web.framework.invoker;

import com.web.framework.exception.BussinessException;
import com.web.framework.vo.AbstractVo;

public interface IInvoker<T extends AbstractVo> {
	public void execute(T featureVo) throws BussinessException;

}
