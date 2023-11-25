package com.web.framework.enricher;

import org.springframework.stereotype.Component;

import com.web.framework.vo.AbstractVo;
@Component
public abstract class Enricher<T extends AbstractVo>  implements IEnricher<AbstractVo> {

	 

}
