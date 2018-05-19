package com.tenwa.kernal.utils;   

import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2013-5-16 上午11:08:44
 * 类说明
 */
public class SortUtil {
	public static List<String> mergeArray(List<String> list1, List<String> list2){
		List<String> sortList = new ArrayList<String>();
		for (int i = 0; i < list1.size(); i++) {
			for(int j = 0; j < list2.size(); j++){
				if(list1.get(i).equals(list2.get(j))){
					if(j>0){
						for(int m = 0; m < j; m++){
							sortList.add(list2.get(m));
						}
						for(int n = 0; n <= j; n++){
							list2.remove(0);
						}
						break;
					} else{
						list2.remove(j);
						break;
					}
				} 
			}
			
			sortList.add(list1.get(i));
			if(i == list1.size() -1  && list2.size() > 0){
				sortList.addAll(list2);
			}
		}
		
		return sortList;
	}
}
  
