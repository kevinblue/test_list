package com.tenwa.leasing.serviceImpl.fileTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataStringValidator {
   public static boolean checkStringData(String checkvalue,String type){
	   System.out.println(checkvalue+":"+type);
	   String rexp="";
	  if("string".equals(type)){
		  return true;
	  }else if("date".equals(type)){
		   rexp = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";
	  }else if("double".equals(type)||"float".equals(type)||"decimal".equals(type)){
		  rexp="([-\\+]?[0-9]([0-9]*)(\\.[0-9]+)?)|(^0$)";
	  }else if("int".equals(type)){
		  rexp="^[1-9]\\d*$";
	  }else{
		  return true;
	  }
	  checkvalue=checkvalue.trim();
	  Pattern pattern = Pattern.compile(rexp);
      Matcher isNum = pattern.matcher(checkvalue);
      if(isNum.matches()) {
           return true;
       } else {
          return false;
       }
   }
   
}
