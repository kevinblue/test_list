package com.tenwa.leasing.serviceImpl.message;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.BaseMessage;
import com.tenwa.business.entity.BaseMessageToGroup;
import com.tenwa.business.entity.BaseMessageToUser;
import com.tenwa.business.entity.DepartmentRole;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.ManagerDistrict;
import com.tenwa.business.entity.Role;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.UserDepartment;
import com.tenwa.business.entity.UserDepartmentRole;
import com.tenwa.business.entity.UserGroup;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.service.message.MessageService;



/**
 * 类名称： MessageServiceImpl
 * 
 * @version 1.0.0
 **/
@Service(value = "messageService")
public class MessageServiceImpl implements MessageService {
	@Resource(name="baseService")
	private BaseService baseService;
    /**
     * 日志
     */
    Logger logger=Logger.getLogger(MessageServiceImpl.class.getName());
	@Override
	public void addMessaage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		BaseMessage baseMessage = new BaseMessage();
		this.baseService.copyAndOverrideExistedValueFromStringMap(model, baseMessage, null);
		//baseMessage.setMessageStatus("0");
		this.baseService.saveEntity(baseMessage);
		DictionaryData msgGroup = new DictionaryData();
		BaseMessageToGroup messageToGroup = null;
		BaseMessageToUser messageToUser = null;
		Map<String, Object> mapGroup = null;
		Set<BaseMessageToUser> set = new HashSet<BaseMessageToUser>();
		Set<BaseMessageToUser> setTemp = new HashSet<BaseMessageToUser>();
		String[] usertype = model.get("tousertype").split(",");
		String[] users = null;
		String[] groups = null;
		String[] depts = null;
		String[] roles = null;
		String[] areas = null;
		if(null != model.get("users")){
			users = model.get("users").split(",");
		}
		if(null != model.get("groups")){
			groups = model.get("groups").split(",");
		}
		if(null != model.get("depts")){
			depts = model.get("depts").split(",");
		}
		if(null != model.get("roles")){
			roles = model.get("roles").split(",");
		}
		if(null != model.get("areas")){
			areas = model.get("areas").split(",");
		}
		for (int i = 0; i < usertype.length; i++) {
			if(usertype[i].equals("msggroup_user") && users != null && users.length > 0){
				mapGroup = new HashMap<String, Object>();
				mapGroup.put("code", "msggroup_user");
				for (int j = 0; j < users.length; j++) {
					messageToGroup = new BaseMessageToGroup();
					msgGroup = (DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, mapGroup).get(0);
					messageToGroup.setGoupID(users[j]);
					messageToGroup.setMsgID(baseMessage);
					messageToGroup.setMsgGroup(msgGroup);
					messageToGroup.setCreateDate(DateUtil.getSystemDateTime());
					messageToGroup.setCreator(currentUser);
					this.baseService.saveEntity(messageToGroup);
					messageToUser = new BaseMessageToUser();
					model.put("readuser", users[j]);
					this.baseService.copyAndOverrideExistedValueFromStringMap(model, messageToUser, null);
					messageToUser.setMsgID(baseMessage);
					set.add(messageToUser);
					setTemp.add(messageToUser);
				}
			}
			//群组中经销商分区域
			if(usertype[i].equals("msggroup_group") && groups != null && groups.length > 0 && areas==null){
				mapGroup = new HashMap<String, Object>();
				mapGroup.put("code", "msggroup_group");
				boolean isExit = true;
				for (int j = 0; j < groups.length; j++) {
					messageToGroup = new BaseMessageToGroup();
					msgGroup = (DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, mapGroup).get(0);
					messageToGroup.setGoupID(groups[j]);
					messageToGroup.setMsgID(baseMessage);
					messageToGroup.setMsgGroup(msgGroup);
					messageToGroup.setCreateDate(DateUtil.getSystemDateTime());
					messageToGroup.setCreator(currentUser);
					this.baseService.saveEntity(messageToGroup);
					List<UserGroup> listUserGroup = this.baseService.findResultsByHSQL("select ug from UserGroup ug left join ug.group g where g.id=?", groups[j]);
					for (int k = 0; k < listUserGroup.size(); k++) {
						System.out.println(listUserGroup.get(k).getUser().getId());
						messageToUser = new BaseMessageToUser();
						this.baseService.copyAndOverrideExistedValueFromStringMap(model, messageToUser, null);
						if(setTemp != null && setTemp.size() > 0){
							for (BaseMessageToUser user : setTemp) {
								System.out.println(user.getReadUser().getId());
								if(listUserGroup.get(k).getUser().getId().equals(user.getReadUser().getId())){
									isExit = false;
								}
							}
							if(isExit){
								messageToUser.setReadUser(listUserGroup.get(k).getUser());
								messageToUser.setMsgID(baseMessage);
								set.add(messageToUser);
							}
							isExit = true;
						}else{
							messageToUser.setReadUser(listUserGroup.get(k).getUser());
							messageToUser.setMsgID(baseMessage);
							set.add(messageToUser);
							setTemp.add(messageToUser);
						}
					}
					setTemp.addAll(set);
				}
			}
			if(usertype[i].equals("msggroup_dept") && depts != null && depts.length > 0){
				mapGroup = new HashMap<String, Object>();
				mapGroup.put("code", "msggroup_dept");
				boolean isExit = true;
				for (int j = 0; j < depts.length; j++) {
					messageToGroup = new BaseMessageToGroup();
					msgGroup = (DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, mapGroup).get(0);
					messageToGroup.setGoupID(depts[j]);
					messageToGroup.setMsgID(baseMessage);
					messageToGroup.setMsgGroup(msgGroup);
					messageToGroup.setCreateDate(DateUtil.getSystemDateTime());
					messageToGroup.setCreator(currentUser);
					this.baseService.saveEntity(messageToGroup);
					List<UserDepartment> listUserDept = this.baseService.findResultsByHSQL("from UserDepartment ud where ud.dept.id=?", depts[j]);
					for (int k = 0; k < listUserDept.size(); k++) {
						messageToUser = new BaseMessageToUser();
						this.baseService.copyAndOverrideExistedValueFromStringMap(model, messageToUser, null);
						if(setTemp != null && setTemp.size() > 0){
							for (BaseMessageToUser user : setTemp){
								if(listUserDept.get(k).getUser().getId().equals(user.getReadUser().getId())){
									isExit = false;
								}
							}
							if(isExit){
								messageToUser.setReadUser(listUserDept.get(k).getUser());
								messageToUser.setMsgID(baseMessage);
								set.add(messageToUser);
							}
							isExit = true;
						}else{
							messageToUser.setReadUser(listUserDept.get(k).getUser());
							messageToUser.setMsgID(baseMessage);
							set.add(messageToUser);
							setTemp.add(messageToUser);
						}
					}
					setTemp.addAll(set);
				}
			}
			//单独按角色查询
			if(usertype[i].equals("msggroup_roles") && roles != null && roles.length > 0 && areas==null){
				mapGroup = new HashMap<String, Object>();
				mapGroup.put("code", "msggroup_roles");
				boolean isExit = true;
				Role role = null;
				for (int j = 0; j < roles.length; j++) {
					messageToGroup = new BaseMessageToGroup();
					msgGroup = (DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, mapGroup).get(0);
					messageToGroup.setGoupID(roles[j]);
					messageToGroup.setMsgID(baseMessage);
					messageToGroup.setMsgGroup(msgGroup);
					messageToGroup.setCreateDate(DateUtil.getSystemDateTime());
					messageToGroup.setCreator(currentUser);
					this.baseService.saveEntity(messageToGroup);
					role = (Role) this.baseService.findEntityByID(Role.class, roles[j]);
					Set<DepartmentRole> departmentRole = role.getDeptRoles();
					for (int k = 0; k < departmentRole.size(); k++) {
						Set<UserDepartmentRole> userDepartmentRoles = (Set<UserDepartmentRole>) departmentRole.iterator().next().getUserDeptRoles();
						List<UserDepartmentRole> listRole=new ArrayList<UserDepartmentRole>(userDepartmentRoles);
						for (int l = 0; l < listRole.size(); l++) {
							messageToUser = new BaseMessageToUser();
							this.baseService.copyAndOverrideExistedValueFromStringMap(model, messageToUser, null);
							if(setTemp != null && setTemp.size() > 0){
								for (BaseMessageToUser user : setTemp){
									if(listRole.get(l).getUser().getId().equals(user.getReadUser().getId())){
										isExit = false;
									}
								}
								if(isExit){
									messageToUser.setReadUser(listRole.get(l).getUser());
									messageToUser.setMsgID(baseMessage);
									set.add(messageToUser);
								}
								isExit = true;
							}else{
								messageToUser.setReadUser(listRole.get(l).getUser());
								messageToUser.setMsgID(baseMessage);
								set.add(messageToUser);
								setTemp.add(messageToUser);
							}
						}
						setTemp.addAll(set);
					}
				}
			}
			//单独按区域查询
			/*
			if(usertype[i].equals("msggroup_district") && areas != null && areas.length > 0 && roles == null && groups==null){
				mapGroup = new HashMap<String, Object>();
				mapGroup.put("code", "msggroup_district");
				boolean isExit = true;
				for (int j = 0; j < areas.length; j++) {
					messageToGroup = new BaseMessageToGroup();
					msgGroup = (DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, mapGroup).get(0);
					messageToGroup.setGoupID(areas[j]);
					messageToGroup.setMsgID(baseMessage);
					messageToGroup.setMsgGroup(msgGroup);
					messageToGroup.setCreateDate(DateUtil.getSystemDateTime());
					messageToGroup.setCreator(currentUser);
					this.baseService.saveEntity(messageToGroup);
					
					//获取项目经理，业务区划
					List<ManagerDistrict> managerDistrict = this.baseService.findResultsByHSQL("from ManagerDistrict md where md.district.code=?", areas[j]);
					for (int k = 0; k < managerDistrict.size(); k++) {
						messageToUser = new BaseMessageToUser();
						this.baseService.copyAndOverrideExistedValueFromStringMap(model, messageToUser, null);
						if(setTemp != null && setTemp.size() > 0){
							for (BaseMessageToUser user : setTemp){
								if(managerDistrict.get(k).getManager().getId().equals(user.getReadUser().getId())){
									isExit = false;
								}
							}
							if(isExit){
								messageToUser.setReadUser(managerDistrict.get(k).getManager());
								messageToUser.setMsgID(baseMessage);
								set.add(messageToUser);
							}
							isExit = true;
						}else{
							messageToUser.setReadUser(managerDistrict.get(k).getManager());
							messageToUser.setMsgID(baseMessage);
							set.add(messageToUser);
						}
					}
					
					
					//获取经销商对应的
					List<CustInfoDealer> CustInfoDealerlList = this.baseService.findResultsByHSQL("from CustInfoDealer cd where cd.ownerDistrict.code=?", areas[j]);
					Set<User> us=new HashSet<User>();
					for (CustInfoDealer cd:CustInfoDealerlList){
						List<DealerDeptInfo> DealerDeptInfo = this.baseService.findResultsByHSQL("from DealerDeptInfo dd where dd.custId.id=?",cd.getCustId().getId());
						for(DealerDeptInfo dd:DealerDeptInfo){
							Department dt=dd.getDealerDept();
						    List<UserDepartment> userdept=new ArrayList<UserDepartment>(dt.getUserDepts());
						    for (UserDepartment ud:userdept){
						 	   us.add(ud.getUser());
						    }
						}
					}
					
					for(User usre:us){
						messageToUser = new BaseMessageToUser();
						this.baseService.copyAndOverrideExistedValueFromStringMap(model, messageToUser, null);
						messageToUser.setReadUser(usre);
						messageToUser.setMsgID(baseMessage);
						set.add(messageToUser);
					}
				}
			}*/
		}
		//角色和区域同时存在，取并集
		if(roles!=null && areas!=null){
			mapGroup = new HashMap<String, Object>();
			mapGroup.put("code", "msggroup_district");
			boolean isExit = true;
			for (int i = 0; i < roles.length; i++) {
				for (int j = 0; j < areas.length; j++) {
					messageToGroup = new BaseMessageToGroup();
					msgGroup = (DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, mapGroup).get(0);
					messageToGroup.setGoupID(areas[j]);
					messageToGroup.setMsgID(baseMessage);
					messageToGroup.setMsgGroup(msgGroup);
					messageToGroup.setCreateDate(DateUtil.getSystemDateTime());
					messageToGroup.setCreator(currentUser);
					this.baseService.saveEntity(messageToGroup);
					List<ManagerDistrict> managerDistrict = this.baseService.findResultsByHSQL("from ManagerDistrict md where md.district.code=? and md.managerRole.id=? ", areas[j],roles[i]);
					for (int k = 0; k < managerDistrict.size(); k++) {
						messageToUser = new BaseMessageToUser();
						this.baseService.copyAndOverrideExistedValueFromStringMap(model, messageToUser, null);
						if(setTemp != null && setTemp.size() > 0){
							for (BaseMessageToUser user : setTemp){
								if(managerDistrict.get(k).getManager().getId().equals(user.getReadUser().getId())){
									isExit = false;
								}
							}
							if(isExit){
								messageToUser.setReadUser(managerDistrict.get(k).getManager());
								messageToUser.setMsgID(baseMessage);
								set.add(messageToUser);
								setTemp.add(messageToUser);
							}
							isExit = true;
						}else{
							messageToUser.setReadUser(managerDistrict.get(k).getManager());
							messageToUser.setMsgID(baseMessage);
							set.add(messageToUser);
							setTemp.add(messageToUser);
						}
			       }
				 }
			}
		}
		
		//群组和区域同时存在取并集
		/*
		if(groups!=null && areas!=null){
			mapGroup = new HashMap<String, Object>();
			mapGroup.put("code", "msggroup_group");
			boolean isExit = true;
			for (int j = 0; j < groups.length; j++) {
				//获取经销商对应的
				Group gp=this.baseService.findEntityByID(Group.class,groups[j]);
		     	if("经销商".equals(gp.getName())){
		     		for (int k = 0; k < areas.length; k++) {
						List<CustInfoDealer> CustInfoDealerlList = this.baseService.findResultsByHSQL("from CustInfoDealer cd where cd.ownerDistrict.code=?", areas[k]);
						Set<User> us=new HashSet<User>();
						for (CustInfoDealer cd:CustInfoDealerlList){
							List<DealerDeptInfo> DealerDeptInfo = this.baseService.findResultsByHSQL("from DealerDeptInfo dd where dd.custId.id=?",cd.getCustId().getId());
							for(DealerDeptInfo dd:DealerDeptInfo){
								Department dt=dd.getDealerDept();
							    List<UserDepartment> userdept=new ArrayList<UserDepartment>(dt.getUserDepts());
							    for (UserDepartment ud:userdept){
							 	   us.add(ud.getUser());
							    }
							}
						}
						
						for(User usre:us){
							messageToUser = new BaseMessageToUser();
							this.baseService.copyAndOverrideExistedValueFromStringMap(model, messageToUser, null);
							messageToUser.setReadUser(usre);
							messageToUser.setMsgID(baseMessage);
							set.add(messageToUser);
						}
		     		}
				
			    }else{
					messageToGroup = new BaseMessageToGroup();
					msgGroup = (DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, mapGroup).get(0);
					messageToGroup.setGoupID(groups[j]);
					messageToGroup.setMsgID(baseMessage);
					messageToGroup.setMsgGroup(msgGroup);
					messageToGroup.setCreateDate(DateUtil.getSystemDateTime());
					messageToGroup.setCreator(currentUser);
					this.baseService.saveEntity(messageToGroup);
					List<UserGroup> listUserGroup = this.baseService.findResultsByHSQL("select ug from UserGroup ug left join ug.group g where g.id=?", groups[j]);
					for (int k = 0; k < listUserGroup.size(); k++) {
						System.out.println(listUserGroup.get(k).getUser().getId());
						messageToUser = new BaseMessageToUser();
						this.baseService.copyAndOverrideExistedValueFromStringMap(model, messageToUser, null);
						if(setTemp != null && setTemp.size() > 0){
							for (BaseMessageToUser user : setTemp) {
								System.out.println(user.getReadUser().getId());
								if(listUserGroup.get(k).getUser().getId().equals(user.getReadUser().getId())){
									isExit = false;
								}
							}
							if(isExit){
								messageToUser.setReadUser(listUserGroup.get(k).getUser());
								messageToUser.setMsgID(baseMessage);
								set.add(messageToUser);
							}
							isExit = true;
						}else{
							messageToUser.setReadUser(listUserGroup.get(k).getUser());
							messageToUser.setMsgID(baseMessage);
							set.add(messageToUser);
							setTemp.add(messageToUser);
						}
					}
					setTemp.addAll(set);
					
			    }
			}
		}*/
		this.baseService.saveAllEntities(set);
	}
	@Override
	public void updateMessage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		BaseMessage message = this.baseService.findEntityByID(BaseMessage.class, model.get("id"));
		this.baseService.removeAllEntites(message.getToUser());
		this.baseService.removeAllEntites(message.getToGroup());
		this.baseService.removeEntity(message);
		//删除记录然后新增
		BaseMessage baseMessage = new BaseMessage();
		this.baseService.copyAndOverrideExistedValueFromStringMap(model, baseMessage, null);
		//baseMessage.setMessageStatus("0");
		this.baseService.saveEntity(baseMessage);
		DictionaryData msgGroup = new DictionaryData();
		BaseMessageToGroup messageToGroup = null;
		BaseMessageToUser messageToUser = null;
		Map<String, Object> mapGroup = null;
		Set<BaseMessageToUser> set = new HashSet<BaseMessageToUser>();
		Set<BaseMessageToUser> setTemp = new HashSet<BaseMessageToUser>();
		String[] usertype = model.get("tousertype").split(",");
		String[] users = null;
		String[] groups = null;
		String[] depts = null;
		String[] roles = null;
		String[] areas = null;
		if(null != model.get("users")){
			users = model.get("users").split(",");
		}
		if(null != model.get("groups")){
			groups = model.get("groups").split(",");
		}
		if(null != model.get("depts")){
			depts = model.get("depts").split(",");
		}
		if(null != model.get("roles")){
			roles = model.get("roles").split(",");
		}
		if(null != model.get("areas")){
			areas = model.get("areas").split(",");
		}
		for (int i = 0; i < usertype.length; i++) {
			if(usertype[i].equals("msggroup_user") && users != null && users.length > 0){
				mapGroup = new HashMap<String, Object>();
				mapGroup.put("code", "msggroup_user");
				for (int j = 0; j < users.length; j++) {
					messageToGroup = new BaseMessageToGroup();
					msgGroup = (DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, mapGroup).get(0);
					messageToGroup.setGoupID(users[j]);
					messageToGroup.setMsgID(baseMessage);
					messageToGroup.setMsgGroup(msgGroup);
					messageToGroup.setCreateDate(DateUtil.getSystemDateTime());
					messageToGroup.setCreator(currentUser);
					this.baseService.saveEntity(messageToGroup);
					messageToUser = new BaseMessageToUser();
					model.put("readuser", users[j]);
					this.baseService.copyAndOverrideExistedValueFromStringMap(model, messageToUser, null);
					messageToUser.setMsgID(baseMessage);
					set.add(messageToUser);
					setTemp.add(messageToUser);
				}
			}
			if(usertype[i].equals("msggroup_group") && groups != null && groups.length > 0){
				mapGroup = new HashMap<String, Object>();
				mapGroup.put("code", "msggroup_group");
				boolean isExit = true;
				for (int j = 0; j < groups.length; j++) {
					messageToGroup = new BaseMessageToGroup();
					msgGroup = (DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, mapGroup).get(0);
					messageToGroup.setGoupID(groups[j]);
					messageToGroup.setMsgID(baseMessage);
					messageToGroup.setMsgGroup(msgGroup);
					messageToGroup.setCreateDate(DateUtil.getSystemDateTime());
					messageToGroup.setCreator(currentUser);
					this.baseService.saveEntity(messageToGroup);
					List<UserGroup> listUserGroup = this.baseService.findResultsByHSQL("select ug from UserGroup ug left join ug.group g where g.id=?", groups[j]);
					for (int k = 0; k < listUserGroup.size(); k++) {
						System.out.println(listUserGroup.get(k).getUser().getId());
						messageToUser = new BaseMessageToUser();
						this.baseService.copyAndOverrideExistedValueFromStringMap(model, messageToUser, null);
						if(setTemp != null && setTemp.size() > 0){
							for (BaseMessageToUser user : setTemp) {
								System.out.println(user.getReadUser().getId());
								if(listUserGroup.get(k).getUser().getId().equals(user.getReadUser().getId())){
									isExit = false;
								}
							}
							if(isExit){
								messageToUser.setReadUser(listUserGroup.get(k).getUser());
								messageToUser.setMsgID(baseMessage);
								set.add(messageToUser);
							}
							isExit = true;
						}else{
							messageToUser.setReadUser(listUserGroup.get(k).getUser());
							messageToUser.setMsgID(baseMessage);
							set.add(messageToUser);
							setTemp.add(messageToUser);
						}
					}
					setTemp.addAll(set);
				}
			}
			if(usertype[i].equals("msggroup_dept") && depts != null && depts.length > 0){
				mapGroup = new HashMap<String, Object>();
				mapGroup.put("code", "msggroup_dept");
				boolean isExit = true;
				for (int j = 0; j < depts.length; j++) {
					messageToGroup = new BaseMessageToGroup();
					msgGroup = (DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, mapGroup).get(0);
					messageToGroup.setGoupID(depts[j]);
					messageToGroup.setMsgID(baseMessage);
					messageToGroup.setMsgGroup(msgGroup);
					messageToGroup.setCreateDate(DateUtil.getSystemDateTime());
					messageToGroup.setCreator(currentUser);
					this.baseService.saveEntity(messageToGroup);
					List<UserDepartment> listUserDept = this.baseService.findResultsByHSQL("from UserDepartment ud where ud.dept.id=?", depts[j]);
					for (int k = 0; k < listUserDept.size(); k++) {
						messageToUser = new BaseMessageToUser();
						this.baseService.copyAndOverrideExistedValueFromStringMap(model, messageToUser, null);
						if(setTemp != null && setTemp.size() > 0){
							for (BaseMessageToUser user : setTemp){
								if(listUserDept.get(k).getUser().getId().equals(user.getReadUser().getId())){
									isExit = false;
								}
							}
							if(isExit){
								messageToUser.setReadUser(listUserDept.get(k).getUser());
								messageToUser.setMsgID(baseMessage);
								set.add(messageToUser);
							}
							isExit = true;
						}else{
							messageToUser.setReadUser(listUserDept.get(k).getUser());
							messageToUser.setMsgID(baseMessage);
							set.add(messageToUser);
							setTemp.add(messageToUser);
						}
					}
					setTemp.addAll(set);
				}
			}
			if(usertype[i].equals("msggroup_roles") && roles != null && roles.length > 0){
				mapGroup = new HashMap<String, Object>();
				mapGroup.put("code", "msggroup_roles");
				boolean isExit = true;
				Role role = null;
				for (int j = 0; j < roles.length; j++) {
					messageToGroup = new BaseMessageToGroup();
					msgGroup = (DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, mapGroup).get(0);
					messageToGroup.setGoupID(roles[j]);
					messageToGroup.setMsgID(baseMessage);
					messageToGroup.setMsgGroup(msgGroup);
					messageToGroup.setCreateDate(DateUtil.getSystemDateTime());
					messageToGroup.setCreator(currentUser);
					this.baseService.saveEntity(messageToGroup);
					role = (Role) this.baseService.findEntityByID(Role.class, roles[j]);
					Set<DepartmentRole> departmentRole = role.getDeptRoles();
					for (int k = 0; k < departmentRole.size(); k++) {
						Set<UserDepartmentRole> userDepartmentRoles = (Set<UserDepartmentRole>) departmentRole.iterator().next().getUserDeptRoles();
						for (int l = 0; l < userDepartmentRoles.size(); l++) {
							messageToUser = new BaseMessageToUser();
							this.baseService.copyAndOverrideExistedValueFromStringMap(model, messageToUser, null);
							if(setTemp != null && setTemp.size() > 0){
								for (BaseMessageToUser user : setTemp){
									if(userDepartmentRoles.iterator().next().getUser().getId().equals(user.getReadUser().getId())){
										isExit = false;
									}
								}
								if(isExit){
									messageToUser.setReadUser(userDepartmentRoles.iterator().next().getUser());
									messageToUser.setMsgID(baseMessage);
									set.add(messageToUser);
								}
								isExit = true;
							}else{
								messageToUser.setReadUser(userDepartmentRoles.iterator().next().getUser());
								messageToUser.setMsgID(baseMessage);
								set.add(messageToUser);
								setTemp.add(messageToUser);
							}
						}
						setTemp.addAll(set);
					}
				}
			}
			if(usertype[i].equals("msggroup_district") && areas != null && areas.length > 0){
				mapGroup = new HashMap<String, Object>();
				mapGroup.put("code", "msggroup_district");
				boolean isExit = true;
				for (int j = 0; j < areas.length; j++) {
					messageToGroup = new BaseMessageToGroup();
					msgGroup = (DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, mapGroup).get(0);
					messageToGroup.setGoupID(areas[j]);
					messageToGroup.setMsgID(baseMessage);
					messageToGroup.setMsgGroup(msgGroup);
					messageToGroup.setCreateDate(DateUtil.getSystemDateTime());
					messageToGroup.setCreator(currentUser);
					this.baseService.saveEntity(messageToGroup);
					List<ManagerDistrict> managerDistrict = this.baseService.findResultsByHSQL("from ManagerDistrict md where md.district.code=?", areas[j]);
					for (int k = 0; k < managerDistrict.size(); k++) {
						messageToUser = new BaseMessageToUser();
						this.baseService.copyAndOverrideExistedValueFromStringMap(model, messageToUser, null);
						if(setTemp != null && setTemp.size() > 0){
							for (BaseMessageToUser user : setTemp){
								if(managerDistrict.get(k).getManager().getId().equals(user.getReadUser().getId())){
									isExit = false;
								}
							}
							if(isExit){
								messageToUser.setReadUser(managerDistrict.get(k).getManager());
								messageToUser.setMsgID(baseMessage);
								set.add(messageToUser);
							}
							isExit = true;
						}else{
							messageToUser.setReadUser(managerDistrict.get(k).getManager());
							messageToUser.setMsgID(baseMessage);
							set.add(messageToUser);
						}
					}
				}
			}
		}
		this.baseService.saveAllEntities(set);
	}
	@Override
	public void addMyMessaage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		BaseMessage baseMessage = new BaseMessage();
		BaseMessageToUser toUser = new BaseMessageToUser();
		this.baseService.copyAndOverrideExistedValueFromStringMap(model, baseMessage, null);
		this.baseService.copyAndOverrideExistedValueFromStringMap(model, toUser, null);
		
		//消息实体
		//消息类型为上报
		baseMessage.setMsgType(this.baseService.findEntityByID(DictionaryData.class, "msgtype.1"));
		baseMessage.setFromUser(SecurityUtil.getPrincipal());//保存当前用户为发送人
		
		//接受消息的用户
		toUser.setMsgID(baseMessage);
		toUser.setReadStatus("1");
		
		this.baseService.saveEntity(baseMessage);
		this.baseService.saveEntity(toUser);
		
	}
	@Override
	public void updateMyMessage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		BaseMessage baseMessage = (BaseMessage) this.baseService.findEntityByID(BaseMessage.class, model.get("id"));
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = (User) this.baseService.findEntityByID(User.class, model.get("readuser"));
		Set<BaseMessageToUser> set = baseMessage.getToUser();
		BaseMessageToUser toUser = set.iterator().next();
		this.baseService.copyAndOverrideExistedValueFromStringMap(model, baseMessage, null);
		this.baseService.updateEntity(baseMessage);
		toUser.setReadUser(user);
		toUser.setModificator(currentUser);
		toUser.setModifyDate(DateUtil.getSystemDateTime());
		this.baseService.updateEntity(toUser);
		
	}
	@Override
	public void removeMessage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		String message_id = model.get("id");
		String hsql = "update BaseMessage BM set BM.messageStatus = 1 where BM.id = ?";
		this.baseService.updateByHSQL(hsql, message_id);
	}
	
}
