
 /**
 * 项目名称：    系统名称
 * 包名：              org.springframework.security.authentication.encoding
 * 文件名：         tttt.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-5-28-下午05:27:22
 * Copyright：2013XX公司-版权所有
 **/

package org.springframework.security.authentication.encoding;


 /**
 * 类名称：     CryptUtil
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-5-28 下午05:27:22
 * 修改备注：
 * @version 1.0.0
 **/
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
   
public class SSODecoder {   
       
    private static final SSODecoder instance = new SSODecoder();   
   
    private SSODecoder() {}   
   
    public static SSODecoder getInstance() {   
        return instance;   
    }   
   
    private Key initKeyForAES(String key) throws NoSuchAlgorithmException {   
        if (null == key || key.length() == 0) {   
            throw new NullPointerException("key not is null");   
        }   
        SecretKeySpec key2 = null;   
        try {   
            KeyGenerator kgen = KeyGenerator.getInstance("AES");   
            kgen.init(128, new SecureRandom(key.getBytes()));   
            SecretKey secretKey = kgen.generateKey();   
            byte[] enCodeFormat = secretKey.getEncoded();   
            key2 = new SecretKeySpec(enCodeFormat, "AES");   
        } catch (NoSuchAlgorithmException ex) {   
            throw new NoSuchAlgorithmException();   
        }   
        return key2;   
   
    }   
       
    private String decryptAES(String content, String key){   
        try{   
            SecretKeySpec secretKey = (SecretKeySpec) initKeyForAES(key);   
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
            cipher.init(Cipher.DECRYPT_MODE, secretKey);// 初始化   
            byte[] result = cipher.doFinal(asBytes(content));   
            return new String(result); // 加密   
        }   
        catch (Exception e){   
            e.printStackTrace();   
        }   
        return null;   
    }   
       
    private byte[] asBytes(String hexStr) {   
        if (hexStr.length() < 1)   
            return null;   
        byte[] result = new byte[hexStr.length() / 2];   
        for (int i = 0; i < hexStr.length() / 2; i++){   
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);   
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),16);   
            result[i] = (byte) (high * 16 + low);   
        }   
        return result;   
    }   
    public String getDecodeSSOUserName(HttpServletRequest request) {
    	SSOEncoder ssoEncoder = SSOEncoder.getInstance();
        String userNameParamNameEncode = ssoEncoder.getEncodeUserNameParamEncode();
        String userNameEncode    = request.getParameter(userNameParamNameEncode);
        if(null != userNameEncode){
        	userNameEncode = userNameEncode.trim();
    	    SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    	 	String currentDateStr = sdf.format(new java.util.Date());
        	String userNameRealValue = decryptAES(userNameEncode, currentDateStr);
        	String encodeKey = userNameRealValue+";"+currentDateStr; 
        	
        	Map<String,String> encodeInfo = ssoEncoder.setEncodeSSOUserName(userNameRealValue);
            String isSSOParamNameEncode   = encodeInfo.get("isSSOParamNameEncode");
            String isSSOEncode            = request.getParameter(isSSOParamNameEncode);
            if(null != isSSOEncode){
            	isSSOEncode = isSSOEncode.trim();
            	String isSSO = decryptAES(isSSOEncode, encodeKey);
            	if("true".equalsIgnoreCase(isSSO)){
            		return userNameRealValue;
            	}
            }
        }
        return null;
    }
} 
