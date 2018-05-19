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



import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * 
 * @author ZYH
 * @date 2016-9-23下午09:33:10
 * @info 银承台帐
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "银承台帐")
@Table(name="SILVER_ACCOUNT")
public class SilverAccount {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	

	@FieldName(name="付款人（上手背书人）")
	@Column(name="DRA_FIT_ENDORSER")
	private String draFitEndorser ;
	
	
	@FieldName(name="票号")
	@Column(name="TICKET", length=50)
	private String ticket;//
	
	@FieldName(name="票据金额")
	@Column(name="TICKET_AMOUNT",precision = 22, scale = 2)
	private BigDecimal ticketAmount;
	
	
	
	@FieldName(name="出票日期")
	@Column(name="OUT_DATE",length=50)
	private String outDate;
	
	@FieldName(name="到期日期")
	@Column(name="DUE_DATE",length=50)
	private String dueDate;
	
	@FieldName(name="出票银行")
	@Column(name="DRAWER_BANK",length=50)
	private String  drawerBank;
	
	@FieldName(name="出票人账号")
	@Column(name="DRAWER_ACCOUNT",length=50)
	private String drawerAccount;
	
	@FieldName(name="出票人")
	@Column(name="DRAWER",length=50)
	private String drawer;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@FieldName(name="背书转让、收款、贴现")
	@JoinColumn(name="END_PAY_DIS")
	private DictionaryData endPayDis;
	
	
	@FieldName(name="背书转让、收款、贴现日期")
	@Column(name="END_PAY_DIS_DATE",length=50)
	private String endPayDisDate;
	
	
	@FieldName(name="被背书人")
	@Column(name="ENDORSEE")
	private String endorsee;
	
	@FieldName(name="背书转让用途")
	@Column(name="ENDORSE_PURPOSE",length=50)
	private String endorsePurpose;	
	
	@ManyToOne
	@FieldName(name="所属项目")
	@JoinColumn(name="PROJ_NAME_ID")
	private ProjInfo projNameId;
	
	@FieldName(name="款项")
	@Column(name="PAYMENT",length=50)
	private String payment;
	
	@FieldName(name="取得方式")
	@Column(name="ACCESS_METHOD",length=50)
	private String accessMethod;
	
	@FieldName(name="利率")
	@Column(name="RATE",precision = 22, scale = 4)
	private BigDecimal rate;
	
	@FieldName(name="利息")
	@Column(name="INTEREST",precision = 22, scale = 2)
	private BigDecimal interest;
	
	
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
	
	@FieldName(name = "上传文件名")
	@ManyToOne(targetEntity = BaseFile.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "UP_ID")
	private BaseFile upLoadId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getDraFitEndorser() {
		return draFitEndorser;
	}

	public void setDraFitEndorser(String draFitEndorser) {
		this.draFitEndorser = draFitEndorser;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public BigDecimal getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(BigDecimal ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getDrawerBank() {
		return drawerBank;
	}

	public void setDrawerBank(String drawerBank) {
		this.drawerBank = drawerBank;
	}

	public String getDrawerAccount() {
		return drawerAccount;
	}

	public void setDrawerAccount(String drawerAccount) {
		this.drawerAccount = drawerAccount;
	}

	public String getDrawer() {
		return drawer;
	}

	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}

	public DictionaryData getEndPayDis() {
		return endPayDis;
	}

	public void setEndPayDis(DictionaryData endPayDis) {
		this.endPayDis = endPayDis;
	}

	public String getEndPayDisDate() {
		return endPayDisDate;
	}

	public void setEndPayDisDate(String endPayDisDate) {
		this.endPayDisDate = endPayDisDate;
	}

	public String getEndorsee() {
		return endorsee;
	}

	public void setEndorsee(String endorsee) {
		this.endorsee = endorsee;
	}

	public String getEndorsePurpose() {
		return endorsePurpose;
	}

	public void setEndorsePurpose(String endorsePurpose) {
		this.endorsePurpose = endorsePurpose;
	}

	public ProjInfo getProjNameId() {
		return projNameId;
	}

	public void setProjNameId(ProjInfo projNameId) {
		this.projNameId = projNameId;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	
	public String getAccessMethod() {
		return accessMethod;
	}

	public void setAccessMethod(String accessMethod) {
		this.accessMethod = accessMethod;
	}

	

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
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

	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}

	
}
