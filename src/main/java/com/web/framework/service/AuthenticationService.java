package com.web.framework.service;

import com.web.framework.dao.response.AuthenticationResponse;
import com.web.framework.vo.UserVo;

public interface AuthenticationService {
    AuthenticationResponse signup(UserVo request);

    AuthenticationResponse signin(UserVo request);
}
