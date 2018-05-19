package com.tenwa.leasing.entity.lawmng;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;

@Entity
@Table(name = "LAW_CLOSE")
@FieldName(name="法务结案流程信息 【url: workflow/forms/law_mng/law_close/law_close_open_list.bi】")
public class LawClose implements java.io.Serializable {

	private static final long serialVersionUID = 1428059606565007580L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 64)
	@FieldName(name="标识符")
	private String id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACTINFO_ID")
	@FieldName(name="合同信息主键  id")
	private ContractInfo contractInfo;
	
	@Column(name = "LAWTYPE", length = 50)
	@FieldName(name="费用类型")
	private String lawtype;
	
	@Column(name = "CLOSEINFO",length = 100)
	@FieldName(name="结案信息")
	private String closeinfo;

	@Column(name = "CLOSEMONEY",length = 100)
	@FieldName(name="结案金额")
	private String closemoney;
	
	@Column(name = "CLOSEMEMO",length = 4000)
	@FieldName(name="法务处理说明")
	private String closememo;
	
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

	public ContractInfo getContractInfo() {
		return contractInfo;
	}

	public void setContractInfo(ContractInfo contractInfo) {
		this.contractInfo = contractInfo;
	}

	public String getLawtype() {
		return lawtype;
	}

	public void setLawtype(String lawtype) {
		this.lawtype = lawtype;
	}

	public String getCloseinfo() {
		return closeinfo;
	}

	public void setCloseinfo(String closeinfo) {
		this.closeinfo = closeinfo;
	}

	public String getClosemoney() {
		return closemoney;
	}

	public void setClosemoney(String closemoney) {
		this.closemoney = closemoney;
	}

	public String getClosememo() {
		return closememo;
	}

	public void setClosememo(String closememo) {
		this.closememo = closememo;
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
	

}