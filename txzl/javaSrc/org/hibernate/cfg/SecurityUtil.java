package org.hibernate.cfg;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

  
public class SecurityUtil {  
    public static String DES = "AES"; // optional value AES/DES/DESede  
      
    public static String CIPHER_ALGORITHM = "AES"; // optional value AES/DES/DESede  
      
  
    public static Key getSecretKey(String key) throws Exception{  
    	 try {    
    		if(key==null){
    			key="";
    		} 
             KeyGenerator _generator = KeyGenerator.getInstance( "AES" );
              SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
             secureRandom.setSeed(key.getBytes());
             _generator.init(128,secureRandom);
                 return _generator.generateKey();
         }  catch (Exception e) {
              throw new RuntimeException( " 初始化密钥出现异常 " );
         }
    }  
      
    public static String encrypt(String data,String key) throws Exception {  
    	 if (key == null) {
    		    key = "";
         }
        //SecureRandom sr = new SecureRandom();  
    	SecureRandom sr =SecureRandom.getInstance("SHA1PRNG");
    	sr.setSeed(key.getBytes("UTF-8"));
        Key securekey = getSecretKey(key);  
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);  
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);  
        byte[] bt = cipher.doFinal(data.getBytes());  
        String strs = new BASE64Encoder().encode(bt);  
        return strs;  
    }  
      
      
    public static String detrypt(String message,String key) throws Exception{  
       // SecureRandom sr = new SecureRandom(); 
    	SecureRandom sr =SecureRandom.getInstance("SHA1PRNG");
    	sr.setSeed(key.getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);  
        Key securekey = getSecretKey(key);  
        cipher.init(Cipher.DECRYPT_MODE, securekey,sr);  
        byte[] res = new BASE64Decoder().decodeBuffer(message);  
        res = cipher.doFinal(res);  
        return new String(res);  
    }  
      
    public static void main(String[] args)throws Exception{  
        //String message = args[0];  
        String message = "123";  
        String key = "";  
        String entryptedMsg = encrypt(message,key);  
        System.out.println("encrypted message is below :");  
        System.out.println(entryptedMsg);  
          
        String decryptedMsg = detrypt(entryptedMsg,key);  
        System.out.println("decrypted message is below :");  
        System.out.println(decryptedMsg);  
    }  
}  