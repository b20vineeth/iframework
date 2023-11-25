package com.web.framework.util;

public interface ICommonUtl {

	public String getStatus(String string);
	
	public String encrypt(String string,String key);
	public String decrypt(String encryptedData, String tokenKey);
	public  boolean isAlphaNumeric(String s);
	public boolean isValidName(String s);
	public boolean isEmailIdValid(String s);
}
