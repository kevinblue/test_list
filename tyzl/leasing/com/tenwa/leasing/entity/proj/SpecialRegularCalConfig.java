package com.tenwa.leasing.entity.proj;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * 租前息配置been
 * @author Administrator
 *
 */
@Entity
@FieldName(name = "特殊规则测算配置表")
@Table(name = "SPECIAL_REGULAR_CALCONFIG")
public class SpecialRegularCalConfig {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;//ID
	
	@FieldName(name = "报价编号")
	@Column(name = "DOC_ID")
	private String docId;//docId
	
	@FieldName(name = "合同编号")
	@OneToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;//合同信息编号
	
	@FieldName(name = "项目编号")
	@OneToOne(targetEntity = ProjInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID")
	private ProjInfo projId;//项目编号
	
	@ManyToOne
	@FieldName(name="客户")
	@JoinColumn(name="CUST_ID")
	private CustInfo custId;//客户编号
	
	@FieldName(name = "特殊规则测算配置json")
	@Lob
	@Column(name = "SPECIAL_REGULAR_JSONS")
	private String specialRegularJsons;
	
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

	@FieldName(name = "测算")
	@OneToMany(mappedBy = "specialRegularCal")
	private Set<SpecialRegular> specialRegulars = new HashSet<SpecialRegular>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public ProjInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjInfo projId) {
		this.projId = projId;
	}


	public CustInfo getCustId() {
		return custId;
	}

	public void setCustId(CustInfo custId) {
		this.custId = custId;
	}

	public String getSpecialRegularJsons() {
		return specialRegularJsons;
	}

	public void setSpecialRegularJsons(String specialRegularJsons) {
		this.specialRegularJsons = specialRegularJsons;
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

	public Set<SpecialRegular> getSpecialRegulars() {
		return specialRegulars;
	}

	public void setSpecialRegulars(Set<SpecialRegular> specialRegulars) {
		this.specialRegulars = specialRegulars;
	}
	
}
