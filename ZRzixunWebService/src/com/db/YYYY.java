package com.db;

import java.util.ArrayList;
import java.util.HashMap;

public class YYYY {

	public static void main(String[] args) {

		ArrayList list=new ArrayList(); //存储的是数据库中得到的90条数据
		String[] personArr={"张三","李四","李四","李四","王五","赵六"};
		//1.将从数据库中的数据放到list表中
		list=getDate();
		//System.out.println(list);
		//2.将数据平分给数组中的人
		HashMap splitMap=splitDate(list,personArr);
		//System.out.println(splitMap);
	}

   /**
    * 模拟从数据库中获取数据，放到list集合中
    * @return
    */
	public static ArrayList getDate(){
		ArrayList dateList=new ArrayList();
		for(int i=0;i<10;i++){
			String element="这是第"+(i+1)+"个数据";
			dateList.add(i, element);
		}
		return dateList;
	}

	public static HashMap splitDate(ArrayList list,String[] personArr){
		HashMap splitMap=new HashMap();
		for(int i=0;i<personArr.length;i++){
			ArrayList splitList=new ArrayList();
			splitMap.put(personArr[i], splitList);
		}
	System.out.println(splitMap);
		int num=personArr.length;

		for(int i=0;i<list.size();i++){
			int j=i%num;
			ArrayList splitList=(ArrayList) splitMap.get(personArr[j]);
			splitList.add(list.get(i));
			splitMap.put(personArr[j], splitList);
			System.out.println(splitList);
		}
	
	System.out.println(splitMap);
		return splitMap;
	}
}
