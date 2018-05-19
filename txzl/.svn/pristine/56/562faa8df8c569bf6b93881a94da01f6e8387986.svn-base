package com.tenwa.leasing.entity.factoring;

import java.io.Serializable;

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

@Entity
@FieldName(name = "保理争议解除表")
@Table(name = "FACTORING_CONTROVERSY_RELIEVE")
public class FactoringControversyRelieve implements Serializable {

	private static final long serialVersionUID = 1842602351876048321L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@OneToOne(fetch = FetchType.LAZY)
	@FieldName(name = "保理争议申请ID")
	@JoinColumn(name = "FACTORING_CONTROVERSY_ID")
	private FactoringControversy factoringControversyId;

	@FieldName(name = "争议状态")
	@Column(name = "STATUS", length = 20)
	private Integer status;

	@FieldName(name = "争议解除日期")
	@Column(name = "RELIEVE_DATE", length = 20)
	private String relieveDate;

	@FieldName(name = "争议解除说明")
	@Column(name = "RELIEVE_EXPLAINATION", length = 4000)
	private String relieveExplaination;

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

	public FactoringControversy getFactoringControversyId() {
		return factoringControversyId;
	}

	public void setFactoringControversyId(
			FactoringControversy factoringControversyId) {
		this.factoringControversyId = factoringControversyId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRelieveDate() {
		return relieveDate;
	}

	public void setRelieveDate(String relieveDate) {
		this.relieveDate = relieveDate;
	}

	public String getRelieveExplaination() {
		return relieveExplaination;
	}

	public void setRelieveExplaination(String relieveExplaination) {
		this.relieveExplaination = relieveExplaination;
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
