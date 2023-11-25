package com.web.framework.vo;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseVo  {
	
	AbstractVo data;
	List<? extends AbstractVo> datas;
	Page page;
	
	Map<String, String> mapResponse;
	
}
