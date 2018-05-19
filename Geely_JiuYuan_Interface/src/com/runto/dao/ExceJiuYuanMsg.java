package com.runto.dao;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runto.db.DataBaseManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class ExceJiuYuanMsg {

	private static final Logger log = Logger.getLogger(ExceJiuYuanMsg.class);  
	private static DataBaseManager dbm = DataBaseManager.getInstance();
	private Connection conn=null;
	private PreparedStatement pstm=null;
	private String result_str="";
	//解析json，并且放到对象中
	public String ExceMsg(String json_str){
		
		String jsonContent = json_str;
		log.info("开始执行");
		log.info("开始解析json");
		log.info("json内容："+json_str);
        JSONObject json;
        MsgBean mb=new MsgBean();
        try {
            json = new JSONObject(jsonContent);
            String request_obj=json.getString("request");
            JSONObject result=new JSONObject(request_obj);
            	 String message_id=result.getString("message_id");
            	 String message_time=result.getString("message_time");
            	 mb.setMessage_id(message_id);
            	 mb.setMessage_time(message_time);
            	 log.info("message_id==="+message_id);
            	 log.info("message_time=="+message_time);
            	 
            	 String user_obj=result.getString("user");
            	 JSONObject json_user= new JSONObject(user_obj);
            	 String name=json_user.getString("name");
            	 String tel=json_user.getString("tel");
            	 String backup_name=json_user.getString("backup_name");
            	 String backup_tel=json_user.getString("backup_tel");
            	 mb.setName(name);
            	 mb.setTel(tel);
            	 mb.setBackup_name(backup_name);
            	 mb.setBackup_tel(backup_tel);
            	 
            	
            	 String vehicle_obj=result.getString("vehicle");
            	 JSONObject json_vehicle=new JSONObject(vehicle_obj);
            	 String plate_num=json_vehicle.getString("plate_num");
            	 String model_name=json_vehicle.getString("model_name");
            	 String color=json_vehicle.getString("color");
            	 String vin=json_vehicle.getString("vin");
            	 mb.setPlate_num(plate_num);
            	 mb.setModel_name(model_name);
            	 mb.setColor(color);
            	 mb.setVin(vin);
            	 
            	 
            	 String position_obj=result.getString("position");
            	 JSONObject json_position=new JSONObject(position_obj);
            	 String location_obj=json_position.getString("location");
            	 JSONObject json_location=new JSONObject(location_obj);
            	 String direction=json_location.getString("direction");
            	 String description=json_location.getString("description");
            	 String longitude=json_location.getString("longitude");
            	 String latitude=json_location.getString("latitude");
            	 mb.setDirection(direction);
            	 mb.setDescription(description);
            	 mb.setLongitude(longitude);
            	 mb.setLatitude(latitude);
            	 
            	 String fasq_msg="";
            	 JSONArray array_previous_location=json_position.getJSONArray("previous_location");
            	 for(int a=0;a<array_previous_location.length();a++){
            		 JSONObject json_previous_location=array_previous_location.getJSONObject(a);
            		 String latitude1=json_previous_location.getString("latitude");
            		 String longitude1=json_previous_location.getString("longitude");
            		 String direction1=json_previous_location.getString("direction");
            		 String speed1=json_previous_location.getString("speed");
            		 int b=a+1;
            		 fasq_msg+="第"+b+"个位置信息：纬度："+latitude1+"，经度："+longitude1+"，航向："+direction1+"，此时速度："+speed1+"；";
            	 }
            	 mb.setFasq_msg(fasq_msg);
            	 
            	 
            	 String event_obj=result.getString("event");
            	 JSONObject json_event=new JSONObject(event_obj);
            	 String time=json_event.getString("time");
            	 String source=json_event.getString("source");
            	 mb.setTime(time);
            	 mb.setSource(source);
            	 
            	 String intensity_obj=json_event.getString("intensity");
            	 JSONObject json_intensity=new JSONObject(intensity_obj);
            	 String x=json_intensity.getString("x");
            	 String y=json_intensity.getString("y");
            	 String z=json_intensity.getString("z");
            	 String intensity="X："+x+"，Y："+y+"，Z："+z+"";
            	 mb.setIntensity(intensity);
            	 
        } catch (JSONException e) {
            log.info("json解析出错");
            result_str="解析json时出错，请检查json格式";
        	e.printStackTrace();
        	log.error(getTrace(e));
        	return result_str;
        }
        log.info("json解析完毕");
        ImpToDb(mb);
        log.info("结束执行");
        result_str="执行成功";
        return result_str;
	}
	
	
	public void ImpToDb(MsgBean mb){
		log.info("开始往数据库中写入");
		conn=dbm.getConnection();
		String insert_sql=" insert into t_jiuyuan_msg " +
				" (tuid, " +
				" message_id, " +
				" message_time, " +
				" name, " +
				" tel, " +
				" backup_name, " +
				" backup_tel, " +
				" plate_num, " +
				" model_name, " +
				" color, " +
				" vin, " +
				" latitude, " +
				" longitude, " +
				" direction, " +
				" description, " +
				" fasq_msg, " +
				" time, " +
				" source, " +
				" intensity) " +
				" values " +
				"(geelyin.seq_default.nextval," +
				" '"+mb.getMessage_id()+"'," +
				" '"+mb.getMessage_time()+"'," +
				" '"+mb.getName()+"'," +
				" '"+mb.getTel()+"'," +
				" '"+mb.getBackup_name()+"'," +
				" '"+mb.getBackup_tel()+"'," +
				" '"+mb.getPlate_num()+"'," +
				" '"+mb.getModel_name()+"'," +
				" '"+mb.getColor()+"'," +
				" '"+mb.getVin()+"'," +
				" '"+mb.getLatitude()+"'," +
				" '"+mb.getLongitude()+"'," +
				" '"+mb.getDirection()+"'," +
				" '"+mb.getDescription()+"'," +
				" '"+mb.getFasq_msg()+"'," +
				" '"+mb.getTime()+"'," +
				" '"+mb.getSource()+"'," +
				" '"+mb.getIntensity()+"'" +
				")";
		try {
			pstm=conn.prepareStatement(insert_sql);
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info("往数据库插入异常");
			result_str="往数据库插入异常,请联系服务端接口管理员";
			e.printStackTrace();
			log.error(getTrace(e));
		}finally{
			log.info("关闭数据库连接");
			try {
				conn.close();
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info("往数据库写入结束");
	}
	
	//将异转成字符串，输出到日志中
	public static String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }
	
	
	//读取json格式的txt文件，用于测试
	 public String readTxtFile(String filePath){
		 String json_string="";
	        try {
	                String encoding="GBK";
	                File file=new File(filePath);
	                if(file.isFile() && file.exists()){ //判断文件是否存在
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//考虑到编码格式
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    while((lineTxt = bufferedReader.readLine()) != null){
	                       // System.out.println(lineTxt);
	                        json_string+=lineTxt;
	                    }
	                    read.close();
	        }else{
	            System.out.println("找不到指定的文件");
	        }
	        } catch (Exception e) {
	            System.out.println("读取文件内容出错");
	            e.printStackTrace();
	        }
	        return json_string;
	 }
	public static void main(String[] args) {
		ExceJiuYuanMsg ey=new ExceJiuYuanMsg();
		String s_json=ey.readTxtFile("E:\\1.TXT");
		ey.ExceMsg(s_json);
	}
}
