package com.tenwa.business.log;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Table;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostDeleteEventListener;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;

import com.tenwa.business.entity.EntityLog;
import com.tenwa.business.entity.EntityLog.EntityOpeType;
import com.tenwa.business.entity.User;
import com.tenwa.freemaker.tools.FreemakerTool;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.EntityUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.VoUtil;

public class PostListener  implements PostInsertEventListener,    
PostUpdateEventListener, PostDeleteEventListener{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void onPostInsert(PostInsertEvent event) {
		String isEntityLog =  ResourceUtil.getConfigValue("system.framework.entitylog");
		if(isEntityLog != null && isEntityLog.equals("true")){
			Object object = event.getEntity();
			//将新增实体
			Class<?> clazz = object.getClass();
			String entityName =  object.getClass().getSimpleName();
			String fullName = clazz.getName();
			//将系统日志和自身实体日志排除在外，另外将JBPM框架表排除在外(因为流程日志，有专门的流程记录，不需要再次做处理)
			if(!entityName.equalsIgnoreCase("SystemLog") && !entityName.equalsIgnoreCase("EntityLog") && fullName.toLowerCase().indexOf("jbpm") < 0){
				EntityLog entityLog = createEntityLog(entityName, clazz, EntityOpeType.INSERT, null, object);
				if(null != entityLog){
					saveOperate(event.getSession(),entityLog);
				}
			}
		}
	}
	
	@Override
	public void onPostDelete(PostDeleteEvent event) {
		String isEntityLog =  ResourceUtil.getConfigValue("system.framework.entitylog");
		if(isEntityLog != null && isEntityLog.equals("true")){
			Object object = event.getEntity();
			Class<?> clazz = object.getClass();
			String entityName =  object.getClass().getSimpleName();
			String fullName = clazz.getName();
			//将系统日志和自身实体日志排除在外，另外将JBPM框架表排除在外(因为流程日志，有专门的流程记录，不需要再次做处理)
			if(!entityName.equalsIgnoreCase("SystemLog") && !entityName.equalsIgnoreCase("EntityLog") && fullName.toLowerCase().indexOf("jbpm") < 0){
				EntityLog entityLog = createEntityLog(entityName, clazz, EntityOpeType.DELETE, null, object);
				if(null != entityLog){
					saveOperate(event.getSession(),entityLog);
				}
			}
		}
	}
	
	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		String isEntityLog =  ResourceUtil.getConfigValue("system.framework.entitylog");
		if(isEntityLog != null && isEntityLog.equals("true")){
			Object object = event.getEntity();
			Class<?> clazz = object.getClass();
			String entityName =  object.getClass().getSimpleName();
			String fullName = clazz.getName();
			//将系统日志和自身实体日志排除在外，另外将JBPM框架表排除在外(因为流程日志，有专门的流程记录，不需要再次做处理)
			if(!entityName.equalsIgnoreCase("SystemLog") && !entityName.equalsIgnoreCase("EntityLog") && fullName.toLowerCase().indexOf("jbpm") < 0){
				String content = "";
				Object[] oldStates = event.getOldState();
				Object[] states = event.getState();
				for(int i = 0 ; i < states.length ; i++){
					String currentContent = "";
					Object nowState =  states[i];
					Object oldState = (oldStates != null && oldStates.length > 0) ? oldStates[i] : null;
					if(nowState != null){
						if(FreemakerTool.checkIsBaseClass(nowState.getClass().getSimpleName())){
							if(oldState == null || !nowState.toString().equals(oldState.toString())){
								currentContent  = (oldState == null ? "null" : oldState.toString())+"-------->>>"+nowState.toString();
							}
						}else if(EntityUtil.isTenwaEntity(nowState.getClass())){
							try{
								Field field = nowState.getClass().getField("id");
								if(null != field){
									String nowStateStr = (String)nowState.getClass().getMethod("getId").invoke(nowState);
									String oldStateStr = "";
									if(oldState != null){
										oldStateStr = (String)oldState.getClass().getMethod("getId").invoke(oldState);
									}
									if(!nowStateStr.equals(oldStateStr)){
										currentContent  = oldStateStr.toString()+"-------->>>"+nowStateStr.toString();
									}
								}
							}catch(Exception e){
								continue;
							}
							
						}
					}
					if(currentContent.length() > 0){	
						if(content.length() == 0 ){
							content += currentContent;
						}else{
							content = content + ","+currentContent;
						}
					}
				}
				EntityLog entityLog = createEntityLog(entityName, clazz, EntityOpeType.UPDATE, null, content);
				if(null != entityLog){
					saveOperate(event.getSession(),entityLog);
				}
			}
		}
	}
	
	
	private EntityLog createEntityLog(String entityName,Class<?> clazz,EntityOpeType opeType,Object before,Object after){
		User user    =  null;
		try{
			user = (User)SecurityUtil.getPrincipal();
		}catch(Exception e){
			return null;
		}
		String currentTime = DateUtil.getSystemDateTime();
		EntityLog entityLog = new EntityLog();
		entityLog.setOperateEntity(entityName);
		entityLog.setOperateUser(user);
		entityLog.setOpeType(opeType);
		entityLog.setTime(currentTime);
		if(clazz.isAnnotationPresent(Table.class)){
			Table table = clazz.getAnnotation(Table.class);
			String tableName = table.name();
			entityLog.setOperateTable(tableName);
		}
		Map<String, String> returnMap = new HashMap<String, String>();
		try{
			ObjectMapper mapper = new ObjectMapper();
			if(after != null ){
				String content = "";
				if(after instanceof String){
					content = after.toString();
				}else{
					VoUtil.convertObjectToMap(returnMap, after, null, false);
					content = mapper.writeValueAsString(returnMap);
				}
				entityLog.setContent(
						content);
			}
			if(before != null){
				String beforeContent = null;
				if(beforeContent instanceof String){
					beforeContent = before.toString();
				}else{
					returnMap.clear();
					VoUtil.convertObjectToMap(returnMap, before, null, false);
					beforeContent = mapper.writeValueAsString(returnMap);
				}
				entityLog.setBeforeContent(beforeContent);
			}
			return entityLog;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	private void saveOperate(Session session, EntityLog entry) {
        Session tempSession = session.getSessionFactory().openSession();
        Transaction tran =  tempSession.beginTransaction();
        try {
        	tran.begin();
        	tempSession.save(entry);
        	tran.commit();
        } catch (Exception ex) {
        	tran.rollback();
        	return;
        }
        tempSession.close();
    }
}
