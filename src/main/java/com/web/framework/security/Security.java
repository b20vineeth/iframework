package com.web.framework.security;

public interface Security {

	public String decrypt(String encryptedData) throws Exception;
	public String encrypt(String Data) throws Exception;
	
	public Integer secureDecrpt(String data);
	public String secureEncrypt(Integer integer);
	
}
