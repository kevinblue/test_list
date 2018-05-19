package com.tenwa.leasing.entity.fivecategoryapply;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;

/**
 * FundEbankProcess entity. @author MyEclipse Persistence Tools
 */
@Entity
@FieldName(name = "五级分类申请详细")
@Table(name="FIVE_CATEGORY_APPLY_DETAIL")
public class FiveCategoryApplyDetail {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="申请ID")
	@ManyToOne(targetEntity=FiveCategoryApply.class,fetch=FetchType.LAZY)
	@JoinColumn(name="APPLY_ID")
	private FiveCategoryApply applyid ;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_ID")
	@FieldName(name="客户ID")
	private ContractInfo contractId;
	
	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	@FieldName(name="客户名称")
	@Column(name="CUST_NAME")
	private String custname ;
	
	
	
	@FieldName(name = "组织机构代码/身份证号码")
	@Column(name="CODE")
	private String code;

	@FieldName(name="逾期原因")
	@Column(name="LATE_CAUSE", length=1000)
	private String latecause ;
	
	@FieldName(name="重大事项")
	@Column(name="IMPORTANT_MATTERS", length=1000)
	private String importantmatters ;
	
	
	
	@FieldName(name="客户巡视")
	@Column(name="CUST_VISIT", length=1000)
	private String custvisit ;
	
	@FieldName(name="出险及涉诉情况")
	@Column(name="ACCIDENT_LITIGATION_SITUATION")
	private String accidentlitigationsituation ;
	
	@FieldName(name="前次资产分类结果")
	@Column(name="LAST_CLASSIFICATION_RESULT")
	private String lastclassificationresult ;
	
	@FieldName(name="本次资产分类结果")
	@Column(name="CLASSIFICATION_RESULT")
	private String classificationresult ;
	
	@FieldName(name="调整原因")
	@Column(name="ADJUSTMENT_REASON", length=1000)
	private String adjustmentreason ;
	

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

	public FiveCategoryApply getApplyid() {
		return applyid;
	}

	public void setApplyid(FiveCategoryApply applyid) {
		this.applyid = applyid;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLatecause() {
		return latecause;
	}

	public void setLatecause(String latecause) {
		this.latecause = latecause;
	}

	public String getImportantmatters() {
		return importantmatters;
	}

	public void setImportantmatters(String importantmatters) {
		this.importantmatters = importantmatters;
	}

	public String getCustvisit() {
		return custvisit;
	}

	public void setCustvisit(String custvisit) {
		this.custvisit = custvisit;
	}

	public String getAccidentlitigationsituation() {
		return accidentlitigationsituation;
	}

	public void setAccidentlitigationsituation(String accidentlitigationsituation) {
		this.accidentlitigationsituation = accidentlitigationsituation;
	}

	public String getLastclassificationresult() {
		return lastclassificationresult;
	}

	public void setLastclassificationresult(String lastclassificationresult) {
		this.lastclassificationresult = lastclassificationresult;
	}

	public String getClassificationresult() {
		return classificationresult;
	}

	public void setClassificationresult(String classificationresult) {
		this.classificationresult = classificationresult;
	}

	public String getAdjustmentreason() {
		return adjustmentreason;
	}

	public void setAdjustmentreason(String adjustmentreason) {
		this.adjustmentreason = adjustmentreason;
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
	
	
	
	}