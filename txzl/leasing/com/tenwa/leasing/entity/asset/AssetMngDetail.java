package com.tenwa.leasing.entity.asset;

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
import com.tenwa.leasing.entity.cust.CustInfo;

@Entity
@FieldName(name = "资产巡视计划申请详细")
@Table(name="ASSET_MNG_DETAIL")
public class AssetMngDetail {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="申请ID")
	@ManyToOne(targetEntity=AssetMngApply.class,fetch=FetchType.LAZY)
	@JoinColumn(name="APPLY_ID")
	private AssetMngApply applyid ;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUST_ID")
	@FieldName(name="客户ID")
	private CustInfo custInfo;
	
	@FieldName(name="客户名称")
	@Column(name="CUST_NAME")
	private String custname ;
	
	@FieldName(name = "组织机构代码/身份证号码")
	@Column(name="CODE")
	private String code;
	
	@FieldName(name = "巡视时间")
	@Column(name="XUN_DATE",length =4000)
	private String xundate;

	@FieldName(name="巡视原因")
	@Column(name="XUN_RESULT",length =4000)
	private String xunresult;
	
	@FieldName(name = "巡视重点")
	@Column(name="XUN_THING",length =4000)
	private String xunthing;

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

	public AssetMngApply getApplyid() {
		return applyid;
	}

	public void setApplyid(AssetMngApply applyid) {
		this.applyid = applyid;
	}

	public CustInfo getCustInfo() {
		return custInfo;
	}

	public void setCustInfo(CustInfo custInfo) {
		this.custInfo = custInfo;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getXundate() {
		return xundate;
	}

	public void setXundate(String xundate) {
		this.xundate = xundate;
	}

	public String getXunresult() {
		return xunresult;
	}

	public void setXunresult(String xunresult) {
		this.xunresult = xunresult;
	}

	public String getXunthing() {
		return xunthing;
	}

	public void setXunthing(String xunthing) {
		this.xunthing = xunthing;
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