package com.tenwa.jbpm.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jbpm.pvm.internal.repository.DeploymentImpl;

import com.tenwa.business.entity.User;

@Entity
@Table(name="T_JBPM_GRANT_DELEGATE_USERS")
public class GrantDelegateUser implements Serializable 
{
	private static final long serialVersionUID = -6297218341440605242L;
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id;
	
	@ManyToOne(targetEntity=User.class,cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
    @JoinColumn(name="GRANT_USER_ID_",nullable=false)
    private User grantUser;
	
	@ManyToOne(targetEntity=User.class,cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
    @JoinColumn(name="DELEGATE_USER_ID_",nullable=false)
    private User delegateUser;
	
	@ManyToOne(targetEntity=DeploymentImpl.class,cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
    @JoinColumn(name="DEPLOYMENT_ID_",nullable=false)
    private DeploymentImpl deploymentImpl;
	
	@Column(name="START_DATE_")
	private String startDate;
	
	@Column(name="END_DATE_")
	private String endDate;
	
	@Column(name="GRANT_METHOD_",nullable=false)
	private String grantMethod;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getGrantUser() {
		return grantUser;
	}
	public void setGrantUser(User grantUser) {
		this.grantUser = grantUser;
	}
	public User getDelegateUser() {
		return delegateUser;
	}
	public void setDelegateUser(User delegateUser) {
		this.delegateUser = delegateUser;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	 /**
	 * @param deploymentImpl the deploymentImpl to set
	 **/
	
	public void setDeploymentImpl(DeploymentImpl deploymentImpl) {
		this.deploymentImpl = deploymentImpl;
	}
	
	 /**
	 * deploymentImpl
	 * @return the deploymentImpl
	 * @since 1.0.0
	 **/
	
	public DeploymentImpl getDeploymentImpl() {
		return deploymentImpl;
	}
	
	 /**
	 * @param grantMethod the grantMethod to set
	 **/
	
	public void setGrantMethod(String grantMethod) {
		this.grantMethod = grantMethod;
	}
	
	 /**
	 * grantMethod
	 * @return the grantMethod
	 * @since 1.0.0
	 **/
	
	public String getGrantMethod() {
		return grantMethod;
	}
	
}
