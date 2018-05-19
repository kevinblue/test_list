package com.tenwa.kernal.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

  /**
 *@author liuhongguang
 *@createDate 2010-3-25 下午05:23:51 
 **/
public class MD5Util
{
		public static String getMD5EncodedPassword(String str) {
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			return md5.encodePassword(str, null);
		}
		public static String getMD5EncodedPasswordWithSalt(String str,Object salt) {
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			return md5.encodePassword(str, salt);
		}
		public static void main(String []args) throws Exception{
			System.out.println(MD5Util.getMD5EncodedPasswordWithSalt("111111", "1"));
		}
}
