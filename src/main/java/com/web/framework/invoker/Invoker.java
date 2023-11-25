package com.web.framework.invoker;

import org.springframework.stereotype.Component;

import com.web.framework.vo.AbstractVo;
@Component
public abstract class Invoker<T extends AbstractVo>  implements IInvoker<AbstractVo> {

	 

}
