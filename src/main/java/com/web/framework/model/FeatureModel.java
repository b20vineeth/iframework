package com.web.framework.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeatureModel  extends Model{
	String featureName;
	
	private List<String> validatorsIds;
	private List<String> enrichers;
	private List<String> preinvoker;
	private List<String> postinvoker;
	
	private List<String> enricher;
}
