package com.tenwa.leasing.entity.voucher;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
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
 * <p>凭证基本信息配置子表。</p>
 * <p>凭证基本信息配置表与辅助帐类型信息表之前的关联表。</p>
 * <p>2014-2-12</p>
 * @author sea
 * @version 4.5
 */
@Entity
@FieldName(name = "凭证基本信息配置子表")
@Table(name="VOUCHER_CONNECTION")
public class VoucherConnection {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id ;
	
	@ManyToOne
	@FieldName(name="科目辅助账类型配置表ID")
	@JoinColumn(name="CONFIG_ID")
	private VoucherassStactsConfig configid;
	
	@ManyToOne
	@FieldName(name="辅助账类型编号")
	@JoinColumn(name="ASSTACTTYPE")
	private VoucherassStactsInfo asstActType;
	
	@FieldName(name="预留字段1")
	@Column(name="FILED1", length=100)	
	private String filed1;
	
	@FieldName(name="预留字段2")
	@Column(name="FILED2", length=100)	
	private String filed2;
	
	@FieldName(name="预留字段3")
	@Column(name="FILED3", length=100)	
	private String filed3;
	
	@FieldName(name="预留字段4")
	@Column(name="FILED4", length=10)
	private Integer filed4;
	
	@FieldName(name="预留字段5")
	@Column(name="FILED5",precision=22,scale=2)
	private BigDecimal filed5;
	
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
	
	@FieldName(name = "是否有效")
	@Column(name="status", length = 5,columnDefinition="INT default 0")
	private int status;//0是有效； 1为作废
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public VoucherassStactsConfig getConfigid() {
		return configid;
	}

	public void setConfigid(VoucherassStactsConfig configid) {
		this.configid = configid;
	}

	public VoucherassStactsInfo getAsstActType() {
		return asstActType;
	}

	public void setAsstActType(VoucherassStactsInfo asstActType) {
		this.asstActType = asstActType;
	}

	public String getFiled1() {
		return filed1;
	}

	public void setFiled1(String filed1) {
		this.filed1 = filed1;
	}

	public String getFiled2() {
		return filed2;
	}

	public void setFiled2(String filed2) {
		this.filed2 = filed2;
	}

	public String getFiled3() {
		return filed3;
	}

	public void setFiled3(String filed3) {
		this.filed3 = filed3;
	}

	public Integer getFiled4() {
		return filed4;
	}

	public void setFiled4(Integer filed4) {
		this.filed4 = filed4;
	}

	public BigDecimal getFiled5() {
		return filed5;
	}

	public void setFiled5(BigDecimal filed5) {
		this.filed5 = filed5;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
