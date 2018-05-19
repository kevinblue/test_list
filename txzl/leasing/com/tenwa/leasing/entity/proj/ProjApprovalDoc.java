package com.tenwa.leasing.entity.proj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@FieldName(name = "项目批复文件")
@Table(name="PROJ_APPROVAL_DOC")
public class ProjApprovalDoc {


		@Id
		@OrderBy
	    @GeneratedValue(generator = "paymentableGenerator")     
	    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
	    @Column(length=32)
	    @FieldName(name="标识符")
		private String id;

		@ManyToOne(fetch = FetchType.LAZY)
		@FieldName(name="项目id号")
		@JoinColumn(name = "PROJ_id")
		private ProjDevelopInfo projId;
		

		public ProjDevelopInfo getProjId() {
			return projId;
		}

		public void setProjId(ProjDevelopInfo projId) {
			this.projId = projId;
		}

		@FieldName(name = "文件名称")
		@Column(name="DOC_NAME")
		private String docName;
		
		@FieldName(name = "文件编号")
		@Column(name="DOC_NUM")
		private String docNum;
	
		@FieldName(name = "批复主体/接收单位")
		@Column(name="OFFICIAL_REPLY")
		private String officialReply ;
		
		@FieldName(name = "批复日期")
		@Column(name="REPLY_DATE")
		private String replyDate;
		
		@FieldName(name = "批复单位")
		@Column(name="REPLY_DEPT")
		private String replyDept;
		
		@FieldName(name = "有效期截止日")
		@Column(name="EXPIRY_DATE")
		private String expiryDate ;
		
		@FieldName(name = "批复内容")
		@Column(name="reply_content")
		private String replyContent;
		
		@FieldName(name = "备注")
		@Column(name="REMARKS")
		private String remarks;
		
		@ManyToOne
		@FieldName(name = "登记人")
		@JoinColumn(name="CREATOR_")
		private User creator;
		
		@FieldName(name = "登记时间")
		@Column(name="CREATE_DATE")
		private String createDate;
		
		@ManyToOne
		@FieldName(name = "更新人")
		@JoinColumn(name="MODIFICATOR_")
		private User modificator;
		
		@FieldName(name = "更新日期")
		@Column(name="MODIFY_DATE", length=20)
		private String modifyDate;
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

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getDocName() {
			return docName;
		}

		public void setDocName(String docName) {
			this.docName = docName;
		}

		public String getDocNum() {
			return docNum;
		}

		public void setDocNum(String docNum) {
			this.docNum = docNum;
		}

		public String getOfficialReply() {
			return officialReply;
		}

		public void setOfficialReply(String officialReply) {
			this.officialReply = officialReply;
		}

		public String getReplyDate() {
			return replyDate;
		}

		public void setReplyDate(String replayDate) {
			this.replyDate = replayDate;
		}

		public String getReplyDept() {
			return replyDept;
		}

		public void setReplyDept(String replyDept) {
			this.replyDept = replyDept;
		}

		public String getExpiryDate() {
			return expiryDate;
		}

		public void setExpiryDate(String expiryDate) {
			this.expiryDate = expiryDate;
		}

		public String getReplyContent() {
			return replyContent;
		}

		public void setReplyContent(String replyContent) {
			this.replyContent = replyContent;
		}

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

	
}
