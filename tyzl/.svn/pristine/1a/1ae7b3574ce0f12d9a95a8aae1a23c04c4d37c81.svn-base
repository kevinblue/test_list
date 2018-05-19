
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity.ad
 * 文件名：         AdConfig.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-12-3-上午10:32:30
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.entity.ad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;


 /**
 * 类名称：     AdConfig
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-12-3 上午10:32:30
 * 修改备注：
 * @version 1.0.0
 **/
@FieldName(name="AD域配置信息")
@Entity
@Table(name="T_AD_CONFIG")
public class AdConfig {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	
	@FieldName(name="AD主机")
	@Column(name="HOST_")
	private String  host;
	
	@FieldName(name="AD端口")
	@Column(name="PORT_")
	private Integer  port;
	
	@FieldName(name="AD管理员用户")
	@Column(name="ADMIN_NAME_")
	private String  adminName;
	
	@FieldName(name="AD管理员密码")
	@Column(name="ADMIN_KEY_")
	private String  adminKey;
	
	@FieldName(name="AD域名")
	@Column(name="DOMAIN_NAME_")
	private String  domainName;
	
	@FieldName(name="AD同步数据的OU")
	@Column(name="OU_")
	private String  ou;
	
	public String getId() {
		return id;
	}
	public String getHost() {
		return host;
	}
	public Integer getPort() {
		return port;
	}
	public String getAdminName() {
		return adminName;
	}
	 
	public String getOu() {
		return ou;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	public String getAdminKey() {
		return adminKey;
	}
	public void setAdminKey(String adminKey) {
		this.adminKey = adminKey;
	}
	public void setOu(String ou) {
		this.ou = ou;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getDomainName() {
		return domainName;
	}
}
