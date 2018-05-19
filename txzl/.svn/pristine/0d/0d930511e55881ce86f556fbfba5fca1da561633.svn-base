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
@FieldName(name = "经评模型")
@Table(name="T_RECORD_FILE_SAVE_STATE")
public class RecordFileSaveState {
	
	
//记录经评模型文件是否编辑保存
		@Id
		@OrderBy
	    @GeneratedValue(generator = "paymentableGenerator")     
	    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
	    @Column(length=32)
	    @FieldName(name="标识符")
		private String id;

		//(1.2.3.4.5.6.7.8)
		@FieldName(name = "文件id")
		@Column(name="FILE_ID")
		private String fileId;
		
		//银行贷款(1)-融资租赁(2)
		@FieldName(name = "保存状态")
		@Column(name="SAVE_STATE")
		private String saveState;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "CREATOR_")
		@FieldName(name="创建人")
		private User creator;
		
		@Column(name = "CREATE_DATE", length = 40)
		@FieldName(name="创建时间")
		private String createDate;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getFileId() {
			return fileId;
		}

		public void setFileId(String fileId) {
			this.fileId = fileId;
		}

		public String getSaveState() {
			return saveState;
		}

		public void setSaveState(String saveState) {
			this.saveState = saveState;
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
		
	    
}
