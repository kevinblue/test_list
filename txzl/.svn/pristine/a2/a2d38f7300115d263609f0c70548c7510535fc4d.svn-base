package com.tenwa.jbpm.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@FieldName(name = "APP流程信息分组")
@Table(name = "T_JBPM_PHONEGROUP")
public class JbpmPhoneGroup implements Serializable {
	private static final long serialVersionUID = -6297218341440605242L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@FieldName(name = "标识符")
	@Column(length = 32, name = "ID_")
	private String id;

	@Column(name = "GROUP_NAME",unique=true, length = 20)
	@FieldName(name = "分组名称")
	private String groupName;

	@Column(name = "MEMO", length = 100)
	@FieldName(name = "描述")
	private String memo;

	@FieldName(name = "普通页面对应数据")
	@OneToMany(mappedBy = "phonegroupId", fetch = FetchType.LAZY)
	private Set<JbpmFormValues> jbpmFormValues = new HashSet<JbpmFormValues>();
	
	@FieldName(name = "多行控件对应数据")
	@OneToMany(mappedBy = "phonegroupId", fetch = FetchType.LAZY)
	private Set<JbpmListValues> jbpmListValues = new HashSet<JbpmListValues>();
	
	@FieldName(name = "流程APP分组中间表")
	@OneToMany(mappedBy = "phonegroupId", fetch = FetchType.LAZY)
	private Set<PhoneGroupDesigner> phoneGroupDesigner = new HashSet<PhoneGroupDesigner>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATOR_")
    @FieldName(name="创建人")
    private User creator;
	
	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name="创建时间")
	private String createDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name = "MODIFY_DATE", length = 40)
	private String modifyDate;
	
	@FieldName(name="位置")
	@Column(name="POSITION_")
	private Integer position;
	
    //1代表多行控件,其它默认为普通分组
	@FieldName(name="是否为多行控件")
	@Column(name="IS_LIST",columnDefinition="INT DEFAULT 0", length = 1)
	private Boolean isList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set<JbpmFormValues> getJbpmFormValues() {
		return jbpmFormValues;
	}

	public void setJbpmFormValues(Set<JbpmFormValues> jbpmFormValues) {
		this.jbpmFormValues = jbpmFormValues;
	}
	
	public Set<JbpmListValues> getJbpmListValues() {
		return jbpmListValues;
	}

	public void setJbpmListValues(Set<JbpmListValues> jbpmListValues) {
		this.jbpmListValues = jbpmListValues;
	}

	public Set<PhoneGroupDesigner> getPhoneGroupDesigner() {
		return phoneGroupDesigner;
	}

	public void setPhoneGroupDesigner(Set<PhoneGroupDesigner> phoneGroupDesigner) {
		this.phoneGroupDesigner = phoneGroupDesigner;
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

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Boolean getIsList() {
		return isList;
	}

	public void setIsList(Boolean isList) {
		this.isList = isList;
	}

}
