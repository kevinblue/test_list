package com.yt.client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TTT {
public static void main(String[] args) {
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 获取当前时间
	 System.out.println(df.format(new Date()));
	 DateFormat df2 = new SimpleDateFormat("hh:mm:ss");// 获取当前时间
	 System.out.println(df2.format(new Date()));

   }
}
