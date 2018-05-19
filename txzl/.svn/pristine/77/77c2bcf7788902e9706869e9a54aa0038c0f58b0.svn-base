package com.tenwa.leasing.entity.proj;

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

/**
 * ProjInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@FieldName(name = "项目立项委员会投票")
@Table(name = "PROJ_PRE_VOTE")
public class ProjPreVote implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name="标识符")
	@Column(name = "ID", nullable = false, length = 64)
	private String id;
	
	@FieldName(name = "项目")
	@ManyToOne(targetEntity = ProjInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID")
	private ProjInfo projId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VOTER")
	@FieldName(name="投票人")
	private User voter;
	
	@Column(name = "PREVOTE_VIEW", length = 1000)
	@FieldName(name="投票意见")
	private String prevoteView;
	
	@Column(name = "PREIS_VIEW", length =50)
	@FieldName(name="是否同意")
	private String preisView;
	
	@Column(name = "DOC_ID", length = 510)
	@FieldName(name="流程编号")
	private String docId;

	@Column(name = "FLOW_NAME", length = 510)
	@FieldName(name="流程名")
	private String flowName;
	
	@ManyToOne
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE", length=20)
	private String createDate;
	
	@ManyToOne
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name = "更新日期")
	@Column(name="MODIFY_DATE", length=20)
	private String modifyDate;
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public ProjInfo getProjId() {
		return projId;
	}


	public void setProjId(ProjInfo projId) {
		this.projId = projId;
	}


	public User getVoter() {
		return voter;
	}


	public void setVoter(User voter) {
		this.voter = voter;
	}


	public String getDocId() {
		return docId;
	}


	public void setDocId(String docId) {
		this.docId = docId;
	}


	public String getFlowName() {
		return flowName;
	}


	public void setFlowName(String flowName) {
		this.flowName = flowName;
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


	public String getPrevoteView() {
		return prevoteView;
	}


	public void setPrevoteView(String prevoteView) {
		this.prevoteView = prevoteView;
	}


	public String getPreisView() {
		return preisView;
	}


	public void setPreisView(String preisView) {
		this.preisView = preisView;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}