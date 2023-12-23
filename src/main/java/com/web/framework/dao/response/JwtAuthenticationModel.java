package com.web.framework.dao.response;

import com.web.framework.model.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationModel extends Model {
    private String token;
}
