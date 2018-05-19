package com.runto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.runto.db.DataBaseManager;

public class ProcessingInitData {

	private static final Logger log = Logger
			.getLogger(ProcessingInitData.class);
	private static DataBaseManager dbm = DataBaseManager.getInstance();
	// oracle 连接
	Connection conn = null;
	PreparedStatement inspstmt = null;
	PreparedStatement select_pr = null;

	PreparedStatement insert_log_pr = null;

	// cti连接
	Connection sqlserver_conn = null;
	PreparedStatement sqlserver_inspstmt = null;

	public void initData() {

		try {

			conn = dbm.getConnection();

			// 加载sqlserver数据库连接
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			sqlserver_conn = DriverManager
					.getConnection(
							"jdbc:sqlserver://192.168.26.10:1433;DatabaseName=sis_zt",
							"crm", "crm2015");
			conn.setAutoCommit(false);

			// 自动获取昨天的保养信息
			getCalls();

		} catch (ClassNotFoundException ce) {
			log.info("----加载数据库驱动程序失败----:", ce);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {

			if (conn != null) {
				try {
					conn.close();
					log.info("关闭数据库连接");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 自动获取昨天的保养数据
	 */
	public void getCalls() {
		log.info("------自动获取昨天的保养数据-------");
		String sql_call = " select s.服务站号 cust_fwzh                     "
				+ "       ,s.服务站名称 cust_fwzmc                  "
				+ "       ,s.省份  cust_shengfen                  "
				+ "       ,s.车架号   cust_dph                       "
				+ "       ,s.上传日期 cust_shangchuanriqi           "
				+ "       ,s.保养日期 cust_byrq                     "
				+ "       ,s.车型  cust_chexing                      "
				+ "       ,s.车系  cust_cx                           "
				+ "       ,s.行驶里程 cust_xslc                      "
				+ "       ,s.保养类别 cust_bylb                      "
				+ "       ,s.用户名 cust_yhmc                        "
				+ "       ,s.联系方式  cust_yhdh                      "
				+ "    from dbo.v_cc_byd s                                                  "
				+ "    where  s.上传日期 =CONVERT(VARCHAR(10),DATEADD(day,-1,GETDATE()),120) "
                + "  and  (s.车系 like '%Z700%' or s.车系 like '%Z300%' or s.车系 like '%T600%' or  s.车系 like '%T200%' or s.车系 like '%V10%'  or  s.车系 like '%Z700%' or s.车系 like '%众泰2008%' or s.车系 like '%众泰5008%') ";

		try {
			// 连接sqlserve查询
			sqlserver_inspstmt = sqlserver_conn.prepareStatement(sql_call);

			ResultSet rs = sqlserver_inspstmt.executeQuery();

			String sql_default = "select seq_default.nextval as default_id from dual";
			select_pr = conn.prepareStatement(sql_default);
			ResultSet rs_select = select_pr.executeQuery();
			String default_id = "";
			while (rs_select.next()) {
				default_id = rs_select.getString("default_id");

			}

			log.info("获取系列:=========" + default_id);

			String insert_sql = "insert into t_mid_byhf(tuid,create_date,cust_fwzh,cust_fwzmc,"
					+ "cust_dph,cust_shangchuanriqi,cust_byrq,cust_chexing,"
					+ "cust_cx,cust_xslc,cust_bylb,cust_yhmc,cust_yhdh,cust_shengfen,mid_id)values(seq_default.nextval,sysdate,?,?,?,?,?,?,?,?,?,?,?,?,"
					+ default_id + ")";
			// 连接oracle插入
			inspstmt = conn.prepareStatement(insert_sql);
			// 插入条数
			int i = 0;
			// 批量提交条数限制
			int j = 0;
			List list = new ArrayList();
			while (rs.next()) {
				if(list.contains(rs.getString("cust_dph"))){
					continue;
				}else{
					list.add(rs.getString("cust_dph"));
				}
				inspstmt.setString(1, rs.getString("cust_fwzh"));
				inspstmt.setString(2, rs.getString("cust_fwzmc"));
				inspstmt.setString(3, rs.getString("cust_dph"));
				inspstmt.setString(4, rs.getString("cust_shangchuanriqi"));
				inspstmt.setString(5, rs.getString("cust_byrq"));
				inspstmt.setString(6, rs.getString("cust_chexing"));
				inspstmt.setString(7, rs.getString("cust_cx"));
				inspstmt.setString(8, rs.getString("cust_xslc"));
				inspstmt.setString(9, rs.getString("cust_bylb"));
				inspstmt.setString(10, rs.getString("cust_yhmc"));
				inspstmt.setString(11, rs.getString("cust_yhdh"));
				inspstmt.setString(12, rs.getString("cust_shengfen"));
				inspstmt.addBatch();
				j++;
				// 每500条执行一次
				if (j >= 500) {
					inspstmt.executeBatch();
					j = 0;
				}
				i++;
			}
			String insert_log_sql = "insert into t_interface_log t (t.tuid,t.subject_id,t.create_date,t.number_sum,t.is_synchronous,t.succeed_num,t.notsucceed_num)values("
					+ default_id + ",'4119385',sysdate," + i + ",0,0,"+i+")";
			insert_log_pr = conn.prepareStatement(insert_log_sql);
			insert_log_pr.execute();
			inspstmt.executeBatch();
			conn.commit();

			log.info("获取昨天的保养" + i + "条");
		} catch (SQLException e) {
			log.info("获取昨天的保养错误");
			e.printStackTrace();
		} finally {
			if (sqlserver_conn != null) {
				try {
					sqlserver_conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					sqlserver_conn = null;
				}
			}

		}
	}

	public static void main(String[] args) {

	}
}
