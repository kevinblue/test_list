package com.tenwa.jbpm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@FieldName(name = "流程分组科目")
@Table(name="T_JBPM_FORMVALUES")
public class JbpmFormValues implements Serializable {
	private static final long serialVersionUID = -6297218341440605242L;
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
	@FieldName(name = "标识符")
    @Column(length=32,name="ID_")
    private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PHONEGROUPID")
	@FieldName(name="科目主表")
	private JbpmPhoneGroup phonegroupId;
	
	@Column(name = "SUBJECT_NAME", length = 40)
	@FieldName(name = "科目名称")
	private String subjectName;
	
	@Column(name = "SUBJECT_CODE", length = 100)
	@FieldName(name = "科目域名")
	private String subjectCode;
	
	@Column(name = "SUBJECT_TYPE", length = 100)
	@FieldName(name = "数据类型")
	private String subjectType;
	
	@Column(name = "QUERY_SQL", length = 200)
	@FieldName(name = "自定义SQL")
	private String querySQL;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_")
	@FieldName(name = "创建人")
	private User creator;

	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name = "创建时间")
	private String createDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;

	@FieldName(name = "修改时间")
	@Column(name = "MODIFY_DATE", length = 40)
	private String modifyDate;
	
	@FieldName(name="位置")
	@Column(name="POSITION_")
	private Integer position;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JbpmPhoneGroup getPhonegroupId() {
		return phonegroupId;
	}

	public void setPhonegroupId(JbpmPhoneGroup phonegroupId) {
		this.phonegroupId = phonegroupId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getQuerySQL() {
		return querySQL;
	}

	public void setQuerySQL(String querySQL) {
		this.querySQL = querySQL;
	}

}
