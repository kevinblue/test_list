package com.tenwa.leasing.entity.proj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@FieldName(name = "风电场报告")
@Table(name="WIND_FARM_REPORT_DATA")
public class WindFarmReportData {


		@Id
		@OrderBy
	    @GeneratedValue(generator = "paymentableGenerator")     
	    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
	    @Column(length=32)
	    @FieldName(name="标识符")
		private String id;

		@FieldName(name = "流程id")
		@Column(name="DOC_ID")
		private String docid;
		
		@FieldName(name = "项目id")
		@Column(name="PROJ_ID")
		private String projid;
		
		@FieldName(name = "报表日期")
		@Column(name="REPORT_DATE")
		private String reportdate;
		
		
		
		@FieldName(name = "运行状况分析")
		@Column(name="STATUS_ANALYSIS",columnDefinition="Clob")
		private String statusanalysis;
		
		@FieldName(name = "故障数据分析")
		@Column(name="DATA_ANALYSIS", columnDefinition="Clob")
		private String dataanalysis;
	
		@FieldName(name = "损失电量分析")
		@Column(name="LOSS_ANALYSIS", columnDefinition="Clob")
		private String lossanalysis ;
		
		@FieldName(name = "功率曲线分析")
		@Column(name="CURVE_ANALYSIS", columnDefinition="Clob")
		private String curveanalysis;
		
		@FieldName(name = "结论/关注点")
		@Column(name="CONCLUSION", columnDefinition="Clob")
		private String conclusion;
		
		@FieldName(name = "改进措施与建议")
		@Column(name="SUGGESTIONS", columnDefinition="Clob")
		private String suggestions ;
		
		
		@ManyToOne
		@FieldName(name = "登记人")
		@JoinColumn(name="CREATOR_")
		private User creator;
		
		@FieldName(name = "登记时间")
		@Column(name="CREATE_DATE")
		private String createDate;
		
		@ManyToOne
		@FieldName(name = "更新人")
		@JoinColumn(name="MODIFICATOR_")
		private User modificator;
		
		@FieldName(name = "更新日期")
		@Column(name="MODIFY_DATE", length=20)
		private String modifyDate;
		public User getCreator() {
			return creator;
		}

		public void setCreator(User creator) {
			this.creator = creator;
		}

		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}

		public User getModificator() {
			return modificator;
		}

		public void setModificator(User modificator) {
			this.modificator = modificator;
		}

		public String getModifyDate() {
			return modifyDate;
		}

		public void setModifyDate(String modifyDate) {
			this.modifyDate = modifyDate;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getDocid() {
			return docid;
		}

		public void setDocid(String docid) {
			this.docid = docid;
		}

		public String getProjid() {
			return projid;
		}

		public void setProjid(String projid) {
			this.projid = projid;
		}

		public String getReportdate() {
			return reportdate;
		}

		public void setReportdate(String reportdate) {
			this.reportdate = reportdate;
		}

		public String getStatusanalysis() {
			return statusanalysis;
		}

		public void setStatusanalysis(String statusanalysis) {
			this.statusanalysis = statusanalysis;
		}

		public String getDataanalysis() {
			return dataanalysis;
		}

		public void setDataanalysis(String dataanalysis) {
			this.dataanalysis = dataanalysis;
		}

		public String getLossanalysis() {
			return lossanalysis;
		}

		public void setLossanalysis(String lossanalysis) {
			this.lossanalysis = lossanalysis;
		}

		public String getCurveanalysis() {
			return curveanalysis;
		}

		public void setCurveanalysis(String curveanalysis) {
			this.curveanalysis = curveanalysis;
		}

		public String getConclusion() {
			return conclusion;
		}

		public void setConclusion(String conclusion) {
			this.conclusion = conclusion;
		}

		public String getSuggestions() {
			return suggestions;
		}

		public void setSuggestions(String suggestions) {
			this.suggestions = suggestions;
		}

	
}
