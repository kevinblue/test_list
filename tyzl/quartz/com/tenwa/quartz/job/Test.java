package com.tenwa.quartz.job;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.util.AntPathMatcher;

import com.tenwa.kernal.utils.DateUtil;


 /**
 * 项目名称：    系统名称
 * 包名：              
 * 文件名：         Test.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-12-4-下午03:45:53
 * Copyright：2012XX公司-版权所有
 **/

/**
 * 类名称：     Test
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-12-4 下午03:45:53
 * 修改备注：
 * @version 1.0.0
 **/

public class Test implements Job {

	 /**
	 * (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 **/
	private Integer i ;
	@Override
	public void execute(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("现在执行"+DateUtil.getSystemDateTime());
	}
	public void finalize(){
		System.out.println("释放资源：");
	}
	public void test(){
		
	}
    public  static void main(String []args)throws Exception{
    	//AntUrlPathMatcher将url转换为小写
    	AntPathMatcher rupm = new AntPathMatcher();
    	System.out.println(rupm.match("/**/acl/*.acl", "/leasing/acl/index.acl"));
    	//apm.match(pattern, path);
    	//RegexUrlPathMatcher rupm = new RegexUrlPathMatcher();
    	//System.out.println("leasing/acl/index.acl".matches("/**/acl/index.acl".replaceAll("\\.", "\\\\\\\\.")));
//    	Test ft = new Test();
//    	ft.test();
//    	ft = null;
//    	System.gc();
//    	//Date.class.getField("").getType();
//    	System.out.println(Test.class.getDeclaredField("i").getType());
//		Date   newTime   = new Date();
//		String endTime   = DateUtil.getSystemTimeByFormat(newTime,"yyyy-MM-dd HH:mm:ss.SSS");
//		System.out.println(endTime);
		//SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder(); 
		//SqlSessionFactory sqlSessionFactory = builder.build((Reader)null); 
		//sqlSessionFactory.openSession();
		//Object obj = new SqlSessionTemplate(sqlSessionFactory).selectOne("","");
		//SqlSessionTemplate
		//((org.springframework.context.MessageSource)com.kernal.utils.WebUtil.getApplicationContext().getBean("messageSource")).getMessage(s, aobj, locale);
    }
}
