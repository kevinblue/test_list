package com.tenwa.leasing.entity.paymentinterface;

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

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;

/**
 * 付款接口日志信息
 * @author tenwapc24
 *
 */
@Entity
@FieldName(name = "付款接口日志信息")
@Table(name = "PAYMENT_LOG")
public class PaymentLog implements Serializable{

	private static final long serialVersionUID = 2892650906485059692L;
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name="标识符")
	@Column(name = "ID", nullable = false, length = 64)
	private String id;
	
	@Column(name = "PAYMENT_STATUS", length=32)
	@FieldName(name="付款状态")
	private String paymentstatus;
	
	@Column(name = "FILE_PATH", length=32)
	@FieldName(name="日志路径")
	private String filepath;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="合同编号")
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractid;
	
	@FieldName(name="付款编号")
	@OneToOne(targetEntity=ContractFundFundPlan.class,fetch=FetchType.LAZY)
	@JoinColumn(name="PAYMENT_ID")
	private ContractFundFundPlan paymentid;
	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_")
	@FieldName(name="创建人")
	private User creator;
	
	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name="创建时间")
	private String createDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name = "MODIFY_DATE", length = 40)
	private String modifyDate;

	
	public ContractInfo getContractid() {
		return contractid;
	}

	public void setContractid(ContractInfo contractid) {
		this.contractid = contractid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}


	public ContractFundFundPlan getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(ContractFundFundPlan paymentid) {
		this.paymentid = paymentid;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
	
