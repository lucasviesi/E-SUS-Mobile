package com.focus.funcoes;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {

	public static String MD5(String dado) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(dado.getBytes(), 0, dado.length());
			// System.out.println("MD5: " + new BigInteger(1, m.digest()).toString(16));
			return new BigInteger(1, m.digest()).toString(16);

		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			return ex.getMessage();
		}
	}
}
