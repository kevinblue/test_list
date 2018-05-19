package com.tenwa.leasing.entity.proj;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractGuaranteeEquip;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
@Entity
@FieldName(name = "合同编号设置")
@Table(name = "CONTRACT_NUMBER_SETTING_TMP")
public class ContractNumberSettingTmp implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 64)
	@FieldName(name="标识符")
	private String id;
	
	@Column(name = "OLD_ID")
	@FieldName(name="正式表id")
	private String oldId;
	
	@Column(name = "SAVE_STATUS")
	@FieldName(name="保存状态")
	private String saveStatus;
	
	@Column(name = "DOC_ID")
	@FieldName(name="当前流程ID")
	private String docid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID")
	@FieldName(name="项目")
	private ProjInfo projInfo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "客户信息")
	@JoinColumn(name = "CUST_ID")
	private CustInfo custId;
	
	@FieldName(name="合同id")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
    //手动填写
	@Column(name = "CONTRACT_NUMBER")
	@FieldName(name="合同编号")
	private String contractNumber;
	
	 //手动填写
		@Column(name = "CONTRACT_NAME")
		@FieldName(name="合同名称")
		private String contractName;
	
	//选择框数据字典
	@Column(name = "CONTRACT_TYPE")
	@FieldName(name="合同类型")
	private String contractType;
	
  //输入字段
	@Column(name = "CONTRACT_MONEY", length = 510)
	@FieldName(name="采购总额")
	private String contractMoney;
		
    //输入字段
	@Column(name = "CONTRACT_SUBJECT", length = 510)
	@FieldName(name="合同主体")
	private String contractSubject;
	
	
	@Column(name = "mark")
	@FieldName(name="备注")
	private String mark;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODIFICATOR_")
	@FieldName(name="修改人")
	private User modificator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_")
	@FieldName(name="创建人")
	private User creator;

	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name="创建时间")
	private String createDate;

	@Column(name = "MODIFY_DATE", length = 40)
	@FieldName(name="修改时间")
	private String modifyDate;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public User getModificator() {
		return modificator;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
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

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	
	public String getContractMoney() {
		return contractMoney;
	}

	public void setContractMoney(String contractMoney) {
		this.contractMoney = contractMoney;
	}

	public String getContractSubject() {
		return contractSubject;
	}

	public void setContractSubject(String contractSubject) {
		this.contractSubject = contractSubject;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public ProjInfo getProjInfo() {
		return projInfo;
	}

	public void setProjInfo(ProjInfo projInfo) {
		this.projInfo = projInfo;
	}

	public CustInfo getCustId() {
		return custId;
	}

	public void setCustId(CustInfo custId) {
		this.custId = custId;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}


	public String getOldId() {
		return oldId;
	}

	public void setOldId(String oldId) {
		this.oldId = oldId;
	}

	public String getSaveStatus() {
		return saveStatus;
	}

	public void setSaveStatus(String saveStatus) {
		this.saveStatus = saveStatus;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
	
	
	
}