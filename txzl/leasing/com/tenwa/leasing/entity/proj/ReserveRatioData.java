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
@FieldName(name = "经评模型偿债备付率")
@Table(name="T_RESERVE_RATIO_DATA")
public class ReserveRatioData {
//按现金流计算的偿债备付率（分母为应付租金+手续费）


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
		
		
		@FieldName(name = "项目id")
		@Column(name="PROJ_ID")
		private String projId;
	
		
		@FieldName(name = "流程id")
		@Column(name="FLOWU_ID")
		private String flowId;
		
		@FieldName(name = "文件名称")
		@Column(name="FILE_NAME")
		private String fileName;
		
		@FieldName(name = "开始年份")
		@Column(name="START_YEAR")
		private String startyear ;
		
		@FieldName(name = "年数")
		@Column(name="YEAR_NUM")
		private String yearnum ;
		
		@FieldName(name = "第一个年分")
		@Column(name="YEAR_1")
		private String year1 ;
		
		@FieldName(name = "第二个年分")
		@Column(name="YEAR_2")
		private String year2 ;
		
		@FieldName(name = "第三个年分")
		@Column(name="YEAR_3")
		private String year3 ;
		
		@FieldName(name = "第四个年分")
		@Column(name="YEAR_4")
		private String year4 ;
		
		@FieldName(name = "第五个年分")
		@Column(name="YEAR_5")
		private String year5 ;
		
		@FieldName(name = "第六个年分")
		@Column(name="YEAR_6")
		private String year6 ;
		
		@FieldName(name = "第七个年分")
		@Column(name="YEAR_7")
		private String year7;
		
		@FieldName(name = "第八个年分")
		@Column(name="YEAR_8")
		private String year8 ;
		
		@FieldName(name = "第九个年分")
		@Column(name="YEAR_9")
		private String year9 ;
		
		@FieldName(name = "第十个年分")
		@Column(name="YEAR_10")
		private String year10 ;
		
		@FieldName(name = "第十一个年分")
		@Column(name="YEAR_11")
		private String year11 ;
		
		@FieldName(name = "第十二个年分")
		@Column(name="YEAR_12")
		private String year12 ;
		
		@FieldName(name = "第十三个年分")
		@Column(name="YEAR_13")
		private String year13 ;
		
		@FieldName(name = "第十四个年分")
		@Column(name="YEAR_14")
		private String year14 ;
		
		@FieldName(name = "第十五个年分")
		@Column(name="YEAR_15")
		private String year15 ;
		
		@FieldName(name = "综合DSCR")
		@Column(name="DSCR")
		private String dscr ;
		
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

		public String getStartyear() {
			return startyear;
		}

		public void setStartyear(String startyear) {
			this.startyear = startyear;
		}

		public String getYearnum() {
			return yearnum;
		}

		public void setYearnum(String yearnum) {
			this.yearnum = yearnum;
		}

		public String getYear1() {
			return year1;
		}

		public void setYear1(String year1) {
			this.year1 = year1;
		}

		public String getYear2() {
			return year2;
		}

		public void setYear2(String year2) {
			this.year2 = year2;
		}

		public String getYear3() {
			return year3;
		}

		public void setYear3(String year3) {
			this.year3 = year3;
		}

		public String getYear4() {
			return year4;
		}

		public void setYear4(String year4) {
			this.year4 = year4;
		}

		public String getYear5() {
			return year5;
		}

		public void setYear5(String year5) {
			this.year5 = year5;
		}

		public String getYear6() {
			return year6;
		}

		public void setYear6(String year6) {
			this.year6 = year6;
		}

		public String getYear7() {
			return year7;
		}

		public void setYear7(String year7) {
			this.year7 = year7;
		}

		public String getYear8() {
			return year8;
		}

		public void setYear8(String year8) {
			this.year8 = year8;
		}

		public String getYear9() {
			return year9;
		}

		public void setYear9(String year9) {
			this.year9 = year9;
		}

		public String getYear10() {
			return year10;
		}

		public void setYear10(String year10) {
			this.year10 = year10;
		}

		public String getYear11() {
			return year11;
		}

		public void setYear11(String year11) {
			this.year11 = year11;
		}

		public String getYear12() {
			return year12;
		}

		public void setYear12(String year12) {
			this.year12 = year12;
		}

		public String getYear13() {
			return year13;
		}

		public void setYear13(String year13) {
			this.year13 = year13;
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

		public String getDscr() {
			return dscr;
		}

		public void setDscr(String dscr) {
			this.dscr = dscr;
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

		public String getYear14() {
			return year14;
		}

		public void setYear14(String year14) {
			this.year14 = year14;
		}

		public String getYear15() {
			return year15;
		}

		public void setYear15(String year15) {
			this.year15 = year15;
		}

		
}
