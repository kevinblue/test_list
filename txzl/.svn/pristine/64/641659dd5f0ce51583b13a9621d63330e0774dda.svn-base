
 /**
 * 项目名称：    系统名称
 * 包名：              com.business.serviceImpl.cust
 * 文件名：         CustServiceImpl.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-5-18-下午12:30:07
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.leasing.serviceImpl.sysmgr;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.base.District;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.entity.cust.CustInfoPerson;
import com.tenwa.leasing.entity.cust.CustTypeInfo;
import com.tenwa.leasing.service.cust.CustService;
import com.tenwa.leasing.service.sysmgr.SysDataMgrService;




 /**
 * 类名称：     CustServiceImpl
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-5-18 下午12:30:07
 * 修改备注：
 * @version 1.0.0
 **/
@Service(value="sysDataMgrService")
public  class SysDatamgrServiceImpl  implements SysDataMgrService {


	@Resource(name = "baseService")
	private BaseService baseService;
	

	//orgCode
	@Override
	public void addDistrict(Map<String, String> model) throws Exception {
		
		String jsonArrayString=model.get("data");
		
		//List<District> dist=(List<District>) this.baseService.getEntitiesByJSONArrayString(District.class, jsonArrayString,null,false);
		List<District> dist = null;
		
		this.baseService.mergeAllEntities(dist);
	}
	
	@Override
	public void updateDistrict(Map<String, String> model) throws Exception {
		District district = (District)this.baseService.findEntityByID(District.class, model.get("id"));//法人客户信息
		this.baseService.copyAndOverrideExistedValueFromStringMap(model, district, null);
		this.baseService.updateEntity(district);
	}
	
	@Override
	public void removeDistrict(Map<String, String> model) throws Exception {
		//构建出法人基本信息
		District district = (District)this.baseService.findEntityByID(District.class, model.get("id"));
		district.getChildrenDistrict();
		this.baseService.removeEntity(district);
	}
	
	
}
