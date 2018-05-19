package com.tenwa.leasing.entity.finacial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

/**
 * 
 * @author zyh
 * @date 2016-9-23下午09:33:10
 * @info 利息计提表
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "利息计提表")
@Table(name="INTEREST_PROVISION")
public class InterestProvision {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="流水号")
	@Column(name="SN", length=50)
	private String sN;//
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "是否开票")
	@JoinColumn(name = "IS_BILLING")
	private DictionaryData isBilling;
	
	
	public DictionaryData getIsBilling() {
		return isBilling;
	}

	public void setIsBilling(DictionaryData isBilling) {
		this.isBilling = isBilling;
	}

	@FieldName(name="日期")
	@Column(name="DIDATE",length=50)
	private String didate;
		
	@FieldName(name="备注")
	@Column(name="NOTE_",length=50)
	private String note;
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

	public String getsN() {
		return sN;
	}

	public void setsN(String sN) {
		this.sN = sN;
	}



	public String getDidate() {
		return didate;
	}

	public void setDidate(String didate) {
		this.didate = didate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
