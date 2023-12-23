package com.web.framework.dao.request;

import com.web.framework.model.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SigninRequest extends Model {
    private String email;
    private String password;
    private String uname;
    private String firstName;
    private String lastName;
    
}
