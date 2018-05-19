/**
 * 项目名称：    系统名称
 * 包名：              org.springframework.security.authentication.encoding
 * 文件名：         test.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-5-29-上午10:34:21
 * Copyright：2013XX公司-版权所有
 **/

package org.springframework.security.authentication.encoding;

import javax.servlet.http.HttpServletRequest;

public class Base64 {

	public String EncodeBase64(final String strSource) {
		if (strSource == null)
			return null;
		return new String(org.apache.commons.codec.binary.Base64.encodeBase64(strSource.getBytes()));
		// return (new sun.misc.BASE64Encoder()).encode( strSource.getBytes() );
	}

	@SuppressWarnings("restriction")
	private String DecodeBase64(final String strSource) {
		if (strSource == null)
			return null;
		// byte[] bs = (new sun.misc.BASE64Decoder()).decodeBuffer(strSource);
		byte[] bs = org.apache.commons.codec.binary.Base64.decodeBase64(strSource.getBytes());
		return new String(bs);
	}

	public String getDecodeSSOUserName(HttpServletRequest request) {
		String method = request.getParameter("method");
		if (null != method) {
			String userNameEncode = request.getParameter("userName");
			if (null != userNameEncode) {
				userNameEncode = userNameEncode.trim();
				String userName = this.DecodeBase64(userNameEncode);
				return userName;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Base64 m = new Base64();
		String strSrc = "cn=wpsadmin,ou=users,o=franshion";
		System.out.println(m.EncodeBase64(strSrc));
		System.out.println(m.DecodeBase64("Y249d2FuZ2hhbw=="));
	}
}
