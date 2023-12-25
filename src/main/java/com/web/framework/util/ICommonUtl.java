package com.web.framework.util;

import org.springframework.http.ResponseEntity;

import com.web.framework.exception.BusinessException;
import com.web.framework.model.EErrorType;
import com.web.framework.vo.ErrorVo;

public interface ICommonUtl {

	public String getStatus(String string);
	
	public String encrypt(String string,String key);
	public String decrypt(String encryptedData, String tokenKey);
	public  boolean isAlphaNumeric(String s);
	public boolean isValidName(String s);
	public boolean isEmailIdValid(String s);
	public boolean containsWord(String sentence, String wordToCheck);
	public String getMessage(String msg);

	public ErrorVo generateErrorVo(String string, EErrorType e);

	public ResponseEntity<?> handleErrorMessage(BusinessException e);
}
