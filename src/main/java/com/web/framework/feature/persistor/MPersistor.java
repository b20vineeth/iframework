package com.web.framework.feature.persistor;

import java.util.List;

import org.springframework.stereotype.Component;

import com.web.framework.exception.BusinessException;
import com.web.framework.vo.AbstractVo;
@Component
public abstract class MPersistor<T extends AbstractVo> implements IMPersistor{

	 public abstract <R extends Object> R perform(T featureVo) throws BusinessException;
	 public abstract List<T> getList(T featureVo) throws BusinessException;	
}
