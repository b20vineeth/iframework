package com.web.framework.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventVo extends AbstractVo {
	
	AbstractVo data;
	List<? extends AbstractVo> datas;
	String eventName;
	String msg;
	String msgClass;

}
