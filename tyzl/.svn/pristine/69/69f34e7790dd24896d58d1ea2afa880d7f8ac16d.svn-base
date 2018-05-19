package com.tenwa.leasing.entity.file;

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

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@Table(name = "BASE_FILE_TEMPLATE")
@FieldName(name="模板类")
public class BaseFileTemplate implements java.io.Serializable {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	@FieldName(name="模板编号")
	@Column(name="TEMPLATE_NO")
	private String templateNo;
	
	@FieldName(name="模板显示名称")
	@Column(name="TEMPLATE_SHOW_NAME")
	private String templateShowName;

	@FieldName(name="模板名称")
	@Column(name="TEMPLATE_NAME")
	private String templateName;
	
	@FieldName(name="一级分类")
	@JoinColumn(name="ONE_CLASSIFY")
	@ManyToOne
	private DictionaryData oneClassify;
	
	@FieldName(name="二级分类")
	@JoinColumn(name="TWO_CLASSIFY")
	@ManyToOne
	private DictionaryData twoClassify;
	


	@FieldName(name="三级分类")
	@JoinColumn(name="THREE_CLASSIFY")
	@ManyToOne
	private DictionaryData threeClassify;
	
	@FieldName(name="四级分类")
	@JoinColumn(name="FOUR_CLASSIFY")
	@ManyToOne
	private DictionaryData fourClassify;
	
	@FieldName(name="排序位置")
	@Column(name="TEMPLATE_Index")
	private String templateIndex;
	
	@FieldName(name="是否起用[是/否--0/1][S]")
	@Column(name="STATUS_",length=2)
	private String status;
	
	@FieldName(name="五级分类")
	@JoinColumn(name="FIVE_CLASSIFY")
	@ManyToOne
	private DictionaryData fiveClassify;
	
	@FieldName(name="六级分类")
	@JoinColumn(name="SIX_CLASSIFY")
	@ManyToOne
	private DictionaryData sixClassify;
	
	@FieldName(name="七级分类")
	@JoinColumn(name="SEVEN_CLASSIFY")
	@ManyToOne
	private DictionaryData sevenClassify;
	
	@FieldName(name="八级分类")
	@JoinColumn(name="EIGHT_CLASSIFY")
	@ManyToOne
	private DictionaryData eightClassify;
	


	@FieldName(name="合同模板文件地址")
	@Column(name="TEMPLATE_PATH")
	private String templatePath;

	@FieldName(name="模板配置数据")
	@OneToMany(mappedBy="fileTemplate",fetch=FetchType.LAZY) 
	private Set<BaseFileTemplateData> fileTemplateDataConfigs = new HashSet<BaseFileTemplateData>();
	
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR")
	@ManyToOne
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE", length=20)	
	private String createDate;
	
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR")
	@ManyToOne
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE", length=20)	
	private String modifyDate;

	public String getId() {
		return id;
	}
	public String getTemplateShowName() {
		return templateShowName;
	}

	public String getTemplateName() {
		return templateName;
	}

	public DictionaryData getOneClassify() {
		return oneClassify;
	}

	public DictionaryData getTwoClassify() {
		return twoClassify;
	}

	 

	public DictionaryData getFourClassify() {
		return fourClassify;
	}

	public DictionaryData getFiveClassify() {
		return fiveClassify;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public User getCreator() {
		return creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public User getModificator() {
		return modificator;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public void setTemplateShowName(String templateShowName) {
		this.templateShowName = templateShowName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public void setOneClassify(DictionaryData oneClassify) {
		this.oneClassify = oneClassify;
	}

	public void setTwoClassify(DictionaryData twoClassify) {
		this.twoClassify = twoClassify;
	}

	public DictionaryData getThreeClassify() {
		return threeClassify;
	}
	public void setThreeClassify(DictionaryData threeClassify) {
		this.threeClassify = threeClassify;
	}
	public void setFourClassify(DictionaryData fourClassify) {
		this.fourClassify = fourClassify;
	}

	public void setFiveClassify(DictionaryData fiveClassify) {
		this.fiveClassify = fiveClassify;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}


	public String getTemplateNo() {
		return templateNo;
	}

 


	public void setTemplateNo(String templateNo) {
		this.templateNo = templateNo;
	}


	 
	public Set<BaseFileTemplateData> getFileTemplateDataConfigs() {
		return fileTemplateDataConfigs;
	}
	public void setFileTemplateDataConfigs(
			Set<BaseFileTemplateData> fileTemplateDataConfigs) {
		this.fileTemplateDataConfigs = fileTemplateDataConfigs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public DictionaryData getSixClassify() {
		return sixClassify;
	}
	public void setSixClassify(DictionaryData sixClassify) {
		this.sixClassify = sixClassify;
	}
	public DictionaryData getSevenClassify() {
		return sevenClassify;
	}
	public void setSevenClassify(DictionaryData sevenClassify) {
		this.sevenClassify = sevenClassify;
	}
	public DictionaryData getEightClassify() {
		return eightClassify;
	}
	public void setEightClassify(DictionaryData eightClassify) {
		this.eightClassify = eightClassify;
	}
	public String getTemplateIndex() {
		return templateIndex;
	}
	public void setTemplateIndex(String templateIndex) {
		this.templateIndex = templateIndex;
	}
	

}