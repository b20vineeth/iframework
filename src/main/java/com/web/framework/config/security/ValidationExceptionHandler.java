package com.web.framework.config.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.web.framework.util.ICommonUtl;
import com.web.framework.vo.ErrorVo;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ValidationExceptionHandler {
	@Autowired
	ICommonUtl utl;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
		List<ErrorVo> errors = new ArrayList<>();

		ex.getAllErrors().forEach(err -> {

			errors.add(utl.generateErrorVo(err.getDefaultMessage()));
		});

		Map<String, List<ErrorVo>> result = new HashMap<>();
		result.put("errors", errors);

		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
}
