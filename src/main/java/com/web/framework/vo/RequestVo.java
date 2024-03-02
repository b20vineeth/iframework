package com.web.framework.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RequestVo extends AbstractVo{

	List<? extends AbstractVo> datas;
}
