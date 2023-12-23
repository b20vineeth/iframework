package com.web.framework.security;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityImpl implements Security {

	@Value("${security.tokenKey}")
	private String tokenKey;
	
	
	
	

	private static final String ALGO = "AES/CBC/PKCS5Padding";
	private byte[] keyValue;

	public void Crypto(String key) {
		keyValue = key.getBytes();
	}

	
	public String encrypt(String Data) throws Exception {
		Key key = generateKey(this.tokenKey);
		String encryptedValue = generateEncriptedValue(Data, key);
		String urlEncodeddata=URLEncoder.encode(encryptedValue,"UTF-8");
		return urlEncodeddata;
	}
	
 

	private String generateEncriptedValue(String Data, Key key) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher c = Cipher.getInstance(ALGO);
		int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
		c.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[c.getBlockSize()]));
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = Base64.getEncoder().encodeToString(encVal);
		return encryptedValue;
	}

	 
	public String decrypt(String encryptedData) throws Exception {
		Key key = generateKey(this.tokenKey);
		encryptedData=URLDecoder.decode(encryptedData, "UTF-8");
		String decryptedValue = generateDecryptedValues(encryptedData, key);
		return decryptedValue;
	}


	private String generateDecryptedValues(String encryptedData, Key key)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[c.getBlockSize()]));
		byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
		byte[] decValue = c.doFinal(decodedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	public Key generateKey() throws Exception {
		Crypto(tokenKey);
		Key key = new SecretKeySpec(keyValue, "AES");
		return key;
	}
	
	public Key generateKey(String token) throws Exception {
		Crypto(token);
		Key key = new SecretKeySpec(keyValue, "AES");
		return key;
	}


	@Override
	public Integer secureDecrpt(String data) {

		String datakey = null;
		if (Objects.nonNull(data)) {
			try {
				datakey = this.decrypt(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (Objects.nonNull(datakey)) {

				return Integer.valueOf(datakey);
			}
		}
		return null;
	}


	@Override
	public String secureEncrypt(Integer data) {
	 
		String datakey=null;
		try {
			datakey = this.encrypt(data.toString());
		} catch (Exception e) {
		 
			e.printStackTrace();
		}
		return Objects.nonNull(datakey)?datakey:null;
	}


}
