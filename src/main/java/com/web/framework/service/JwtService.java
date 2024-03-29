package com.web.framework.service;

import com.web.framework.vo.UserVo;

public interface JwtService {
    String extractUserName(String token);
    Integer extractUserId(String token);
    String generateToken(UserVo userDetails);
    public String extractKey(String token);

    boolean isTokenValid(String token, UserVo userDetails);
}
