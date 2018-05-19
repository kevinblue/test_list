package com.reckon.util;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;

import com.reckon.commons.helper.ObjectConvertUtils;
import com.tenwa.business.entity.BaseColumnInfo;
import com.tenwa.business.entity.BaseTableInfo;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.EntityUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;


public class copyObjectToHisTools {
 /**
  * 把set集合中每一个对象拷贝到新对象上，并给新的对象赋上其他值
  * @date 2013-5-30
  * xuyunlong
  * @param fromObjectList 数据源
  * @param toObjectClass  目标对象的CLASS
  * @param otherPropertyMap 目标对象的属性的其他值
  * @return 返回新对象的List
  * @throws Exception
  */
  public static  List<?> CopySetObjectAndAddOtherProperty(BaseService baseService,Set<?> fromObjectList,Class<?> toObjectClass,Map<String,Object> otherPropertyMap) throws Exception{
	  int i=0;
	  if(fromObjectList==null){return null;}
	  if(fromObjectList.size()<=0){
		  return null;
	  }else{
		List toObjectlist=new ArrayList();
		Method writeMethod;
		for(Object fromobject:fromObjectList){
			Object toObject=toObjectClass.newInstance();
			String id="";
			id=getObjectMethodValue(fromobject,"getId").toString();
			baseService.copyAndOverrideExistedValueFromObjectNoSet(fromobject, toObject);
			if(otherPropertyMap!=null){
				PropertyDescriptor[] toObjectPD = BeanUtils.getPropertyDescriptors(toObjectClass);
				for(PropertyDescriptor propertyDescriptor_target : toObjectPD)
				{
				   String name_target = propertyDescriptor_target.getName();
				   if("id".equalsIgnoreCase(name_target)){
					   writeMethod = propertyDescriptor_target.getWriteMethod();
					   writeMethod.invoke(toObject, id);
				   }
				   if(otherPropertyMap.containsKey(name_target.toLowerCase())){
					   writeMethod = propertyDescriptor_target.getWriteMethod();
					   writeMethod.invoke(toObject,otherPropertyMap.get(name_target.toLowerCase()));
				   }
				}  
			}
			toObjectlist.add(toObject);
		}
	    return toObjectlist;
	  }
  }
  /**
   * 
   * CopyObjectAndAddOtherProperty(拷贝一个实体)
   * (这里描述这个方法适用条件 – 可选)
   * @param fromObject
   * @param toObjectClass
   * @param otherPropertyMap
   * @return
   * @throws Exception 
   *Object
   * @exception 
   * @since  1.0.0
   */
  public static  Object CopyObjectAndAddOtherProperty(BaseService baseService,Object fromObject,Class<?> toObjectClass,Map<String,Object> otherPropertyMap) throws Exception{
	  int i=0;
	  if(fromObject==null){return null;}
		Method writeMethod;
			Object toObject=toObjectClass.newInstance();
			String id="";
			id=getObjectMethodValue(fromObject,"getId")+"";
			baseService.copyAndOverrideExistedValueFromObjectNoSet(fromObject, toObject);
			if(otherPropertyMap!=null){
				PropertyDescriptor[] toObjectPD = BeanUtils.getPropertyDescriptors(toObjectClass);
				for(PropertyDescriptor propertyDescriptor_target : toObjectPD)
				{
				   String name_target = propertyDescriptor_target.getName();
				   if("id".equalsIgnoreCase(name_target)){
					   writeMethod = propertyDescriptor_target.getWriteMethod();
					   writeMethod.invoke(toObject, id);
				   }
				   if(otherPropertyMap.containsKey(name_target.toLowerCase())){
					   writeMethod = propertyDescriptor_target.getWriteMethod();
					   writeMethod.invoke(toObject,otherPropertyMap.get(name_target.toLowerCase()));
				   }
				}  
			}
		return  toObject;	
  }
  
  /**
   * 
   * @Title: CopyFundPlanAndRemove
   * @Description: 由于资金计划比较灵活，所以在对拷资金计划的时候，要重新写
   * @param baseService
   * @param old
   * @param clazz
   * @param newPlanJsonStr
   * @return
   * @throws Exception
   * @return Map<String,Map<String,Set<ContractFundFundPlan>>>
   * @throws
   */
  public static List<ContractFundFundPlan>  CopyFundPlanAndOverwrite(BaseService baseService,List<ContractFundFundPlan>old,Class<ContractFundFundPlan> clazz,String newPlanJsonStr)throws Exception{
	  ObjectMapper jsonMapper = new ObjectMapper();
	  Map<String,String>classFieldMapping=new HashMap<String,String>();
	  List<ContractFundFundPlan> addSet = new ArrayList<ContractFundFundPlan>();
      classFieldMapping.put("DictionaryData", "code");
      classFieldMapping.put("ContractInfo", "contractId");
      Map<String,Object>[] newSetMaps = null;
      if(null!=newPlanJsonStr&&(!"".equals(newPlanJsonStr))&&(!"[]".equals(newPlanJsonStr))){
    	  newSetMaps = jsonMapper.readValue(newPlanJsonStr, Map[].class);
      }
      String nowTime = DateUtil.getSystemDate();
      User user = SecurityUtil.getPrincipal();
      for(Map<String,Object> newPlanMap : newSetMaps){
    	  ContractFundFundPlan newPlan =  ObjectConvertUtils.convertMapToBean(clazz, newPlanMap);
    	  //baseService.copyAndOverrideExistedValueFromStringMap(newPlanMap, newPlan, classFieldMapping);
    	  addSet.add(newPlan);
    	  for(ContractFundFundPlan oldplan : old){
    		  	  if(newPlan.getFeeType().getCode().equals(oldplan.getFeeType().getCode()) && newPlan.getPaymentId().equals(oldplan.getPaymentId())){
    		  		  addSet.remove(newPlan);
    		  		  oldplan.setPlanDate(newPlan.getPlanDate());
					  oldplan.setPlanMoney(newPlan.getPlanMoney());
					  oldplan.setModificator(user);
					  oldplan.setModifyDate(nowTime);
					  addSet.add(oldplan);
					  break;
    		  	  }else{
    		  		  continue;
    		  	  }
    	  }
    }
	  return addSet;
  }
  
  public static Map<String,?>CopyOjbectFormStrAndRemove(BaseService baseService,Set<?>toObjectSet,Class<?> toObjectClass,String fromStringOne,String fromStringTwo)throws Exception{
	    ObjectMapper jsonMapper = new ObjectMapper();
	    Map<String,String>classFieldMapping=new HashMap<String,String>();
	    classFieldMapping.put("DictionaryData", "code");
	    classFieldMapping.put("ContractInfo", "contractId");
	    Map<String,Object>returnMap=new HashMap<String,Object>();
	    Set<Object> saveSet=new HashSet<Object>();
	    Set<Object> removeSet=new HashSet<Object>();
	    Map[] newSetMaps=null;
	    Boolean flag=true;
	    if(null!=fromStringOne&&(!"".equals(fromStringOne))&&(!"[]".equals(fromStringOne))){
		newSetMaps = jsonMapper.readValue(fromStringOne, Map[].class);
	
		for (int j = 0; j < newSetMaps.length; j++) {//构建出json中的map对象
			Object toObject=toObjectClass.newInstance();
			Map<String,String> cdate=newSetMaps[j];
			if(cdate.containsKey("id")){
			   cdate.remove("id");
			}
		    baseService.copyAndOverrideExistedValueFromStringMap(cdate, toObject, classFieldMapping);
		    flag=false;
		    if(null!=toObjectSet||toObjectSet.size()>0){
		    	for(Object fromobject:toObjectSet){
			    	if(fromobject.equals(toObject)){
			    	   baseService.copyAndOverrideExistedValueFromStringMap(cdate, fromobject, classFieldMapping);
			    	   saveSet.add(fromobject);
			    	   flag=true;
			    	}
		    	}
		    }
		    if(!flag){
		    	saveSet.add(toObject);
		    }
		}}
	    if(null!=fromStringTwo&&(!"".equals(fromStringTwo))&&(!"[]".equals(fromStringTwo))){
			newSetMaps = jsonMapper.readValue(fromStringTwo, Map[].class);
			for (int j = 0; j < newSetMaps.length; j++) {//构建出json中的map对象
				Object toObject=toObjectClass.newInstance();
				Map<String,String> cdate=newSetMaps[j];
				if(cdate.containsKey("id")){
				   cdate.remove("id");
				}
			    baseService.copyAndOverrideExistedValueFromStringMap(cdate, toObject, classFieldMapping);
			    flag=false;
			    if(null!=toObjectSet||toObjectSet.size()>0){
			    for(Object fromobject:toObjectSet){
			    	if(fromobject.equals(toObject)){
			    		   flag=true;
			    		baseService.copyAndOverrideExistedValueFromStringMap(cdate, fromobject, classFieldMapping);
				    	   saveSet.add(fromobject);
			    	}
			    }}
			   
			    if(!flag){
			    	saveSet.add(toObject);
			    }
			}}
		  if(null!=toObjectSet||toObjectSet.size()>0){
		 for(Object fromobject:toObjectSet){
			 flag=false;
			  for(Object saveObject:saveSet ){
				  if(fromobject.equals(saveObject)){
					  flag=true;
				  }
			  }
			  if(flag==false){
				  removeSet.add(fromobject);
			  }
		 }
		  }
		 returnMap.put("save", saveSet);
		 returnMap.put("remove", removeSet);
	  return returnMap;
  }
  public static Method getObjectMethod(Object object,String strMethod) throws Exception{
	  Method method=null;
	  try {
		  System.out.println("=="+strMethod);
		  method = object.getClass().getMethod(strMethod,String.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("获得类"+object.getClass().getSimpleName()+"方法" + strMethod + ":" + e.getMessage());
		}
	  return method;
  }
  public static Object getObjectMethodValue(Object object,String strMethod) throws Exception{
	  Method method=null;
	  Object returnObject=null;
	  try {
		  method = object.getClass().getMethod(strMethod);
		  returnObject=method.invoke(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("获得类"+object.getClass().getSimpleName()+"值" + strMethod + ":" + e.getMessage());
		}
	  return returnObject;
  }
  
 
  public static   <T> List<String> saveOrUpdateMutilTable(BaseService BaseService,Class<T> target,Map<String,Object>searchMap,String mutilTableJsonString,List<String>primaryKey) throws Exception{
	 System.out.println(DateUtil.getSystemTimeByFormat("yyyy-MM-dd HH:mm:ss:sss"));
	  String strprimarykeyList="";
	  String searchCondtion="";
	//  List <String>keyList=new ArrayList<String>();
	  List keyValue=new ArrayList();
	  List<String>sqlList=new ArrayList<String>();
	  BaseTableInfo bi=getTableInfo(target.newInstance());
	  String oneKey="";
	  for(int i=0;i<primaryKey.size();i++){
		  oneKey=primaryKey.get(i).toString();
		 if(strprimarykeyList==""){
			 strprimarykeyList="a."+oneKey+ " as "+oneKey;
		 }else{
			 strprimarykeyList=strprimarykeyList+",a."+oneKey+ " as "+oneKey;
		 }
	  }
	  Set<String>searchkey=searchMap.keySet();
	  String[]keyList=new String[searchkey.size()];
	  int j=-1;
	  for(String key :searchkey){
		  j=j+1;
		  keyList[j]=key.toString();
		  keyValue.add(searchMap.get(key));
		  if(searchCondtion==""){
			  searchCondtion="a."+key+"=:"+key;
		  }else{
			  searchCondtion="and a."+key+"=:"+key;
		  }
	  }
	  try {
		
		String HQL = "select new Map(" + strprimarykeyList + ") from "+ target.getSimpleName() + " as a  where "+searchCondtion;
		List<Map<String, String>> ListPrimaryKey = BaseService
				.findResultsByNamedParamHSQL(HQL, Arrays.asList( keyList ).toArray( new String[0] ),
						keyValue.toArray());
		 int primaryKeyDataIndex=0;
		    if(!"[]".equals(mutilTableJsonString)){
		    	JSONArray jsonArray = new JSONArray(mutilTableJsonString);
				for(int i=0;i<jsonArray.length();i++){
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					primaryKeyDataIndex=getPrimaryKeyDateIndex(ListPrimaryKey,jsonObj,primaryKey);
					if(primaryKeyDataIndex>=0){
						//更新数据
						sqlList.add(getUpdateSQL(bi,ListPrimaryKey.get(primaryKeyDataIndex),jsonObj));
						ListPrimaryKey.remove(primaryKeyDataIndex);
						
					}else{
						//插入SQL
						sqlList.add(getInterSQL(bi,searchMap,jsonObj));
					}
				}
				//删除
				if(ListPrimaryKey.size()>0){
		           sqlList.addAll(getDeleteSQL(bi,ListPrimaryKey));
				}
			}
		    System.out.println(DateUtil.getSystemTimeByFormat("yyyy-MM-dd HH:mm:ss:sss"));
		    return sqlList;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	 return null; 
  }
  public static int  getPrimaryKeyDateIndex(List<Map<String, String>> ListPrimaryKey,JSONObject jobj,List<String> primaryKey) throws Exception{
	  Boolean tempflag=false;
	  String tempkey="";
	  Map<String,String> onePrimaryKey=null;
	  if(ListPrimaryKey.size()>0){
		 
		  for(int i=0;i<ListPrimaryKey.size();i++){
			  onePrimaryKey=ListPrimaryKey.get(i);
			  tempflag=true;
			  for(int j=0;j<primaryKey.size();j++){
				  tempkey=primaryKey.get(j);
				  if(jobj.has(tempkey)){
				  if(!onePrimaryKey.get(tempkey).equals(jobj.get(tempkey).toString())){
					  tempflag=false;
				  }
				  }else{
					  throw new BusinessException("检查是多行，是否是新增还是删除时原值中没有"+tempkey+"对象");
				  }
			  }
			  if(tempflag){return i;}
		  }
		  return -1;
	  }else{
		 return -1;
	  }
  }
  public static List<String> getDeleteSQL(BaseTableInfo bi,List<Map<String,String>>primaryKey){
	  List<String>sqlLists=new ArrayList();
	  String sql="";
	  Set<String>condtionSet=null;
	  String condtion="";
	  for(int i=0;i<primaryKey.size();i++){
		  sql="delete "+bi.getTableSqlName()+" where ";
		  condtion=getCondtion(bi,primaryKey.get(i));
		  if(condtion!=""){
		  sqlLists.add(sql+condtion);
		  }
	  }
	  return sqlLists;
  }
  public static String getInterSQL(BaseTableInfo bi,Map<String,Object>searchMap,JSONObject jobj) throws Exception{
	  String sql="insert into "+bi.getTableSqlName()+" (";
	  String sqlkey="";
	  String sqlvalue="";
	  Iterator<String> it=jobj.keys();
	  String filekey="";
	  int i=-1;
	  BaseColumnInfo bci=null;
	  Map<String,String> sqlKeyValue=new HashMap<String,String>();
	  while(it.hasNext()){
		 filekey="";
		 filekey=it.next();
		 bci=null;
		 bci=bi.getBaseColumnInfo(filekey.toLowerCase());
		 if(null!=bci&& jobj.getString(filekey).length()>0){
			 
		    sqlKeyValue.put(bci.getColumnSqlName(), jobj.getString(filekey));
		 }
	  }
	  for(String key:searchMap.keySet()){
		  Class<?> targetFieldClass = searchMap.get(key).getClass();
		  if (EntityUtil.isTenwaEntity(targetFieldClass)){
		     sqlKeyValue.put(bi.getBaseColumnInfo(key.toLowerCase()).getColumnSqlName(),getObjectMethodValue(searchMap.get(key),"getId").toString());
		  }else{
			  sqlKeyValue.put(bi.getBaseColumnInfo(key.toLowerCase()).getColumnSqlName(), searchMap.get(key).toString());
		  }
	  }
	  i=-1;
	  sqlkey="";
	  sqlvalue="";
	  for(String key:sqlKeyValue.keySet()){
		  i=i+1;
		  if(i>0){
			  sqlkey=sqlkey+",";
			  sqlvalue=sqlvalue+",";
		  }
		  sqlkey=sqlkey+key;
		  sqlvalue=sqlvalue+"'"+sqlKeyValue.get(key).toString()+"'";
	  }
	  String UUID = "SQLSERVER".equals(ResourceUtil.getDBType())?"replace(newid(), '-', '')":"sys_guid()";
	  return sql+"id,"+sqlkey+") values ("+UUID+","+sqlvalue+" )";
	  
  }
  public static <T> String getOneTableHisSql(Class<T> target,Map<String,String> otherFiled) throws Exception{
	  BaseTableInfo bi=getTableInfo(target.newInstance());
	  String sql="insert into "+bi.getTableSqlName()+"_his (";
	  String insertField="hid";//插入的列
	  
	  String valueField = "SQLSERVER".equals(ResourceUtil.getDBType())?"replace(newid(), '-', '')":"sys_guid()";//选择的列
	  Map<String,BaseColumnInfo> bcolums=bi.getBaseColumnInfos();
	  Set<String> keys= bcolums.keySet();
	  Iterator<String> itkeys=keys.iterator();
	  String column="";
	  String sqlcolumn="";
	  while(itkeys.hasNext()){
		  column="";
		  column=itkeys.next();
		  sqlcolumn=bcolums.get(column).getColumnSqlName();
		  insertField=insertField+","+sqlcolumn;
		  valueField=valueField+","+sqlcolumn;
	  }	  
	  if(null!=otherFiled){
		  for(String key:otherFiled.keySet()){
			  insertField=insertField+","+key;
			  valueField=valueField+",'"+otherFiled.get(key)+"' as " +key; 
		  }
	  }
	  sql=sql+insertField+") select "+valueField+" from "+bi.getTableSqlName() ;
	  return sql;
  }
  /**
   * 
   * getOneTableUpdateSql(这里用一句话描述这个方法的作用)获得更新的SQL。将前台中的数据更前到后台，条件自己再加上去。
   * (这里描述这个方法适用条件 – 可选)
   * @param <T>
   * @param target   要更新表对应的类
   * @param model    更新数据
   * @param prix     在前台数据中的前缀
   * @return
   * @throws Exception 
   *String
   * @exception 
   * @since  1.0.0
   */
  public static <T> String getOneTableUpdateSql(Class<T> target,Map<String,String>model,String prix) throws Exception{
	  //更新SQL
	  BaseTableInfo bi=getTableInfo(target.newInstance());
	  String updateSQL="";
	  String sql="update "+bi.getTableSqlName()+" set ";
	  Map<String,BaseColumnInfo> bcolums=bi.getBaseColumnInfos();
	  Set<String> keys= bcolums.keySet();
	  Iterator<String> itkeys=keys.iterator();
	  String column="";
	  String cvalue;
	  if(null==prix||"".equals(prix)){
		  prix=""; 
	  }else{
		  prix=prix+".";
	  }
	  int i=-1;
	  String ColumnSqlName="";
	  while(itkeys.hasNext()){
		  column="";
		  column=itkeys.next();
		  if(model.containsKey(prix+column)){
			    ColumnSqlName="";
			    ColumnSqlName=bcolums.get(column).getColumnSqlName().toLowerCase();
			    if(null!=ColumnSqlName||!"".equals(ColumnSqlName)){
			       if(ColumnSqlName.equals("modify_date")|| ColumnSqlName.equals("id")|| ColumnSqlName.equals("id_")){}
			       else{
			    	  if(null!=model.get(prix+column)){
				      i=i+1;
				      if(i>0){sql=sql+" , ";}
			          sql=sql+"  "+bcolums.get(column).getColumnSqlName()+"='"+model.get(prix+column).toString()+"' ";
		         }}
			 }}
	  }
	  sql=sql+", MODIFY_DATE='"+DateUtil.getSystemTimeByFormat("yyyy-MM-dd HH:mm:ss")+"' ";
	  return sql;
  }
  
  public static String getUpdateSQL(BaseTableInfo bi,Map<String,String>primaryData,JSONObject jobj) throws Exception{
	   String sql="update "+bi.getTableSqlName()+" set ";
	  Set<String>condtionSet=null;
	  String condtion="";
	  condtion=getCondtion(bi,primaryData);
	  Iterator<String> it=jobj.keys();
	  String filekey="";
	  int i=-1;
	  BaseColumnInfo bci=null;
	  while(it.hasNext()){
		 filekey="";
		 filekey=it.next();
		 if(jobj.has(filekey)){
			 bci=bi.getBaseColumnInfo(filekey);
			 if(null!=bci &&jobj.getString(filekey).length()>0){
				 i=i+1;
				 if(i>0){
					 sql=sql+",";
				 }
		       sql=sql+" "+bci.getColumnSqlName()+"='"+jobj.getString(filekey)+"'" ;
			 }
		 }
	  }
	  return sql+"  where 1=1 and  "+condtion;
  }
  public static String getCondtion(BaseTableInfo bi,Map<String,String> Cdate){
	  String condtion="";
	  for(String key :Cdate.keySet()){
		  if(condtion!=""){condtion=condtion+" and ";}
		  System.out.println(key.toLowerCase());
		  condtion=condtion+bi.getBaseColumnInfo(key.toLowerCase()).getColumnSqlName()+"='"+Cdate.get(key).toString()+"'";
	  }
	  return condtion;
  }
  public static BaseTableInfo  getTableInfo(Object target){
	  String columnName="";
	  String columnSqlName="";
	  String fileName="";
	  BaseTableInfo bi=new BaseTableInfo();
      Table ctable=(Table) target.getClass().getAnnotation(Table.class);
      Field[] field = target.getClass().getDeclaredFields();
      bi.setTableSqlName(ctable.name());
      Map<String,BaseColumnInfo>baseColumnInfos=new HashMap<String,BaseColumnInfo>();
      for (int i = 0; i < field.length; i++) {
    	  columnName="";
    	  columnSqlName="";
    	  fileName="";
    	  Field fd=field[i];
    	  FieldName anF= (FieldName)fd.getAnnotation(FieldName.class);
    	  if(anF!=null){fileName=anF.name();}
    	    Column anC= (Column)fd.getAnnotation(Column.class);
    	    if(anC!=null){columnSqlName=anC.name();}
    	    JoinColumn anD= (JoinColumn)fd.getAnnotation(JoinColumn.class);
    	    if(anD!=null){columnSqlName=anD.name();}
    	    columnName=fd.getName().toLowerCase();
    	    if(columnName.equals("id")){columnSqlName="id";}
    	    BaseColumnInfo bc=new BaseColumnInfo(columnName,columnSqlName,fileName);
    	    baseColumnInfos.put(columnName, bc);
      }  
      bi.setBaseColumnInfos(baseColumnInfos);
	  return bi;
  }
}
