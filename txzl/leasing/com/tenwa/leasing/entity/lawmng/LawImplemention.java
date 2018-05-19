package com.tenwa.leasing.entity.lawmng;


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

@Entity
@FieldName(name = "法务执行情况登记")
@Table(name="LAW_IMPLEMENTION")

public class LawImplemention {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;

	@FieldName(name = "法务编号")
	@JoinColumn(name = "LAW_ID")
	private String lawId;
	
	@FieldName(name="合同编号")
	@Column(name="CONTRACT_ID")
	private String contractId ;
	
	@FieldName(name="业务合同编号")
	@Column(name="CONTRACT_NUMBER")
	private String contractNumber ;
	
	@FieldName(name = "客户名称")
	@Column(name="CUST_NAME")
	private String custName;
	
	@FieldName(name = "身份证/组织结构代码")
	@Column(name="CODE")
	private String code;

	@FieldName(name="项目经理")
	@Column(name="PROJ_MANAGE",length=1000)
	private User projManage;
	
	@FieldName(name="申请执行日")
	@Column(name="APPLICATION_EXECUTION_DATE")
	private String applicationExecutionDate;
	
	@FieldName(name="执行案号")
	@Column(name="EXECUTION_CASE_NUMBER")
	private String executionCaseNumber;
	
	@FieldName(name="执行法官")
	@Column(name="EXECUTIVE_JUDGE")
	private String executiveJudge;
	
	@FieldName(name="联系方式")
	@Column(name="CONTACT_WAY")
	private String contactWay;
	
	@FieldName(name="登记日期")
	@Column(name="REGISTE_DATE")
	private String registeDate;
	
	@FieldName(name="事项说明")
	@Column(name="ITEM_DESCRIPTION",length=1000)
	private String itemDescription;
	
	@FieldName(name = "外键")
	@Column(name = "RELATE_KEY", length = 100)
	private String relateKey;

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
	
	

	public String getExecutiveJudge() {
		return executiveJudge;
	}

	public void setExecutiveJudge(String executiveJudge) {
		this.executiveJudge = executiveJudge;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public String getLawId() {
		return lawId;
	}

	public void setLawId(String lawId) {
		this.lawId = lawId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public User getProjManage() {
		return projManage;
	}

	public void setProjManage(User projManage) {
		this.projManage = projManage;
	}

	public String getApplicationExecutionDate() {
		return applicationExecutionDate;
	}

	public void setApplicationExecutionDate(String applicationExecutionDate) {
		this.applicationExecutionDate = applicationExecutionDate;
	}

	public String getExecutionCaseNumber() {
		return executionCaseNumber;
	}

	public void setExecutionCaseNumber(String executionCaseNumber) {
		this.executionCaseNumber = executionCaseNumber;
	}

	public String getRegisteDate() {
		return registeDate;
	}

	public void setRegisteDate(String registeDate) {
		this.registeDate = registeDate;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getRelateKey() {
		return relateKey;
	}

	public void setRelateKey(String relateKey) {
		this.relateKey = relateKey;
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