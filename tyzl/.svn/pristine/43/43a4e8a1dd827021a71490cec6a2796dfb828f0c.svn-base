
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.kernal.utils
 * 文件名：         EhcacheUtil.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-8-31-下午05:57:32
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.kernal.utils;


import java.io.Serializable;  
  
import org.apache.commons.logging.Log;  
import org.apache.commons.logging.LogFactory;  
  
import net.sf.ehcache.Cache;  
import net.sf.ehcache.CacheException;  
import net.sf.ehcache.CacheManager;  
import net.sf.ehcache.Element;  
  
/** 
 * 缓存管理器 
 * @author tracywindy 
 */  
public class EhcacheUtil {  
      
    private final static Log log = LogFactory.getLog(EhcacheUtil.class);  
    public  final static String PermissionCachedName        = "permissionCaches";
    
    public static CacheManager manager;  
      
    static{  
        try {  
            manager = CacheManager.getInstance();  
            if(manager==null)  
                manager = CacheManager.create();  
        } catch (CacheException e) {  
            log.fatal("Initialize cache manager failed.", e);  
        }  
    }  
  
    /** 
     * 从缓存中获取对象 
     * @param cache_name 
     * @param key 
     * @return 
     */  
    public static Serializable getCachedValue(String cache_name, Serializable key){  
        Cache cache = getCache(cache_name);  
        if(cache!=null){  
            try {  
                Element elem = cache.get(key);  
                if(elem!=null && !cache.isExpired(elem))  
                    return elem.getValue();  
            } catch (Exception e) {  
                log.error("Get cache("+cache_name+") of "+key+" failed.", e);  
            }  
        }  
        return null;  
    }  
      
    /** 
     * 把对象放入缓存中 
     * @param cache_name 
     * @param key 
     * @param value 
     */  
    public synchronized static void setCachedValue(String cache_name, Serializable key, Serializable value){  
        Cache cache = getCache(cache_name);  
        if(cache!=null){  
            try {  
                cache.remove(key);  
                Element elem = new Element(key, value);  
                cache.put(elem);  
            } catch (Exception e) {  
                log.error("put cache("+cache_name+") of "+key+" failed.", e);  
            }  
        }  
    }  
      
    /** 
     * 获取指定名称的缓存 
     * @param arg0 
     * @return 
     * @throws IllegalStateException 
     */  
    private static Cache getCache(String cache_name) throws IllegalStateException {  
        return manager.getCache(cache_name);  
    }  
    
     /**
     * @method removeCache(删除指定名称的缓存)
     * 
     * @param cache_name
     * @throws Exception
     * @returnType void
     * @exception  
     * @since      1.0.0
     **/
    public static void removeCache(String cache_name) throws Exception{
    	manager.removeCache(cache_name);
    }
    public static boolean removeCacheValueByKey(String cache_name,Object key) throws Exception{
    	return getCache(cache_name).remove(key);
    }
    /** 
     * 停止缓存管理器 
     */  
    public static void shutdown(){  
        if(manager!=null)  
            manager.shutdown();  
    }  
  
}  

