package com.tenwa.business.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.tenwa.business.entity.SystemLog.TerminateType;
import com.tenwa.kernal.annotation.FieldName;

/**
 * 
* @ClassName: TableExportLog 
* @Description: table数据导出日志
* @author zhangc
* @date 2015年9月8日 下午2:27:57 
*
 */
@Entity
@Table(name = "T_LOGS_TABLE_EXPORT")
@FieldName(name = "导出操作日志")
public class TableExportLog {
	// 枚举类型
	public enum ExportType{
		TABLE,REPORT
	}
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
	private String id;

	@FieldName(name = "导出类型")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10, name = "EXPORT_TYPE_")
	private ExportType exportType;

	@FieldName(name = "日志时间")
	@Column(name = "TIME_", length = 22)
	private String time;
	
	
	@FieldName(name = "导出耗时")
	@Column(name = "EXPORT_TIME")
	private Long exportTime;
	
	@FieldName(name = "导出数据量")
	@Column(name = "EXPORT_DATA_SIZE")
	private Long exportDataSize;
	
	@FieldName(name = "操作人")
	@ManyToOne
	@JoinColumn(name = "OPERATE_USER")
	private User operateUser;

	@FieldName(name = "日志内容")
	@Lob 
	@org.hibernate.annotations.Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name = "CONTENT_")
	private String content;
	

	@FieldName(name = "备注")
	@Lob 
	@org.hibernate.annotations.Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name = "REMARK_")
	private String remark;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public ExportType getExportType() {
		return exportType;
	}


	public void setExportType(ExportType exportType) {
		this.exportType = exportType;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public Long getExportTime() {
		return exportTime;
	}


	public void setExportTime(Long exportTime) {
		this.exportTime = exportTime;
	}


	public Long getExportDataSize() {
		return exportDataSize;
	}


	public void setExportDataSize(Long exportDataSize) {
		this.exportDataSize = exportDataSize;
	}


	public User getOperateUser() {
		return operateUser;
	}


	public void setOperateUser(User operateUser) {
		this.operateUser = operateUser;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
}
