package com.tenwa.leasing.entity.file;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

/**
 * BaseFileTemplateData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BASE_FILE_TEMPLATE_DATA")
@FieldName(name="模板配置数据")
public class BaseFileTemplateData implements java.io.Serializable {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
    private String id;
	
	@FieldName(name="模板")
	@ManyToOne(targetEntity=BaseFileTemplate.class,fetch=FetchType.LAZY)
	@JoinColumn(name="FILE_TEMPLATE")
    private BaseFileTemplate fileTemplate;
   
   @FieldName(name="数据关键字")
   @Column(name="WORD_KEY")
   private String wordKey;
   
   @FieldName(name="数据列类型")
   @Column(name="KEY_TYPE")
   private String keyType;
   
   @FieldName(name="数据列")
   @Column(name="WORD_FIELD",length=1000)
   private String wordField;
   
   @FieldName(name="数据的SQL")
   @Column(name="DATA_SQL",length=1000)
   private String dataSql;
   
   @FieldName(name="数据说明")
   @Column(name="WORD_MEMO")
   private String wordMemo;
   
   @FieldName(name="数据来源")
   @Column(name="DATA_SOURCE")
   private String dataSource;
  
   @FieldName(name="多行控件存储的域名")
   @Column(name="MULTI_WORD_FIELD")
   private String multiWordField;
   
   @FieldName(name="千分位字段")
   @Column(name="FORMAT_FIELD" ,length=1000)
   private String formatfield;
   
   @Transient
   @FieldName(name="操作类型")
   private String operType;
   
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

	public String getWordKey() {
		return wordKey;
	}

	public String getKeyType() {
		return keyType;
	}

	public String getWordField() {
		return wordField;
	}

	public String getDataSql() {
		return dataSql;
	}

	public String getWordMemo() {
		return wordMemo;
	}

	public String getDataSource() {
		return dataSource;
	}

	public String getMultiWordField() {
		return multiWordField;
	}

	public String getOperType() {
		return operType;
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

	public void setWordKey(String wordKey) {
		this.wordKey = wordKey;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public void setWordField(String wordField) {
		this.wordField = wordField;
	}

	public void setDataSql(String dataSql) {
		this.dataSql = dataSql;
	}

	public void setWordMemo(String wordMemo) {
		this.wordMemo = wordMemo;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public void setMultiWordField(String multiWordField) {
		this.multiWordField = multiWordField;
	}

	public void setOperType(String operType) {
		this.operType = operType;
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

	public String getFormatfield() {
		return formatfield;
	}

	public void setFormatfield(String formatfield) {
		this.formatfield = formatfield;
	}

	public BaseFileTemplate getFileTemplate() {
		return fileTemplate;
	}

	public void setFileTemplate(BaseFileTemplate fileTemplate) {
		this.fileTemplate = fileTemplate;
	}
	

}