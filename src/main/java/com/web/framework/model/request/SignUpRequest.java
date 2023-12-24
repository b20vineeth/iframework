package com.web.framework.model.request;

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
public class SignUpRequest  extends Model{
    private String firstName;
    private String lastName;
    private String uname;
    private String password;
    private String id;
}
