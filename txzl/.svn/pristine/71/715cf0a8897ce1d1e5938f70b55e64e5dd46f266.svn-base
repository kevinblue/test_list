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
@FieldName(name = "经评模型")
@Table(name="T_EVALUATION_MODEL_DATA")
public class EvaluationModelData {
//T_ODBC_EVALUATION_SUBJECT  odbc导入项目表
	//改了带文件生成文件的路径

		@Id
		@OrderBy
	    @GeneratedValue(generator = "paymentableGenerator")     
	    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
	    @Column(length=32)
	    @FieldName(name="标识符")
		private String id;

		//(1.2.3.4.5.6.7.8)
		@FieldName(name = "文件序号")
		@Column(name="FILE_ORDER")
		private String fileOrder;
		
		

		//银行贷款(1)-融资租赁(2)
		@FieldName(name = "表头名称")
		@Column(name="TABLE_TITLE")
		private String tableTitle;
		
		
		@FieldName(name = "项目id")
		@Column(name="PROJ_ID")
		private String projId;
	
		
		@FieldName(name = "流程id")
		@Column(name="FLOWU_ID")
		private String flowId;
		
		@FieldName(name = "文件名称")
		@Column(name="FILE_NAME")
		private String fileName;
		
		//1--20
		@FieldName(name = "装机容量(万千瓦)")
		@Column(name="SUBJECT_1")
		private String subject1 ;
		
		@FieldName(name = "年上网电量总计(万度)")
		@Column(name="SUBJECT_2")
		private String subject2 ;
		
		@FieldName(name = "总投资（万元）")
		@Column(name="SUBJECT_3")
		private String subject3 ;
		
		@FieldName(name = "建设期利息（万元）")
		@Column(name="SUBJECT_4")
		private String subject4 ;
		
		@FieldName(name = "年发电销售收入总额（不含增值税）（万元）")
		@Column(name="SUBJECT_5")
		private String subject5 ;
		
		@FieldName(name = "增值税返还（20年合计）（万元）")
		@Column(name="SUBJECT_6")
		private String subject6 ;
		
		
		@FieldName(name = "总成本费用（20年合计）（万元）")
		@Column(name="SUBJECT_7")
		private String subject7 ;
		
		@FieldName(name = "销售税金附加总额（20年合计）（万元）")
		@Column(name="SUBJECT_8")
		private String subject8 ;
		
		@FieldName(name = "发电利润总额（20年合计）（万元）")
		@Column(name="SUBJECT_9")
		private String subject9 ;
		
		@FieldName(name = "电价(不含税)")
		@Column(name="SUBJECT_10")
		private String subject10 ;
		
		@FieldName(name = "静态投资回收期")
		@Column(name="SUBJECT_11")
		private String subject11 ;
		
		
		@FieldName(name = "全投资静态投资回收期（年）")
		@Column(name="SUBJECT_12")
		private String subject12 ;
		
		@FieldName(name = "自有资金静态投资回收期（年）")
		@Column(name="SUBJECT_13")
		private String subject13 ;
		
		
		@FieldName(name = "内部收益率")
		@Column(name="SUBJECT_14")
		private String subject14 ;
		
		@FieldName(name = "全部投资内部收益率（%）")
		@Column(name="SUBJECT_15")
		private String subject15 ;
		
		@FieldName(name = "自有资金内部收益率（%）")
		@Column(name="SUBJECT_16")
		private String subject16 ;
		
		
		@FieldName(name = "财务净现值")
		@Column(name="SUBJECT_17")
		private String subject17 ;
		
		@FieldName(name = "全部投资财务净现值(ic=9%)（万元）")
		@Column(name="SUBJECT_18")
		private String subject18 ;
		
		@FieldName(name = "自有资金财务净现值(ic=12%)（万元）")
		@Column(name="SUBJECT_19")
		private String subject19 ;
		
		@FieldName(name = "自有资金财务净现值(ic=15%)（万元）")
		@Column(name="SUBJECT_20")
		private String subject20 ;
		
		//有效1，删除2
		@FieldName(name = "状态")
		@Column(name="status")
		private String status ;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "CREATOR_")
		@FieldName(name="创建人")
		private User creator;
		
		@Column(name = "CREATE_DATE", length = 40)
		@FieldName(name="创建时间")
		private String createDate;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@FieldName(name="修改人")
		@JoinColumn(name = "MODIFICATOR_")
		private User modificator;
		
		@FieldName(name="修改时间")
		@Column(name = "MODIFY_DATE", length = 40)
		private String modifyDate;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getFileOrder() {
			return fileOrder;
		}

		public void setFileOrder(String fileOrder) {
			this.fileOrder = fileOrder;
		}

		public String getProjId() {
			return projId;
		}

		public void setProjId(String projId) {
			this.projId = projId;
		}


		public String getFlowId() {
			return flowId;
		}

		public void setFlowId(String flowId) {
			this.flowId = flowId;
		}

		public String getTableTitle() {
			return tableTitle;
		}

		public void setTableTitle(String tableTitle) {
			this.tableTitle = tableTitle;
		}

	    

		public String getSubject1() {
			return subject1;
		}

		public void setSubject1(String subject1) {
			this.subject1 = subject1;
		}

		public String getSubject2() {
			return subject2;
		}

		public void setSubject2(String subject2) {
			this.subject2 = subject2;
		}

		public String getSubject3() {
			return subject3;
		}

		public void setSubject3(String subject3) {
			this.subject3 = subject3;
		}

		public String getSubject4() {
			return subject4;
		}

		public void setSubject4(String subject4) {
			this.subject4 = subject4;
		}

		public String getSubject5() {
			return subject5;
		}

		public void setSubject5(String subject5) {
			this.subject5 = subject5;
		}

		public String getSubject6() {
			return subject6;
		}

		public void setSubject6(String subject6) {
			this.subject6 = subject6;
		}

		public String getSubject7() {
			return subject7;
		}

		public void setSubject7(String subject7) {
			this.subject7 = subject7;
		}

		public String getSubject8() {
			return subject8;
		}

		public void setSubject8(String subject8) {
			this.subject8 = subject8;
		}

		public String getSubject9() {
			return subject9;
		}

		public void setSubject9(String subject9) {
			this.subject9 = subject9;
		}

		public String getSubject10() {
			return subject10;
		}

		public void setSubject10(String subject10) {
			this.subject10 = subject10;
		}

		public String getSubject11() {
			return subject11;
		}

		public void setSubject11(String subject11) {
			this.subject11 = subject11;
		}

		public String getSubject12() {
			return subject12;
		}

		public void setSubject12(String subject12) {
			this.subject12 = subject12;
		}

		public String getSubject13() {
			return subject13;
		}

		public void setSubject13(String subject13) {
			this.subject13 = subject13;
		}

		public String getSubject14() {
			return subject14;
		}

		public void setSubject14(String subject14) {
			this.subject14 = subject14;
		}

		public String getSubject15() {
			return subject15;
		}

		public void setSubject15(String subject15) {
			this.subject15 = subject15;
		}

		public String getSubject16() {
			return subject16;
		}

		public void setSubject16(String subject16) {
			this.subject16 = subject16;
		}

		public String getSubject17() {
			return subject17;
		}

		public void setSubject17(String subject17) {
			this.subject17 = subject17;
		}

		public String getSubject18() {
			return subject18;
		}

		public void setSubject18(String subject18) {
			this.subject18 = subject18;
		}

		public String getSubject19() {
			return subject19;
		}

		public void setSubject19(String subject19) {
			this.subject19 = subject19;
		}

		public String getSubject20() {
			return subject20;
		}

		public void setSubject20(String subject20) {
			this.subject20 = subject20;
		}

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

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
	    
}
