package com.tenwa.report.entity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.report.enums.ReportType;

@Entity
@Table(name = "Report_Report")
@XmlRootElement(name = "report")
@XmlAccessorType(XmlAccessType.FIELD)
public class Report {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
	@XmlID
	private String id;

	@Column(name = "NAME_", nullable = false)
	@FieldName(name = "报表名称")
	private String name;
	
	@Column(name = "ENNAME_")
	@FieldName(name = "报表名称")
	private String enname;

	@Column(name = "CODE_", nullable = true, unique = true)
	private String code;

	@FieldName(name = "菜单表中的ID")
	@XmlTransient
	private String menuId;

	/*@Type(type = "org.hibernate.type.YesNoType")*/
	private Boolean isActived;

	@Column(name = "DESCRIPTION_")
	@FieldName(name = "描述")
	private String description;

	@Column(nullable = false, name = "POSITION_")
	@FieldName(name = "菜单顺序")
	private int position = 0;

	@Enumerated(EnumType.STRING)
	@FieldName(name = "报表类型")
	private ReportType reportType;
	
	@Column(name = "REPORT_ORTYPE")
	@FieldName(name = "报表类型{text:'普通报表',value:'0'},{text:'1104报表',value:'1'}")
	private String reportOrType;
	@Column(name = "REPORT_CLASSIFY")
	@FieldName(name = "报表分类[{text:'月报',value:'0'},{text:'季报',value:'1'},{text:'半年报',value:'2'}]")
	private String reportClassify;
	@Column(name = "TABLE_NAME")
	@FieldName(name = "表名用英文后6位加序列动态拼接表名")
	private String tableName;

	private String creator;

	private String createDate;

	@JsonBackReference
	@ManyToOne(targetEntity = Report.class, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "PID_")
	@FieldName(name = "父结点")
	@XmlIDREF
	private Report parentReport;

	@JsonManagedReference
	@OneToMany(mappedBy = "parentReport", fetch = FetchType.EAGER)
	@OrderBy(value = "position ASC,name")
	@FieldName(name = "子节点")
	@XmlElementWrapper(name = "children")
	@XmlElement(name = "child")
	@XmlIDREF
	private Set<Report> childrenReport = new HashSet<Report>();

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "report", fetch = FetchType.EAGER)
	@Where(clause="contentId is not null")
	@OrderBy(value = "position asc")
	@FieldName(name = "布局")
	@XmlElementWrapper(name = "layouts")
	@XmlElement(name = "layout")
	@XmlIDREF
	private Set<ReportLayout> layout;

	@Transient
	@XmlTransient
	// 预留字段
	private Map<String, String> attributes = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Report getParentReport() {
		return parentReport;
	}

	public void setParentReport(Report parentReport) {
		this.parentReport = parentReport;
	}

	public Set<Report> getChildrenReport() {
		return childrenReport;
	}

	public void setChildrenReport(Set<Report> childrenReport) {
		this.childrenReport = childrenReport;
	}

	public Set<ReportLayout> getLayout() {
		return layout;
	}

	public void setLayout(Set<ReportLayout> layout) {
		this.layout = layout;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public Boolean isActived() {
		if(isActived==null){
			return true;
		}
		return isActived;
	}

	public void setActived(Boolean isActived) {
		if (isActived == null) {
			this.isActived = true;
		} else {
			this.isActived = isActived;
		}

	}

	/* when in hibernate session mode ,this function not called */
	@PrePersist
	@PreUpdate
	public void prePersist() {
		if (this.isActived() == null)
			this.setActived(true);
	}
	
	public String toString(){
		return ReflectionToStringBuilder.toStringExclude(this, "childrenReport","parentReport","layout");
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	

	public String getReportOrType() {
		return reportOrType;
	}

	public void setReportOrType(String reportOrType) {
		this.reportOrType = reportOrType;
	}

	public String getReportClassify() {
		return reportClassify;
	}

	public void setReportClassify(String reportClassify) {
		this.reportClassify = reportClassify;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	

}
