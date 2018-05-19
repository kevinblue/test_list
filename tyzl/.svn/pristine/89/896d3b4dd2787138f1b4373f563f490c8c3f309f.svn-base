package com.tenwa.leasing.entity.proj;

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
import com.tenwa.leasing.entity.cust.CustInfo;

@Entity
@FieldName(name = "联合承租人")
@Table(name = "PROJ_UNION_CUST")
public class ProjUnionCust {
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name="标识符")
	@Column(name = "ID", nullable = false, length = 64)
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID")
	@FieldName(name="项目")
	private ProjInfo projInfo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UNION_CUST")
	@FieldName(name="联合承租人")
	private CustInfo unioncust;
	
	@FieldName(name="联合承租人客户类")
	@Column(name="UNION_CUST_CLASS", length=100)	
	private String unioncustclass;
	

	@FieldName(name="是否主付款人")
	@Column(name="IS_MAIN_PAY", length=20)	
	private String isMainPay;
	
	@FieldName(name="备注")
	@Column(name="MEMO", length=2000)	
	private String memo;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProjInfo getProjInfo() {
		return projInfo;
	}

	public void setProjInfo(ProjInfo projInfo) {
		this.projInfo = projInfo;
	}

	public CustInfo getUnioncust() {
		return unioncust;
	}

	public void setUnioncust(CustInfo unioncust) {
		this.unioncust = unioncust;
	}

	public String getUnioncustclass() {
		return unioncustclass;
	}

	public void setUnioncustclass(String unioncustclass) {
		this.unioncustclass = unioncustclass;
	}

	public String getIsMainPay() {
		return isMainPay;
	}

	public void setIsMainPay(String isMainPay) {
		this.isMainPay = isMainPay;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
