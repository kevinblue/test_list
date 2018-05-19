package com.tenwa.leasing.controller.voucher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tenwa.business.service.BaseService;

import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.vouchersFactory.RentIncomeVoucherService;
import com.tenwa.leasing.utils.DataBaseManager;
import com.tenwa.report.entity.ReportDataSource;
import com.tenwa.report.query.DataSourceFactory;
import com.tenwa.report.query.dialect.AbstractSQLDialect;

@Controller(value = "importInterVoucherInfoController")
@RequestMapping(value = "/**/acl")
public class ImportInterVoucherInfoController {
	private Logger log = Logger
			.getLogger(ImportInterVoucherInfoController.class);
	
	
	private static DataBaseManager dbm = DataBaseManager.getInstance();
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
	private AbstractSQLDialect dialect;

	@Resource(name = "rentIncomeVoucherService")
	private RentIncomeVoucherService rentIncomeVoucherService;

	@RequestMapping(value = "/importVoucherEbs.acl")
	@ResponseBody
	public String importVoucherEbs(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String returnstr = null;
		Connection conn = null;
		PreparedStatement pslvoucher = null;
		PreparedStatement inspstmt = null;
		Statement ps = null;
		ReportDataSource rds;
		Connection connrzzl = null;
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		try {
			// 得到第三方oracle连接
			conn = dbm.getConnection();
			//conn.setAutoCommit(false);
			rds = new ReportDataSource();
			//获取oracle连接
			connrzzl = DataSourceFactory.getConnection(rds);
			conn.setAutoCommit(false);
			
			StringBuffer sbkey = new StringBuffer("insert into inter_voucher_info (");// 拼接表的字段
			// 查询所有列
			String sqltablecolumns = "SELECT c.COLUMN_NAME as columnname FROM USER_TAB_COLUMNS c  where c.TABLE_NAME='INTER_VOUCHER_INFO' order by c.COLUMN_ID asc ";
			pslvoucher = conn.prepareStatement(sqltablecolumns);
			ResultSet rs = pslvoucher.executeQuery();
			
			int rscount=rs.getConcurrency();
			List list=new ArrayList();
			while (rs.next()) {
				//添加所有列
				sbkey.append(rs.getString("columnname")).append(",");
				list.add(rs.getString("columnname"));
			}
			sbkey.deleteCharAt(sbkey.length()-1);
			
			String insertsql = sbkey.toString()
					+ ") values (seq_default.nextval,'凭证编号','Eleasing','eg.180000000','eg.国药控股（中国）融资租赁有限公司'";
		    inspstmt.addBatch(insertsql);//批量添加
		    
			String rowdatastring = model.get("rowdatastring");// 获取数组对象
			JSONArray jsonArray = new JSONArray(rowdatastring);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonobject = jsonArray.getJSONObject(i);
				String vouchernumber = jsonobject.getString("vouchernumber");
				String updateV8 = "update  VOUCHER_V8  v set v.V8_Memo='已传输EBS成功',v.V8_FLAG=1 where v.F3='"+vouchernumber+"'";
                ps.addBatch(updateV8);
                log.info(updateV8);
			}
			inspstmt.executeBatch();
			ps.executeBatch();
			conn.commit();
       
		} catch (Exception e) {
			returnstr = "执行插入语句报错";
			log.info("执行插入语句报错!" + e);
		}finally {
			if (connrzzl != null) {
				try {
					connrzzl.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					connrzzl = null;
				}
			}

		}
		return returnstr;
       


	}

}
