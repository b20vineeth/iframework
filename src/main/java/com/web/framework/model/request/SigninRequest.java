package com.web.framework.model.request;

import com.web.framework.model.EProvider;
import com.web.framework.model.Model;
import com.web.framework.model.request.validation.EitherEmailOrUnameRequired;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EitherEmailOrUnameRequired
public class SigninRequest extends Model {
 
	 
	private String email;
 
	private String uname;

	@NotNull(message = "validation.notNull.password")
	private String password;

	@NotNull(message = "validation.notNull.provider")
	private EProvider provider;

	 

}



 
