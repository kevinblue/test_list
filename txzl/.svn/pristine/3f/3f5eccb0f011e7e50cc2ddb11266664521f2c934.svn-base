package com.tenwa.test.test;


import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.tenwa.kernal.annotation.FieldName;
/**
 * 读类的属性
 * 
 */
public class TestReadClassProperty {
  public static void main(String [] args){
	  String className="com.tenwa.business.entity.asset.FundEbankData";
	  Class<?> demo1=null;
	  try{
          demo1=Class.forName(className);
          Table ctable=(Table) demo1.getAnnotation(Table.class);
          Field[] field = demo1.getDeclaredFields();
          for (int i = 0; i < field.length; i++) {
        	  Field fd=field[i];
        	  FieldName anF= (FieldName)fd.getAnnotation(FieldName.class);
        	  if(anF!=null){System.out.print(anF.name()+":");}
        	    Column anC= (Column)fd.getAnnotation(Column.class);
        	    if(anC!=null){System.out.print(anC.name()+":");}
        	    JoinColumn anD= (JoinColumn)fd.getAnnotation(JoinColumn.class);
        	    if(anD!=null){System.out.print(anD.name()+":");}
        	  System.out.println(ctable.name().toLowerCase()+"."+fd.getName().toLowerCase());
          }
          System.out.println("=============xx");
          
          for (int i = 0; i < field.length; i++) {
        	  Field fd=field[i];
        	  Column anC= (Column)fd.getAnnotation(Column.class);
        	  if(anC!=null){System.out.print(anC.name());}
        	  JoinColumn anD= (JoinColumn)fd.getAnnotation(JoinColumn.class);
        	  if(anD!=null){System.out.print(anD.name());}
        	  System.out.print(" "+fd.getName().toLowerCase() +",");
          }
          System.out.println(" from "+ctable.name());
      }catch(Exception e){
          e.printStackTrace();
      }
  }
}
