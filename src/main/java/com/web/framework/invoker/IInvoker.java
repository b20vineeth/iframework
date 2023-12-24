package com.web.framework.invoker;

import com.web.framework.exception.BusinessException;
import com.web.framework.vo.AbstractVo;

public interface IInvoker<T extends AbstractVo> {
	public void execute(T featureVo) throws BusinessException;

}
