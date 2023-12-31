package com.web.framework.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.web.framework.exception.BusinessException;
import com.web.framework.model.EErrorType;
import com.web.framework.vo.ErrorVo;

@Component
public class CommonUtl implements ICommonUtl {

	@Value("${security.tokenKey}")
	private String tokenKey;

	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

	private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

	private static final String ALGO = "AES/CBC/PKCS5Padding";
	private byte[] keyValue;

	
	@Autowired
    private MessageSource messageSource;
	
	public String getMessage(String msg) {
		 return messageSource.getMessage(msg, null, LocaleContextHolder.getLocale());
	}
	
	public boolean isValidName(String s) {
		return s.matches("^[a-zA-Z0-9\\s]+$");
	}

	public boolean isAlphaNumeric(String s) {
		return s != null && s.matches("^[a-zA-Z0-9.]*$");
	}

	@Override
	public String getStatus(String string) {
		if (Objects.nonNull(string)) {
			return string;
		} else {
			return "Y";
		}
	}

	public void Crypto(String key) {
		keyValue = key.getBytes();
	}

	public String encrypt(String Data, String tokenKey) {
		Key key;
		try {
			key = generateKey(tokenKey);
			String encryptedValue = generateEncriptedValue(Data, key);
			String urlEncodeddata = URLEncoder.encode(encryptedValue, "UTF-8");
			return urlEncodeddata;
		} catch (Exception e) {

			return null;
		}

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

	public String decrypt(String encryptedData, String tokenKey) {
		try {

			Key key = generateKey(tokenKey);
			encryptedData = URLDecoder.decode(encryptedData, "UTF-8");
			String decryptedValue = generateDecryptedValues(encryptedData, key);
			return decryptedValue;
		} catch (Exception e) {
			return null;
		}
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

	public boolean isEmailIdValid(String email) {
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public boolean containsWord(String sentence, String wordToCheck) {
		// Check if the sentence contains the word (case-sensitive)
		return sentence.contains(wordToCheck);
	}

	@Override
	public ErrorVo generateErrorVo(String code, EErrorType type) {
		ErrorVo error = new ErrorVo();
		error.setCode(code);
		error.setType(type);
		try {
			error.setMessage(messageSource.getMessage(code, null, LocaleContextHolder.getLocale()));
		} catch (Exception e) {
		}
		return error;
	}

	@Override
	public ResponseEntity<?> handleErrorMessage(BusinessException e) {
		 
			Map<String, List<ErrorVo>> result = new HashMap<>();
			result.put("errors", e.getErrors());
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		 
	}
	public String getRandomtoken() throws Exception {
		 
        SecretKey secretKey = generateSecretKey();

        // Get the current time as a string
        String currentTime = Long.toString(System.currentTimeMillis());

        // Generate a random number between 11111 and 99999
        int randomNumber = generateRandomNumber(11111, 99999);

        // Create a string combining current time and random number
        String combinedString = currentTime + randomNumber;

        // Encrypt the combined string
       return encrypt(combinedString, secretKey);
       
		
	}
	private static SecretKey generateSecretKey() throws Exception {
        // Use AES algorithm
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

        // Generate a 128-bit key
        keyGenerator.init(128);

        // Generate the secret key
        return keyGenerator.generateKey();
    }

	public  int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    private static String encrypt(String originalString, SecretKey secretKey) throws Exception {
        // Get a cipher instance
        Cipher cipher = Cipher.getInstance("AES");

        // Initialize the cipher for encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt the string
        byte[] encryptedBytes = cipher.doFinal(originalString.getBytes());

        // Convert the encrypted bytes to Base64 for easy representation
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
	 
}
