package com.tenwa.report.query;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tenwa.report.dao.ReportDao;
import com.tenwa.report.enums.QueryType;
import com.tenwa.report.query.impl.SQLQueryServiceImpl;

@Component(value = "queryFactory")
public class QueryFactory {
	@Resource(name = "reportDao")
	private ReportDao reportDao;

	public QueryService getService(QueryType queryType) {
		
		switch (queryType) {
		case SIMPLESQL:
		case COMPLEXSQL:
		case PROCEDURE:
			return new SQLQueryServiceImpl();
		default:
			return null;
		}
	}
}
