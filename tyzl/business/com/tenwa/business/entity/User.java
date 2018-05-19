package com.tenwa.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import com.tenwa.jbpm.entity.GrantDelegateUser;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfoUser;
import com.tenwa.jbpm.entity.UserWorkflowStart;
import com.tenwa.jbpm.entity.UserWorkflowView;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.kernal.i18n.LocaleEnum;

@Entity
@Table(name="T_USERS")
public class User implements Serializable,Comparable<User>,UserDetails
{
	private static final long serialVersionUID = -7289820236799793248L;
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	@Column(name="USERNAME_",unique=true,nullable=false)
	private String username;
	@Column(name="CODE_",unique=true,nullable=false)
	private String code;
	@Column(name="PASSWORD_",length=100,nullable=false)
	private String password;
	
	@Column(name = "USER_MAC_", length = 100)
	private String usermac;

	@Column(name="REALNAME_",nullable=false)
	private String realname;
	@Column(name="TELEPHONE_",length=50)
	private  String telephone;
	@Column(name="EMAIL_",length=100)
	private  String email;
	@Column(name="LASTUPDATEPASSWORDDATE_",length=20)
	private  String lastUpdatePasswordDate;
	@Column(name="ENABLED_",length=1,nullable=false)
	/*@Type(type = "org.hibernate.type.YesNoType")*/
	private Boolean enabled;
	@Column(name="DEPTNAME_",nullable=true)
	private String deptName;
	@Column(name="IS_EXCEPTED_",length=1,nullable=true)
	private Boolean isExcepted;
	
	@Column(name="SOURCEPASSWORD_",length=100,nullable=false)
	private String sourcepassword;
	
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	@ManyToOne(fetch=FetchType.LAZY)
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE_", length=20)	
	private String createDate;
	
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	@ManyToOne(fetch=FetchType.LAZY)
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE_", length=20)	
	private String modifyDate;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20, name = "USER_LOCAL")
	private LocaleEnum userLocal;
	
	@OneToMany(mappedBy="manager",fetch=FetchType.LAZY) 
	private Set<Department> managementDepts = new HashSet<Department>();
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY) 
	@OrderBy(value = "dept ASC")
	private Set<UserDepartment> userDepts = new HashSet<UserDepartment>();
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY) 
	@OrderBy(value = "group  ASC")
	private Set<UserGroup> userGroups = new HashSet<UserGroup>();
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY) 
	private Set<UserDepartmentRole> userDeptRoles = new HashSet<UserDepartmentRole>();
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY) 
	private Set<UserMenu> userMenus = new HashSet<UserMenu>();
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY) 
	private Set<UserResource> userResources = new HashSet<UserResource>();
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY) 
	private Set<UserAction> userActions = new HashSet<UserAction>();
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY) 
	private Set<UserWorkflowStart> userWorkflowStarts = new HashSet<UserWorkflowStart>();
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY) 
	private Set<UserWorkflowView> userWorkflowViews = new HashSet<UserWorkflowView>();
	//快捷操作
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY) 
	private Set<QuickUserMenu> quickUserMenus = new HashSet<QuickUserMenu>();
	
    public Set<QuickUserMenu> getQuickUserMenus() {
		return quickUserMenus;
	}
	public void setQuickUserMenus(Set<QuickUserMenu> quickUserMenus) {
		this.quickUserMenus = quickUserMenus;
	}
	public Set<UserWorkflowStart> getUserWorkflowStarts() {
		return userWorkflowStarts;
	}
	public Set<UserWorkflowView> getUserWorkflowViews() {
		return userWorkflowViews;
	}
	public void setUserWorkflowStarts(Set<UserWorkflowStart> userWorkflowStarts) {
		this.userWorkflowStarts = userWorkflowStarts;
	}
	public void setUserWorkflowViews(Set<UserWorkflowView> userWorkflowViews) {
		this.userWorkflowViews = userWorkflowViews;
	}
	public Set<UserResource> getUserResources() {
		return userResources;
	}
	public Set<UserAction> getUserActions() {
		return userActions;
	}
	public void setUserResources(Set<UserResource> userResources) {
		this.userResources = userResources;
	}
	public void setUserActions(Set<UserAction> userActions) {
		this.userActions = userActions;
	}

	/*与JBPMWorkflowHistoryInfoUser的一对多关系*/
	@OneToMany(mappedBy="planActor",fetch=FetchType.LAZY)
    private Set<JBPMWorkflowHistoryInfoUser> jbpmWorkflowHistoryInfoUsers = new HashSet<JBPMWorkflowHistoryInfoUser>();
	/**与上传明细的一对多关系**/
	@OneToMany(mappedBy="uploadUser",fetch = FetchType.LAZY)
	private Set<AttachmentFileUploadInfoDetail> attachmentFileUploadInfoDetails = new HashSet<AttachmentFileUploadInfoDetail>();
	/**与文档下载的一对多关系**/
	@OneToMany(mappedBy="downloadUser",fetch = FetchType.LAZY)
	private Set<AttachmentFileUploadInfoDetailDownload> attachmentFileUploadInfoDetailDownloads = new HashSet<AttachmentFileUploadInfoDetailDownload>();
	/*与GrantDelegateUser的一对多关系*/
	@OneToMany(mappedBy="delegateUser",fetch=FetchType.LAZY)
	private Set<GrantDelegateUser> delegateUsers = new HashSet<GrantDelegateUser>();
	
	/*与JBPMWorkflowHistoryInfoUser的一对多关系*/
	@OneToMany(mappedBy="grantUser",fetch=FetchType.LAZY)
	private Set<GrantDelegateUser> grantUsers = new HashSet<GrantDelegateUser>();
	
	public JSONArray getChildrenWithRolesJsonArray() throws Exception{
		JSONArray jsonArray = new JSONArray();
		for(UserDepartmentRole userDeptRole : this.getUserDeptRoles()){
				JSONObject deptRoleJsonObj = this.getJsonObjectRole(userDeptRole);
				jsonArray.put(deptRoleJsonObj);
		}
		return jsonArray;
	}
	public JSONObject getJsonObjectRole(UserDepartmentRole userDeptRole) throws Exception{
		String currentState  =  "opened";
		DepartmentRole deptRole = userDeptRole.getDeptRole();
		Role role = deptRole.getRole();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", role.getId());
		jsonObj.put("text", role.getName()+"("+role.getCode()+")");
		jsonObj.put("iconCls", "icon-contact-black");
		jsonObj.put("state", currentState);
		JSONObject atrributesJsonObj = new JSONObject();
		atrributesJsonObj.put("type", "role");
		atrributesJsonObj.put("deptUserId", deptRole.getId());
		jsonObj.put("attributes", atrributesJsonObj);
		return jsonObj;
	}
	@Override
	public boolean equals(Object obj) 
	{
		return this.id.equals(((User)obj).id);
	}

	@Override
	public int hashCode() 
	{
		return this.id.hashCode();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastUpdatePasswordDate() {
		return lastUpdatePasswordDate;
	}
	public void setLastUpdatePasswordDate(String lastUpdatePasswordDate) {
		this.lastUpdatePasswordDate = lastUpdatePasswordDate;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public String getPassword() {
		return this.password;
	}
	@Override
	public String getUsername() {
		return this.username;
	}
	@Override
	public boolean isAccountNonExpired() {
		return this.enabled.booleanValue();
	}
	@Override
	public boolean isAccountNonLocked() {
		return this.enabled.booleanValue();
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return this.enabled.booleanValue();
	}
	@Override
	public boolean isEnabled() {
		return this.enabled.booleanValue();
	}
	
	@Override
	@Transient
	public List<GrantedAuthority> getAuthorities() 
	{
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
		return authorities;
	}
	@Transient
    public String getAllAuthoritiesString()
    {
    	StringBuffer sb = new StringBuffer();
    	for(GrantedAuthority auth : this.getAuthorities())
    	{
    		String authStr = auth.getAuthority();
    		sb.append(authStr);
    		sb.append(",");
    	}
    	if(sb.length()>=1)
    	{
    		return sb.substring(0, sb.length()-1);
    	}
    	return sb.toString();
    }
//	@Transient
//	public String getAllAuthoritiesLabelStr() 
//	{
//    	StringBuffer roleLabelStr_sb = new StringBuffer("");
//    	int i=0;
//    	for(Role role:roles)
//    	{
//    		String roleLabel = role.getLabel();
//    		if("用户角色".equals(roleLabel))continue;
//    		if( 0 < i++)
//    		{
//    			roleLabelStr_sb.append(" , ");
//    		}
//    		roleLabelStr_sb.append(roleLabel);
//    	}
//    	return roleLabelStr_sb.toString();
//	}

	 /**
	 * @param jbpmWorkflowHistoryInfoUsers the jbpmWorkflowHistoryInfoUsers to set
	 **/
	
	public void setJbpmWorkflowHistoryInfoUsers(
			Set<JBPMWorkflowHistoryInfoUser> jbpmWorkflowHistoryInfoUsers) {
		this.jbpmWorkflowHistoryInfoUsers = jbpmWorkflowHistoryInfoUsers;
	}

	
	 public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCode() {
		return code;
	}

	public Set<Department> getManagementDepts() {
		return managementDepts;
	}

	public Set<UserDepartment> getUserDepts() {
		return userDepts;
	}

	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public Set<UserDepartmentRole> getUserDeptRoles() {
		return userDeptRoles;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setManagementDepts(Set<Department> managementDepts) {
		this.managementDepts = managementDepts;
	}

	public void setUserDepts(Set<UserDepartment> userDepts) {
		this.userDepts = userDepts;
	}
    
	public Boolean getEnabled() {
		return enabled;
	}
	public Set<UserMenu> getUserMenus() {
		return userMenus;
	}
	public void setUserMenus(Set<UserMenu> userMenus) {
		this.userMenus = userMenus;
	}
	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	public void setUserDeptRoles(Set<UserDepartmentRole> userDeptRoles) {
		this.userDeptRoles = userDeptRoles;
	}

	/**
	 * jbpmWorkflowHistoryInfoUsers
	 * @return the jbpmWorkflowHistoryInfoUsers
	 * @since 1.0.0
	 **/
	
	public Set<JBPMWorkflowHistoryInfoUser> getJbpmWorkflowHistoryInfoUsers() {
		return jbpmWorkflowHistoryInfoUsers;
	}

	
	 /**
	 * @param delegateUsers the delegateUsers to set
	 **/
	
	public void setDelegateUsers(Set<GrantDelegateUser> delegateUsers) {
		this.delegateUsers = delegateUsers;
	}

	
	 /**
	 * delegateUsers
	 * @return the delegateUsers
	 * @since 1.0.0
	 **/
	
	public Set<GrantDelegateUser> getDelegateUsers() {
		return delegateUsers;
	}

	
	 /**
	 * @param grantUsers the grantUsers to set
	 **/
	
	public void setGrantUsers(Set<GrantDelegateUser> grantUsers) {
		this.grantUsers = grantUsers;
	}

	
	 /**
	 * grantUsers
	 * @return the grantUsers
	 * @since 1.0.0
	 **/
	
	public Set<GrantDelegateUser> getGrantUsers() {
		return grantUsers;
	}

	
	 /**
	 * @param attachmentFileUploadInfoDetails the attachmentFileUploadInfoDetails to set
	 **/
	
	public void setAttachmentFileUploadInfoDetails(
			Set<AttachmentFileUploadInfoDetail> attachmentFileUploadInfoDetails) {
		this.attachmentFileUploadInfoDetails = attachmentFileUploadInfoDetails;
	}

	
	 /**
	 * attachmentFileUploadInfoDetails
	 * @return the attachmentFileUploadInfoDetails
	 * @since 1.0.0
	 **/
	
	public Set<AttachmentFileUploadInfoDetail> getAttachmentFileUploadInfoDetails() {
		return attachmentFileUploadInfoDetails;
	}

	
	 /**
	 * @param attachmentFileUploadInfoDetailDownloads the attachmentFileUploadInfoDetailDownloads to set
	 **/
	
	public void setAttachmentFileUploadInfoDetailDownloads(
			Set<AttachmentFileUploadInfoDetailDownload> attachmentFileUploadInfoDetailDownloads) {
		this.attachmentFileUploadInfoDetailDownloads = attachmentFileUploadInfoDetailDownloads;
	}

	
	 /**
	 * attachmentFileUploadInfoDetailDownloads
	 * @return the attachmentFileUploadInfoDetailDownloads
	 * @since 1.0.0
	 **/
	
	public Set<AttachmentFileUploadInfoDetailDownload> getAttachmentFileUploadInfoDetailDownloads() {
		return attachmentFileUploadInfoDetailDownloads;
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
	
	 /**
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 **/
	
	@Override
	public int compareTo(User o) {
		return this.id.compareTo(o.getId());
	}
	public void setIsExcepted(Boolean isExcepted) {
		this.isExcepted = isExcepted;
	}
	public Boolean getIsExcepted() {
		return (null == isExcepted ) ? false : isExcepted;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptName() {
		return deptName;
	}
	public String getSourcepassword() {
		return sourcepassword;
	}
	public void setSourcepassword(String sourcepassword) {
		this.sourcepassword = sourcepassword;
	}
	public LocaleEnum getUserLocal() {
		return userLocal;
	}
	public void setUserLocal(LocaleEnum userLocal) {
		this.userLocal = userLocal;
	}
	public String getUsermac() {
		return usermac;
	}
	public void setUsermac(String usermac) {
		this.usermac = usermac;
	}
	
	
}
