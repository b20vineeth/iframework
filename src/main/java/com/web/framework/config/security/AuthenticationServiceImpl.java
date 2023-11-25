package com.web.framework.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.framework.dao.request.SignUpRequest;
import com.web.framework.dao.request.SigninRequest;
import com.web.framework.dao.response.JwtAuthenticationResponse;
import com.web.framework.entity.Role;
import com.web.framework.entity.User;
import com.web.framework.repository.UserRepository;
import com.web.framework.service.AuthenticationService;
import com.web.framework.service.JwtService;
import com.web.framework.vo.UserVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository = null;
    private final PasswordEncoder passwordEncoder = null;
    private final JwtService jwtService = null;
    private final AuthenticationManager authenticationManager = null;
    @Override
    public JwtAuthenticationResponse signup(UserVo uservo) {
        var user = User.builder().firstName(uservo.getFirstname()).lastName(uservo.getLastname())
                .uname(uservo.getEmail()).password(passwordEncoder.encode(uservo.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByUname(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}