package com.web.framework.validator;

import org.springframework.stereotype.Component;

import com.web.framework.vo.AbstractVo;
@Component
public abstract class Validator<T extends AbstractVo>  implements IValidator<AbstractVo> {

	 

}
