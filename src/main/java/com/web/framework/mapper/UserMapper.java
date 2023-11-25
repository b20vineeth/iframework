package com.web.framework.mapper;

import org.mapstruct.Mapper;

import com.web.framework.dao.request.SignUpRequest;
import com.web.framework.vo.UserVo;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

	 public abstract UserVo convertSignUpModelToVo(SignUpRequest signUpRequest);

}
