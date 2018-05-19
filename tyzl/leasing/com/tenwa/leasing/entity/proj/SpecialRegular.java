package com.tenwa.leasing.entity.proj;

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

@Entity
@FieldName(name = "特殊规则测算配置表")
@Table(name = "SPECIAL_REGULAR")
public class SpecialRegular {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;// ID
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "P_ID")
	@FieldName(name="项目")
	private SpecialRegularCalConfig specialRegularCal;

	@Column(name = "startlist")
	@FieldName(name="开始期次")
	private Integer startlist;
	
	@Column(name = "endlist")
	@FieldName(name="结束期次")
	private Integer endlist;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "regular_settlemethod")
	@FieldName(name="计算方式")
	private DictionaryData regular_settlemethod;
     
	@Column(name = "regular_months")
	@FieldName(name="间隔")
	private Integer  regular_months;
	
	@Column(name = "rate")
	@FieldName(name="每期本金占总本金比例")
	private String rate;
	
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

	public SpecialRegularCalConfig getSpecialRegularCal() {
		return specialRegularCal;
	}

	public void setSpecialRegularCal(SpecialRegularCalConfig specialRegularCal) {
		this.specialRegularCal = specialRegularCal;
	}
	public Integer getStartlist() {
		return startlist;
	}
	public void setStartlist(Integer startlist) {
		this.startlist = startlist;
	}
	public Integer getEndlist() {
		return endlist;
	}
	public void setEndlist(Integer endlist) {
		this.endlist = endlist;
	}
	public DictionaryData getRegular_settlemethod() {
		return regular_settlemethod;
	}
	public void setRegular_settlemethod(DictionaryData regular_settlemethod) {
		this.regular_settlemethod = regular_settlemethod;
	}
	public Integer getRegular_months() {
		return regular_months;
	}
	public void setRegular_months(Integer regular_months) {
		this.regular_months = regular_months;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
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
