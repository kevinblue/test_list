package com.tenwa.leasing.entity.fund.overdue;


import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;


/**
 * 领导指示
 * @Title: OverdueDunningDirectives.java
 * @package: com.tenwa.leasing.entity.fund.overdue
 * @author: tpf
 * @date 2014年11月24日 下午1:50:20 
 * @version V5.1
 */
@Entity
@FieldName(name = "催收记录")
@Table(name="OVERDUE_DUNNING_DIRECTIVES")
public class OverdueDunningDirectives {
	@Id
	@OrderBy
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;

	@FieldName(name="客户ID")
	@ManyToOne
	@JoinColumn(name="CUST_ID")
	private CustInfo custId;
	
	@ManyToOne
	@FieldName(name = "指示人")
	@JoinColumn(name="DIRECTIVE_PERSON")
	private User directivePerson;
	
	@FieldName(name="合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name = "指示日期")
	@Column(name="DIRECTIVE_DATE", length=20)
	private String directiveDate;
	
	@FieldName(name = "指示内容")
	@Column(name="DIRECTIVE_INFO", length=2000)
	private String directiveInfo;
	
	@ManyToOne
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;

	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE", length = 20)
	private String createDate;
	
	@ManyToOne
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;

	@FieldName(name = "更新时间")
	@Column(name="MODIFY_DATE", length = 20)
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CustInfo getCustId() {
		return custId;
	}

	public void setCustId(CustInfo custId) {
		this.custId = custId;
	}

	public User getDirectivePerson() {
		return directivePerson;
	}

	public void setDirectivePerson(User directivePerson) {
		this.directivePerson = directivePerson;
	}

	public String getDirectiveDate() {
		return directiveDate;
	}

	public void setDirectiveDate(String directiveDate) {
		this.directiveDate = directiveDate;
	}

	public String getDirectiveInfo() {
		return directiveInfo;
	}

	public void setDirectiveInfo(String directiveInfo) {
		this.directiveInfo = directiveInfo;
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

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}
}