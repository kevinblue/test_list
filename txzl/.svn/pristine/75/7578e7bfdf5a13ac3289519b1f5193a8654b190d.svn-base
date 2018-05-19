package com.tenwa.business.entity;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;


@FieldName(name = "粗利计划利率表")
@Table(name = "BASE_COARSE")
@Entity
public class BaseCoarse {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@FieldName(name = "实际授信月数类型")
	@JoinColumn(name = "CREDIT_MONTH_TYPE")
	@ManyToOne
	private DictionaryData creditMonthType;

	@FieldName(name = "实际授信开始月数")
	@Column(name = "BEGIN_MONTH")
	private Integer beginMonth;

	@FieldName(name = "实际授信结束月数")
	@Column(name = "END_MONTH")
	private Integer endMonth;

	@FieldName(name = "固定利率")
	@Column(name = "BASE_RATE", precision = 22, scale = 6)
	private BigDecimal baseRate;

	@FieldName(name = "浮动利率")
	@Column(name = "FLOAT_RATE", precision = 22, scale = 6)
	private BigDecimal floatRate;

	@FieldName(name = "开始执行日期")
	@Column(name = "START_DATE_", length = 20)
	private String startDate;

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

	public DictionaryData getCreditMonthType() {
		return creditMonthType;
	}

	public void setCreditMonthType(DictionaryData creditMonthType) {
		this.creditMonthType = creditMonthType;
	}

	public Integer getBeginMonth() {
		return beginMonth;
	}

	public void setBeginMonth(Integer beginMonth) {
		this.beginMonth = beginMonth;
	}

	public Integer getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(Integer endMonth) {
		this.endMonth = endMonth;
	}

	public BigDecimal getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(BigDecimal baseRate) {
		this.baseRate = baseRate;
	}

	public BigDecimal getFloatRate() {
		return floatRate;
	}

	public void setFloatRate(BigDecimal floatRate) {
		this.floatRate = floatRate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
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
