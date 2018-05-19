package com.tenwa.kernal.web.listener;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tenwa.business.entity.I18nDictionary;
import com.tenwa.business.service.TableService;
import com.tenwa.freemaker.service.FreemakerService;
import com.tenwa.kernal.utils.FreeMarkerUtil;
import com.tenwa.kernal.utils.PermissionUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.kernal.utils.XMLDataUtil;
import com.tenwa.quartz.listener.JobListenerImpl;
import com.tenwa.quartz.listener.SchedulerListenerImpl;
import com.tenwa.quartz.listener.TriggerListenerImpl;

public class InitializeListener implements  ServletContextListener 
{
    Log log = LogFactory.getLog(this.getClass());
	@Override
	public void contextInitialized(ServletContextEvent sce) 
	{
		ServletContext sc = sce.getServletContext();
		WebUtil.setServletContext(sc);
        WebUtil.setApplicationContext(WebApplicationContextUtils.getWebApplicationContext(sc));
        WebUtil.setWebContextPath(sc.getContextPath());
        WebUtil.setWebContextRealPath(sc.getRealPath("/")+"/");
        String lanaguage = ResourceUtil.getConfigValue("Language");
        
       	if(log.isInfoEnabled()){	
        	log.info("开始缓存系统语言...");
        }
    	if(null != lanaguage && lanaguage.equalsIgnoreCase("US")){
    		WebUtil.setDefaultLocal(new Locale("en","US"));
    	}else{
    		WebUtil.setDefaultLocal(new Locale("zh","CN"));
    	}
        try{
        	PermissionUtil.saveSystemLang(WebUtil.getDefaultLocal()); 
        	ResourceUtil.cacheI18N();
        	TableService bs =  (TableService)WebUtil.getApplicationContext().getBean("tableService");
        	List<I18nDictionary> dicts =  bs.findResultsByHSQL("from I18nDictionary where enabled = ?", true);
        	ResourceUtil.synI18nProperties(dicts);
        }catch(Exception e){
        	e.printStackTrace();
        }
        if(log.isInfoEnabled()){	
        	log.info("缓存系统语言完毕！！！");
        }
        
        if(log.isInfoEnabled()){	
        	log.info("开始缓存系统freemaker实体...");
        }
        
        try{
        	ApplicationContext applicationContext =  WebApplicationContextUtils.getWebApplicationContext(sc);
        	FreemakerService freemakerService = (FreemakerService)applicationContext.getBean("freemakerService");
        	Map<String, String> model = new HashMap<String, String>();
        	model.put("parent", "0");
        	String jsonStr =   freemakerService.saveOrQueryFreemakerEntities(model);
        	ResourceUtil.setFreemakerEntityStr(jsonStr);
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        
        if(log.isInfoEnabled()){	
        	log.info("缓存实体完毕...");
        }
        //initialize the framework's config-file
        ResourceUtil.init();
        //initialize the charts's info
        FreeMarkerUtil.init();
        //清除过期用户
        try {
			PermissionUtil.clearExpireLoginInfos();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
        //缓存用户菜单
		if(log.isInfoEnabled())
		{
			log.info("正在缓存用户菜单...");			
		}
		try {
			PermissionUtil.cachedAllUserTreeMenus(false);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//缓存xml
        //if(ResourceUtil.isDebug())
        {
    		try 
    		{
    			long startMilis = System.currentTimeMillis();
    			if(log.isInfoEnabled())
    			{
    				log.info("正在缓存xml文件...");			
    			}
    			Map<String,Map<String,String>> allXmlTable = XMLDataUtil.getAllXMLTable();
    			WebUtil.setAllXMLTable(allXmlTable);
    			Map<String,Map<String,String>> allXmlChart = XMLDataUtil.getAllXMLChart();
    			WebUtil.setAllXMLChart(allXmlChart);
    			if(log.isInfoEnabled())
    			{
    				log.info("开始缓存xml文件完毕,共用时:"+(System.currentTimeMillis()-startMilis)/1000+" 秒 ");			
    			}
    		} catch (Exception e2) {
    			e2.printStackTrace();
    		}
        }
       if(log.isInfoEnabled())
        {
        	log.info("quartz定时调度线程信息：Quartz Initializer Servlet loaded, initializing Scheduler...");
        }
        ServletContext servletContext = sce.getServletContext();
        try
        {
            String configFile = servletContext.getInitParameter("quartz-config-file");
            if(null!=configFile)
            {
            	URL quartzConfigFileURL = Thread.currentThread().getContextClassLoader().getResource(configFile);
            	if(null!=quartzConfigFileURL)
            	{
            		if(log.isInfoEnabled())
            		{
            			log.info("quartz定时调度线程信息："+StringUtil.nullToString(quartzConfigFileURL.getFile()));
            		}            	
                }
            }
            String shutdownPref = servletContext.getInitParameter("shutdown-on-unload");
            if(shutdownPref != null)
                performShutdown = Boolean.valueOf(shutdownPref).booleanValue();
            StdSchedulerFactory factory;
            if(configFile != null)
                factory = new StdSchedulerFactory(configFile);
            else
                factory = new StdSchedulerFactory();
            scheduler = factory.getScheduler();
            //添加quartz任务监听日志
            scheduler.addSchedulerListener(new SchedulerListenerImpl());
            scheduler.addJobListener(new JobListenerImpl());
            scheduler.addTriggerListener(new TriggerListenerImpl());
          
            String startOnLoad = servletContext.getInitParameter("start-scheduler-on-load");
            int startDelay = 0;
            String startDelayS = servletContext.getInitParameter("start-delay-seconds");
            try
            {
                if(startDelayS != null && startDelayS.trim().length() > 0)
                    startDelay = Integer.parseInt(startDelayS);
            }
            catch(Exception e)
            {
            	if(log.isInfoEnabled())
            	{
                  log.info("quartz定时调度线程信息：Cannot parse value of 'start-delay-seconds' to an integer: " + startDelayS + ", defaulting to 5 seconds.");
            	}
                startDelay = 5;
            }
            if(startOnLoad == null || Boolean.valueOf(startOnLoad).booleanValue())
            {
                if(startDelay <= 0)
                {
                    scheduler.start();
                    if(log.isInfoEnabled())
                    {
                       log.info("quartz定时调度线程信息：Scheduler has been started...");
                    }
                } 
                else
                {
                    scheduler.startDelayed(startDelay);
                    if(log.isInfoEnabled())
                    {
                       log.info("quartz定时调度线程信息：Scheduler will start in " + startDelay + " seconds.");
                    }              
               }
            } 
            else
            {
                if(log.isInfoEnabled())
                {
                	log.info("quartz定时调度线程信息：Scheduler has not been started. Use scheduler.start()");
                }           
            }
            String factoryKey = servletContext.getInitParameter("servlet-context-factory-key");
            if(factoryKey == null)
                factoryKey = "org.quartz.impl.StdSchedulerFactory.KEY";
            if(log.isInfoEnabled())
            {
               log.info("quartz定时调度线程信息：Storing the Quartz Scheduler Factory in the servlet context at key: " + factoryKey);
            }
            servletContext.setAttribute(factoryKey, factory);
            //加载定时调度上下文
            WebUtil.setSchedulerFactory(factory);
        }
        catch(Exception e)
        {
            if(log.isInfoEnabled())
            {
            	log.info("quartz定时调度线程信息：Quartz Scheduler failed to initialize: " + e.toString());
            }
            e.printStackTrace();
        }
    }
	@Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        if(!performShutdown)
            return;
        try
        {
            if(scheduler != null)
                scheduler.shutdown();
        }
        catch(Exception e)
        {
        	if(log.isInfoEnabled())
        	{
        		log.info("quartz定时调度线程信息：Quartz Scheduler failed to shutdown cleanly: " + e.toString());
        	}
        	e.printStackTrace();
        }
    	if(log.isInfoEnabled())
    	{
           log.info("quartz定时调度线程信息：Quartz Scheduler successful shutdown.");
    	}
        System.gc();
    }

    public static final String QUARTZ_FACTORY_KEY = "org.quartz.impl.StdSchedulerFactory.KEY";
    private boolean performShutdown;
    private Scheduler scheduler;
    
}
