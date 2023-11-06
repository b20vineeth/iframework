package com.web.framework.dao.request;

import com.web.framework.model.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest extends Model {
    private String email;
    private String password;
}
