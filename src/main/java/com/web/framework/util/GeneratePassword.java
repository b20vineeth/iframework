package com.web.framework.util;

import java.util.Random;

public class GeneratePassword {

	public String salchar="";
	public GeneratePassword() {
		salchar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";

	}
	public GeneratePassword(String type) {
		if ("NUMERIC".equals(type)) { 
			salchar = "0123456789"; 
		} else if ("ALBHA".equals(type)) {
			salchar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

		} else {
			salchar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";

		}

	}

	public String generatePassword(int length) {

		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < length) {
			int index = (int) (rnd.nextFloat() * salchar.length());
			salt.append(salchar.charAt(index));
		}
		return salt.toString();
	}

}
