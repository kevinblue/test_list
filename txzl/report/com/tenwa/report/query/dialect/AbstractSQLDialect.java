package com.tenwa.report.query.dialect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.report.enums.ColumnDataType;

public abstract class AbstractSQLDialect {
	private static final Logger log = LoggerFactory.getLogger(AbstractSQLDialect.class);
	protected final static Pattern pattern_parameter = Pattern.compile("\\{.*?\\}");
	private static List<String> unavailableDDL = new ArrayList<String>();
	private BidiMap keywordMap = new DualHashBidiMap();

	protected Connection conn = null;

	static {
		unavailableDDL.add("UPDATE");
		unavailableDDL.add("DELETE");
		unavailableDDL.add("DROP");
		unavailableDDL.add("INSERT");
	}

	public AbstractSQLDialect() {
		registerSQLKeyword();
	}

	public AbstractSQLDialect(Connection conn) {
		this.conn = conn;
		registerSQLKeyword();
	}

	protected abstract String buildPagingQuery(String sql, int start, int limit);
	
	protected abstract List<String> appendOrder(String sql);

	public abstract ResultSet executeProcdure(String proc);

	/**
	 * 
	 * @param sql
	 * @param start
	 * @param limit
	 *            -1时，执行总数查询语句
	 * @return
	 * @throws BusinessException 
	 * @throws SQLException 
	 * @throws Exception
	 */
	public ResultSet executeSQL(String sql, int start, int limit,boolean ... isCount) throws Exception {
		if (this.conn == null)
			throw new BusinessException(WebUtil.getMessage("report.exception.connection.null"));
		// 判断SQL是否合法
		if (!validateLegalSql(sql))
			throw new BusinessException(WebUtil.getMessage("report.exception.sql.invalid"));

		// 分页参数
		if (limit > 0) {
			sql = buildPagingQuery(sql, start, limit);
			log.debug("paging SQL:{}", sql);
		} else {
			List<String> returnSql =  appendOrder(sql);
			if(null != isCount  && 0 < isCount.length  && isCount[0]){
				sql = returnSql.get(0) + returnSql.get(1) ;
			}else{
				sql = returnSql.get(0) + returnSql.get(1) +  returnSql.get(2);
			}
		}
		Statement stmt = conn.createStatement();
		// sql = "select * from report_test where id=1 order by data1";
		ResultSet rs = stmt.executeQuery(sql);

		return rs;
	}
	


	public String to_date(String t, boolean hasApostrophe) {
		String apostrophe = "'";
		if (!hasApostrophe)
			apostrophe = "";
		return apostrophe + t + apostrophe;
	}

	/**
	 * 由子类实现，并注册SQL关键字
	 */
	protected abstract void registerSQLKeyword();

	protected void registerSQLKeyword(String sqlKeyword, String replacedName) {
		this.keywordMap.put(sqlKeyword.toUpperCase(), replacedName.toUpperCase());
	}

	public BidiMap getReservedKeyword() {
		return this.keywordMap;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	private boolean validateLegalSql(String sql) {

		for (String ddlKey : unavailableDDL) {
			Pattern p = Pattern.compile("(;{1}|\\b)" + ddlKey + "\\b", Pattern.CASE_INSENSITIVE);
			if (p.matcher(sql).find())
				return false;
		}
		return true;
	}

	public ColumnDataType getColumnDbType(int columnType) {
		switch (columnType) {
		case Types.DATE:
		case Types.TIME:
		case Types.TIMESTAMP:
			return ColumnDataType.DATE;

		case Types.DECIMAL:
		case Types.DOUBLE:
		case Types.INTEGER:
		case Types.BIGINT:
		case Types.FLOAT:
		case Types.NUMERIC:
		case Types.REAL:
		case Types.SMALLINT:
			return ColumnDataType.NUMBER;

		default:
			return ColumnDataType.STRING;
		}
	}

	public void closeConnection() {
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				log.warn("",e);
			}finally{
				conn = null;
			}
		}
		conn = null;
	}
}
