package com.tenwa.leasing.entity.BankReport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.Industry;
import com.tenwa.kernal.annotation.FieldName;
@Entity
@FieldName(name = "1104报表列配置")
@Table(name = "BANK_REPORT_COLUMN_CONFIG")
public class BankReportColumnConfig {
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name="标识符")
	@Column(name = "ID", nullable = false, length = 64)
	private String id;
	
	@FieldName(name="排序号")
	@Column(name="REPORT_NAME")
	private String ReportName;
	
	 
	@FieldName(name="客户所属行业门类[B]")
	@Column(name="INDUSTRY_")
	private String industry;
	
	@FieldName(name="排序号")
	@Column(name="SORT_NO")
	private Integer sortNo;
	
	@FieldName(name="标题")
	@Column(name="CTITLE")
	private String ctitle;

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getReportName() {
		return ReportName;
	}


	public void setReportName(String reportName) {
		ReportName = reportName;
	}


 
	public String getIndustry() {
		return industry;
	}


	public void setIndustry(String industry) {
		this.industry = industry;
	}


	public Integer getSortNo() {
		return sortNo;
	}


	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}


	public String getCtitle() {
		return ctitle;
	}


	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}
    
}
