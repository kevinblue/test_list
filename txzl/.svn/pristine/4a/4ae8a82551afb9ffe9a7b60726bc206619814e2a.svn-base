package com.tenwa.leasing.entity.finacial;

import java.math.BigDecimal;

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

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
@Entity
@FieldName(name = "保理台账")
@Table(name="CONTRACT_FACTORING")
public class ContractFactoring {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	

	@FieldName(name = "合同编号")
	@OneToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;
	
	
	
	@FieldName(name="是否保理")
	@Column(name="IF_FACTORING", length=10)
	private String ifFactoring;
	
	
	
	@FieldName(name="保理银行")
	@Column(name="FACTORING_BANK", length=100)
	private String factoringBank;
	
	
	@FieldName(name="保理金额")
	@Column(name="FACTORING_MONEY",precision = 22, scale = 2)
	private BigDecimal factoringMoney;
	
	@FieldName(name="保理开始日期")
	@Column(name="FACTORING_BEGIN_DATE",length=50)
	private String factoringBeginDate;
	
	@FieldName(name="保理结束日期")
	@Column(name="FACTORING_END_DATE",length=50)
	private String factoringEndDate;
	
	@FieldName(name="备注")
	@Column(name="REMARK",length=1000)
	private String remark;
	
	
	@ManyToOne
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE", length=20)	
	private String createDate;
	
	@ManyToOne
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE", length=20)	
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getIfFactoring() {
		return ifFactoring;
	}

	public void setIfFactoring(String ifFactoring) {
		this.ifFactoring = ifFactoring;
	}

	public String getFactoringBank() {
		return factoringBank;
	}

	public void setFactoringBank(String factoringBank) {
		this.factoringBank = factoringBank;
	}

	public BigDecimal getFactoringMoney() {
		return factoringMoney;
	}

	public void setFactoringMoney(BigDecimal factoringMoney) {
		this.factoringMoney = factoringMoney;
	}

	public String getFactoringBeginDate() {
		return factoringBeginDate;
	}

	public void setFactoringBeginDate(String factoringBeginDate) {
		this.factoringBeginDate = factoringBeginDate;
	}

	public String getFactoringEndDate() {
		return factoringEndDate;
	}

	public void setFactoringEndDate(String factoringEndDate) {
		this.factoringEndDate = factoringEndDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
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
