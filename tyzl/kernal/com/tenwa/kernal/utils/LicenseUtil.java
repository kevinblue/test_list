
package com.tenwa.kernal.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.multipart.MultipartFile;

public class LicenseUtil {
    private static  byte[] privateKeyBytes = null;
    private static  byte[] encodedAuthorizeInfoBytes = null;
	public static String updateUploadLicenseInfo(MultipartFile privateKeyMultipartFile,MultipartFile authorizeInfoMultipartFile,Map<String, String> modelData,JdbcTemplate jdbcTemplate) throws Exception{
//		if((null != privateKeyMultipartFile) && (null != authorizeInfoMultipartFile))
//		{
//			final byte[] privateKeyBytes    = privateKeyMultipartFile.getBytes();
//			final byte[] authorizeInfoBytes = authorizeInfoMultipartFile.getBytes();
//			final String prepareSql = "update T_LICENSE set PRIVATE_KEY_ = ? , GRANT_INFO_ = ?  ";
//			jdbcTemplate.execute(new PreparedStatementCreator(){
//				@Override
//				public PreparedStatement createPreparedStatement(Connection conn)
//						throws SQLException {
//					PreparedStatement ps = conn.prepareStatement(prepareSql);
//					return ps;
//				}
//			}, new  PreparedStatementCallback<Integer>(){
//				@Override
//				public Integer doInPreparedStatement(PreparedStatement ps)
//						throws SQLException, DataAccessException {
//					ByteArrayInputStream privateKeyInputStream = null;
//					ByteArrayInputStream authorizeInfoInputStream = null;
//					try {
//						privateKeyInputStream    = new ByteArrayInputStream(privateKeyBytes);
//						authorizeInfoInputStream = new ByteArrayInputStream(authorizeInfoBytes);
//						ps.setBinaryStream(1, privateKeyInputStream, privateKeyInputStream.available());
//						ps.setBinaryStream(2, authorizeInfoInputStream, authorizeInfoInputStream.available());
//					} catch (Exception e) {
//						e.printStackTrace();
//						throw new SQLException(StringUtil.traceExceptionMessage(this, e));
//					}
//					finally{
//						if(null != privateKeyInputStream){
//							try {
//								privateKeyInputStream.close();
//							} catch (IOException e) {
//								e.printStackTrace();
//								throw new SQLException(StringUtil.traceExceptionMessage(this, e));
//							}
//						}
//						if(null != authorizeInfoInputStream){
//							try {
//								authorizeInfoInputStream.close();
//							} catch (IOException e) {
//								e.printStackTrace();
//								throw new SQLException(StringUtil.traceExceptionMessage(this, e));
//							}
//						}
//					}
//					return ps.executeUpdate();
//				}
//			});
//			LicenseUtil.privateKeyBytes               = privateKeyBytes;
//			LicenseUtil.encodedAuthorizeInfoBytes     = authorizeInfoBytes;
//		}else{
//			if((null == LicenseUtil.privateKeyBytes)||(null == LicenseUtil.encodedAuthorizeInfoBytes)){
//				final String prepareSql = "SELECT PRIVATE_KEY_,GRANT_INFO_ FROM T_LICENSE ";
//				jdbcTemplate.query(prepareSql, new ResultSetExtractor<String>(){
//					@Override
//					public String extractData(ResultSet rs)
//							throws SQLException, DataAccessException {
//						if(rs.next()){
//							LicenseUtil.privateKeyBytes = rs.getBytes(1);
//							LicenseUtil.encodedAuthorizeInfoBytes = rs.getBytes(2);
//						}
//						return null;
//					}
//				});
//			}
//		}
//		String deadLineDate = compareWithCompanyIsSuccess(LicenseUtil.privateKeyBytes,LicenseUtil.encodedAuthorizeInfoBytes);
//		if(null == deadLineDate){
//			throw new Exception("grant is not success!");
//		}
		//return deadLineDate;
		return "2020-01-21";
	}
	private static String compareWithCompanyIsSuccess( byte[] key,byte[] data) throws Exception{
		String configCompanyName = ResourceUtil.getCompanyName();
		String[] grantInfos        = new String(decryptByPrivateKey(data,key)).split(",");
		if(1 == grantInfos.length){
			return null;
		}
		if(!configCompanyName.equals(grantInfos[0])){
			return null;
		}
		String   dealineDate       = grantInfos[1];
		if(DateUtil.getSystemDate().compareTo(dealineDate)>0){
			return null;
		}
		return dealineDate;
	}
    private static byte[] decryptByPrivateKey(byte[] encodedData, byte[] key)  throws Exception {  
    	byte[] keyBytes = Base64.decodeBase64(key); 
		final String KEY_ALGORITHM = "RSA"; 
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);  
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
		cipher.init(Cipher.DECRYPT_MODE, privateKey);  
		return cipher.doFinal(encodedData);  
	} 
}
