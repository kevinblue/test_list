package com.tenwa.leasing.serviceImpl.ebank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.service.TableService;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoAccount;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.service.ebank.EbankService;
import com.tenwa.leasing.service.fileTemplate.FileTemplateService;
import com.tenwa.leasing.utils.WorkflowUtil;

@Service(value="ebankService")
public class EbankServiceImpl extends AbstractBaseServiceImpl implements EbankService {
	
	
	@Resource(name="fileTemplateService")
	private FileTemplateService  templateService;

	@Resource(name="tableService")
	private TableService tableService;
	@Resource
	 private BaseDao baseDao;
	 private Logger logger=Logger.getLogger(EbankServiceImpl.class);
	@Override
	public BaseDao getBussinessDao() throws Exception {
		// TODO Auto-generated method stub
		return baseDao;
	}

	@Override
	public String addEbank(Map<String, String> model) throws Exception {
		// TODO Auto-generated method stub
		 Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		 dictDataClassMapping.put("DictionaryData", "code");
		 String sn=model.get("sn");
		 Map<String,Object>propertiesMap=new HashMap<String,Object>();
		 propertiesMap.put("sn",sn );
		 List  listf=new ArrayList ();
		 listf= this.getBussinessDao().findEntityByProperties(FundEbankData.class, propertiesMap);
		 if(listf!=null&&listf.size()>0){
			 return "流水号重复";  
		 }else{	 
		   FundEbankData info = new FundEbankData();
		   this.getBussinessDao().copyAndOverrideExistedValueFromStringMap(model, info, dictDataClassMapping);
		   info.setEbdataId(WorkflowUtil.getEbankNoInfoSerialNumber(null,this.getBussinessDao().getHibernateTemplate(), this.getBussinessDao().getJdbcTemplate()));
		   if(info.getNoWithMoney()==null){
			   info.setNoWithMoney(new BigDecimal(0.00));
		   }
		   this.getBussinessDao().saveEntity(info);
		   
		   if(logger.isInfoEnabled()){
				 logger.info("新增成功!");
		   }
		 }
       return null;
	}
	@Override
	public String checkLoadEbank(String ebankInfo, Map<String, String> model)
			throws Exception {
		JSONArray jsonArray = new JSONArray(ebankInfo);
		JSONArray newjsonArray = new JSONArray();
		List<String> ebankList=new ArrayList<String>();
        String sns="";
        String sn ="";
        String samesns="";
        String temp="";
        String client="";
        String message="";
        String dealerstr="";
        String dealer="";
        String dealertemp="";
        String noindealerstr="";
        String bank="";
        String account="";
        String number="";
        String sbankmessage="";
        List<String> dealerArray=new ArrayList<String>();
        String requirestring="";
        //测查编号是否有重复
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			sn=(jsonObj.has("sn"))?jsonObj.get("sn").toString():"";
			dealer=(jsonObj.has("custid"))?jsonObj.getString("custid").trim():"";
			client=(jsonObj.has("clientname"))?jsonObj.getString("clientname").trim():"";
			bank=(jsonObj.has("ownbank"))?jsonObj.getString("ownbank").trim():"";
			account=(jsonObj.has("ownaccount"))?jsonObj.getString("ownaccount").trim():"";
			number=(jsonObj.has("ownaccnumber"))?jsonObj.getString("ownaccnumber").trim():"";
			temp="";
			if("".equalsIgnoreCase(sn)){temp=temp+",流程水号必填";}
			if("".equalsIgnoreCase(dealer) && "".equalsIgnoreCase(client)){temp=temp+",经销商和付款人必填其中一个";}
			if("".equalsIgnoreCase(bank)){temp=temp+",本方银行必填";}
			if("".equalsIgnoreCase(account)){temp=temp+",本方账户必填";}
			if("".equalsIgnoreCase(number)){temp=temp+",本方账号必填";}
			if(temp.length()>0){
				if(requirestring.length()>0){requirestring=requirestring+",";}
				requirestring=requirestring+"第"+(i+1)+"行"+temp;
			}
			dealerArray.add(dealer);
			temp="'"+sn+"'";
			dealertemp="'"+dealer+"'";
			if(sns.indexOf(temp)>=0){
				if(!samesns.equalsIgnoreCase("")){samesns=samesns+",";}
				samesns=samesns+temp;
			}
		    if (sns.equalsIgnoreCase("")){
		    	sns=temp;
		    	dealerstr=dealertemp;
		    }else{
		    	sns=sns+","+temp;
		    	dealerstr=dealerstr+","+dealertemp;
		     }
		   }
	    
		//测查编号是否已经导入数据库
		String HQL="from FundEbankData as fd where fd.sn in( "+sns+")";
		List<FundEbankData> FundList=new ArrayList<FundEbankData>();
		sns="";
		FundList=this.getBussinessDao().findResultsByHSQL(HQL);
		if (FundList.size()>0){
			for(int i=0;i<FundList.size();i++){
			 if (sns.equalsIgnoreCase("")){
				 sns=sns+"'"+FundList.get(i).getSn()+"'";
			    }else{
			    	sns=sns+",'"+FundList.get(i).getSn()+"'";}	
			 }
	    }
		
		/*
		//检查导入的经销商是否在系统中
	    HQL="from CustInfo as c where c.custName in("+dealerstr+") and c.custClass.id='CUST_INFO_DEALER'";
	    List<CustInfo> custList=new ArrayList<CustInfo>();
	    custList=(List<CustInfo>)this.getBussinessDao().findResultsByHSQL(HQL, null);
	    dealerstr="";
	    if(custList.size()>0){
	        for(int i=0;i<custList.size();i++){
	        	if("".equalsIgnoreCase(dealerstr)){
	        		dealerstr=custList.get(i).getCustName();
	        	}else{
	        		dealerstr=dealerstr+","+custList.get(i).getCustName();
	        	}
	        }	
	    }
	    dealerstr=","+dealerstr+",";
	    noindealerstr="";
	    for(int i=0;i<dealerArray.size();i++){
	       if(!"".equalsIgnoreCase(dealerArray.get(i))){
	          dealertemp=","+dealerArray.get(i)+",";
	          if(dealerstr.indexOf(dealertemp)<0){
	        	  if(noindealerstr.equalsIgnoreCase("")){
	        		  noindealerstr=dealerArray.get(i);
	        	  }else{
	        		  noindealerstr=noindealerstr+","+ dealerArray.get(i);
	        	  }
	          }
	       }
	    }
	    
	    
	    //检查本方的银行账户账号
	    
	    HQL="from OwnAccount as c ";
	    List<OwnAccount> accountList=new ArrayList<OwnAccount>();
	    accountList=(List<OwnAccount> )this.getBussinessDao().findResultsByHSQL(HQL, null);
	    String noinbank="";
	    boolean cflag=false;
	    for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			bank=(jsonObj.has("ownbank"))?jsonObj.getString("ownbank").trim():"";
			account=(jsonObj.has("ownaccount"))?jsonObj.getString("ownaccount").trim():"";
			number=(jsonObj.has("ownaccnumber"))?jsonObj.getString("ownaccnumber").trim():"";
			cflag=false;
		    for(OwnAccount oa :accountList){
		    	if(oa.getAccBank().equals(bank)&&oa.getAccName().equals(account)&&oa.getAccNumber().equals(number)){
		    		cflag=true;
		    	}
		    }
		    if(!cflag){
		    	noinbank=noinbank+",("+bank+","+account+","+number+")";
		    }
	    }*/
	    message=""; 
	    if(requirestring.length()>0){message=requirestring;}
		if(sns.length()>0){message=message+",流水号"+sns+"已经导入";}
		if(samesns.length()>0){message=message+",流程水号"+samesns+"重复";}
		//if(noindealerstr.length()>0){message=message+",经销商"+noindealerstr+"在系统中不存在";}
	//	if(noinbank.length()>0){message=message+",下列本方信息"+noinbank+"没有在系统中登记";}
		if(message.length()>0){message="导入失败:"+message;}
		return message;
	}

	@Override
	public String removeEbank(Map<String, String> model) throws Exception {
		// TODO Auto-generated method stub
		 FundEbankData info = new FundEbankData();
		 info=(FundEbankData)this.getBussinessDao().findEntityByID(FundEbankData.class, model.get("id"));
		 info.initFundEbank();
		 BigDecimal mmoney=info.getFactMoney().subtract(info.getNoWithMoney());
		 if(info.getMayOpeMoney().subtract(mmoney).intValue()==0){
		    this.getBussinessDao().removeEntity(info);
		    if(logger.isInfoEnabled()){
			   logger.info("删除成功!");
		    }
		  }else{
			 return "已经核销过租金或是非租金";
		 }
        return null;
	}

	@Override
	public String updateEbank(Map<String, String> model) throws Exception {
		// TODO Auto-generated method stub
		 FundEbankData info = new FundEbankData();
		 info=(FundEbankData)this.getBussinessDao().findEntityByID(FundEbankData.class, model.get("id"));
		
		 String ebankid=model.get("ebdataid");
		 String sn=model.get("sn");
		 Map<String,Object>propertiesMap=new HashMap<String,Object>();
		 propertiesMap.put("sn",sn );
		 List  listf=new ArrayList ();
		 listf= this.getBussinessDao().findEntityByProperties(FundEbankData.class, propertiesMap);
		 if(listf.size()>0){
			 FundEbankData temp=(FundEbankData)listf.get(0);
			 if(!temp.getEbdataId().equals(ebankid)){
				 return "修改的流水号已经登记到系统中";
			 }
		 }
		 this.getBussinessDao().copyAndOverrideExistedValueFromStringMap(model, info, null);
		 info.initFundEbank();
		 if(info.getMayOpeMoney().compareTo(BigDecimal.ZERO)>=0){
		     this.getBussinessDao().updateEntity(info);
		     if(logger.isInfoEnabled()){
			 logger.info("修改成功!");
		      }
		  }else{
			 return "修改金额小于已经核销的金额";
		 }
		 return null;
	}

	@Override
	public List<FundEbankData> uploadEbankFromFile(String ebankInfo, Map<String, String> model,BaseFile bf)
			throws Exception {
		// TODO Auto-generated method stub
		 ObjectMapper jsonMapper = new ObjectMapper(); 
		JSONArray jsonArray = new JSONArray(ebankInfo);
		List<String> ebankList=new ArrayList<String>();
		 HashMap<String,String>classFieldMapping=new  HashMap<String,String>();
		 classFieldMapping.put("CustInfo", "custName");
		 Map<String,Object>propertiesMap=new HashMap<String,Object>();
		
		 List<FundEbankData> fds=new ArrayList<FundEbankData>();
		 for(int i=0;i<jsonArray.length();i++){
			  JSONObject jsonObj = jsonArray.getJSONObject(i);
			  Map[] newSetMaps = jsonMapper.readValue("["+jsonObj.toString()+"]", Map[].class);
			  FundEbankData fd=new FundEbankData();
			  this.getBussinessDao().copyAndOverrideExistedValueFromStringMap(newSetMaps[0], fd, classFieldMapping);
			
			  if (newSetMaps[0].containsKey("clientaccnumber")){
				  propertiesMap.clear();
			     String clientaccnumber=(String)newSetMaps[0].get("clientaccnumber");
			     propertiesMap.put("accNumber", clientaccnumber);
			     List<CustInfoAccount> custaccount=(List<CustInfoAccount>)this.getBussinessDao().findEntityByProperties(CustInfoAccount.class, propertiesMap);
			     if(custaccount.size()>0){
			    	 fd.setCustId(custaccount.get(0).getCustId());
			     }
			  }
			  if (newSetMaps[0].containsKey("custid")){
				     propertiesMap.clear();
				     String custname=(String)newSetMaps[0].get("custid");
				     propertiesMap.put("custName", custname);
				     List<CustInfo> cust=(List<CustInfo>)this.getBussinessDao().findEntityByProperties(CustInfo.class, propertiesMap);
				     if(cust.size()>0){
				    	 fd.setCustId(cust.get(0));
				     }
				  }
			  String EbankNumber=WorkflowUtil.getEbankNoInfoSerialNumber(null,this.getBussinessDao().getHibernateTemplate(), this.getBussinessDao().getJdbcTemplate());
			  if(fd.getNoWithMoney()== null){
				  fd.setNoWithMoney(new BigDecimal(0.00));
			  }
			  fd.setEbdataId(EbankNumber);
			  fd.setUpLoadId(bf);
			  this.getBussinessDao().saveEntity(fd);
			  fds.add(fd);
		 }			
		return fds;
	}
	 public String insertEbankFromLoadFile(MultipartFile  multipartFile,Map<String,String> model)throws Exception  {
		    String message;
		    try{
		    BaseFile basefile=this.templateService.saveUpFiletoService(multipartFile, model);
	        String ebankInfo = this.tableService.importExcel(multipartFile, model); 
			       message=checkLoadEbank(ebankInfo, null);
			List<FundEbankData> fds=new ArrayList<FundEbankData>();
			if(message.length()==0){
				 fds=uploadEbankFromFile(ebankInfo,model,basefile);
				//加入导入信息
				message="导入成功:导入"+fds.size()+"条";
			}
			} catch (Exception e) {
				return  "网银上传出错\n"+e.getMessage();
			}
		 return message;
	 }
	 public String updateCancalEbank(Map<String,String> model)throws Exception{
		    String ids=model.get("ids");
			String[] idarray=ids.split(",");
			List<FundEbankData> fundEbankList;
			fundEbankList=(List<FundEbankData>)this.tableService.findEntityByIDArray(FundEbankData.class, idarray);
			for(FundEbankData fd:fundEbankList){
				FundEbankData ftemp=fd;
				ftemp.setInvalid("是");
				ftemp.setSn(ftemp.getSn()+"-作废");
				this.tableService.saveOrUpdateEntity(ftemp);
			}
		 return null;
	 }

}
