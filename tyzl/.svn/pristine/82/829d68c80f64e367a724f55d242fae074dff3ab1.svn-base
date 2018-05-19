
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity
 * 文件名：         AttachmentFileUploadInfo.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-11-9-上午09:58:44
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.business.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.leasing.entity.cust.CustInfo;


 /**
 * 类名称：     AttachmentFileUploadInfo
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-11-9 上午09:58:44
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_ATTACHMENT_INFO")
public class AttachmentFileUploadInfo implements Serializable
{
	private static final long serialVersionUID = 2053944557585622504L;

	@Id
     @GeneratedValue(generator = "paymentableGenerator")     
     @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
     @Column(length=32,name="ID_")
     private String id ;
	 
	 //标识字段
	 @Column(name="MODULE_",nullable=false)
	 private String module;
	 @Column(name="IDENTIFIER_ONE_",nullable=false)
	 private String identifierOne;
	 @Column(name="IDENTIFIER_TWO_")
	 private String identifierTwo;
	 @Column(name="IDENTIFIER_THREE_")
	 private String identifierThree;
	 @Column(name="IDENTIFIER_FOUR_")
	 private String identifierFour;
	 @Column(name="IDENTIFIER_FIVE_")
	 private String identifierFive;
	 @Column(name="IDENTIFIER_SIX_")
	 private String identifierSix;
	 @Column(name="IDENTIFIER_SEVEN_")
	 private String identifierSeven;
	 @Column(name="IDENTIFIER_EIGHT_")
	 private String identifierEight;
	 @Column(name="IDENTIFIER_NINE_")
	 private String identifierNine;
	 @Column(name="IDENTIFIER_TEN_")
	 private String identifierTen;
	 
	 @ManyToOne(cascade = 
	 {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	 ,fetch=FetchType.LAZY) 
	 @JoinColumn(name="ATTACHMENT_FILE_DICT_ID_",nullable=false)
	 private DictionaryData attachmentFileDict;
	 
	 @Column(name="UNION_KEY_")
	 private String unionKey;
	 
	 /**与上传明细的一对多关系**/
	 @OneToMany(mappedBy="attachmentFileUploadInfo",fetch = FetchType.LAZY)
	 @OrderBy("uploadTime desc")
	 private Set<AttachmentFileUploadInfoDetail> attachmentFileUploadInfoDetails = new HashSet<AttachmentFileUploadInfoDetail>();
		
	@ManyToOne(targetEntity = CustInfo.class, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CUST_ID_")
	private CustInfo cust;

	
	public CustInfo getCust() {
		return cust;
	}

	public void setCust(CustInfo cust) {
		this.cust = cust;
	}
	 /**
	 * @param attachmentFileDict the attachmentFileDict to set
	 **/
	
	public void setAttachmentFileDict(DictionaryData attachmentFileDict) {
		this.attachmentFileDict = attachmentFileDict;
	}
	
	 /**
	 * attachmentFileDict
	 * @return the attachmentFileDict
	 * @since 1.0.0
	 **/
	
	public DictionaryData getAttachmentFileDict() {
		return attachmentFileDict;
	}

	
	 /**
	 * @param id the id to set
	 **/
	
	public void setId(String id) {
		this.id = id;
	}

	
	 /**
	 * id
	 * @return the id
	 * @since 1.0.0
	 **/
	
	public String getId() {
		return id;
	}

	
	 /**
	 * @param attachmentFileUploadInfoDetails the attachmentFileUploadInfoDetails to set
	 **/
	
	public void setAttachmentFileUploadInfoDetails(
			Set<AttachmentFileUploadInfoDetail> attachmentFileUploadInfoDetails) {
		this.attachmentFileUploadInfoDetails = attachmentFileUploadInfoDetails;
	}

	
	public Set<AttachmentFileUploadInfoDetail> getAttachmentFileUploadInfoDetails() {
		return attachmentFileUploadInfoDetails;
	}
	

	public String getIdentifierOne() {
		return identifierOne;
	}

	public String getIdentifierTwo() {
		return identifierTwo;
	}

	public String getIdentifierThree() {
		return identifierThree;
	}

	public String getIdentifierFour() {
		return identifierFour;
	}

	public void setIdentifierFour(String identifierFour) {
		this.identifierFour = identifierFour;
	}

	public String getIdentifierFive() {
		return identifierFive;
	}

	public String getIdentifierSix() {
		return identifierSix;
	}

	public String getIdentifierSeven() {
		return identifierSeven;
	}

	public String getIdentifierEight() {
		return identifierEight;
	}

	public String getIdentifierNine() {
		return identifierNine;
	}

	public String getIdentifierTen() {
		return identifierTen;
	}

	public void setIdentifierOne(String identifierOne) {
		this.identifierOne = identifierOne;
	}

	public void setIdentifierTwo(String identifierTwo) {
		this.identifierTwo = identifierTwo;
	}

	public void setIdentifierThree(String identifierThree) {
		this.identifierThree = identifierThree;
	}
	public void setIdentifierFive(String identifierFive) {
		this.identifierFive = identifierFive;
	}

	public void setIdentifierSix(String identifierSix) {
		this.identifierSix = identifierSix;
	}

	public void setIdentifierSeven(String identifierSeven) {
		this.identifierSeven = identifierSeven;
	}

	public void setIdentifierEight(String identifierEight) {
		this.identifierEight = identifierEight;
	}

	public void setIdentifierNine(String identifierNine) {
		this.identifierNine = identifierNine;
	}

	public void setIdentifierTen(String identifierTen) {
		this.identifierTen = identifierTen;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public void setUnionKey(String unionKey) {
		this.unionKey = unionKey;
	}

	public String getUnionKey() {
		return unionKey;
	}
	
}
