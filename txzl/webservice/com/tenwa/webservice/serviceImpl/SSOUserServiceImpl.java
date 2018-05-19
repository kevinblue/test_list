package com.tenwa.webservice.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.User;
import com.tenwa.business.entity.UserOtherInfo;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.i18n.LocaleEnum;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.MD5Util;
import com.tenwa.webservice.service.SSOUserService;

@Service
@WebService(serviceName = "SSOUserService",endpointInterface="com.tenwa.webservice.service.SSOUserService")
public class SSOUserServiceImpl implements SSOUserService {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Override
	public String addAccount(String value) {
		try{
			JSONObject data = new JSONObject(value);
			//员工编号
			String pernr = data.getString("PERNR");
			//用户真实姓名
			String ename = data.getString("ENAME");
			//电子邮件
			String email = data.getString("ZHR_EMAIL");
			//手机号码
			String phone = data.getString("ZHR_CELL");
			//密码
			String password = data.getString("PASSWORD");
			//判断员工编号，在系统中是否存在
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("username", pernr);
			List<User> users  = this.tableService.findEntityByProperties(User.class, params);
			if(users != null && users.size() > 0 ){
				return "001";
			}else{
				User user = new User();
				user.setUsername(pernr);
				user.setCode(pernr);
				user.setRealname(ename);
				user.setEmail(email);
				user.setTelephone(phone);
				user.setSourcepassword(password);
				user.setPassword("TEMP_PASSWORD");
				user.setEnabled(true);
				user.setUserLocal(LocaleEnum.zh_CN);
				User mUser = new User();
				mUser.setId("Administrator");
				user.setCreator(mUser);
				user.setCreateDate(DateUtil.getSystemDateTime());
				this.tableService.saveEntity(user);
				String passwordAes =  MD5Util.getMD5EncodedPasswordWithSalt(password, user.getId());
				user.setPassword(passwordAes);
				this.tableService.saveOrUpdateEntity(user);
				UserOtherInfo oi = new UserOtherInfo();
				tableService.copyAndOverrideExistedValueFromJSONObject(data, oi, null, false);
				oi.setCreator(user);
				this.tableService.saveEntity(oi);
				return "000";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "002";
		}
		
	}
	
	@Override
	public String modifyAccount(String value) {
		try{
			JSONObject data = new JSONObject(value);
			//员工编号
			String pernr = data.getString("PERNR");
			//用户真实姓名
			String ename = data.getString("ENAME");
			//电子邮件
			String email = data.getString("ZHR_EMAIL");
			//手机号码
			String phone = data.getString("ZHR_CELL");
			//密码
			String password = data.getString("PASSWORD");
			//判断员工编号，在系统中是否存在
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("username", pernr);
			List<User> users  = this.tableService.findEntityByProperties(User.class, params);
			if(users != null && users.size() > 0 ){
				User user = users.get(0);
				user.setRealname(ename);
				user.setEmail(email);
				user.setTelephone(phone);
				user.setSourcepassword(password);
				String passwordAes =  MD5Util.getMD5EncodedPasswordWithSalt(password, user.getId());
				user.setPassword(passwordAes);
				User mUser = new User();
				mUser.setId("Administrator");
				user.setModificator(mUser);
				user.setModifyDate(DateUtil.getSystemDateTime());
				user.setLastUpdatePasswordDate(DateUtil.getSystemDateTime());
				this.tableService.saveOrUpdateEntity(user);
				Map<String, Object> otherParams = new HashMap<String, Object>();
				otherParams.put("creator", user);
				List<UserOtherInfo>  userInfos = this.tableService.findEntityByProperties(UserOtherInfo.class, otherParams);
				UserOtherInfo info = new UserOtherInfo();
				if(userInfos != null &&  userInfos.size() > 0 ){
					info = userInfos.get(0);
				}
				tableService.copyAndOverrideExistedValueFromJSONObject(data, info, null, false);
				info.setCreator(user);
				this.tableService.saveOrUpdateEntity(info);
				return "100";
			}else{
				return "101";
			}
		}catch(Exception e){
			return "102";
		}
	}
	
	@Override
	public String disableAccount(String value) {
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("username", value);
			List<User> users  = this.tableService.findEntityByProperties(User.class, params);
			if(users != null && users.size() > 0 ){
				User user = users.get(0);
				user.setEnabled(false);
				this.tableService.saveOrUpdateEntity(user);
				return "200";
			}else{
				return "201";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "202";
		}
	}
	
	@Override
	public String enableAccount(String value) {
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("username", value);
			List<User> users  = this.tableService.findEntityByProperties(User.class, params);
			if(users != null && users.size() > 0 ){
				User user = users.get(0);
				user.setEnabled(true);
				this.tableService.saveOrUpdateEntity(user);
				return "300";
			}else{
				return "301";
			}
		}catch(Exception e){
			return "302";
		}
	}

}
