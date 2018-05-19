package com.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//平均分派数据
public class UUU {
	 public static void main(String[] args) {

		 List<String> list = new ArrayList<String>();//经销商tuid
	        list.add("1");
	        list.add("2");
	        list.add("3");
	        list.add("4");
	        list.add("5");
	        list.add("6");
	        list.add("7");
	        list.add("8");
	        list.add("9");
	        list.add("10");
	        list.add("11");
	
	        List<String> alist = new ArrayList<String>();//经销商名称
	        alist.add("a");
	        alist.add("a");
	        alist.add("a");
	        alist.add("b");
	        alist.add("c");
	     
	        Map<String, List<Integer>> resultMap = new HashMap<String, List<Integer>>();//用map存储经销商对应tuid
	        for(String box:alist) {
	            resultMap.put(box, new ArrayList<Integer>());
	        }
   
	        int count = 0;
	      //按排序法平均分派
	        ap:for (int i = 1; i <=list.size(); i++) {//循环所有客户数据
	            for (int j = 0; j < alist.size(); j++) {//循环要分配经销商名称
	                if (resultMap.get(alist.get(j)).size() == count) {//过滤不相同的经销名称
	                
	                    resultMap.get(alist.get(j)).add(Integer.parseInt(list.get(i)));//按排序法平均分派
	                    continue ap;
	                }
	            }
	            count++;
	            i--;
	        }
	      System.out.println(resultMap);
	        for (String key : resultMap.keySet()) {//遍历map键和值
	        	 
	        	if (alist.contains(key)) {//group by所有经销商信息对应key，平均分派的任务
	           	List<Integer> y  =	resultMap.get(key);
	           	System.out.println(y);
	           	int p=0;//用来判断是不是最后的一个值
	               for(int i=0;i<y.size();i++){
	            	  p++;
	            	   if(p==y.size()){//如果是最后的一条经销商的值，就插入到历史数据中
	            		   System.out.println(y.get(i)+"---------");  
	            		   
	            	   }
	            	   //System.out.println(y.get(i));
	                   
	                 }
	        	  }
	        }
	    }
}
