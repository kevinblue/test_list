package com.tenwa.leasing.entity.paymentrhythm;

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
@Entity
@FieldName(name = "付款节奏维护")
@Table(name="PAYMENT_RHYTHM")
public class PaymentRhythm {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@ManyToOne
	@FieldName(name="贸易类型")
	@JoinColumn(name="VNDR_TYPE")
	private DictionaryData vndrtype ;
	
	@FieldName(name = "付款笔数")
	@Column(name="PAYMENT_PEN")
	private String paymentpen;

	@FieldName(name="支付条件")
	@Column(name="TERMS_PAYMET", length=1000)
	private String termspayment ;
	
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



	public DictionaryData getVndrtype() {
		return vndrtype;
	}

	public void setVndrtype(DictionaryData vndrtype) {
		this.vndrtype = vndrtype;
	}

	public String getPaymentpen() {
		return paymentpen;
	}

	public void setPaymentpen(String paymentpen) {
		this.paymentpen = paymentpen;
	}

	public String getTermspayment() {
		return termspayment;
	}

	public void setTermspayment(String termspayment) {
		this.termspayment = termspayment;
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
