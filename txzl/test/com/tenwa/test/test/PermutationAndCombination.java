package com.tenwa.test.test;
import java.util.ArrayList;
import java.util.List;
public class PermutationAndCombination
{
 /**
 * author:54powerman@163.com
 * blog:http://blog.sina.com.cn/m/54powerman
 **/
 public static void main(String[] args)
 {
  long date1=System.currentTimeMillis();
  List<String> list=count("12345");//89abcdefghijklmnopqrstuvwxyz
  for(int i=0;i<list.size();i++)
  {
   System.out.println(list.get(i));
  }
  System.out.println("Process complete!Get "+list.size()+" strings and take ");
  System.out.print(System.currentTimeMillis()-date1+" ms");
 }
 public static List<String> count(String strData)
 {
  List<String> list = new ArrayList<String>();
  if(strData.length()<2)
  {
   list.add(strData);
   return list;
  }
  else if(strData.length()==2)
  {
   String str1=strData.substring(0,1);
   String str2=strData.substring(1);
   list.add(strData);
   list.add(str2+str1);
  }
  else
  {
   for(int i=0;i<strData.length();i++)
   {
    String strTmp=strData.substring(i,i+1);
    List<String> listTmp=count(strData.substring(0,i)+strData.substring(i+1,strData.length()));
    for(int j=0;j<listTmp.size();j++)
    {
     list.add(strTmp+listTmp.get(j).toString());
    }
   }
  }
  return list;
 }
}