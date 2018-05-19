package com.tenwa.leasing.entity.other;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@Table(name = "T_BUSINESS_CREDIT_CONFIG")
public class BusinessCreditConfig {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@FieldName(name = "标识符")
	private String id;

	@ManyToOne
	@FieldName(name = "业务部ID")
	@JoinColumn(name = "BUSINESS_DEPT")
	private Department businessDept;

	@ManyToOne
	@FieldName(name = "行业ID")
	@JoinColumn(name = "INDUSTRY_ID")
	private DictionaryData industryId;

	@ManyToOne
	@FieldName(name = "信审部ID")
	@JoinColumn(name = "CREDIT_DEPT")
	private Department creditDept;

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

	public Department getBusinessDept() {
		return businessDept;
	}

	public void setBusinessDept(Department businessDept) {
		this.businessDept = businessDept;
	}

	public DictionaryData getIndustryId() {
		return industryId;
	}

	public void setIndustryId(DictionaryData industryId) {
		this.industryId = industryId;
	}

	public Department getCreditDept() {
		return creditDept;
	}

	public void setCreditDept(Department creditDept) {
		this.creditDept = creditDept;
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
