package com.yt.webs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.db.DataBaseManager;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
/**
 * 		第一步 解析请求过来的xml文件，获取数据库才查询条件   实际就是开始时间和结束时间
 *		
 *		第二步：根据第一步获取的查询条件，查询数据库
 *		
 *		第三步：讲查询出来的数据按照格式要求，生成txt文件，并且写入到txt文件
 *		
 *		第四步：sftp上传
 */
public class GetCtiDateServer implements IGetCtiDateServer {

	private static final Logger log=Logger.getLogger(GetCtiDateServer.class);
	private static DataBaseManager db=DataBaseManager.getInstance();
	private int rowCount=0;//从数据库中查询出来的数据的总行数
	
	public String getCtiDate(String para) {
		log.info("开始执行。。。。"+getDate());
		try {
			log.info("第一步：获取请求的参  报文并且解析"+para);
			Document doc    = DocumentHelper.parseText(para);
			Element rootElt = doc.getRootElement(); // 获取根节点
			String TranDate = rootElt.elementTextTrim("StartDate");// 请求开始时间
			String TranTime = rootElt.elementTextTrim("EndDate");// 请求结束时间
			log.info("第二步：查询数据库");
			String str = getString_xml(TranDate, TranTime);// 调用该方法获取结果集\
			log.info("str===="+str);
			
			
			log.info("第三步：生成本地TXT文件");
			DateFormat df = new SimpleDateFormat("yyyyMMdd");// 获取当前时间
			String f_name="dhzx"+df.format(new Date())+".txt";
			String f_file_path=db.getHost_directory()+f_name;   //本地文件路径
	
			File filename = new File(f_file_path);
		
			//RandomAccessFile mm = null;
			
			//mm = new RandomAccessFile(filename, "rw");
			//mm.writeBytes(str);// 把结果集写入到txt文件中
			
	
		log.info("1111111111111");
			if (!filename.getParentFile().exists()) {
			filename.getParentFile().mkdirs();
			}
			FileOutputStream out = new FileOutputStream(filename, false);
		
		out.write((str).getBytes("UTF-8"));
			log.info("0000000");   
		out.close();
			//log.info("22222222222");
			log.info("第四步：上传本地txt至sftp");
			
			String host = db.getSftp_host();//主机
			
			int port = Integer.parseInt(db.getSftp_port());//端口
			String username = db.getSftp_user();//用户名
			String password = db.getSftp_password();//密码
			String directory = db.getSftp_directory();// directory 上传的目录
			String uploadFile = f_file_path;// uploadFile 要上传的文件
//			String deleteFile = "E:\\dhzx" + df.format(new Date())+ ".txt";;//要删除的文件
			ChannelSftp sftp=connect(host, port, username, password);//调用该方法登录服务器，
		    if(sftp!=null){
				upload(directory, uploadFile, sftp);
			//delete(directory, uploadFile, sftp);//上传之后删除
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("FileNotFoundException",e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("IOException",e);
		}
	                  	String result_xml="<Body><CountNum>"+rowCount+"</CountNum></Body>";
		log.info("执行结束1。。。。。。"+getDate()+"。。。。。。"+result_xml);
		rowCount =0;
		log.info("执行结束2。。。。。。"+getDate()+"。。。。。。"+result_xml);
		return result_xml;
	}
	/**
	 * 
	 * 根据请求过来的俩个参数连接SQLserver查询出结果集
	 * 
	 * 把结果集拼接成XML文件返回String
	 * 
	 */
	public String getString_xml(String TranDate, String TranTime) {
		// 加载sqlserver数据库连接
		Connection sqlserver_conn = null;
		PreparedStatement sqlserver_inspstmt = null;
		String str_wbs = "";
		try {
			sqlserver_conn=db.getConnection();
			String sql_call = " select  	calltime             "// -- 呼叫时间
					+ "                  ,disconnecttime            "// 挂机时间
					+ "     ,replace(sourcecode,substring(sourcecode,LEN(sourcecode)-7,4),'****') sourcecode  "// 主叫号码
					+ "      ,replace(destcode,substring(destcode,LEN(destcode)-6,4),'****') destcode          "// 被叫号码
					+ "              ,case callresult when '接通' then '1' else '0'  end     callresult         "// 呼叫结果
					+ "           ,ConnectTime                "// 人工通话时长
					+ "          from Calllog                  "
					+ "           where   CallTime>='"+TranDate+"'"
					+ "              and 	CallTime<='"+TranTime+"'"
					+ "             and  TransferCode = 601               ";
				
			sqlserver_inspstmt = sqlserver_conn.prepareStatement(sql_call);
			ResultSet rs = sqlserver_inspstmt.executeQuery();
			StringBuffer sb = new StringBuffer();
			
			while (rs.next()) {
			   
             sb.append(rs.getString("calltime")).append("|");// 呼叫时间
             sb.append(rs.getString("sourcecode")).append("|");// 主叫号码
             sb.append(rs.getString("callresult")).append("|");// 呼叫结果
             sb.append(rs.getString("ConnectTime")).append("|");//人工接通时间         
             sb.append(rs.getString("disconnecttime")).append("|");// 挂机时间
           
             //sb.append(rs.getString("destcode")).append("|");// 被叫号码

      

             sb.append("\r\n");//换行
             rowCount++;
			}
			str_wbs = sb.toString();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("查询数据库异常",e);
		}finally{
				log.info("关闭数据库连接");
				try {
					if(sqlserver_conn!=null){
						sqlserver_conn.close();
					}
					if(sqlserver_inspstmt!=null){
						sqlserver_inspstmt.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return str_wbs;

	}
	public ChannelSftp connect(String host, int port, String username,String password) {
		log.info("开始连接sftp");
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			Session sshSession = jsch.getSession(username, host, port);
			sshSession.setTimeout(30000);
			System.out.println("Session created.");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			System.out.println("Session connected.");
			System.out.println("Opening Channel.");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			
			sftp = (ChannelSftp) channel;
			log.info("连接sftp陈功");
		} catch (Exception e) {
			log.error("连接sftp异常",e);
		}
			return sftp;
	}

		/**
		* 上传文件
		* @param directory 上传的目录
		* @param uploadFile 要上传的文件
		* @param sftp
		*/
		public void upload(String directory, String uploadFile, ChannelSftp sftp) {
			log.info("开始上传txt至sftp");
			try {
			
				sftp.cd(directory);
				File file=new File(uploadFile);
				
				sftp.put(new FileInputStream(file), file.getName());
				log.info("上传txt至sftp成功");
			} catch (Exception e) {
				log.error("上传至sftp异常",e);
				e.printStackTrace();
			}
		}
		/**
		* 删除文件
		* @param directory 要删除文件所在目录
		* @param deleteFile 要删除的文件
		* @param sftp
		*/
		public void delete(String directory, String deleteFile, ChannelSftp sftp) {
		try {
		sftp.cd(directory);
		sftp.rm(deleteFile);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}

		/**
		* 列出目录下的文件
		* @param directory 要列出的目录
		* @param sftp
		* @return
		* @throws SftpException
		*/
		public Vector listFiles(String directory, ChannelSftp sftp) throws SftpException{
		return sftp.ls(directory);
		}
		
		public String getDate(){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// 获取当前时间
			return df.format(new Date());
		}
		
}
