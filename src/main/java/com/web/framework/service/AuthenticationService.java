package com.web.framework.service;

import com.web.framework.dao.request.SignUpRequest;
import com.web.framework.dao.request.SigninRequest;
import com.web.framework.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
