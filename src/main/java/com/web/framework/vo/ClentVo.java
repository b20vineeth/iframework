package com.web.framework.vo;

public class ClentVo extends AbstractVo{
	
 
	private String url;
	
	private AbstractVo data;
	
	private String outputFormat;
	
	private String module;
	
	
	
	

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getOutputFormat() {
		return outputFormat;
	}

	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public AbstractVo getData() {
		return data;
	}

	public void setData(AbstractVo data) {
		this.data = data;
	}

	 
	
	
}
