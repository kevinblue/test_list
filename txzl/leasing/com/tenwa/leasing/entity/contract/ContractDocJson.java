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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
/**
 * ContractSignatory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CONTRACT_DOC_JSON")
@FieldName(name="合同文件表JSON")
public class ContractDocJson implements java.io.Serializable {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="合同编号")
	@OneToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name="CONTRACT_DOC_JSON", nullable=false)
	private String  contract_doc_json;


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


	public String getContract_doc_json() {
		return contract_doc_json;
	}


	public void setContract_doc_json(String contract_doc_json) {
		this.contract_doc_json = contract_doc_json;
	}	
	
	
}