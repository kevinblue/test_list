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
 * 出险项目情况表指示
 * @Title: OverdueProject.java
 * @package: com.tenwa.leasing.entity.fund.overdue
 * @author: tpf
 * @date 2014年12月12日 上午11:38:50 
 * @version V5.1
 */
@Entity
@FieldName(name = "出险项目情况表指示")
@Table(name="OVERDUE_PROJECT")
public class OverdueProject {
	@Id
	@OrderBy
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;

	
	@FieldName(name="合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@ManyToOne
	@FieldName(name = "处理状态")
	@JoinColumn(name="PROCESSING_STATE")
	private DictionaryData processingState;
	
	@FieldName(name="本金余额")
	@Column(name="CORPUS_OVERAGE", precision = 22)
	private BigDecimal corpusOverage;
	
	@FieldName(name = "出险时间")
	@Column(name="DANGER_DATE", length=20)	
	private String dangerDate;
	
	
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

	public DictionaryData getProcessingState() {
		return processingState;
	}

	public void setProcessingState(DictionaryData processingState) {
		this.processingState = processingState;
	}

	public BigDecimal getCorpusOverage() {
		return corpusOverage;
	}

	public void setCorpusOverage(BigDecimal corpusOverage) {
		this.corpusOverage = corpusOverage;
	}

	public String getDangerDate() {
		return dangerDate;
	}

	public void setDangerDate(String dangerDate) {
		this.dangerDate = dangerDate;
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