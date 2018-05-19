package com.tenwa.test.test;

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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.User;
import com.tenwa.freemaker.entity.FreeMakerEntity;
import com.tenwa.freemaker.entity.FreeMakerEntityColumn;
import com.tenwa.freemaker.enums.ColumnFetchEnum;
import com.tenwa.freemaker.enums.EntityColumTypeEnum;
import com.tenwa.freemaker.enums.EntityTypeEnum;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.FileUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:applicationContext.xml","classpath:applicationContext-jedis.xml"})
public class FreemakerTest {
	public static void main(String[] args) {
		/*Configuration config = new Configuration();
		try{
			config.setEncoding(Locale.CHINA, "UTF-8");
			config.setDirectoryForTemplateLoading(new File("D:\\sirchuang\\workplace64\\tlsnew\\WebContent\\FreemakerTemplate"));   
			config.setSharedVariable("upperFC", new UpperFirstCharacter());//添加一个"宏"共享变量用来将属性名首字母大写  
			Template template = config.getTemplate("/JavaBeanTemplate.ftl");//指定模板
			FileOutputStream fos = new FileOutputStream("D:/sirchuang/workplace64/tlsnew/leasing/com/tenwa/leasing/entity/UserTest.java");
			//创建数据模型
			Map<String, Object>data = new HashMap<String, Object>();
			data.put("package", "com.tenwa.leasing.entity");
			data.put("className", "UserTest");
			data.put("entityDesc", "freemaker用户测试类");
			data.put("entityName", "T_USER_TEST");
			//创建类的私有变量的属性值
			List<Map<String, String>> properties = new ArrayList<Map<String,String>>();
			Map<String, String> property = new HashMap<String, String>();
			property.put("proType", String.class.getSimpleName());
			property.put("proName", "name");
			property.put("columnType", EntityColumTypeEnum.PRIMARY.toString());
			property.put("proDesc", "主键");
			properties.add(property);
			Map<String, String> property2 = new HashMap<String, String>();
			property2.put("proType", Integer.class.getSimpleName());
			property2.put("proName", "age");
			//property2.put("proDesc", "年龄");
			properties.add(property2);
			Map<String, String>property3 = new HashMap<String, String>();
			property3.put("proType", "DictionaryData");
			property3.put("proName", "userType");
			property3.put("columnName", "USER_TYPE");
			property3.put("columnType", EntityColumTypeEnum.FOREIGNKEY.toString());
			property3.put("proDesc", "用户类别");
			properties.add(property3);
			Map<String, String>property4 = new HashMap<String, String>();
			property4.put("proType", "Set<DictionaryData>");
			property4.put("proName", "userTypes");
			property4.put("columnName", "userId");
			property4.put("columnType", EntityColumTypeEnum.ONETOMANY.toString());
			property4.put("proDesc", "用户类别S");
			properties.add(property4);
			data.put("properties", properties);
			template.process(data,new BufferedWriter(new OutputStreamWriter(fos)));
			fos.flush();
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}*/
		String path = "com.tenwa.business.dao";
		System.out.println(path.lastIndexOf("."));
		System.out.println(path.substring(0, path.lastIndexOf(".")));
		String [] paths = path.split("\\.");
		for(String p : paths){
			System.out.println(p );
		}
	}
	
	@Autowired
	private  BaseDao baseDao;
	private static String systemDate = DateUtil.getSystemDateTime();
	private static User user = new User();
	static{
		user.setId("Administrator");
	}
	
	@Test
	public void testFreemaker(){
		String[] sourceFolders = new String[]{"jbpm","business","report","leasing","freemaker"};
		//将所有的package查询出来，然后存放到map中根据名称去取对应的实体
		try{
			List<FreeMakerEntity> entitys = this.baseDao.findEntities(FreeMakerEntity.class);
			Map<String, FreeMakerEntity> entityMap = new HashMap<String, FreeMakerEntity>();
			for(FreeMakerEntity entity : entitys){
				entityMap.put(entity.getEntityName(), entity);
			}
			for(String sourceFold : sourceFolders){
				//根据名称去查找对应的 entity实体是否存在
				String entityParentPackage = "com.tenwa."+sourceFold+".entity";
				Set<Class<?>> clazzes =  FileUtil.getClasses(entityParentPackage);
				for(Class<?> clazz : clazzes){
					String clazzFullName = clazz.getName();
					String packageName = clazzFullName.substring(0, clazzFullName.lastIndexOf("."));
					check(packageName, entityMap, sourceFold);
					createEntityColumns(clazz, entityMap, sourceFold,packageName);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private static int j = 0;
	public void check(String packageName,Map<String, FreeMakerEntity> entityMap,String sourceFold)throws Exception{
		
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
				newEntity.setPosition(j++);
				newEntity.setCreateDate(systemDate);
				newEntity.setCreator(user);
				if(!pp.equals(parentPP)){
					newEntity.setParent(entityMap.get(parentPP));
				}
				this.baseDao.saveEntity(newEntity);
				entityMap.put(pp, newEntity);
			}
		}
	}
	public void createEntityColumns(Class<?> clazz,Map<String, FreeMakerEntity> entityMap,String sourceFold,String packageName)throws Exception{
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
				entity.setCreateDate(systemDate);
				entity.setCreator(user);
				this.baseDao.saveEntity(entity);
				entityMap.put(clazz.getName(), entity);
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
						column.setCreateDate(systemDate);
						column.setCreator(user);
						column.setPosition(k++);
						this.baseDao.saveEntity(column);
					}
				}
			}
		}
		
	}
	
	private static String[] baseJavaType = new String [] {"Boolean","String","Float","Double","Integer"};
	
	public boolean checkIsBaseClass(String classSimpleName){
		boolean flag = false;
		for(String javaType : baseJavaType){
			if(javaType.equalsIgnoreCase(classSimpleName)){
				flag =  true;
				break;
			}
		}
		return flag;
	}
	
	public Map<String, String> getParameterizedType(Field field){
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
	