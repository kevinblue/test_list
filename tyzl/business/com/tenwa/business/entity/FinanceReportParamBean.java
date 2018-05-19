package com.tenwa.business.entity;

import com.tenwa.kernal.annotation.FieldName;

@FieldName(name = "财务导出参数类")
public class FinanceReportParamBean {
	@FieldName(name="excel中的sheet名")
    public String sheeName ;
	@FieldName(name="sheet对应的类名")
    public String targetClass;
	@FieldName(name="sheet中标题开始对应的cell")
    public String headerCell;
	@FieldName(name="sheet中数据开始对应的cell")
    public String dataCell;
	
	@FieldName(name="数据的间隔是指财报上的第一年和第二年之间还有数据要跳过之间的列,默认0")
	public int dateInterval;
	@FieldName(name="是否第一项数据有百分比")
	public int isPercentage;
	
	@FieldName(name="是否有标题的日期要合并")
    public int isMergeTitle;
	
	@FieldName(name="自定义方法来解析EXCEL")
	public String custMethod;
	
	@FieldName(name="银行对账单月分")
	public int monthLength;
	
	@FieldName(name="是否检查EXCEL中的标题是否和类中定义的标题一样")
	public boolean isCheckTitle;
	
	public FinanceReportParamBean(String sheeName, String targetClass,
			String headerCell, String dataCell,int dateInterval,int isPercentage,int isMergeTitle,String custMethod,int monthLength ,Boolean isCheckTitle) {
		super();
		this.sheeName = sheeName;
		this.targetClass = targetClass;
		this.headerCell = headerCell;
		this.dataCell = dataCell;
		this.dateInterval=dateInterval;
		this.isPercentage=isPercentage;
		this.isMergeTitle=isMergeTitle;
		this.custMethod=custMethod;
		this.monthLength=monthLength;
		this.isCheckTitle=isCheckTitle;
	}
	public String getSheeName() {
		return sheeName;
	}
	public void setSheeName(String sheeName) {
		this.sheeName = sheeName;
	}
	public String getTargetClass() {
		return targetClass;
	}
	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}
	public String getHeaderCell() {
		return headerCell;
	}
	public void setHeaderCell(String headerCell) {
		this.headerCell = headerCell;
	}
	public String getDataCell() {
		return dataCell;
	}
	public void setDataCell(String dataCell) {
		this.dataCell = dataCell;
	}
	public int getDateInterval() {
		return dateInterval;
	}
	public void setDateInterval(int dateInterval) {
		this.dateInterval = dateInterval;
	}
	public int getIsPercentage() {
		return isPercentage;
	}
	public void setIsPercentage(int isPercentage) {
		this.isPercentage = isPercentage;
	}
	public int getIsMergeTitle() {
		return isMergeTitle;
	}
	public void setIsMergeTitle(int isMergeTitle) {
		this.isMergeTitle = isMergeTitle;
	}
	public String getCustMethod() {
		return custMethod;
	}
	public void setCustMethod(String custMethod) {
		this.custMethod = custMethod;
	}
	public int getMonthLength() {
		return monthLength;
	}
	public void setMonthLength(int monthLength) {
		this.monthLength = monthLength;
	}
	public boolean isCheckTitle() {
		return isCheckTitle;
	}
	public void setCheckTitle(boolean isCheckTitle) {
		this.isCheckTitle = isCheckTitle;
	}
	
	
}
