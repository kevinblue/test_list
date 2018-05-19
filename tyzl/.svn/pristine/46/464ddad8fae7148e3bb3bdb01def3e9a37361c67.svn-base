package com.reckon.entity.proj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.reckon.calculation.utils.Scale;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * <p>已知租金法多行控件的数据在流程结束时进行归档操作。</p>
 * <p>相同项目号or合同号有且仅能存在一批数据（该判断排除流程号，因为流程号只是什么流程结束才产生了这笔数据，纯纪录作用）。</p>
 * <p>项目号 不为空并且合同号为空，为项目层数据。</p>
 * <p>合同号不为空，为合同层数据。</p>
 * @author sea
 * <p>2014-5-22</p>
 */
@Entity
@FieldName(name = "已知租金法表")
@Table(name = "FUND_KNOWING_RENT")
public class KnowingRent {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;
	
	@FieldName(name = "报价编号")
	@Column(name = "DOC_ID")
	private String docId;
	
	@FieldName(name = "项目编号")
	@OneToOne(targetEntity = ProjInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID")
	private ProjInfo projId;
	
	@FieldName(name = "合同编号")
	@OneToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name = "开始期项")
	@Column(name = "START_LIST")
	private Integer startlist;
	
	@FieldName(name = "截止期项")
	@Column(name = "END_LIST")
	private Integer endlist;
	
	@FieldName(name = "对应租金值")
	@Column(name = "rent", precision = 22, scale = Scale.DEFAULT)
	private String rent;//
	
	@FieldName(name = "已知租金法完整json")////json_knowing_rent_plan_str
	@Lob
	@Column(name = "KNOWING_RENT_JSONS")
	private String knowingRentJsons;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public ProjInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjInfo projId) {
		this.projId = projId;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public Integer getStartlist() {
		return startlist;
	}

	public void setStartlist(Integer startlist) {
		this.startlist = startlist;
	}

	public Integer getEndlist() {
		return endlist;
	}

	public void setEndlist(Integer endlist) {
		this.endlist = endlist;
	}

	public String getRent() {
		return rent;
	}

	public void setRent(String rent) {
		this.rent = rent;
	}
	
	/**
	 * <p>GET已知租金法完整json,在新流程启动时，需加载入jsp中,记载时取值通过取TOP-ONE。</p>
	 * @author sea
	 * @return
	 */
	public String getKnowingRentJsons() {
		return knowingRentJsons;
	}

	/**
	 * <p>SET已知租金法完整json,在新流程启动时，需加载入jsp中,记载时取值通过取TOP-ONE。</p>
	 * @author sea
	 * @return
	 */
	public void setKnowingRentJsons(String knowingRentJsons) {
		this.knowingRentJsons = knowingRentJsons;
	}
	
	
	
}
