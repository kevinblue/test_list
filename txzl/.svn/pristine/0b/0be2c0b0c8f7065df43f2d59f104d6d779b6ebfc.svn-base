
 /**
 * 项目名称：    系统名称
 * 包名：              com.jbpm.utils
 * 文件名：         DaftUUIDUtil.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-10-25-上午10:57:42
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.jbpm.utils;


 /**
 * 类名称：     DaftUUIDUtil
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-10-25 上午10:57:42
 * 修改备注：
 * @version 1.0.0
 **/

public class DraftUUIDUtil 
{
	 /**
     * @param myinfo
     * 为字符串加密
     * @return
     */ 
    public static String passwordDigest(String myinfo) { 
        String newPwd = ""; 
        try { 
            java.security.MessageDigest alga=java.security.MessageDigest.getInstance("MD5"); 
//          java.security.MessageDigest alga = java.security.MessageDigest.getInstance("SHA-1"); 
            alga.update(myinfo.getBytes()); 
            byte[] digesta = alga.digest(); 
            newPwd = byte2hex(digesta); 
        } catch (java.security.NoSuchAlgorithmException e) { 
            System.out.println("密码加密异常：非法摘要算法"+e); 
        } 
        return newPwd; 
 
    } 
    public static String byte2hex(byte[] b) // 二行制转字符串 
    { 
        String hs = ""; 
        String stmp = ""; 
        for (int n = 0; n < b.length; n++) { 
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF)); 
            if (stmp.length() == 1){ 
                hs = hs + "0" + stmp; 
            }else{ 
                hs = hs + stmp; 
            } 
        } 
        return hs.toUpperCase(); 
    } 
     public static String getDraftUUID(String uri,String queryString)
     {
    	 return passwordDigest(uri+queryString);
     }
}
