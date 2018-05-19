package com.tenwa.report.query;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.MutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tenwa.business.entity.User;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.FilterTextManipulator;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.report.entity.ReportColumn;
import com.tenwa.report.entity.ReportDataSource;
import com.tenwa.report.entity.ReportFilter;
import com.tenwa.report.entity.ReportTable;
import com.tenwa.report.enums.QueryType;

public abstract class AbstractQueryService implements QueryService {

	private static final Logger log = LoggerFactory.getLogger(AbstractQueryService.class);
	protected static final Pattern parameter_pattern = Pattern.compile("\\{.*?\\}");
	protected static final Pattern parameter_second_pattern = Pattern.compile("/~.*~/");
	protected static final String cacheName = "reportCache";	
	
	protected Map<String, String> columnHidden = new HashMap<String, String>(); // 附加隐藏域，行色，用户ID

	protected String externOrder = null;

	// blow parameters must inited by execute other code,using createQuery to init.

	protected ReportDataSource reportDataSource;

	protected User user;

	protected QueryType queryType;

	protected Set<ReportColumn> columns;

	protected String sql;

	protected String contentId;

	protected Map<QueryRestriction, Boolean> additionParameters = new HashMap<QueryRestriction, Boolean>();

	// protected ReportTable table;

	public boolean isQueryStart(Integer start) {
		if (start == null || start <= 1)
			return true;
		else
			return false;
	}

	@Override
	public abstract List<Map<String, CellData>> getTableData(List<MutablePair<ReportFilter, String>> params, Integer start, Integer limit,ReportTable ... table) throws Exception;

	@Override
	public abstract List<Map<String, String>> getTableMetadata(List<MutablePair<String, String>> params) throws Exception;

	@Override
	public abstract List<Map<String, CellData>> getTotal(List<MutablePair<ReportFilter, String>> params,ReportTable ... table) throws Exception;

	@Override
	public abstract int getTotalCount(List<MutablePair<ReportFilter, String>> params,ReportTable ... table) throws Exception;

	@Override
	public void setExternOrder(String externOrder) {
		this.externOrder = externOrder;
	}

	@Override
	public void addRestrction(QueryRestriction key, Boolean value) {
		if (key == null)
			return;
		this.additionParameters.put(key, value);
	}

	protected Boolean isRestriction(QueryRestriction key) {
		if (key == null)
			return false;
		if (this.additionParameters.containsKey(key))
			return this.additionParameters.get(key);
		return false;
	}

	@Override
	public void initQuery(String contentId, ReportDataSource rds, String sql, QueryType queryType, Set<ReportColumn> columns, User user) {
		this.contentId = contentId;
		this.reportDataSource = rds;
		this.queryType = queryType;
		this.sql = sql;
		this.columns = columns;
		this.user = user;
		this.addHiddenColumn(_TENWA_ROW_COLOR_); // 预加着色行
		this.addHiddenColumn(_TENWA_CELL_COLOR_); // 预加着色单元格
	}

	protected String getSQL() {
		String[] sqlframe = this.sql.replaceAll("(\n|\r|(\r\n)|(\u0085)|(\u2028)|(\u2029))", " ").split(" ");
		String sql = "";
		for (String s : sqlframe) {
			sql += (" " + s.trim());
		}
		return sql.trim();
	}

	protected List<ReportColumn> getSubTotalColumns() {
		List<ReportColumn> totalColumns = new ArrayList<ReportColumn>();
		for (ReportColumn c : columns) {
			if (c.getIsCountSubTotal()) {
				totalColumns.add(c);
			}
		}
		return totalColumns;
	}

	protected List<ReportColumn> getTotalColumns() {
		List<ReportColumn> totalColumns = new ArrayList<ReportColumn>();
		for (ReportColumn c : columns) {
			if (c.getIsCountTotal()) {
				totalColumns.add(c);
			}
		}
		return totalColumns;
	}

	
	/*
	 * 现将类似与/~param: ... ~/替换掉
	 */
	protected Map replaceSQLParams(List<MutablePair<String, String>> params) throws BusinessException {
		String sql = this.getSQL();
		Matcher m = null;
		Map resultMap=new HashMap();
		List paramValue=new ArrayList();
		List paramType=new ArrayList();
		FilterTextManipulator man = new FilterTextManipulator();
		sql = man.manipulate(new StringBuffer(sql), null).toString();
		while ((m = parameter_pattern.matcher(sql)).find()) {
			String paramName = sql.substring(m.start() + 1, m.end() - 1);
			boolean hasTheParam = false;
			for (MutablePair<String, String> pair : params) {
				if (pair.getLeft().equalsIgnoreCase(paramName)) {
					paramValue.add(pair.getRight());
					paramType.add(Types.VARCHAR);
					sql = m.replaceFirst("?");
					hasTheParam = true;
					break;
				}
			}
			if (!hasTheParam) {
				throw new BusinessException(WebUtil.getMessage("report.exception.param.notfound", paramName));
			}
		}
		log.debug("sql replaced param:{}", sql);
		resultMap.put("sql", sql);
		resultMap.put("param", paramValue);
		resultMap.put("type", paramType);
		return resultMap;
	}
	

	protected void addHiddenColumn(String columnName) {
		if (columnName != null && !"".equals(columnName.trim()) && !this.columnHidden.containsKey(columnName.toUpperCase())) {
			this.columnHidden.put(columnName.toUpperCase(), columnName.toUpperCase());
		}
	}

	protected boolean isHiddenColumn(String columnName) {
		if (columnName == null || "".equals(columnName.trim()))
			return false;
		if (this.columnHidden.containsKey(columnName.toUpperCase()))
			return true;
		return false;
	}

	protected boolean isTrue(Object v) {
		if (v == null)
			return false;
		if (v instanceof Boolean) {
			return (Boolean) v;
		} else {
			return Boolean.valueOf((v.toString()));
		}
	}

}
