package com.tenwa.business.entity;

import java.util.Map;

import com.tenwa.kernal.annotation.FieldName;


@FieldName(name="表信息")
public class BaseTableInfo {
   public String tableName; 
   public String tableSqlName;//SQL 表名
   public Map<String,BaseColumnInfo>BaseColumnInfos;  //列信息
   public BaseColumnInfo getBaseColumnInfo(String key){
	   if(BaseColumnInfos.containsKey(key)){
		   return BaseColumnInfos.get(key);
	   }else{
		   return null;
	   }
   }
   public String getTableName() {
	return tableName;
   }
   public void setTableName(String tableName) {
	  this.tableName = tableName;
   }
   public String getTableSqlName() {
	  return tableSqlName;
   }
   public void setTableSqlName(String tableSqlName) {
	  this.tableSqlName = tableSqlName;
   }
public Map<String, BaseColumnInfo> getBaseColumnInfos() {
	return BaseColumnInfos;
}
public void setBaseColumnInfos(Map<String, BaseColumnInfo> baseColumnInfos) {
	BaseColumnInfos = baseColumnInfos;
};
   
}
