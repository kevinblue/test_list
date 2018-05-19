package com.tenwa.leasing.entity.contract;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;



/**
 * 
 * @author ganwei
 * @date 2016-03-18
 * @info 资产签约报告表
 * @Copyright Tenwa
 */
@Entity
@FieldName(name = "资产签约报告表")
@Table(name = "CONTRACT_ASSETS_SIGN_REPORT")
public class ContractAssetsSignReport {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@FieldName(name="ID")
	private String id;

	@FieldName(name = "合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name = "客户配合度")
	@Column(name = "COOPERATION_ATTITUDE", length = 2000)
	private String cooperationAttitude;
	
	@FieldName(name = "客户配合度情况说明")
	@Column(name = "COOPERATION_ATTITUDE_MEMO", length = 2000)
	private String cooperationAttitudeMemo;
	
	@FieldName(name = "客户付款流程")
	@Column(name = "CUST_PAYMENT", length = 2000)
	private String custpayment;
	
	@FieldName(name = "客户付款流程情况说明")
	@Column(name = "CUST_PAYMENT_MEMO", length = 2000)
	private String custpaymentmemo;
	
	@FieldName(name = "调研问卷完成情况")
	@Column(name = "STUDY_ALLREAL", length = 2000)
	private String studyAllreal; 

	@FieldName(name = "调研问卷完成情况情况说明")
	@Column(name = "STUDY_ALLREAL_MEMO", length = 2000)
	private String studyallrealmemo;
	
	@FieldName(name = "经营状况")
	@Column(name = "HOSPITAL_STATUS", length = 2000)
	private String hospitalStatus;
	
	@FieldName(name = "经营状况说明")
	@Column(name = "HOSPITAL_MEMO", length = 2000)
	private String hospitalMemo;
	
	@FieldName(name = "其他")
	@Column(name = "OTHERS", length = 5000)
	private String others;
	
	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "ANOTHER", nullable = false)
	private String another; 

	@FieldName(name = "流程的ID")
	@Column(name = "FLOW_UNID", length = 200)
	private String flowUnid;
	
	@FieldName(name = "资产签约报告")
	@Column(name = "JSON_ASSETS_SIGN_WORD_STR", length = 4000)
	private String jsonassetssignwordstr;
	
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

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public String getCooperationAttitude() {
		return cooperationAttitude;
	}

	public void setCooperationAttitude(String cooperationAttitude) {
		this.cooperationAttitude = cooperationAttitude;
	}

	public String getCooperationAttitudeMemo() {
		return cooperationAttitudeMemo;
	}

	public void setCooperationAttitudeMemo(String cooperationAttitudeMemo) {
		this.cooperationAttitudeMemo = cooperationAttitudeMemo;
	}

	public String getStudyAllreal() {
		return studyAllreal;
	}

	public void setStudyAllreal(String studyAllreal) {
		this.studyAllreal = studyAllreal;
	}

	public String getHospitalStatus() {
		return hospitalStatus;
	}

	public void setHospitalStatus(String hospitalStatus) {
		this.hospitalStatus = hospitalStatus;
	}

	public String getHospitalMemo() {
		return hospitalMemo;
	}

	public void setHospitalMemo(String hospitalMemo) {
		this.hospitalMemo = hospitalMemo;
	}

	public String getAnother() {
		return another;
	}

	public void setAnother(String another) {
		this.another = another;
	}

	public String getFlowUnid() {
		return flowUnid;
	}

	public void setFlowUnid(String flowUnid) {
		this.flowUnid = flowUnid;
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

	public String getJsonassetssignwordstr() {
		return jsonassetssignwordstr;
	}

	public void setJsonassetssignwordstr(String jsonassetssignwordstr) {
		this.jsonassetssignwordstr = jsonassetssignwordstr;
	}

	public String getCustpayment() {
		return custpayment;
	}

	public void setCustpayment(String custpayment) {
		this.custpayment = custpayment;
	}

	public String getCustpaymentmemo() {
		return custpaymentmemo;
	}

	public void setCustpaymentmemo(String custpaymentmemo) {
		this.custpaymentmemo = custpaymentmemo;
	}

	public String getStudyallrealmemo() {
		return studyallrealmemo;
	}

	public void setStudyallrealmemo(String studyallrealmemo) {
		this.studyallrealmemo = studyallrealmemo;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}
	
	
	
}
