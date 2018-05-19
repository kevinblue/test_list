package com.tenwa.leasing.entity.conference;

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
import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * 
 * @author hucl
 * @date 2017-01-03 下午2:24:10
 * @info 决策会议审批
 * Tenwa
 *
 */
@Entity
@FieldName(name = "总经理办公会会议审批")	
@Table(name="CONFERENCE_DECISION")
public class ConferenceDecision {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name = "项目编号")
	@Column(name = "PROJ_ID", length=100)
	private String projId;
	
	@FieldName(name="会议编号")
	@Column(name="CONFER_NUMBER", length=300)
	private String  confernumber;
	
	@FieldName(name="议案标题")
	@Column(name="CONFER_NAME", length=500)
	private String  confername;

	@FieldName(name="议案内容")
	@Column(name="CONFER_ABSTRACT",length=3000)
	private String  conferabstract;

	@FieldName(name="决策点")
	@Column(name="DECISION_POINT",length=1000)
	private String  decisionpoint;
	
	@FieldName(name="提案人")
	@Column(name="ORIGINATOR",length=300)
	private String  originator;
	
	@FieldName(name="发起时间")
	@Column(name="ORIGINATIME",length=300)
	private String  originatime;
	
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
	
	public String getConfernumber() {
		return confernumber;
	}

	public void setConfernumber(String confernumber) {
		this.confernumber = confernumber;
	}

	public String getConfername() {
		return confername;
	}

	public void setConfername(String confername) {
		this.confername = confername;
	}

	public String getConferabstract() {
		return conferabstract;
	}

	public void setConferabstract(String conferabstract) {
		this.conferabstract = conferabstract;
	}

	public String getOriginator() {
		return originator;
	}

	public void setOriginator(String originator) {
		this.originator = originator;
	}

	public String getOriginatime() {
		return originatime;
	}

	public void setOriginatime(String originatime) {
		this.originatime = originatime;
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

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getDecisionpoint() {
		return decisionpoint;
	}

	public void setDecisionpoint(String decisionpoint) {
		this.decisionpoint = decisionpoint;
	}

	

	
	
	
}
