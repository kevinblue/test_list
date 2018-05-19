package com.tenwa.server;

import java.lang.reflect.Method;

public class A {
	
	 public void foo(String name) {
		 System.out.println("Hello, " + name);
	 }
	 
	 
	 
	 public static void main(String[] args) throws Exception  {
		 A a =new A();
		 Class<?> clz = Class.forName("A");
		 Object o = clz.newInstance();
		 Method m = clz.getMethod("foo", String.class);
		for (int i = 0; i < 16; i++) {
	     m.invoke(o, Integer.toString(i));       
	  }
	}
	 
}