package com.tenwa.leasing.entity.filingmng;


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
import com.tenwa.leasing.entity.lawmng.LawApproval;

@Entity
@Table(name = "LAW_FILING_NOTICE")
@FieldName(name="立案事项通知信息 【url: workflow/forms/filing_mng/filing_cost/filing_cost_open_list.bi】")
public class LawFilingNotice implements java.io.Serializable {
	
	private static final long serialVersionUID = 3141494191853495745L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 64)
	@FieldName(name="标识符")
	private String id;
	
	@FieldName(name = "法务申请流程ID")
	@ManyToOne(targetEntity = LawApproval.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "LAWAPPROVAL_ID")
	private LawApproval lawapprovalId;
	
	@Column(name = "LAWNUM", length =100)
	@FieldName(name="法务编号")
	private String lawnum;
	
	@Column(name = "FILINGNO", length =100)
	@FieldName(name="案号")
	private String filingno;
	
	@Column(name = "FILINGGACT", length =100)
	@FieldName(name="采理法院")
	private String filinggact;
	
	@Column(name = "FILINGJUDGE", length = 50)
	@FieldName(name="主办法官")
	private String filingjudge;
	
	@Column(name = "FILINGDATE",length = 100)
	@FieldName(name="立案日期")
	private String filingdate;
	
	@Column(name = "FILINGTEL",length = 50)
	@FieldName(name="联系方式")
	private String filingtel;
	
	@Column(name = "FILINGREQ",length = 4000)
	@FieldName(name="诉讼请求")
	private String filingreq;
	
	@Column(name = "FILINGREASON",length = 4000)
	@FieldName(name="延迟立案原因")
	private String filingreason;
	
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
	
	@Column(name = "DRAWALREASON", length = 4000)
	@FieldName(name="撤诉原因")
	private String drawalreason;

	@Column(name = "DRAWAL_DATE", length = 40)
	@FieldName(name="撤诉时间")
	private String drawalDate;
	
	
	public LawApproval getLawapprovalId() {
		return lawapprovalId;
	}

	public void setLawapprovalId(LawApproval lawapprovalId) {
		this.lawapprovalId = lawapprovalId;
	}

	public String getId() {
		return id;
	}

	public String getDrawalreason() {
		return drawalreason;
	}

	public void setDrawalreason(String drawalreason) {
		this.drawalreason = drawalreason;
	}

	public String getDrawalDate() {
		return drawalDate;
	}

	public void setDrawalDate(String drawalDate) {
		this.drawalDate = drawalDate;
	}

	public void setId(String id) {
		this.id = id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLawnum() {
		return lawnum;
	}

	public void setLawnum(String lawnum) {
		this.lawnum = lawnum;
	}

	public String getFilingno() {
		return filingno;
	}

	public void setFilingno(String filingno) {
		this.filingno = filingno;
	}

	public String getFilinggact() {
		return filinggact;
	}

	public void setFilinggact(String filinggact) {
		this.filinggact = filinggact;
	}

	public String getFilingjudge() {
		return filingjudge;
	}

	public void setFilingjudge(String filingjudge) {
		this.filingjudge = filingjudge;
	}

	public String getFilingdate() {
		return filingdate;
	}

	public void setFilingdate(String filingdate) {
		this.filingdate = filingdate;
	}

	public String getFilingtel() {
		return filingtel;
	}

	public void setFilingtel(String filingtel) {
		this.filingtel = filingtel;
	}

	public String getFilingreq() {
		return filingreq;
	}

	public void setFilingreq(String filingreq) {
		this.filingreq = filingreq;
	}

	public String getFilingreason() {
		return filingreason;
	}

	public void setFilingreason(String filingreason) {
		this.filingreason = filingreason;
	}
	
}