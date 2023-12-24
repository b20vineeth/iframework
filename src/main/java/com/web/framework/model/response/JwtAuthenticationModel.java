package com.web.framework.model.response;

import com.web.framework.model.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationModel extends Model {
    private String token;
}
