package com.example.service_hi.user.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

/**
 * MD5 Util，密码加密
 * @author: Apathy
 * @date: 2019年10月29日
 */
public class MD5Util {
//	private static final String SALT = "&%5123***&&%%$$#@";
	
	public static String getMD5(String str,String salt) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		String base = str + "/" + salt;
		return DigestUtils.md5DigestAsHex(base.getBytes());
	}
}
