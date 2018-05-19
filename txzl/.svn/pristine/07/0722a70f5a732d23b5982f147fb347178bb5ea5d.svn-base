package com.tenwa.leasing.serviceImpl.cust;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.entity.cust.CustInfoPerson;
import com.tenwa.leasing.entity.cust.CustTypeInfo;
import com.tenwa.leasing.service.cust.CustService;
import com.tenwa.leasing.utils.WorkflowUtil;




/**
 * 客户信息
 * @Title: CustServiceImpl.java
 * @package: com.tenwa.leasing.serviceImpl.cust
 * @author: tpf
 * @date 2014年11月17日 下午6:04:01 
 * @version V5.1
 */
@Service(value="custService")
public class CustServiceImpl extends AbstractBaseServiceImpl implements
		CustService {

	@Resource
    private BaseDao baseDao;
	@Override
	public BaseDao getBussinessDao() throws Exception {
		return baseDao;
	}
	@Resource(name = "tableService")
	private TableService tableService;
	/**
	  * 添加法人客户信息
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void addCustLaw(Map<String, String> model) throws Exception {
		CustInfo custInfo = new CustInfo();//客户基本信息
		//设置客户编号
		String custNumber = WorkflowUtil.getCustInfoSerialNumber(this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
		custInfo.setCustNumber(custNumber);
		custInfo.setCustName(model.get("custname"));
		custInfo.setCustShorthand(model.get("custshorthand"));
		custInfo.setDraft(model.get("draft"));
		custInfo.setInvalid("否");
		CustInfoCompany custInfoCompany = new CustInfoCompany();//法人客户信息
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		dictDataClassMapping.put("District", "id");
		dictDataClassMapping.put("Industry", "id");
		baseDao.copyAndOverrideExistedValueFromStringMap(model, custInfoCompany, dictDataClassMapping);
		
		custInfoCompany.setCustId(custInfo);
		
		DictionaryData dict=new DictionaryData();
		Map<String, Object> whereMap=new HashMap<String, Object>();
		whereMap.put("code", "CUST_INFO_COMPANY");
		dict=(DictionaryData)this.findEntityByProperties(DictionaryData.class, whereMap).get(0);
		custInfo.setCustClass(dict);
		
		//保存客户类别
		String custType=model.get("custtype");
		if(custType!=null&&!custType.equals("")){
			 String custTypes[]=custType.split(",");
			 List<CustTypeInfo> custTypeInfos=new ArrayList<CustTypeInfo>();
			 for (String temp:custTypes) {
				 CustTypeInfo cti=new CustTypeInfo();
				 whereMap=new HashMap<String, Object>();
				 whereMap.put("code", temp);
				 dict=(DictionaryData)this.findEntityByProperties(DictionaryData.class, whereMap).get(0);
				 cti.setCustId(custInfo);
				 cti.setCustType(dict);
				 custTypeInfos.add(cti);
			}
			 this.saveAllEntities(custTypeInfos);
		}
		
		//保存
		this.baseDao.saveEntity(custInfo);
		this.baseDao.saveEntity(custInfoCompany);
	}
	/**
	  * 修改法人客户信息
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void updateCustLaw(Map<String, String> model) throws Exception {
		CustInfoCompany custInfoCompany = (CustInfoCompany)this.baseDao.findEntityByID(CustInfoCompany.class, model.get("companyid"));//法人客户信息
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		this.baseDao.copyAndOverrideExistedValueFromStringMap(model, custInfoCompany, dictDataClassMapping);
		
		//构建客户基本信息主表
		CustInfo info=custInfoCompany.getCustId();
		info.setCustName(model.get("custname"));
		info.setCustShorthand(model.get("custshorthand"));
		info.setDraft(model.get("draft"));
		info.setInvalid(model.get("invalid"));
		//保存客户类别信息
		 Map<String,CustTypeInfo> containMap = new HashMap<String,CustTypeInfo>();
		 for(CustTypeInfo cti : info.getCustType()){
			 containMap.put(cti.getCustType().getCode(),cti); 
		 }
		//保存客户类别信息
		String custType=model.get("custtype");
		 JSONArray jArray = new JSONArray();
	     if(!StringUtil.nullToString(custType).trim().isEmpty()){
		     String custTypes[]=custType.split(",");
		     for (String temp:custTypes) {
		    	  JSONObject jobj  = new JSONObject();
		    	  jobj.put("custType", temp);
		    	  if(containMap.containsKey(temp)){
		    		  jobj.put("id", containMap.get(temp).getId());
		    	  }
		    	  jArray.put(jobj);
		     }
	     }

			 this.updateOneToManyCollections(info, "custType", CustTypeInfo.class, "custId", jArray.toString(), dictDataClassMapping);
		
		
		this.updateEntity(info);
		this.updateEntity(custInfoCompany);
		
	}
	/**
	  * 删除法人客户信息
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void removeCustLaw(Map<String, String> model) throws Exception {
		//构建出法人基本信息
		CustInfo info = (CustInfo)this.baseDao.findEntityByID(CustInfo.class, model.get("id"));
		info.setInvalid("是");
		this.updateEntity(info);
	}
	/**
	  * 添加自然人客户信息
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void addCustEwlp(Map<String, String> model) throws Exception {
		CustInfo custInfo = new CustInfo();//客户基本信息
		//设置客户编号
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMdd");
		//String custNumber=sdf.format(new Date())+(int)(Math.random()*9000+1000);
		String custNumber = WorkflowUtil.getCustInfoSerialNumber(this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
		//客户编号
		custInfo.setCustNumber(custNumber);
		custInfo.setCustName(model.get("custname"));
		custInfo.setDraft(model.get("draft"));
		custInfo.setInvalid(model.get("invalid"));
		CustInfoPerson custInfoPerson = new CustInfoPerson();//法人客户信息
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		dictDataClassMapping.put("District", "id");
		dictDataClassMapping.put("Industry", "id");
		baseDao.copyAndOverrideExistedValueFromStringMap(model, custInfoPerson, dictDataClassMapping);
	 	
		custInfoPerson.setCustId(custInfo);
		
		DictionaryData dict=new DictionaryData();
		Map<String, Object> whereMap=new HashMap<String, Object>();
		whereMap.put("code", "CUST_INFO_PERSON");
		dict=(DictionaryData)this.findEntityByProperties(DictionaryData.class, whereMap).get(0);
		custInfo.setCustClass(dict);
		
		//保存客户类别
		String custType=model.get("custtype");
		if(custType!=null&&!custType.equals("")){
			 String custTypes[]=custType.split(",");
			 List<CustTypeInfo> custTypeInfos=new ArrayList<CustTypeInfo>();
			 for (String temp:custTypes) {
				 CustTypeInfo cti=new CustTypeInfo();
				 whereMap=new HashMap<String, Object>();
				 whereMap.put("code", temp);
				 dict=(DictionaryData)this.findEntityByProperties(DictionaryData.class, whereMap).get(0);
				 cti.setCustId(custInfo);
				 cti.setCustType(dict);
				 custTypeInfos.add(cti);
			}
			 this.saveAllEntities(custTypeInfos);
		}
		
		//保存
		this.baseDao.saveEntity(custInfo);
		this.baseDao.saveEntity(custInfoPerson);
	}
	/**
	  * 修改自然人客户信息
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void updateCustEwlp(Map<String, String> model) throws Exception {
		CustInfoPerson custInfoPerson = (CustInfoPerson)this.baseDao.findEntityByID(CustInfoPerson.class, model.get("personid"));//法人客户信息
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		dictDataClassMapping.put("Department", "id");
		this.baseDao.copyAndOverrideExistedValueFromStringMap(model, custInfoPerson, dictDataClassMapping);
		
		//构建客户基本信息主表
		CustInfo info=custInfoPerson.getCustId();
		info.setCustName(model.get("custname"));
		info.setDraft(model.get("draft"));
		info.setInvalid(model.get("invalid"));
		//保存客户类别信息
		 Map<String,CustTypeInfo> containMap = new HashMap<String,CustTypeInfo>();
		 for(CustTypeInfo cti : info.getCustType()){
			 containMap.put(cti.getCustType().getCode(),cti); 
		 }
		//保存客户类别信息
		String custType=model.get("custtype");
		 JSONArray jArray = new JSONArray();
	     if(!StringUtil.nullToString(custType).trim().isEmpty()){
		     String custTypes[]=custType.split(",");
		     for (String temp:custTypes) {
		    	  JSONObject jobj  = new JSONObject();
		    	  jobj.put("custType", temp);
		    	  if(containMap.containsKey(temp)){
		    		  jobj.put("id", containMap.get(temp).getId());
		    	  }
		    	  jArray.put(jobj);
		     }
	     }

			 this.updateOneToManyCollections(info, "custType", CustTypeInfo.class, "custId", jArray.toString(), dictDataClassMapping);
		
		
		this.updateEntity(info);
		this.updateEntity(custInfoPerson);
		
	}
	/**
	  * 删除自然人客户信息
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void removeCustEwlp(Map<String, String> model) throws Exception {
		//构建出法人基本信息
		CustInfo info = (CustInfo)this.baseDao.findEntityByID(CustInfo.class, model.get("id"));
		info.setInvalid("是");
		this.updateEntity(info);
	}
	@Override
	public void saveBelongingPeople(Map<String, String> model) throws Exception {
		String custid = model.get("custid");
		String belongingPeople = model.get("belongingPeople");
		User user = this.findEntityByID(User.class, belongingPeople);
		CustInfo custInfo = this.findEntityByID(CustInfo.class, custid);
		String systemDate = DateUtil.getSystemDateTime();//当前系统时间
	    if("CUST_INFO_PERSON".equals(custInfo.getCustClass().getId())){
	    	CustInfoPerson custInfoPerson = custInfo.getCustInfoPerson();
	    	custInfoPerson.setBelongingPeople(belongingPeople);
	    	custInfoPerson.setBelongingDate(systemDate);
	    	this.updateEntity(custInfoPerson);
	    }else{
	    	CustInfoCompany custInfoCompany = custInfo.getCustInfoCompany();
	    	custInfoCompany.setBelongingPeople(belongingPeople);
	    	custInfoCompany.setBelongingDate(systemDate);
	    	this.updateEntity(custInfoCompany);
	    }
	}
}
