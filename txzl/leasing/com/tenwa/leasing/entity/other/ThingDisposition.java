package com.tenwa.leasing.entity.other;


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

@Entity
@Table(name = "THING_DISPOSITION")
@FieldName(name="重大事项申请")
public class ThingDisposition implements java.io.Serializable {

	private static final long serialVersionUID = -1174732233135947652L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 64)
	@FieldName(name="标识符")
	private String id;
	
	@Column(name = "dnum",length = 100)
	@FieldName(name="唯一的编号")
	private String dnum;
	
	@Column(name = "CONTRACT_ID",length = 100)
	@FieldName(name="合同表主键id")
	private String contractId;
	 
	@Column(name = "THING_MEMO",length = 4000)
	@FieldName(name="重大事项内容")
	private String thingmemo;
	
	@Column(name = "OCCUR_DATE",length = 100)
	@FieldName(name="发生时间")
	private String occurdate;
	
	@Column(name = "FOLLOW_STEP",length =  4000)
	@FieldName(name="跟踪措施")
	private String followstep;
	
	@Column(name = "OVER_PLAN",length =  4000)
	@FieldName(name="后续计划")
	private String overplan;
	
	@Column(name = "THING_STATUS",length = 100)
	@FieldName(name="重大事项状态")
	private String thingstatus;
	
	@Column(name = "ASSET_TYPE",length = 100)
	@FieldName(name="资产分类")
	private String assettype;
	
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

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDnum() {
		return dnum;
	}

	public void setDnum(String dnum) {
		this.dnum = dnum;
	}

	public String getThingmemo() {
		return thingmemo;
	}

	public void setThingmemo(String thingmemo) {
		this.thingmemo = thingmemo;
	}

	public String getOccurdate() {
		return occurdate;
	}

	public void setOccurdate(String occurdate) {
		this.occurdate = occurdate;
	}

	public String getFollowstep() {
		return followstep;
	}

	public void setFollowstep(String followstep) {
		this.followstep = followstep;
	}

	public String getOverplan() {
		return overplan;
	}

	public void setOverplan(String overplan) {
		this.overplan = overplan;
	}

	public String getThingstatus() {
		return thingstatus;
	}

	public void setThingstatus(String thingstatus) {
		this.thingstatus = thingstatus;
	}

	public String getAssettype() {
		return assettype;
	}

	public void setAssettype(String assettype) {
		this.assettype = assettype;
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