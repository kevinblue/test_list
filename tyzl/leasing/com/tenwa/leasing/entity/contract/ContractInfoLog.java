package com.tenwa.leasing.entity.contract;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;
@Entity
@FieldName(name = "合同信息表")
@Table(name = "CONTRACT_INFO_lOG")
public class ContractInfoLog {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@FieldName(name="标识符")
	private String id;
	

	@FieldName(name="合同编号")
	@OneToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name = "记录时间")
	@Column(name = "LOG_DATE", length = 20)
	private String logDate;
	
	
	@FieldName(name="本金余额")
	@Column(name="CORPUS_OVER",precision=22,scale=2)
	private BigDecimal corpusOver;

	@FieldName(name="本金余额")
	@Column(name="EQUIPT_MONEY",precision=22,scale=2)
	private BigDecimal equiptMoney;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getCorpusOver() {
		return corpusOver;
	}

	public void setCorpusOver(BigDecimal corpusOver) {
		this.corpusOver = corpusOver;
	}

	public BigDecimal getEquiptMoney() {
		return equiptMoney;
	}

	public void setEquiptMoney(BigDecimal equiptMoney) {
		this.equiptMoney = equiptMoney;
	}
	
}
