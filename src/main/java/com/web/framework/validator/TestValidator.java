package com.web.framework.validator;

import org.springframework.stereotype.Component;

import com.web.framework.exception.BusinessException;
import com.web.framework.vo.AbstractVo;

@Component("TestValidator")
public class TestValidator extends Validator    {

	@Override
	public void execute(AbstractVo featureVo) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

}
