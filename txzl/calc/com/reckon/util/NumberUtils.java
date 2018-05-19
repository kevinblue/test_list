package com.reckon.util;



import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 通过BigDecimal提供高精度的运算支持.
 * 所以函数以double为参数类型，兼容INT与FLOAT.
 * @author 小谢
 * @Date 2009.02.11
 */
public class NumberUtils {
	
	public static char ADD='+';
	public static char SUBTRACT='-';
	public static char MULTIPLY='*';
	public static char DIVIDE='/';
	
  /**
   * 构造函数。
   */
  public NumberUtils() {

  }
  
  
  /**
   * <p>精确的加法运算.</p>
   * @param v1 
   * @param v2 
   * @return double
   */
  public static double add(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return b1.add(b2).doubleValue();
  }
  
  /**
   * 
   * <p>精确的减法运算.</p>
   * @param v1 被减数
   * @param v2 减数
   * @return double  v1-v2
   */
  public static double subtract(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    return b1.subtract(b2).doubleValue();
  }
  
  /**
   * <p>提供精确的乘法运算.</p
   * @param v1 
   * @param v2 
   * @return double v1 * v2
   */
  public static double multiply(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return b1.multiply(b2).doubleValue();
  }
  
  /**
   * <p>提供精确的乘法运算，并对运算结果截位.</p>
   * @param v1 
   * @param v2 
   * @param scale 运算结果小数后精确的位数
   * @return double
   */
  public static double multiply(double v1, double v2, int scale) {
    if (scale < 0) {
      throw new IllegalArgumentException("The scale must be a positive integer or zero");
    }
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return b1.multiply(b2).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
  }


  /**
   * <p>提供（相对）精确的除法运算.</p>
   * @param v1 
   * @param v2 
   * @return double v1/v2
   * @see #divide(double, double, int)
   */
  public static double divide(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return b1.divide(b2).doubleValue();
  }

  /**
   * <p>提供（相对）精确的除法运算.由scale参数指定精度，以后的数字四舍五入.</p>
   * @param v1 被除数
   * @param v2 除数
   * @param scale 表示表示需要精确到小数点以后几位
   * @return double v1/v2
   */
  public static double divide(double v1, double v2, int scale) {
    if (scale < 0) {
      throw new IllegalArgumentException("The scale must be a positive integer or zero");
    }

    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
  }
  
  /**
   * <p>提供精确的小数位四舍五入处理.</p>
   * @param v 需要四舍五入的数字
   * @param scale 小数点后保留几位
   * @return double
   */
  public static double round(double v, int scale) {
    if (scale < 0) {
      throw new IllegalArgumentException("The scale must be a positive integer or zero");
    }
    BigDecimal b = new BigDecimal(v);
    return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
  }
  
  /**
   * <p>说明 ：字符串转换为数字型实用方法</p>
   * @param strValue 
   * @return Double
   */
   public static Double parseDouble(String strValue)
    {
      double rtnDouble = 0.0;
      if(strValue == null || strValue.equals(""))
      {
        return rtnDouble;
      }else{
        return Double.parseDouble(strValue);        
      }
    }
   
   /**
   * <p>数字型转换为字符串实用方法。</p>
   * @param d
   * @return String
   */
  public static String doubleToString(Double d)
     {
      String value="0.00";
      if(d!=null)
      {
    	  DecimalFormat format = new DecimalFormat("#0.000000");
    	  value = format.format(d);
      }
      //System.out.println("value值："+value);
      return value;
     }
  
  /**
   * <p>是否似乎数字。</p>
   * <pre>try{
        Double.parseDouble(strValue);
        return true;
      }catch (Exception e) {
        return false;
      }</pre>
   * @param strValue
   * @return boolean
   */
   public static boolean isNumber(String strValue)
    {
      try{
        Double.parseDouble(strValue);
        return true;
      }catch (Exception e) {
        return false;
      }
    }
  
	/**
	 * <p>将空串或者null值置为0返回。</p>
	 * @author Sea
	 * @param value
	 * @return
	 */
	public static String nullToZero(String value) {
		try {
			String temp_n = value;
			if (temp_n == null || temp_n.equals("") || temp_n.equals("null")) {
				temp_n = "0";
			}
			return temp_n;
		} catch (Exception e) {
			
		}
		return "0";
	}
	
	  /** 
	    * 提供精确的类型转换(Float) 
	    * @param v 需要被转换的数字 
	    * @return 返回转换结果 
	    */ 
	    public static float convertsToFloat(double v){ 
		    BigDecimal b = new BigDecimal(v); 
		    return b.floatValue(); 
	    } 
	    
	    /**  
	    * 提供精确的类型转换(Int)不进行四舍五入 
	    * @param v 需要被转换的数字 
	    * @return 返回转换结果 
	    */ 
	    public static int convertsToInt(double v){ 
	    	BigDecimal b = new BigDecimal(v); 
	        return b.intValue(); 
	    } 
	    /**  
	    * 提供精确的类型转换(Long) 
	    * @param v 需要被转换的数字 
	    * @return 返回转换结果 
	    */ 
	    public static long convertsToLong(double v){ 
	    	BigDecimal b = new BigDecimal(v); 
	        return b.longValue(); 
	    } 
	    
	    /**  
	    * 返回两个数中大的一个值 
	    * @param v1 需要被对比的第一个数 
	    * @param v2 需要被对比的第二个数 
	    * @return 返回两个数中大的一个值 
	    */ 
	    public static double returnMax(double v1,double v2){ 
		    BigDecimal b1 = new BigDecimal(v1); 
		    BigDecimal b2 = new BigDecimal(v2); 
	        return b1.max(b2).doubleValue(); 
	    }
	    
	    /**  
	    * 返回两个数中小的一个值 
	    * @param v1 需要被对比的第一个数 
	    * @param v2 需要被对比的第二个数 
	    * @return 返回两个数中小的一个值 
	    */ 
	    public static double returnMin(double v1,double v2){ 
		    BigDecimal b1 = new BigDecimal(v1); 
		    BigDecimal b2 = new BigDecimal(v2); 
	        return b1.min(b2).doubleValue(); 
	    }
	    
	    /**  
	    * 精确对比两个数字 
	    * @param v1 需要被对比的第一个数 
	    * @param v2 需要被对比的第二个数 
	    * @return 如果两个数一样则返回0，如果第一个数比第二个数大则返回1，反之返回-1 
	    */ 
	    public static int compareTo(double v1,double v2){ 
	    	BigDecimal b1 = new BigDecimal(v1); 
	    	BigDecimal b2 = new BigDecimal(v2); 
	        return b1.compareTo(b2); 
	    }
	    
	    /**
	     * <p>取NUM的绝对值。</p>
	     * @author 小谢
	     * @param num 需取绝对值的数字
	     * @return
	     */
	    public static int numberToAbs(int num){
	    	int digital = 0;
	    	if(isNumericOfFun(String.valueOf(num))){
	    		digital = Math.abs(num);
	    	}
	    	 return digital;
	    }
	    
	    /**
	     * <p>JAVA自带函数判断是否整型。</p>
	     * @author 小谢
	     * @param str
	     * @return
	     */
	    public static boolean isNumericOfFun(String str){ 
	    	for (int i = str.length();--i>=0;){   
	    	   if (!Character.isDigit(str.charAt(i))){ 
	    	    return false; 
	    	   } 
	    	} 
	    	return true; 
	    }

    	/**
    	 * <p>用正则表达式判断整型。</p>
    	 * @author 小谢
    	 * @param str
    	 * @return 是整数返回true,否则返回false
    	 */
    	public static boolean isNumericOfReg(String str){   
    		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
    		return pattern.matcher(str).matches();   
    	}  

    	/**
    	 * <p>用ascii码判断整型。</p>
    	 * @author 小谢
    	 * @param str
    	 * @return
    	 */  
    	public static boolean isNumericOfAscii(String str){   
    	   for(int i=str.length();--i>=0;){   
    	      int chr=str.charAt(i);   
    	      if(chr<48 || chr>57)   
    	         return false;   
    	   }   
    	   return true;   
    	}

    	/**
    	 * <p>判断是否为浮点数，包括double和float。</p>
    	 * @author 小谢
    	 * @param str  传入的字符串
    	 * @return 是浮点数返回true,否则返回false
    	 */
    	public static boolean isDouble(String str) {
    		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
    		return pattern.matcher(str).matches();
    	}
    	
    	/**
    	 * 
    	 *  (从第startList开始求和corpus的值)
    	 * @param corpus  NUM数组
    	 * @param startList 开始求和起点，数组的下标
    	 * @return
    	 */
    	public static String getSumCorpusOverage(List<String> corpus,int startList){
    		BigDecimal bd = new BigDecimal("0");
    		for(int i = startList-1;i< corpus.size();i++){
    			bd = bd.add(new BigDecimal(corpus.get(i).toString()));
    		}
    		return bd.toString();
    	}
    	
    	/**
    	 * <p>'+'/'-'/'*'/'/'综合版。</p>
    	 * @author Sea
    	 * @param num1 数字1 
    	 * @param num2 数字2
    	 * @param type 计算类型
    	 * @param accuracy 小数位
    	 * @return
    	 */
    	public static String calculationStr(String num1,String num2,char type,int accuracy) {
    		String rs="";
    		num1=num1.equals("")?"0":num1;
    		num2=num2.equals("")?"0":num2;
    		//logger.info("计算公式是:"+num1+type+num2);
    		BigDecimal bigNum1=new BigDecimal(num1);
    		BigDecimal bigNum2=new BigDecimal(num2);
    		switch (type) {
    			case '+':
    				rs = bigNum1.add(bigNum2).setScale(accuracy, BigDecimal.ROUND_HALF_UP).toString();
    				break;
    			case '-':
    				rs = bigNum1.subtract(bigNum2).setScale(accuracy, BigDecimal.ROUND_HALF_UP).toString();
    				break;
    			case '*':
    				rs = bigNum1.multiply(bigNum2).setScale(accuracy, BigDecimal.ROUND_HALF_UP).toString();
    				break;
    			case '/':
    				if(bigNum1.equals("0")){
    					rs="0";
    					break;
    				}
    				rs = bigNum1.divide(bigNum2,accuracy,BigDecimal.ROUND_HALF_UP).toString();
    				break;
    			default:
    				break;
    		}
    		return rs;
    	}
    	/**
    	 * <p>格式化位四位小数。</p>
    	 * @author sea
    	 * @param str
    	 * @return
    	 */
    	public String formatNumberDoubleFour(String str){
    		try
    		{
    	        String temp_num=str;
    	            if ((temp_num==null) || (temp_num.equals("")))
    	        {
    	        temp_num="";
    	        }
    	        else
    	        {
    			 java.text.DecimalFormat ft =  new java.text.DecimalFormat("###0.0000");
    			 BigDecimal bd=new BigDecimal(temp_num);
    			 temp_num=ft.format(bd);
    	 
    	        }
    	        return temp_num; 
    		}
    		catch(Exception e)
    		{
    		}
    		return "";
    	}
    	/**
    	 * <p>格式化位四位小数。</p>
    	 * @author sea
    	 * @param str
    	 * @return
    	 */
    	public String formatNumberDoubleTwo(String str){
    		try
    		{
    			String temp_num=str;
    			if ((temp_num==null) || (temp_num.equals("")))
    			{
    				temp_num="";
    			}
    			else
    			{
    				java.text.DecimalFormat ft =  new java.text.DecimalFormat("###0.00");
    				BigDecimal bd=new BigDecimal(temp_num);
    				temp_num=ft.format(bd);
    				
    			}
    			return temp_num; 
    		}
    		catch(Exception e)
    		{
    		}
    		return "";
    	}
//	    public static long sin(double x): 传回x径度的正弦函数值  
//	    public static long cos(double x)：传回x径度的余弦函数值   
//	    public static long tan(double x): 传回x径度的正切函数值 
//	    public static long asin(double x)：传回x值的反正弦函数值。
//	    public static long acos(double x)：传回x值的反余弦函数值。
//	    public static long atan(double x)：传回x值的反正切函数值。 
//	    public static long atan2(double x, double y)：传回极坐标（polar）的θ值 
//	    public static long floor(double x)：传回不大于x的最大整数值 
//	    public static long ceil(double x)：传回不小于x的最小整数值。 
//	    public static long exp(double x)：传回相当于ex值 
//	    public static long log(double x)：传回x的自然对数函数值 
//	    public static long max(double x,double y)：传回x、y较大数 
//	    public static long min(double x,double y)：传回x、y较小数 
//	    public static long pow(double x,double y)：传回x的y次幂值 
//	    public static long sqrt(double x): 传回x开平方值 
//	    public static long rint(double x):传回最接近x的整数值 
//	    public static long round(double x):传回x的四舍五入值 
//	    public static long toDegrees(double angrad):传回将angrad径度转换成角度 
//	    public static long toRadians(double angdeg): 传回将angdeg角度转换成径度
//	    public static long random():传回随机数值,产生一个0-1之间的随机数(不包括0和1)
    	public BigDecimal nullToZero(BigDecimal number){
    		return number == null ? BigDecimal.ZERO : number;
    	}
}