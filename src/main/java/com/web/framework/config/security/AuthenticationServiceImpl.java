package com.web.framework.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.framework.dao.response.AuthenticationResponse;
import com.web.framework.entity.Role;
import com.web.framework.entity.User;
import com.web.framework.repository.UserRepository;
import com.web.framework.service.AuthenticationService;
import com.web.framework.service.JwtService;
import com.web.framework.util.LocalDate;
import com.web.framework.vo.UserVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	 UserRepository userRepository ;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse signup(UserVo uservo) {
    	 
    	
        var user = User.builder().firstName(uservo.getFirstName()).lastName(uservo.getLastName())
                .uname(uservo.getUname()).password(passwordEncoder.encode(uservo.getPassword()))
                .role(Role.USER).createdDate(new LocalDate().now())
                .validFrom(new LocalDate().now())
                .validTo(new LocalDate().getNormalValidity())
                .email(uservo.getEmail()).lastupdate(new LocalDate().now()).build();
        
        userRepository.save(user);
        var jwt = jwtService.generateToken(user); 
        return  populateAuthenticationResponse( user, jwt);
    }

	private AuthenticationResponse populateAuthenticationResponse(  User user, String jwt) {
		AuthenticationResponse response=  new AuthenticationResponse();
        response.setToken(jwt);
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setValidFrom(user.getValidFrom());
        response.setCreatedDate(user.getCreatedDate());
        response.setValidTo(user.getValidTo());
        response.setLastupdate(user.getLastupdate());
		return response;
	}

    @Override
    public AuthenticationResponse signin(UserVo request) {
        
        var user = userRepository.findByUname(request.getUname())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return  populateAuthenticationResponse( user, jwt);
    }
}
