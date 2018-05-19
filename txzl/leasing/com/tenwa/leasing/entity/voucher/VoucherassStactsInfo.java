package com.tenwa.leasing.entity.voucher;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;


/**
 * 
 * @author 孙传良
 * @date 2013-9-13下午05:34:32
 * @info 辅助账类型表
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "辅助账类型表")
@Table(name="VOUCHERASSSTACTS_INFO")
public class VoucherassStactsInfo {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id ;
	
	@FieldName(name="辅助账类型编号")
	@Column(name="ASSTACTTYPE", length=50)	
	private String asstActType;
	
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@FieldName(name="辅助账类型名称")
	@Column(name="ASSTACTTYPENAME")	
	private String asstActTypeName;
	
	@FieldName(name="备注")
	@Column(name="MEMO", length=1000)	
	private String memo;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAsstActType() {
		return asstActType;
	}

	public void setAsstActType(String asstActType) {
		this.asstActType = asstActType;
	}

	public String getAsstActTypeName() {
		return asstActTypeName;
	}

	public void setAsstActTypeName(String asstActTypeName) {
		this.asstActTypeName = asstActTypeName;
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
