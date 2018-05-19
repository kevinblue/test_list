package com.tenwa.leasing.entity.sapmaindatainfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@FieldName(name = "客户对应客户类别信息")
@Table(name="DESIGN_MASTER_DATA_INFO")
public class DesignMasterDataInfo {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	@FieldName(name="SAP主数据ID")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SAP_ID")
	private SapMainDataInfo sapId;
 
	@ManyToOne
	@FieldName(name = "SAP主数据流程")
	@JoinColumn(name="MAIN_DATA")
	private DictionaryData mainData;
 
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
	@Column(name="MODIFY_DATE")
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SapMainDataInfo getSapId() {
		return sapId;
	}

	public void setSapId(SapMainDataInfo sapId) {
		this.sapId = sapId;
	}

	public DictionaryData getMainData() {
		return mainData;
	}

	public void setMainData(DictionaryData mainData) {
		this.mainData = mainData;
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
