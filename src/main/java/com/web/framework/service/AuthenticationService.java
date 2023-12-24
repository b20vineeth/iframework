package com.web.framework.service;

import com.web.framework.exception.BusinessException;
import com.web.framework.model.response.AuthenticationResponse;
import com.web.framework.vo.UserVo;

public interface AuthenticationService {
    AuthenticationResponse signup(UserVo request);

    AuthenticationResponse signin(UserVo request) throws BusinessException ;
}
