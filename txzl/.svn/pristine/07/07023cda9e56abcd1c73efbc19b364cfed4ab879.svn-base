
package com.tenwa.freemaker.tools;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.freemaker.entity.FreeMakerEntity;
import com.tenwa.freemaker.entity.FreeMakerEntityColumn;
import com.tenwa.freemaker.enums.ColumnFetchEnum;
import com.tenwa.freemaker.enums.EntityColumTypeEnum;
import com.tenwa.freemaker.enums.EntityTypeEnum;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.FileUtil;

public class FreemakerTool {
	
	public static void createAllDbData(User user,BaseService baseservice)throws Exception{
		String[] sourceFolders = new String[]{"jbpm","business","report","leasing","freemaker"};
		//将所有的package查询出来，然后存放到map中根据名称去取对应的实体
		List<FreeMakerEntity> entitys = baseservice.findEntities(FreeMakerEntity.class);
		Map<String, FreeMakerEntity> entityMap = new HashMap<String, FreeMakerEntity>();
		for(FreeMakerEntity entity : entitys){
			entityMap.put(entity.getEntityName(), entity);
		}
		for(String sourceFold : sourceFolders){
			//根据名称去查找对应的 entity实体是否存在
			String entityParentPackage = "com.tenwa."+sourceFold+".entity";
			Set<Class<?>> clazzes =  FileUtil.getClasses(entityParentPackage);
			int position = 0 ;
			for(Class<?> clazz : clazzes){
				String clazzFullName = clazz.getName();
				String packageName = clazzFullName.substring(0, clazzFullName.lastIndexOf("."));
				check(packageName, entityMap, sourceFold,user,baseservice,position);
				createEntityColumns(clazz, entityMap, sourceFold,packageName,user,baseservice);
			}
		}
		
	}
	
	public static void createDbDataByEntity(FreeMakerEntity entity,User user,BaseService baseservice)throws Exception{
		Class<?> clazz = Class.forName(entity.getEntityName());
		//check(clazz.getName().substring(0, clazz.getName().lastIndexOf(".")), null, entity.getSourcefolderName(),user,baseservice,0);
		createColumns(clazz, entity, user, baseservice);
	}
	
	private static void check(String packageName,Map<String, FreeMakerEntity> entityMap,String sourceFold,User user,BaseService baseservice,int position)throws Exception{
		
		String[] packages = packageName.split("\\.");
		for(int i = 0 ; i < packages.length ;i++){
			String pp = "";
			Boolean flag = true;
			String parentPP = "";
			for(int j = 0 ; j <= i ;j++){
				if(flag){
					pp += packages[j];
					flag = false;
					parentPP += packages[j];
				}else{
					pp += "."+ packages[j];
					if(j < i){
						parentPP += "."+ packages[j];
					}
				}
			
			}
			if(!entityMap.containsKey(pp)){
				FreeMakerEntity newEntity = new FreeMakerEntity();
				newEntity.setEntityName(pp);
				newEntity.setSourcefolderName(sourceFold);
				newEntity.setEntityType(EntityTypeEnum.valueOf("PACKAGE"));
				newEntity.setPosition(position++);
				newEntity.setCreateDate(DateUtil.getSystemDateTime());
				newEntity.setCreator(user);
				if(!pp.equals(parentPP)){
					newEntity.setParent(entityMap.get(parentPP));
				}
				baseservice.saveEntity(newEntity);
				entityMap.put(pp, newEntity);
			}
		}
	}
	private static void createEntityColumns(Class<?> clazz,Map<String, FreeMakerEntity> entityMap,String sourceFold,String packageName,User user,BaseService baseservice)throws Exception{
		if(!entityMap.containsKey(clazz.getName())){
			FreeMakerEntity entity = new FreeMakerEntity();
			if(clazz.isAnnotationPresent(Entity.class)){
				String tableName = clazz.getSimpleName(); 
				if (clazz.isAnnotationPresent(Table.class)) {
					Table table =  clazz.getAnnotation(Table.class);
					tableName = table.name();
				}
				entity.setParent(entityMap.get(packageName));
				entity.setEntityName(clazz.getName());
				entity.setTableName(tableName);
				entity.setEntityType(EntityTypeEnum.ENTITY);
				entity.setSourcefolderName(sourceFold);
				if(clazz.isAnnotationPresent(FieldName.class)){
					FieldName fieldName =  clazz.getAnnotation(FieldName.class);
					String desc = fieldName.name();
					entity.setTableDesc(desc);
				}
				entity.setCreateDate(DateUtil.getSystemDateTime());
				entity.setCreator(user);
				baseservice.saveEntity(entity);
				entityMap.put(clazz.getName(), entity);
				createColumns(clazz, entity, user, baseservice);
			}
		}
		
	}
	
	private static void createColumns(Class<?> clazz , FreeMakerEntity entity,User user,BaseService baseservice)throws Exception{
		if(clazz.isAnnotationPresent(Entity.class)){
			Field[] fields = clazz.getDeclaredFields();
			int k = 0 ;
			for(Field field : fields){
				if(!field.getName().equals("serialVersionUID")){//将序列化实例私有变量单独拿出来
					FreeMakerEntityColumn column = new FreeMakerEntityColumn();
					column.setEntity(entity);
					Class fieldClass  = field.getType();
					String fieldType = fieldClass.getSimpleName();
					column.setFieldType(fieldType);
					if(checkIsBaseClass(fieldType)){
						column.setFieldTypeFullName(fieldType);
					}else{
						column.setFieldTypeFullName(fieldClass.getName());
					}
					column.setFieldName(field.getName());
					if(field.isAnnotationPresent(FieldName.class)){
						FieldName fieldName =  field.getAnnotation(FieldName.class);
						String desc = fieldName.name();
						column.setTableColumnDesc(desc);
					}
					//判断约束类型
					if(field.isAnnotationPresent(Id.class)){
						column.setTableColumnType(EntityColumTypeEnum.PRIMARY);
						if(field.isAnnotationPresent(Column.class)){
							column.setTableColumnName(field.getAnnotation(Column.class).name());
							column.setTableIsNotnull(false);
							column.setTableIsUnique(true);
						}
					}else if(field.isAnnotationPresent(ManyToOne.class)){
						column.setTableColumnType(EntityColumTypeEnum.FOREIGNKEY);
						column.setFetchType(ColumnFetchEnum.valueOf(field.getAnnotation(ManyToOne.class).fetch().name()));
						if(field.isAnnotationPresent(JoinColumn.class)){
							column.setTableColumnName(field.getAnnotation(JoinColumn.class).name());
							column.setTableIsNotnull( field.getAnnotation(JoinColumn.class).nullable());
							column.setTableIsUnique(field.getAnnotation(JoinColumn.class).unique());
						}
					}else if(field.isAnnotationPresent(OneToMany.class)){
						column.setFetchType(ColumnFetchEnum.valueOf(field.getAnnotation(OneToMany.class).fetch().name()));
						column.setTableColumnType(EntityColumTypeEnum.ONETOMANY);
						column.setTableColumnName(field.getAnnotation(OneToMany.class).mappedBy());
						Map<String, String>parameterClass =   getParameterizedType(field);
						//重新赋值取出set中的泛型类型
						column.setFieldType(parameterClass.get("name")); 
						column.setFieldTypeFullName(parameterClass.get("fullname"));
					}else if(field.isAnnotationPresent(Enumerated.class)){
						column.setTableColumnType(EntityColumTypeEnum.ENUM);
						if(field.isAnnotationPresent(Column.class)){
							column.setTableColumnName(field.getAnnotation(Column.class).name());
							column.setTableIsNotnull( field.getAnnotation(Column.class).nullable());
							column.setTableIsUnique(field.getAnnotation(Column.class).unique());
							column.setTableColumnLength(field.getAnnotation(Column.class).length());
							column.setTableColumnPrecision(field.getAnnotation(Column.class).precision());
							column.setTableColumnScale(field.getAnnotation(Column.class).scale());
							column.setTableColumnDefinition(field.getAnnotation(Column.class).columnDefinition());
						}
					}else if(field.isAnnotationPresent(OneToOne.class)){
						column.setTableColumnType(EntityColumTypeEnum.ONETOONE);
						column.setFetchType(ColumnFetchEnum.valueOf(field.getAnnotation(OneToOne.class).fetch().name()));
						column.setTableColumnName(field.getAnnotation(OneToOne.class).mappedBy());
					}else if(field.isAnnotationPresent(Transient.class)){
						column.setTableColumnType(EntityColumTypeEnum.TRANSIENT);
					}else if(field.isAnnotationPresent(Lob.class)){
						if(field.isAnnotationPresent(Type.class)){
							Type type = field.getAnnotation(Type.class);
							if(type.type().equalsIgnoreCase("org.hibernate.type.StringClobType")){
								column.setTableColumnType(EntityColumTypeEnum.CLOB);
							}else{
								column.setTableColumnType(EntityColumTypeEnum.BLOB);
							}
						}else{
							column.setTableColumnType(EntityColumTypeEnum.CLOB);
						}
						if(field.isAnnotationPresent(Column.class)){
							column.setTableColumnName(field.getAnnotation(Column.class).name());
							column.setTableIsNotnull( field.getAnnotation(Column.class).nullable());
							column.setTableIsUnique(field.getAnnotation(Column.class).unique());
						}
						column.setFetchType(ColumnFetchEnum.LAZY);
					}else {
						column.setTableColumnType(EntityColumTypeEnum.COMMON);
						if(field.isAnnotationPresent(Column.class)){
							column.setTableColumnName(field.getAnnotation(Column.class).name());
							column.setTableIsNotnull( field.getAnnotation(Column.class).nullable());
							column.setTableIsUnique(field.getAnnotation(Column.class).unique());
							column.setTableColumnLength(field.getAnnotation(Column.class).length());
							column.setTableColumnPrecision(field.getAnnotation(Column.class).precision());
							column.setTableColumnScale(field.getAnnotation(Column.class).scale());
							column.setTableColumnDefinition(field.getAnnotation(Column.class).columnDefinition());
						}
					}
					if(field.isAnnotationPresent(Index.class)){
						column.setTableIsIndex(true);
						column.setTableIndexName(field.getAnnotation(Index.class).name());
					}
					if(field.isAnnotationPresent(OrderBy.class)){
						column.setColumnOrderBy(field.getAnnotation(OrderBy.class).value());
					}
					column.setCreateDate(DateUtil.getSystemDateTime());
					column.setCreator(user);
					column.setPosition(k++);
					baseservice.saveEntity(column);
				}
			}
		}
	}
	
	private static String[] baseJavaType = new String [] {"Boolean","String","Float","Double","Integer","int","float","double","boolean"};
	
	public static boolean checkIsBaseClass(String classSimpleName){
		boolean flag = false;
		for(String javaType : baseJavaType){
			if(javaType.equalsIgnoreCase(classSimpleName)){
				flag =  true;
				break;
			}
		}
		return flag;
	}
	
	private static Map<String, String> getParameterizedType(Field field){
		Map<String, String> map = new HashMap<String, String>();
		Class<?> clazz = field.getType();
		
		 if(clazz.isAssignableFrom(List.class) || clazz.isAssignableFrom(Set.class)) {   
             java.lang.reflect.Type fc = field.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型  
  
             if(fc != null && fc instanceof ParameterizedType) // 【3】如果是泛型参数的类型   
            {   
                   ParameterizedType pt = (ParameterizedType) fc;  
                   Class genericClazz = (Class)pt.getActualTypeArguments()[0]; //【4】 得到泛型里的class类型对象。  
                   map.put("name", genericClazz.getSimpleName());
                   map.put("fullname", genericClazz.getName());
             }   
		 }
		 return map;
	}
}
