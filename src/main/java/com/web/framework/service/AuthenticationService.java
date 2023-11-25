package com.web.framework.service;

import com.web.framework.dao.request.SigninRequest;
import com.web.framework.dao.response.JwtAuthenticationResponse;
import com.web.framework.vo.UserVo;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(UserVo request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
