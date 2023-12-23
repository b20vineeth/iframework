package com.web.framework.dao.response;

import com.web.framework.vo.AbstractVo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthenticationResponse extends AbstractVo {
	
    private String token;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    
    
    
    
}
