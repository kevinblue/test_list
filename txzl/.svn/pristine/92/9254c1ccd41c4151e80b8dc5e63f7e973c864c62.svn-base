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
 * @date 2013-9-16下午04:29:46
 * @info
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "凭证记录表")
@Table(name="INTEREAS_VOUCHERLOG")
public class IntereasVoucherLog {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id ;
	
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@FieldName(name="备注")
	@Column(name="memo")	
	private String memo;
	
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@FieldName(name="消息")
	@Column(name="message")	
	private String message;
	
	@FieldName(name="租赁系统凭证号")
	@Column(name="ELEASING_VOUCHERNUMBER", length=20)
	private String eleasingVouchernumber;
	
	@FieldName(name="金蝶EAS系统凭证号")
	@Column(name="EAS_VOUCHERNUMBER", length=20)
	private String easVouchernumber;
	
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
	public String getEleasingVouchernumber() {
		return eleasingVouchernumber;
	}

	public void setEleasingVouchernumber(String eleasingVouchernumber) {
		this.eleasingVouchernumber = eleasingVouchernumber;
	}

	public String getEasVouchernumber() {
		return easVouchernumber;
	}

	public void setEasVouchernumber(String easVouchernumber) {
		this.easVouchernumber = easVouchernumber;
	}
}
