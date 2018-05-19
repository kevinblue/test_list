package com.tenwa.leasing.entity.fund.overdue;


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
import com.tenwa.leasing.entity.contract.ContractInfo;


/**
 * 催款员表
 * @Title: OverdueDunningInfo.java
 * @package: com.tenwa.leasing.entity.fund.overdue
 * @author: tpf
 * @date 2014年11月24日 上午10:15:11 
 * @version V5.1
 */
@Entity
@FieldName(name = "催款员表")
@Table(name = "OVERDUE_DUNNING_INFO")
public class OverdueDunningInfo {
	@Id
	@OrderBy
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	@ManyToOne
	@FieldName(name = "用户")
	@JoinColumn(name="PART_DEPT")
	private User partDept;

	@FieldName(name="合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@ManyToOne
	@FieldName(name = "运营催款员")
	@JoinColumn(name="DUNNING_ONE")
	private User dunningOne;
	
	@ManyToOne
	@FieldName(name = "风控催款员")
	@JoinColumn(name="DUNNING_TWO")
	private User dunningTwo;
	
	@ManyToOne
	@FieldName(name = "法务催款员")
	@JoinColumn(name="DUNNING_THREE")
	private User dunningThree;
	
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

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public User getDunningOne() {
		return dunningOne;
	}

	public void setDunningOne(User dunningOne) {
		this.dunningOne = dunningOne;
	}

	public User getDunningTwo() {
		return dunningTwo;
	}

	public void setDunningTwo(User dunningTwo) {
		this.dunningTwo = dunningTwo;
	}

	public User getDunningThree() {
		return dunningThree;
	}

	public void setDunningThree(User dunningThree) {
		this.dunningThree = dunningThree;
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

	public User getPartDept() {
		return partDept;
	}

	public void setPartDept(User partDept) {
		this.partDept = partDept;
	}


}