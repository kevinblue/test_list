package com.runto.myTimer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.runto.dao.ProcessingInitData;
import com.runto.db.DataBaseManager;

 

public class Timer_Update {
	private static final Logger log = Logger.getLogger(Timer_Update.class);
	private static DataBaseManager dbm = DataBaseManager.getInstance();
	Timer timer;
    public void run_timer(){
    	timer = new Timer();
    	Calendar calendar = Calendar.getInstance();
    	int postpone=Integer.parseInt(dbm.getPostpone());
    	int time=Integer.parseInt(dbm.getTime());
    	int minute=Integer.parseInt(dbm.getMinute());
    	int interval=Integer.parseInt(dbm.getInterval());
    	calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)+postpone, time, minute);  //年、月(月从0开始，0为1月，1为2月....)、日、时、分
    	Date date=calendar.getTime();   //开始执行时间
        timer.schedule(new RemindTask(),date,interval);   //第二个参数，什么时候开始执行，0为立马执行；第三个参数，间隔多长时间执行，单位：毫秒
    }
    
    class RemindTask extends TimerTask{
        public void run(){
        	try{
        		log.info("开始执行插入保养数据，开始时间="+new Date());
        		ProcessingInitData pi = new ProcessingInitData();
        		pi.initData();
        		log.info("结束时间="+new Date());
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
    }
    
    public static void main(String[] args) {
    	Timer_Update tt=new Timer_Update();
    	tt.run_timer();
    	} 
}
