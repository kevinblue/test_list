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

import com.tenwa.kernal.annotation.FieldName;

@Entity
@FieldName(name = "流程APP分组中间表")
@Table(name="T_PHONEGROUP_DESIGNER")
public class PhoneGroupDesigner implements Serializable {
	private static final long serialVersionUID = -6297218341440605242L;
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
	@FieldName(name = "标识符")
    @Column(length=32,name="ID_")
    private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="APP分组信息")
	@JoinColumn(name = "PHONEGROUPID")
	private JbpmPhoneGroup phonegroupId;
	
	@Column(name = "PHONEGROUP_NAME", length = 40)
	@FieldName(name = "分组名称")
	private String phoneGroupName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="流程信息")
	@JoinColumn(name = "DESIGNERID")
	private JbpmWorkflowDesigner designerId;
	
	@Column(name = "DESIGNER_NAME", length = 40)
	@FieldName(name = "流程名称")
	private String designerName;

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

	public JbpmWorkflowDesigner getDesignerId() {
		return designerId;
	}

	public void setDesignerId(JbpmWorkflowDesigner designerId) {
		this.designerId = designerId;
	}

	public String getPhoneGroupName() {
		return phoneGroupName;
	}

	public void setPhoneGroupName(String phoneGroupName) {
		this.phoneGroupName = phoneGroupName;
	}

	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}
	
	
}
