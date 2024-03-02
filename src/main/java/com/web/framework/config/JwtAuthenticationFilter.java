package com.web.framework.config;

import java.io.IOException;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.JsonObject;
import com.web.framework.httpcontroller.HttpActionMethod;
import com.web.framework.httpcontroller.IHttpPost;
import com.web.framework.mapper.ComponentMapper;
import com.web.framework.model.HttpRequestModel;
import com.web.framework.model.UserModel;
import com.web.framework.model.request.AuthRequest;
import com.web.framework.security.Security;
import com.web.framework.service.JwtService;
import com.web.framework.vo.UserVo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final JwtService jwtService;

	// private final UserService userService;

	@Autowired
	IHttpPost httpPost;
	@Autowired
	Security security;
	@Autowired
	ComponentMapper mapper;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final Integer userid;
		String isAuth = request.getHeader("isAuthorized");
		if (Objects.isNull(isAuth)) {
			isAuth = "F";
		}
		if ("T".equals(isAuth) || StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.substring(7);
		try {
			userid = jwtService.extractUserId(jwt);
			if (Objects.nonNull(userid) && SecurityContextHolder.getContext().getAuthentication() == null) {

				AuthRequest authrequest = new AuthRequest();
				authrequest.setId(userid);
				authrequest.setToken(jwtService.extractKey(jwt));
				HttpRequestModel httpmodel = new HttpRequestModel();
				httpmodel.setData(authrequest);
				httpmodel.setRequestType(HttpMethod.POST);
				httpmodel.setIsAuthorized("T");
				httpmodel.setService(HttpActionMethod.AUTH_LOAD_USERNAME);
				JsonObject details = httpPost.getDetails(httpmodel);
				UserModel usermodel = mapper.toUserModel(details.getAsJsonObject("data"));
				String ids;

				ids = security.decrypt(usermodel.getId());
				UserVo uservo = new UserVo();
				uservo.setUname(usermodel.getUsername());
				uservo.setId(Integer.parseInt(ids));
				if (jwtService.isTokenValid(jwt, uservo)) {
					SecurityContext context = SecurityContextHolder.createEmptyContext();
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(uservo,
							null, usermodel.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					context.setAuthentication(authToken);
					SecurityContextHolder.setContext(context);
				}

			}
		} catch (Exception e) {

			 
		}
		filterChain.doFilter(request, response);
	}
}
