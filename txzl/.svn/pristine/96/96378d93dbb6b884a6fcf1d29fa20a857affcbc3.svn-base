package com.tenwa.leasing.entity.lawmng;


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
@Table(name = "LAW_LOG")
@FieldName(name="法务日志信息 【url: /workflow/forms/law_mng/law_log/law_log_list.bi】")
public class LawLog implements java.io.Serializable {

	private static final long serialVersionUID = 3007301093288854797L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 64)
	@FieldName(name="标识符")
	private String id;
	 
	
	@Column(name = "LAWREG_DATE",length = 100)
	@FieldName(name="法务登记日期")
	private String lawregdate;
	
	@Column(name = "THING_MEMO",length = 4000)
	@FieldName(name="事项说明")
	private String thingmemo;
	
	@FieldName(name = "外键")
	@Column(name = "RELATE_KEY", length = 100)
	private String relateKey;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODIFICATOR_")
	@FieldName(name="修改人")
	private User modificator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_")
	@FieldName(name="创建人")
	private User creator;

	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name="创建时间")
	private String createDate;

	@Column(name = "MODIFY_DATE", length = 40)
	@FieldName(name="修改时间")
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLawregdate() {
		return lawregdate;
	}

	public void setLawregdate(String lawregdate) {
		this.lawregdate = lawregdate;
	}

	
	
	public String getRelateKey() {
		return relateKey;
	}

	public void setRelateKey(String relateKey) {
		this.relateKey = relateKey;
	}

	public String getThingmemo() {
		return thingmemo;
	}

	public void setThingmemo(String thingmemo) {
		this.thingmemo = thingmemo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public User getModificator() {
		return modificator;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
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

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
							
}