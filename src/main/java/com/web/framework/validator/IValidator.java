package com.web.framework.validator;

import com.web.framework.exception.BussinessException;
import com.web.framework.vo.AbstractVo;

public interface IValidator<T extends AbstractVo> {
	public void execute(T featureVo) throws BussinessException;

}
