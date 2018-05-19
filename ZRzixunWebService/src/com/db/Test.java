package com.db;

import java.util.ArrayList;
import java.util.List;

public class Test {
 public static void main(String[] args) {
    int sum=100;
    int count=100;
    for(int i=0;i<5;i++){
    	sum/=2;
    	//System.out.println(sum+"====");
    	count +=2*sum;
    	//System.out.println(count+"----------");
    	
    }
    System.out.println(count+"==1111");
    System.out.println(sum/2+"==222");
    for(int i=1;i<=7;i++){
        for(int j=1;j<=7;j++){
        	
        	
        	System.out.println(i+"*"+j+"="+(i*j));
        }
    }
    
    
  }
}
