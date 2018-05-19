package com.tenwa.report.query.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.NamingException;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.lang3.tuple.MutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.ClassUtil;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.EhcacheUtil;
import com.tenwa.kernal.utils.FilterTextManipulator;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.report.entity.ReportColumn;
import com.tenwa.report.entity.ReportControl;
import com.tenwa.report.entity.ReportDataSource;
import com.tenwa.report.entity.ReportFilter;
import com.tenwa.report.entity.ReportTable;
import com.tenwa.report.enums.FilterType;
import com.tenwa.report.enums.HtmlType;
import com.tenwa.report.enums.QueryType;
import com.tenwa.report.query.AbstractQueryService;
import com.tenwa.report.query.CellData;
import com.tenwa.report.query.DataSourceFactory;
import com.tenwa.report.query.QueryRestriction;
import com.tenwa.report.query.QueryService;
import com.tenwa.report.query.dialect.AbstractSQLDialect;
import com.tenwa.report.query.dialect.SQLServer2008Dialect;

import edu.emory.mathcs.backport.java.util.Arrays;

public class SQLQueryServiceImpl extends AbstractQueryService implements QueryService {
	private static final Logger log = LoggerFactory.getLogger(SQLQueryServiceImpl.class);

	private AbstractSQLDialect dialect;
	

	public SQLQueryServiceImpl() {
	}

	@Override
	public void initQuery(String contentId, ReportDataSource rds, String sql, QueryType queryType, Set<ReportColumn> columns, User user) {
		super.initQuery(contentId, rds, sql, queryType, columns, user);
		try {
			dialect = ClassUtil.newInstance(this.reportDataSource.getDialect(), null);
		} catch (Exception e) {
			log.error("", e);
		}
	}

	@Override
	public List<Map<String, CellData>> getTableData(List<MutablePair<ReportFilter, String>> params, Integer start, Integer limit,ReportTable ... table)
			throws Exception {
		validateEnvironment();
		List<Map<String, CellData>> datas = new ArrayList<Map<String, CellData>>();

		// params = combineSearchAndFilter(params);

		switch (queryType) {
		case SIMPLESQL:
		case COMPLEXSQL:
			datas = getSQLQueryData(params, start, limit,table);
			break;
		case PROCEDURE:
			break;
		default:
			return null;
		}

		return datas;
	}

	/**
	 * 合并预查询和过滤的同一字段值,以过滤值替换预查询中同一字段名值
	 * 
	 * @param params
	 * @return
	 */
	private List<MutablePair<ReportFilter, String>> combineSearchAndFilter(List<MutablePair<ReportFilter, String>> params) {
		List<MutablePair<ReportFilter, String>> newParams = new ArrayList<MutablePair<ReportFilter, String>>();
		List<String> filterNames = new ArrayList<String>();
		// 先将所有的Filter取出，并将名字存储在filterNames中
		for (MutablePair<ReportFilter, String> m : params) {
			ReportFilter f = m.getLeft();
			if (f.getFilterType() == FilterType.FILTER) {
				newParams.add(m);
				filterNames.add(f.getName());
			}
		}
		for (MutablePair<ReportFilter, String> m : params) {
			ReportFilter f = m.getLeft();
			if (f.getFilterType() == FilterType.SEARCH && !filterNames.contains(f.getName())) {
				newParams.add(m);
			}
		}
		return newParams;
	}

	@SuppressWarnings("unchecked")
	private List<Map<String, CellData>> getSQLQueryData(List<MutablePair<ReportFilter, String>> params, Integer start, Integer limit,ReportTable ... table)
			throws Exception {

		List<Map<String, CellData>> datas = null;
		switch (queryType) {
		case SIMPLESQL:
		case COMPLEXSQL:
			String sql = this.getSQL();
			sql = this.getParmeteizedSQL(params, sql,table);
			if(log.isInfoEnabled()){
				log.info("查询sql为：----->>>"+sql);
			}
			datas = getQueryData(sql, start, limit);

			if (!isRestriction(QueryRestriction.NO_SUM)) {
				List<Map<String, CellData>> totals = null;
				List<Map<String, CellData>> subTotals = null;
				String cachePrefix = this.contentId + "#" + this.user.getId() + "#";
				if (isQueryStart(start)) {
					totals = getTotal(params,table);
					subTotals = getSubTotal(params,table);
					EhcacheUtil.setCachedValue(cacheName, cachePrefix + "_total", (Serializable) totals);
					EhcacheUtil.setCachedValue(cacheName, cachePrefix + "_subtotal", (Serializable) subTotals);
				} else {
					totals = (List<Map<String, CellData>>) EhcacheUtil.getCachedValue("reportCache", cachePrefix + "_total");
					subTotals = (List<Map<String, CellData>>) EhcacheUtil.getCachedValue(cacheName, cachePrefix + "_subtotal");
					if (totals == null)
						totals = getTotal(params,table);
					if (subTotals == null)
						subTotals = getSubTotal(params,table);
				}

				if (subTotals != null) {
					limit++; // 多取一条数据，用于比较是否在最后一行插入小计
				}

				if (subTotals != null) {
					limit--;
					datas = mergeSubTotal(datas, subTotals, limit);
				}

				datas = mergeTotal(datas, totals);
			}
			break;
		case PROCEDURE:
			datas = null;
			break;
		default:
			break;
		}

		return datas;
	}

	public List<Map<String, CellData>> getQueryData(String sql, Integer start, Integer limit) throws Exception {
		try {
			this.dialect.setConnection(this.getConnection());
			Map msql=new HashMap<String,String>();
			msql.put("sql", sql);
			msql.put("param", new ArrayList());
			msql.put("type", new ArrayList());
			return this.packageResult(this.dialect.executeSQL(msql, start, limit), start, null);
		} finally {
			this.dialect.closeConnection();
		}
	}

	private List<Map<String, CellData>> mergeTotal(List<Map<String, CellData>> datas, List<Map<String, CellData>> totals) {
		if (totals == null || totals.size() == 0)
			return datas;
		datas.add(totals.get(0));
		return datas;
	}

	private List<Map<String, CellData>> mergeSubTotal(List<Map<String, CellData>> datas, List<Map<String, CellData>> subTotals, int limit) {
		if (subTotals == null || subTotals.size() == 0)
			return datas;
		List<String> baseKey = new ArrayList<String>();
		for (ReportColumn c : columns) {
			if (c.getIsGroupby())
				baseKey.add(c.getName());
		}
		List<String> indexs = groupIndexs(datas, baseKey);

		List<Map<String, CellData>> megeredDatas = new ArrayList<Map<String, CellData>>();
		for (String index : indexs) {
			String[] t = index.split("\\|");
			int start = Integer.parseInt(t[0]);
			int end = Integer.parseInt(t[1]);
			boolean addSubTotal = true;
			if (end == -1) {
				if ((datas.size() - 1) % limit == 0 && (datas.size() != 1)) {
					end = datas.size() - 1; // 去除多取的一条数据，并在最后一行不加入小计
					addSubTotal = false;
				} else {
					end = datas.size();
				}
			}

			megeredDatas.addAll(datas.subList(start, end));
			if (addSubTotal) {
				for (Map<String, CellData> subTotalMap : subTotals) {
					if (isEqualMap(getValueMap(datas.get(start), baseKey), getValueMap(subTotalMap, baseKey))) {
						megeredDatas.add(subTotalMap);
						break;
					}
				}
			}
		}
		return megeredDatas;
	}

	private List<String> groupIndexs(List<Map<String, CellData>> datas, List<String> baseKey) {
		List<String> groupIndex = new ArrayList<String>(); // Pattern: startIndex | endIndex
		int start = 0;
		int foundIndex = 0;
		while (foundIndex > -1 && foundIndex < datas.size()) {
			foundIndex = searchLastIndex(datas, foundIndex, baseKey);
			groupIndex.add(String.valueOf(start) + "|" + String.valueOf(foundIndex));
			if (foundIndex == -1)
				break;

			start = (foundIndex++);
		}
		return groupIndex;
	}

	/**
	 * 二分查询，结果集必须有序
	 * 
	 * @param datas
	 * @param start
	 * @param baseKey
	 * @return
	 */
	private int searchLastIndex(List<Map<String, CellData>> datas, int start, List<String> baseKey) {
		if (start >= datas.size())
			return -1;
		int end = datas.size() - 1;
		if (isEqualMap(getValueMap(datas.get(start), baseKey), getValueMap(datas.get(end), baseKey)))
			return -1;

		Map<String, CellData> baseValueMap = getValueMap(datas.get(start), baseKey);
		int mid = (start + end + 1) >>> 1;
		while (start < end && end - start > 1) {

			if (isEqualMap(getValueMap(datas.get(mid), baseKey), baseValueMap)) {
				start = mid;
			} else {
				end = mid;
			}

			mid = (start + end + 1) / 2;
		}

		return mid;
	}

	private <T1, T2> Map<T1, T2> getValueMap(Map<T1, T2> srcMap, List<T1> baseKey) {
		Map<T1, T2> targetMap = new HashMap<T1, T2>();
		for (T1 key : baseKey) {
			if (!srcMap.containsKey(key))
				return null;
			targetMap.put(key, srcMap.get(key));
		}
		return targetMap;
	}

	private <T1, T2> boolean isEqualMap(Map<T1, T2> map1, Map<T1, T2> map2) {
		if (map1 == null || map2 == null)
			return true;
		if (map1.keySet().size() != map2.keySet().size())
			return false;

		for (T1 key : map1.keySet()) {
			if (map1.get(key) == null && map2.get(key) == null)
				continue;
			if (map1.get(key) == null || map2.get(key) == null)
				return false;

			if (!map1.get(key).equals(map2.get(key)))
				return false;
		}
		return true;
	}

	public List<Map<String, CellData>> getTotal(List<MutablePair<ReportFilter, String>> params,ReportTable ...table) throws Exception {
		try {
			switch (queryType) {
			case SIMPLESQL:
			case COMPLEXSQL: {
				String sql = getTotalSql(params, false,table);
				if (sql == null) {
					return null;
				}
				this.dialect.setConnection(getConnection());
				log.debug("Total SQL:{}", sql);
				Map msql=new HashMap<String,String>();
				msql.put("sql", sql);
				msql.put("param", new ArrayList());
				msql.put("type", new ArrayList());
				return this.packageResult(this.dialect.executeSQL(msql, 0, 0), 0, "总计");

			}
			default:
				return null;
			}
		} finally {
			log.debug("finally");
			this.dialect.closeConnection();
		}
	}

	private String getTotalSql(List<MutablePair<ReportFilter, String>> params, boolean isSubTotal,ReportTable ...table) throws Exception {
		switch (queryType) {
		case SIMPLESQL:
		case COMPLEXSQL: {
			boolean hasTotal = false;
			boolean hasGroupby = false;
			StringBuffer sql = new StringBuffer();
			StringBuffer groupby = new StringBuffer();
			sql.append("SELECT ");
			groupby.append("");
			int count = 0;
			for (ReportColumn c : columns) {

				if (c.getIsCountTotal() && !isSubTotal) {
					sql.append("SUM(").append(c.getName()).append(") AS ").append(c.getName());
					hasTotal = true;
				} else if (c.getIsCountSubTotal() && isSubTotal) {
					sql.append("SUM(").append(c.getName()).append(") AS ").append(c.getName());
					hasTotal = true;

				} else if (c.getIsGroupby() && isSubTotal) {
					sql.append(c.getName()).append(" AS ").append(c.getName());
					hasGroupby = true;
				} else {
					sql.append("'-' AS ").append(c.getName());
				}
				if (count < columns.size() - 1) {
					sql.append(",");
				}
				if (c.getIsGroupby() && isSubTotal) {
					groupby.append(c.getName()).append(",");
				}
				count++;
			}

			if (!hasTotal)
				return null;

			if (!hasGroupby && isSubTotal)
				return null;

			sql.append(" FROM (").append(getParmeteizedSQL(params, this.getSQL(),table)).append(")tt");
			if (isSubTotal) {
				String temp = groupby.toString().substring(0, groupby.length() - 1);
				sql.append(" GROUP BY ").append(temp);
			}
			if(log.isInfoEnabled()){
				log.info(sql.toString());
			}
			return sql.toString();
		}
		default:
			return null;
		}
	}

	public List<Map<String, CellData>> getSubTotal(List<MutablePair<ReportFilter, String>> params,ReportTable ... table) throws Exception {
		try {
			switch (queryType) {
			case SIMPLESQL:
			case COMPLEXSQL: {
				String sql = this.getTotalSql(params, true,table);
				if (sql == null)
					return null;
				this.dialect.setConnection(this.getConnection());
				Map msql=new HashMap<String,String>();
				msql.put("sql", sql);
				msql.put("param", new ArrayList());
				msql.put("type", new ArrayList());
				return this.packageResult(this.dialect.executeSQL(msql, 0, 0), 0, "小计");
			}
			default:
				break;
			}
		} finally {
			this.dialect.closeConnection();
		}
		return null;
	}

	private String getParmeteizedSQL(List<MutablePair<ReportFilter, String>> params, String sql,ReportTable ... table) throws Exception {
		params = combineSearchAndFilter(params);
		MutablePair<String, List<Integer>> temp = replaceSQLParams(params, sql);
		sql = temp.getLeft();
		List<Integer> needRemoveParamIndex = temp.getRight();
		//for (Integer index : needRemoveParamIndex) {
		//	params.remove(index.intValue());
		//}
		//modify by: lichaojie
		
		/*
		 * add by zhangc
		 * 重复导致数组越界，去重
		 */
		HashSet<Integer> set  = new HashSet<Integer>(needRemoveParamIndex);
		needRemoveParamIndex.clear();
		needRemoveParamIndex.addAll(set);
		
		Collections.sort(needRemoveParamIndex);
		for(int i = needRemoveParamIndex.size()-1;i >=0 ;i --){
			params.remove(needRemoveParamIndex.get(i).intValue());
		}

		sql = this.buildWhere(params, sql);
		sql = this.addExternOrder(sql);
		sql = this.replaceReservedKeyword(sql);
		if(table != null && table.length > 0 && null != table[0].getControls() && 0< table[0].getControls().size()){
			sql =  this.addControls(sql, table[0].getControls());
		}
		return sql;
	}
	
	private String addControls(String sql ,Set<ReportControl> controls) throws Exception {
		//TODO
		sql = "select * from ("+sql+") ";
		//先判断当前用户所属的群组
		User currentUser = SecurityUtil.getPrincipal();
		/*Set<UserGroup> userGroups =  currentUser.getUserGroups();
		List<Group> groups = new ArrayList<Group>();
				
		for(UserGroup usergroup : userGroups){
			groups.add(usergroup.getGroup());
		}*/
		String currentUserGroupSql = "select g.id_ id from  t_groups g  "
				+ " left join T_USERS_GROUPS ug on ug.group_id_ = g.id_ "
				+ " left join  t_users u on u.id_ = ug.user_id_ where u.id_ = ?";
		TableService tableService =  (TableService)WebUtil.getApplicationContext().getBean("tableService");
		List<Map<String, Object>> results =  tableService.queryListBySql(currentUserGroupSql, currentUser.getId());
		
		int count = 0;
		if(null != results && 0 < results.size() ){
			for(ReportControl control : controls){
				String matchSql = control.getMatch().getMathchSQL();
				Matcher m = null;
				while ((m = parameter_pattern.matcher(matchSql)).find()) {
					matchSql = m.replaceFirst(currentUser.getId());
				}
				for(int i = 0 ; i< results.size() ; i++){
					String groupId = (String)results.get(i).get("id");
					if(control.getGroupId().equals(groupId)){
						if(count == 0 ){
							sql +=" where " +control.getLabel() + " = ("+matchSql+")";
						}else{
							sql +=" and " +control.getLabel() + " = ("+matchSql+")";
						}
						count ++;
					}else{
						continue;
					}
				}
			}
		}
		return sql;
	}
	
	private String addExternOrder(String sql) {
		if (this.externOrder == null || "".equals(this.externOrder))
			return sql;

		return "SELECT * FROM (" + sql + ") ORDER BY " + this.externOrder;
	}

	private String getFieldFunction(ReportFilter f) {
		String fieldFunction = f.getName();
		switch (f.getHtmlType()) {
		case TEXT:
		case COMBOBOX:
		case NUMBERRANGE:
		default:
			break;
		case DATE:
		case DATERANGE:
			switch (f.getDbType()) {
			case STRING:
				fieldFunction = dialect.to_date(f.getName(), false);
				break;
			case DATE:
			default:
				fieldFunction = f.getName();
			}
			break;
		}
		return fieldFunction;
	}

	private String buildWhere(List<MutablePair<ReportFilter, String>> params, String sql) throws Exception {
		if (params == null || params.size() == 0)
			return sql;
		
		Map<String, String> conditions = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		for (MutablePair<ReportFilter, String> pair : params) {
			ReportFilter f = pair.getLeft();
			// 去除权限信息
			if (f.getName().startsWith("_TENWA_"))
				continue;
			// 根据类型添加参数
			switch (f.getHtmlType()) {
			case TEXT:
				conditions.put(f.getName(), getFieldFunction(f) + " LIKE '%" + pair.getRight() + "%'");
				break;
			case COMBOBOX:
				// TODO: add like condition
				conditions.put(f.getName(), getFieldFunction(f) + "='" + pair.getRight() + "'");
				break;
			case DATE:
				String t = sdf.format(sdf.parse(pair.getRight()));

				conditions.put(
						f.getName(),
						getFieldFunction(f) + " BETWEEN " + dialect.to_date(t + " 00:00:00", true) + " AND "
								+ dialect.to_date(t + " 23:59:59", true));
				break;
			case DATERANGE:
				String[] times = pair.getRight().split("\\|");
				String t1 = "";
				if(times[0]!= null  && times[0].length() >0){
					 t1 = sdf.format(sdf.parse(times[0]));
				}else {
					t1 = "";
				}
				if(times.length > 1){
					String t2 = sdf.format(sdf.parse(times[1]));
					if(t1.equals("")){
						conditions.put(
								f.getName(),
								getFieldFunction(f) + " <= " + dialect.to_date(t2 + " 23:59:59", true) );
					}else{
						conditions.put(
								f.getName(),
								getFieldFunction(f) + " BETWEEN " + dialect.to_date(t1 + " 00:00:00", true) + " AND "
										+ dialect.to_date(t2 + " 23:59:59", true));
					}
				}else{
					conditions.put(
							f.getName(),
							getFieldFunction(f) + " >= " + dialect.to_date(t1 + " 00:00:00", true) );
				}
				break;
			case NUMBERRANGE:
				String[] numbers = pair.getRight().split("\\|");
				String s = "";
				if(numbers.length >1){
					if(numbers[0]!=null && numbers[0].length()>0){
						s = String.format(" %s >= %s AND %s <= %s", getFieldFunction(f), numbers[0], getFieldFunction(f), numbers[1]);
					}else{
						s = String.format(" %s <= %s ", getFieldFunction(f), numbers[1]);
					}
				}else{
					 s = String.format(" %s >= %s ", getFieldFunction(f), numbers[0]);
				}
				conditions.put(f.getName(), s);
				break;
			default:
				break;

			}
		}
		StringBuffer sb = new StringBuffer();
		for (String key : conditions.keySet()) {
			// TODO: 根据Table的总表达式更改逻辑连接
			sb.append(" AND ").append("(").append(conditions.get(key)).append(")");
		}

		switch (queryType) {
		case SIMPLESQL: {
			int wherePos = sql.toUpperCase().lastIndexOf(" WHERE ");
			int orderbyPos = sql.toUpperCase().lastIndexOf(" ORDER BY ");
			int groupbyPos = sql.toUpperCase().lastIndexOf(" GROUP BY ");
			int havingPos = sql.toUpperCase().lastIndexOf(" HAVING ");

			int insertPos = getInsertPos(groupbyPos, orderbyPos, havingPos);

			if (wherePos > -1) {
				String newWhere = " WHERE 1=1 " + sb.toString() + " AND ";
				sql = sql.substring(0, wherePos) + newWhere + sql.substring(wherePos + 7, sql.length());
			} else if (insertPos > -1) {
				String newWhere = " WHERE 1=1 " + sb.toString();
				sql = sql.substring(0, insertPos) + newWhere + sql.substring(insertPos, sql.length());
			} else if (wherePos < 0 && orderbyPos < 0) {
				sql = sql + " WHERE 1=1 " + sb.toString();
			}
		}
			break;
		case COMPLEXSQL:
			sql = "SELECT * FROM (" + sql + ")tt WHERE 1=1 " + sb.toString();
			break;
		default:
			break;
		}
		return sql;
	}

	private int getInsertPos(int... pos) {

		Arrays.sort(pos);
		for (int p : pos) {
			if (p > -1)
				return p;
		}
		return -1;
	}

	private MutablePair<String, List<Integer>> replaceSQLParams(List<MutablePair<ReportFilter, String>> params, String sql)
			throws BusinessException {
		MutablePair<String, List<Integer>> result = new MutablePair<String, List<Integer>>();

		if (params == null || params.size() == 0)
			return result;
		Matcher m = null;
		List<Integer> needRemoveIndex = new ArrayList<Integer>();
		//先将非必填的参数进行动态填充
		Map<String, String> models = new HashMap<String, String>();
		for(MutablePair<ReportFilter, String> param : params){
			models.put(param.getLeft().getName(), param.getRight());
		}
		FilterTextManipulator man = new FilterTextManipulator();
		sql = man.manipulate(new StringBuffer(sql), models).toString();
		while ((m = parameter_pattern.matcher(sql)).find()) {
			String paramName = sql.substring(m.start() + 1, m.end() - 1);
			boolean hasTheParam = false;
			for (int i = 0; i < params.size(); i++) {
				MutablePair<ReportFilter, String> pair = params.get(i);
				ReportFilter rf = pair.getLeft();
				if(rf.getHtmlType().equals(HtmlType.DATERANGE) || rf.getHtmlType().equals(HtmlType.NUMBERRANGE)){
					String []values = pair.getRight().split("\\|");
					String startValue = "";
					String endValue = "";
					if(rf.getHtmlType().equals(HtmlType.DATERANGE)){
						Date date  = new Date();
						Calendar c = Calendar.getInstance();
						c.setTime(date);
						c.add(Calendar.YEAR, -500);
						startValue =  DateUtil.getSystemTimeByFormat(c.getTime(), "yyyy-MM-dd"); 
						c.add(Calendar.YEAR, 1000);
						endValue = DateUtil.getSystemTimeByFormat(c.getTime(), "yyyy-MM-dd"); 
						if(values.length ==2){
							startValue = values[0]!=null && values[0].length()>0 ? values[0] : startValue;
							endValue = values[1]!=null && values[1].length()>0 ? values[1] : endValue;
						}
						if(values.length ==1){
							startValue = values[0]!=null && values[0].length()>0 ? values[0] : startValue;
						}
					}else{
						startValue = Integer.MIN_VALUE +"";
						endValue = Integer.MAX_VALUE +"";
						if(values.length ==2){
							startValue = values[0]!=null && values[0].length()>0 ? values[0] : startValue;
							endValue = values[1]!=null && values[1].length()>0 ? values[1] : endValue;
						}
						if(values.length ==1){
							startValue = values[0]!=null && values[0].length()>0 ? values[0] : startValue;
						}
					}
					Boolean isRemove = false;
					if((rf.getName()+"_start").equalsIgnoreCase(paramName)){
						sql = m.replaceFirst(startValue);
						hasTheParam = true;
						needRemoveIndex.add(i);
						isRemove = true;
					}
					if((rf.getName()+"_end").equalsIgnoreCase(paramName)){
						sql = m.replaceFirst(endValue);
						if(!isRemove){
							hasTheParam = true;
							needRemoveIndex.add(i);
						}
					}
				}else{
					if (rf.getName().equalsIgnoreCase(paramName)) {
						// TODO: 表达式需要加入判断
						sql = m.replaceFirst(pair.getRight());
						hasTheParam = true;
						needRemoveIndex.add(i);
						break;
					}
				}
			}
			if (!hasTheParam) {
				throw new BusinessException(WebUtil.getMessage("report.exception.param.notfound", paramName));
			}
		}

		result.setLeft(sql);
		result.setRight(needRemoveIndex);
		return result;
	}

	@Override
	public List<Map<String, String>> getTableMetadata(List<MutablePair<String, String>> params) throws Exception {
		validateEnvironment();
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();

		try {
			this.dialect.setConnection(this.getConnection());
			switch (queryType) {
			case SIMPLESQL:
			case COMPLEXSQL: {
				String sql;
                  
				Map msql = this.replaceSQLParams(params);

				ResultSet rs = dialect.executeSQL(msql, 0, 1);
				ResultSetMetaData rsmd = rs.getMetaData();
				int startIndex = 1;
				int endIndex = rsmd.getColumnCount();
				if(dialect instanceof SQLServer2008Dialect){
					startIndex= 2;
					endIndex = endIndex +1;
				}
				for (int i = startIndex; i < endIndex; i++) {
					Map<String, String> c = new HashMap<String, String>();
					c.put("columnLabel", rsmd.getColumnLabel(i));
					c.put("columnDbType", this.dialect.getColumnDbType(rsmd.getColumnType(i)).name());
					c.put("columnRealName", rsmd.getColumnName(i));
					result.add(c);
				}
			}
			case PROCEDURE:
			default:

			}
		} finally {
			this.dialect.closeConnection();
		}

		return result;
	}

	private void validateEnvironment() {
		// Assert.notNull(table);
		Assert.notNull(dialect);
		Assert.notNull(reportDataSource);

	}

	protected List<Map<String, CellData>> packageResult(ResultSet rs, int start, String totalText) throws SQLException {
		List<Map<String, CellData>> results = new ArrayList<Map<String, CellData>>();

		if (rs == null)
			return results;
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				Map<String, CellData> datas = new LinkedHashMap<String, CellData>();
				// 序列列生成
				// this.isRestrction(QueryRestriction.PACKAGE_NOT_SHOW_ROW_NUMBER)
				if (!this.isRestriction(QueryRestriction.PACKAGE_NOT_SHOW_ROW_NUMBER)
						&& (this.isRestriction(QueryRestriction.TABLE_SHOW_ROW_NUMBER) || this
								.isRestriction(QueryRestriction.TABLE_SHOW_TOTAL_TITLE))) {
					CellData cellData = new CellData();
					cellData.setColumnName(_TENWA_ROWNUMBER);
					cellData.setValue("&nbsp;");

					if (this.isRestriction(QueryRestriction.TABLE_SHOW_ROW_NUMBER) && totalText == null) {
						cellData.setValue(String.valueOf(++start));
					}
					if (this.isRestriction(QueryRestriction.TABLE_SHOW_TOTAL_TITLE) && totalText != null) {
						if (!this.isRestriction(QueryRestriction.PACKAGE_NOT_GENERATE_HTML) && totalText.equals("")) {
							totalText = "&nbsp;";
						}
						cellData.setValue(totalText);
					}
					datas.put(_TENWA_ROWNUMBER.toLowerCase(), cellData);
				}
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					String columnLabel = rsmd.getColumnName(i);
					String columnName = revertReplacedColumnName(columnLabel);
					String columnValue = StringUtil.nullToString(rs.getString(columnLabel));
					CellData cellData = new CellData(columnName, columnValue);
					datas.put(cellData.getColumnName().toLowerCase(), cellData);
				}

				results.add(datas);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new SQLException(e.getMessage());
		}finally{
			if(rs!=null){
				rs.close();
			}
		}
		return results;
	}

	private String revertReplacedColumnName(String columnLabel) {
		BidiMap keywordMap = dialect.getReservedKeyword();
		if (keywordMap.containsValue(columnLabel.toUpperCase())) {
			return (String) keywordMap.getKey(columnLabel.toUpperCase());
		}
		return columnLabel;
	}

	private String replaceReservedKeyword(String sql) {
		BidiMap keywordMap = dialect.getReservedKeyword();
		for (Object srcName : keywordMap.keySet()) {
			Pattern p = Pattern.compile("\\b" + srcName + "\\b", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(sql);
			sql = m.replaceAll((String) keywordMap.get(srcName));
		}
		return sql;
	}

	private Connection getConnection() throws BusinessException, SQLException, NamingException {
		return DataSourceFactory.getConnection(this.reportDataSource);
	}

	@Override
	public int getTotalCount(List<MutablePair<ReportFilter, String>> params,ReportTable ... table) throws Exception {

		int totalCount = 0;
		try {

			String sql = this.getParmeteizedSQL(params, this.getSQL(),table);
			this.dialect.setConnection(this.getConnection());

			switch (queryType) {
			case SIMPLESQL:
			case COMPLEXSQL:
				int fromPos = sql.toUpperCase().indexOf(" FROM ");
				sql = "SELECT COUNT(*) " + sql.substring(fromPos, sql.length());
				ResultSet rs;
				Map msql=new HashMap<String,String>();
				msql.put("sql", sql);
				msql.put("param", new ArrayList());
				msql.put("type", new ArrayList());
				rs = this.dialect.executeSQL(msql, 0, -1,true);
				while (rs != null && rs.next()) {
					totalCount = rs.getInt(1);
					break;
				}
				break;
			default:
				// TODO: add other type's got totoal.
				return 0;

			}
		} finally {
			dialect.closeConnection();
		}

		return totalCount;

	}

	@Override
	public List<Map<String, CellData>> getChartData(List<MutablePair<ReportFilter, String>> params) throws Exception {
		validateEnvironment();
		List<Map<String, CellData>> datas = new ArrayList<Map<String, CellData>>();

		// params = combineSearchAndFilter(params);

		switch (queryType) {
		case SIMPLESQL:
		case COMPLEXSQL:
			datas = getSQLQueryData(params, 0, 0);
			break;
		case PROCEDURE:
			break;
		default:
			return null;
		}

		return datas;
	}

}
