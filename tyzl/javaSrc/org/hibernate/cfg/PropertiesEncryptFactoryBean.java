package org.hibernate.cfg;
import java.util.Properties;  
  
import org.springframework.beans.factory.FactoryBean;  
  
public class PropertiesEncryptFactoryBean implements FactoryBean {  
  
    private Properties properties;  
      
    public Object getObject() throws Exception {  
        return getProperties();  
    }  
  
    public Class getObjectType() {  
        return java.util.Properties.class;  
    }  
  
    public boolean isSingleton() {  
        return true;  
    }  
  
    public Properties getProperties() {  
        return properties;  
    }  
  
    public void setProperties(Properties inProperties) {  
        this.properties = inProperties;  
        String originalUsername = properties.getProperty("user");  
        String originalPassword = properties.getProperty("key"); 
        try{
        	if (originalUsername != null){  
        		String newUsername = SecurityUtil.detrypt(originalUsername, "");   
        		properties.put("user", newUsername);  
        	}  
        	if (originalPassword != null){  
        		String newPassword = SecurityUtil.detrypt(originalPassword, "");   
        		properties.put("password", newPassword);  
        	}  
        }catch(Exception e){
        	e.printStackTrace();
        }
    }  
      
  
}  