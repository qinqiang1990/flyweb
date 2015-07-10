package com.qq.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class MD5Util {
	public static String encodePassword(String password, String salt) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		return md5.encodePassword(password, salt);
	}
	 
	// SHA MD5
	public static String encodePassword(String password) {

		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageDigest.update(password.getBytes());
		return DigestUtils.md5Hex( messageDigest.digest());
		/*StringBuilder sb = new StringBuilder();
		for (byte b : messageDigest.digest()) {
			// 使用两位表示，不足补零
			sb.append(String.format("%02X", b));
		}
		return sb.toString();*/

	}
}
