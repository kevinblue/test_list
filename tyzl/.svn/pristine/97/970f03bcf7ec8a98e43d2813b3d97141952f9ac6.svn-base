package com.reckon.entity.contract.reckon.cash;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;

/**
 * 
 * @author chuang
 *
 */
@Entity
@FieldName(name = "现金流补零辅助表")
@Table(name="CASH_HELP")
public class CashHelp {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@FieldName(name="现金流日期")
	@Column(name="CASH_DATE", length=20)
	private String   cashDate;
	
	@FieldName(name="流入量")
	@Column(name="CASH_VALUE",length = 2)
	private Integer cashValue;

	public String getCashDate() {
		return cashDate;
	}

	public void setCashDate(String cashDate) {
		this.cashDate = cashDate;
	}

	public Integer getCashValue() {
		return cashValue;
	}

	public void setCashValue(Integer cashValue) {
		this.cashValue = cashValue;
	}
	
	
	
}
