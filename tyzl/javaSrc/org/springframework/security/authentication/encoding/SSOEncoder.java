
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
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
   
public class SSOEncoder {   
       
    private static final SSOEncoder instance = new SSOEncoder();   
   
    private SSOEncoder() {}   
   
    public static SSOEncoder getInstance() {   
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
    private String encryptAES(String content, String key){   
        try{   
            SecretKeySpec secretKey = (SecretKeySpec) initKeyForAES(key);   
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
            byte[] byteContent = content.getBytes("utf-8");   
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);// 初始化   
            byte[] result = cipher.doFinal(byteContent);   
            return asHex(result); // 加密   
        }   
        catch (Exception e){   
            e.printStackTrace();   
        }   
        return null;   
    }   
    private String asHex(byte buf[]){   
        StringBuffer strbuf = new StringBuffer(buf.length * 2);   
        int i;   
        for (i = 0; i < buf.length; i++){   
            if (((int) buf[i] & 0xff) < 0x10)   
                strbuf.append("0");   
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));   
        }   
        return strbuf.toString();   
    }   
    public String getEncodeUserNameParamEncode(){
	    SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	 	String currentDateStr = sdf.format(new java.util.Date());
        return encryptAES("userName",currentDateStr);
    }
    public Map<String,String> setEncodeSSOUserName(String userNameRealValue){
    	Map<String,String> encodeMap = new HashMap<String,String>();
        SSOEncoder encoder = SSOEncoder.getInstance();
	    SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	 	String currentDateStr = sdf.format(new java.util.Date());
	    String encodeKey = userNameRealValue+";"+currentDateStr; 
        encodeMap.put("userNameParamNameEncode", getEncodeUserNameParamEncode());
        String userNameEncode          = encoder.encryptAES(userNameRealValue,currentDateStr);
        encodeMap.put("userNameEncode", userNameEncode);
        String isSSOParamNameEncode   =  encoder.encryptAES("isSSO",encodeKey);
        encodeMap.put("isSSOParamNameEncode", isSSOParamNameEncode);
        String isSSOEncode             =  encoder.encryptAES("true",encodeKey);
        encodeMap.put("isSSOEncode", isSSOEncode);
		return encodeMap;
    }
} 
