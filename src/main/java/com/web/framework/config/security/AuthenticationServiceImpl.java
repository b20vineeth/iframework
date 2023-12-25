package com.web.framework.config.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.web.framework.entity.Role;
import com.web.framework.entity.User;
import com.web.framework.exception.BusinessException;
import com.web.framework.model.EErrorType;
import com.web.framework.model.response.AuthenticationResponse;
import com.web.framework.repository.UserRepository;
import com.web.framework.service.AuthenticationService;
import com.web.framework.service.JwtService;
import com.web.framework.util.ICommonUtl;
import com.web.framework.util.LocalDate;
import com.web.framework.vo.ErrorVo;
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
    @Autowired
    ICommonUtl utl;
    @Override
    public AuthenticationResponse signup(UserVo uservo) {
    	 
    	
        var user = User.builder().firstName(uservo.getFirstName()).lastName(uservo.getLastName())
                .uname(uservo.getUname()).password(passwordEncoder.encode(uservo.getPassword()))
                .role(Role.USER).createdDate(new LocalDate().now())
                .validFrom(new LocalDate().now())
                .validTo(new LocalDate().getNormalValidity())
                .email(uservo.getEmail()).lastupdate(new LocalDate().now()).build();
        
        userRepository.save(user);
        uservo.setId(user.getId());
        var jwt = jwtService.generateToken(uservo); 
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
	public AuthenticationResponse signin(UserVo request) throws BusinessException {

		User user = null;
		try {
			Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(Objects.nonNull(request.getUname())?request.getUname():request.getEmail(), request.getPassword()));
			user = (User) authenticate.getPrincipal();
		} catch (Exception e) {
		}

		if (Objects.isNull(user)) {
			List<ErrorVo> errors = new ArrayList<>();
			errors.add(utl.generateErrorVo("signin.validation.invalidUsernameOrPassword", EErrorType.E));
			throw new BusinessException(errors);
		} else {
			request.setFirstName(user.getFirstName());
			request.setLastName(user.getLastName());
			request.setId(user.getId());
			request.setUname(user.getUname());
			request.setEmail(user.getEmail());
		}

		var jwt = jwtService.generateToken(request);
		return populateAuthenticationResponse(user, jwt);

	}
}
