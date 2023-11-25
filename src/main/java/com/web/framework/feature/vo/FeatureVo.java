package com.web.framework.feature.vo;

import java.util.List;

import com.web.framework.vo.AbstractVo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeatureVo  extends AbstractVo{

	private String featureName;

	private List<String> validatorsIds;
	private List<String> enrichers;
	private List<String> preinvoker;
	private List<String> postinvoker;
	
	private List<String> enricher;
	
	
	
	
}
