package com.tenwa.leasing.entity.cust;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@FieldName(name = "档案资料查询")
@Table(name = "CUST_ARCHIVES")
public class CustArchives implements Serializable {
	
	private static final long serialVersionUID = 3133647570511366377L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 90)
	@FieldName(name = "标识符")
	private String id;
	
	@FieldName(name = "序号")
	@Column(name = "ROW_NUMBER",length=255)
	private String rownumber;	
	
	@FieldName(name = "档案标题")
	@Column(name = "ARCHIVES_TITLE",length=255)
	private String archivestitle;	

	@FieldName(name = "档案类型")
	@Column(name = "ARCHIVES_TYPE",length=255)
	private String archivestype;	

	@FieldName(name = "档案说明")
	@Column(name = "ARCHIVES_EXPLAIN",length=255)
	private String archivesexplain;	

	@FieldName(name = "状态")
	@Column(name = "STATUS", length = 20)
	private String status;

	@ManyToOne
	@FieldName(name = "创建人")
	@JoinColumn(name = "CREATOR_")
	private User creator;

	@FieldName(name = "创建时间")
	@Column(name = "CREATE_DATE", length = 20)
	private String createDate;

	@ManyToOne
	@FieldName(name = "修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;

	@FieldName(name = "修改时间")
	@Column(name = "MODIFY_DATE", length = 20)
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public String getRownumber() {
		return rownumber;
	}

	public void setRownumber(String rownumber) {
		this.rownumber = rownumber;
	}

	

	public String getArchivestitle() {
		return archivestitle;
	}

	public void setArchivestitle(String archivestitle) {
		this.archivestitle = archivestitle;
	}

	public String getArchivestype() {
		return archivestype;
	}

	public void setArchivestype(String archivestype) {
		this.archivestype = archivestype;
	}

	public String getArchivesexplain() {
		return archivesexplain;
	}

	public void setArchivesexplain(String archivesexplain) {
		this.archivesexplain = archivesexplain;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
