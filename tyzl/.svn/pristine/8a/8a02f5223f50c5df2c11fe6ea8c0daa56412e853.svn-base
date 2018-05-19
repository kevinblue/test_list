package com.tenwa.leasing.entity.voucher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.entity.cust.CustInfo;


/**
 * 
 * @author 孙传良
 * @date 2013-9-13下午04:26:40
 * @info 辅助账实体(辅助账存在多个，并且是1个凭证体对应多个)
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "辅助账实体")
@Table(name="INTEREAS_VOUCHERASSTACTS")
public class IntereasVoucherasStacts {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id ;
	
	@ManyToOne
	@FieldName(name="凭证体行号")
	@JoinColumn(name="ENTRYSEQ")
	private IntereasVoucherEntries entrySeq ;
	
	@FieldName(name="辅助账行号")
	@Column(name="ASSTSEQ", length=20)
	private String asstSeq ;
	
	@ManyToOne
	@FieldName(name="辅助帐类型编码")
	@JoinColumn(name="ASSTACTTYPE")
	private VoucherassStactsInfo asstActType ;
	
	@FieldName(name="辅助帐类型名称 ")
	@Column(name="ASSTACTTYPENAME", length=200)
	private String asstActTypeName ;
	
	@FieldName(name="辅助账内容")
	@Column(name="ASSTACTNUMBER", length=200)
	private String asstActNumber ;
	
	@ManyToOne
	@FieldName(name="业务客户编号")
	@JoinColumn(name="CUST_ID_B")
	private CustInfo custIdB ;
	
	@ManyToOne
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	private User creator;//制单人
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE", length=20)	
	private String createDate;
	
	@ManyToOne
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;

	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE", length=20)	
	private String modifyDate;
	
	@Transient
	private OwnAccount ownAccount = new OwnAccount();
	
	/**
	  * <p>GET辅助账为银行账户情况下需产生本方银行账户的完整对象信息。</p>
	  * @author sea
	  * @Transient 不将该字段插入库的意思
	  * @return
	 */
	@Transient
	public OwnAccount getOwnAccount() {
		return ownAccount;
	}
	
	/**
	 * <p>SET辅助账为银行账户情况下需产生本方银行账户的完整对象信息。</p>
	 * @author sea
	 * @return
	 */
	@Transient
	public void setOwnAccount(OwnAccount ownAccount) {
		this.ownAccount = ownAccount;
	}
	/**
	 * 
	 * <p>GET辅助账自增长主键。</p>
	 * @author sea
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * 
	 * <p>SET辅助账自增长主键。</p>
	 * @author sea
	 * @return
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 
	 * <p>GET辅助账行号。</p>
	 * <p>根据传入凭证体生成对应行号，一个凭证体下的行号是递增模式.例如：凭证体行号为1，则辅助账行号为：1/2/3..,凭证体行号为2，则辅助账行号为：1/2/3...</p>
	 * @author sea
	 * @return
	 */
	public String getAsstSeq() {
		return asstSeq;
	}
	/**
	 * 
	 * <p>SET辅助账行号。</p>
	 * <p>根据传入凭证体生成对应行号，一个凭证体下的行号是递增模式.例如：凭证体行号为1，则辅助账行号为：1/2/3..,凭证体行号为2，则辅助账行号为：1/2/3...</p>
	 * @author sea
	 * @return
	 */
	public void setAsstSeq(String asstSeq) {
		this.asstSeq = asstSeq;
	}
	/**
	 * 
	 * <p>GET辅助帐类型编码。</p>
	 * <p>系统传入参数，例如：01/02</p>
	 * 01	客户
	 * 02	供应商
	 * 03	银行账户
	 * 04	预留
	 * 05	职员
	 * 06	预留
	 * 07	行政组织
	 * @author sea
	 * @return
	 */
	public VoucherassStactsInfo getAsstActType() {
		return asstActType;
	}
	/**
	 * 
	 * <p>SET辅助帐类型编码。</p>
	 * <p>系统传入参数，例如：01/02</p>
	 * 01	客户
	 * 02	供应商
	 * 03	银行账户
	 * 04	预留
	 * 05	职员
	 * 06	预留
	 * 07	行政组织
	 * @author sea
	 * @return
	 */
	public void setAsstActType(VoucherassStactsInfo asstActType) {
		this.asstActType = asstActType;
	}
	/**
	 * 
	 * <p>GET辅助帐类型名称 。</p>
	 * <p>根据辅助账类型编码去表中查询对应的名称，例如：01表示：客户</p>
	 * @author sea
	 * @return
	 */
	public String getAsstActTypeName() {
		return asstActTypeName;
	}
	/**
	 * 
	 * <p>SET辅助帐类型名称 。</p>
	 * <p>根据辅助账类型编码去表中查询对应的名称，例如：01表示：客户</p>
	 * @author sea
	 * @return
	 */
	public void setAsstActTypeName(String asstActTypeName) {
		this.asstActTypeName = asstActTypeName;
	}
	/**
	 * 
	 * <p>GET辅助账内容。</p>
	 * <p>辅助账内容：辅助帐类型编码(asstActType) 为001时 ，‘辅助账类容’应存入 cust_id_b ,
		，然后根据cust_id_b在表intereas_custid_join查询cust_id_f存入asstActNumber中
		，cust_id_b 存在 ，cust_id_f不存在 则该凭证状态为不完整</p>
	 * @author sea
	 * @return
	 */
	public String getAsstActNumber() {
		return asstActNumber;
	}
	/**
	 * 
	 * <p>SET辅助账内容。</p>
	 * <p>辅助账内容：辅助帐类型编码(asstActType) 为001时 ，传入的‘辅助账类容’应存入 cust_id_b ,
		，然后根据cust_id_b在表intereas_custid_join查询cust_id_f存入asstActNumber中
		，cust_id_b 存在 ，cust_id_f不存在 则该凭证状态为不完整</p>
	 * @author sea
	 * @return
	 */
	public void setAsstActNumber(String asstActNumber) {
		this.asstActNumber = asstActNumber;
	}
	/**
	 * 
	 * <p>GET凭证体行号。</p>
	 * <p>系统传入参数，例如：1/2</p>
	 * @author sea
	 * @return
	 */
	public IntereasVoucherEntries getEntrySeq() {
		return entrySeq;
	}
	/**
	 * 
	 * <p>SET凭证体行号。</p>
	 * <p>系统传入参数，例如：1/2</p>
	 * @author sea
	 * @return
	 */
	public void setEntrySeq(IntereasVoucherEntries entrySeq) {
		this.entrySeq = entrySeq;
	}

	/**
	 * 
	 * <p>GET业务客户编号。</p>
	 * <p>辅助帐类型编码(asstActType) 为01时 ，‘辅助账类容’应存入 cust_id_b，否则该项为空 </p>
	 * @author sea
	 * @return
	 */
	public CustInfo getCustIdB() {
		return custIdB;
	}
	/**
	 * 
	 * <p>SET业务客户编号。</p>
	 * <p>辅助帐类型编码(asstActType) 为01时 ，客户编号对应的UUID存入 cust_id_b中，否则该项为空</p>
	 * @author sea
	 * @return
	 */
	public void setCustIdB(CustInfo custIdB) {
		this.custIdB = custIdB;
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
