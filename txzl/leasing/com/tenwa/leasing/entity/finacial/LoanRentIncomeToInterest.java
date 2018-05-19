package com.tenwa.leasing.entity.finacial;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.file.BaseFile;

@Entity
@FieldName(name = "租金实收表（利息）")
@Table(name = "LOAN_RENT_INCOME_TO_INTEREST")
public class LoanRentIncomeToInterest {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@FieldName(name="标识符")
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "借款合同号")
	@JoinColumn(name = "LOAN_ID")
	private LoanAccount loanid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="计划ID")
	@JoinColumn(name="PLAN_Id")
	private LoanRentPlanToInterest planId;

	@FieldName(name="付息日期")
	@Column(name="HIRE_DATE",length=20)
	private String hireDate;

	@FieldName(name="利率")
	@Column(name="SJINTEREST_RATE",length=50)
	private String sjinterestrate;
		
	@FieldName(name="余额")
	@Column(name="SJBALANCE",length=100)
	private String sjbalance;
	
	@FieldName(name="利息")
	@Column(name="SJINTEREST",length=100)
	private String sjinterest;
	
	@FieldName(name="起息日")
	@Column(name="SJDATE_OF_INTEREST",length=100)
	private String sjdateofinterest;
	
	@FieldName(name="止息日")
	@Column(name="SJCEASE_DATE",length=100)
	private String sjceasedate;

	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "币种")
	@JoinColumn(name = "CURRENCY")
	private DictionaryData currency;
	
	@FieldName(name="是否红冲或被红冲记录")
	@Column(name="ROLL_BACK",length=2)
	private String   rollBack;
	
	@FieldName(name="备注")
	@Column(name="MENO",length=1000)
	private String meno;
		
	
	@FieldName(name="总的期次")
	@Column(name="SUM_PLAN_LIST",length=1000)
	private String sumPlanList;
	
	
	@FieldName(name = "上传文件名")
	@ManyToOne(targetEntity = BaseFile.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "UP_ID")
	private BaseFile upLoadId;
	
	


	@ManyToOne
	@FieldName(name = "创建人")
	@JoinColumn(name = "CREATOR_")
	private User creator;

	@FieldName(name = "创建时间")
	@Column(name = "CREATE_DATE", length = 20)
	private String createDate;

	@ManyToOne
	@FieldName(name = "修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;

	@FieldName(name = "修改时间")
	@Column(name = "MODIFY_DATE", length = 20)
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LoanAccount getLoanid() {
		return loanid;
	}

	public void setLoanid(LoanAccount loanid) {
		this.loanid = loanid;
	}


	

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}


	

	public String getSjinterestrate() {
		return sjinterestrate;
	}

	public void setSjinterestrate(String sjinterestrate) {
		this.sjinterestrate = sjinterestrate;
	}

	public String getSjbalance() {
		return sjbalance;
	}

	public void setSjbalance(String sjbalance) {
		this.sjbalance = sjbalance;
	}

	public String getSjinterest() {
		return sjinterest;
	}

	public void setSjinterest(String sjinterest) {
		this.sjinterest = sjinterest;
	}

	public String getSjdateofinterest() {
		return sjdateofinterest;
	}

	public void setSjdateofinterest(String sjdateofinterest) {
		this.sjdateofinterest = sjdateofinterest;
	}

	public String getSjceasedate() {
		return sjceasedate;
	}

	public void setSjceasedate(String sjceasedate) {
		this.sjceasedate = sjceasedate;
	}

	public DictionaryData getCurrency() {
		return currency;
	}

	public void setCurrency(DictionaryData currency) {
		this.currency = currency;
	}

	public String getRollBack() {
		return rollBack;
	}

	public void setRollBack(String rollBack) {
		this.rollBack = rollBack;
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
	public String getMeno() {
		return meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}
	
	public LoanRentPlanToInterest getPlanId() {
		return planId;
	}

	public void setPlanId(LoanRentPlanToInterest planId) {
		this.planId = planId;
	}
	public String getSumPlanList() {
		return sumPlanList;
	}

	public void setSumPlanList(String sumPlanList) {
		this.sumPlanList = sumPlanList;
	}



}
