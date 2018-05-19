package com.tenwa.leasing.entity.contract;

import java.math.BigDecimal;

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



/**
 * 
 * @author zhangc
 * @ClassName: ContractChangeAfterhire 
 * @date 2014年12月2日 下午3:17:42 
 * @Description:租后合同变更信息
 */
@Entity
@FieldName(name = "租后合同变更信息")
@Table(name = "CONTRACT_CHANGE_AFTERHIRE")
public class ContractChangeAfterhire {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@FieldName(name="ID")
	private String id;

	@FieldName(name = "流程编号")
	@Column(name = "DOC_ID")
	private String docId;
	
	@FieldName(name = "合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name = "变更时间")
	@Column(name = "CHANGE_DATE", length = 40)
	private String changeDate;
	
	
	@FieldName(name = "变更原因")
	@Column(name = "CHANGE_RESON", length = 2000)
	private String changeReason;
	
	@FieldName(name = "变更开始期项")
	@Column(name = "START_PLAN_LIST")
	private Integer startPlanlist;
	
	@FieldName(name="年利率")
	@Column(name="YEAR_RATE", precision = 22)
	private BigDecimal yearRate;
	
	
	@FieldName(name = "变更期次")
	@Column(name = "CHANGE_LISTS")
	private Integer changeLists;
	
	@ManyToOne
	@FieldName(name = "创建人")
	@JoinColumn(name = "CREATOR_")
	private User creator;

	@FieldName(name = "创建时间")
	@Column(name = "CREATE_DATE", length = 20)
	private String createDate;

	@ManyToOne
	@FieldName(name = "修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;

	@FieldName(name = "修改时间")
	@Column(name = "MODIFY_DATE", length = 20)
	private String modifyDate;

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

	public String getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public Integer getStartPlanlist() {
		return startPlanlist;
	}

	public void setStartPlanlist(Integer startPlanlist) {
		this.startPlanlist = startPlanlist;
	}

	public BigDecimal getYearRate() {
		return yearRate;
	}

	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}

	public Integer getChangeLists() {
		return changeLists;
	}

	public void setChangeLists(Integer changeLists) {
		this.changeLists = changeLists;
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
