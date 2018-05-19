package com.tenwa.leasing.controller.contractDocList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.entity.User;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractBorrowHis;
import com.tenwa.leasing.entity.contract.ContractDocList;
import com.tenwa.leasing.entity.contract.ContractDocListBorrow;
import com.tenwa.leasing.entity.contract.ContractFiling;
import com.tenwa.leasing.entity.contract.ContractFilings;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.finacial.DepositInterest;
import com.tenwa.leasing.service.contractcomm.ContractCommService;



@Controller(value = "contractDocListController")
@RequestMapping(value = "/**/acl")
public class ContractDocListController {
	private Logger logger=Logger.getLogger(ContractDocListController.class);
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	/**
	 * @param request
	 * @param response
	 * 待补文档管理
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveallcontractdoclist.acl")
	@ResponseBody
	public JsonReturnResult saveallcontractdoclist(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
			try {
				String jsonEquipsString = model.get("datas");
				String cid=model.get("cid");
				ContractInfo contracinfo=this.tableService.findEntityByID(ContractInfo.class,cid);
				this.tableService.updateOneToManyCollections(contracinfo, "ContractDocLists", ContractDocList.class, "contractId", jsonEquipsString,null);
			
				if(logger.isInfoEnabled()){
					 logger.info("提交成功!");
				}
				//throw new BusinessException("e");
			} catch (Exception e) {
				e.printStackTrace();
				returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
				returnResult.setContent(e.getMessage());
			}
			return returnResult;
	}
	/**
	 * 待补文档管理
	 */
	@RequestMapping(value = "/savecontractdocjson.acl")
	@ResponseBody
	public JsonReturnResult savecontractdocjson(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
			try {
				String jsonDocString = model.get("json_document_list_str");
				String cid=model.get("cid");
				ContractInfo  ci=this.tableService.findEntityByID(ContractInfo.class, cid);
				//文档归档整理
				saveContractApprovalFileInfo(ci, jsonDocString);
			} catch (Exception e) {
				e.printStackTrace();
				returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
				returnResult.setContent(e.getMessage());
			}
			return returnResult;
	}
	
	@RequestMapping(value = "/saveallcontractarchives.acl")
	@ResponseBody
	public JsonReturnResult saveallcontractarchives(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			//model存放来自页面的数据
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
			try {
				String containerNumber = model.get("containerNumber");
				//传的是用户的id
				String userName=model.get("userName");
				User user=this.tableService.findEntityByID(User.class, userName);
				
				String conid=model.get("conid");
				ContractInfo contracinfo=this.tableService.findEntityByID(ContractInfo.class,conid);
				String fillingdate = model.get("filing_date");
				Map<String, Object> propertiesMap=new HashMap<String,Object>();
				propertiesMap.put("contractId", contracinfo);
				//通过合同编号属性查找有没有相应记录
				List<ContractFilings> list=this.tableService.findEntityByProperties(ContractFilings.class, propertiesMap);
				if(list.size()==0){
					ContractFilings confils=new ContractFilings();
					confils.setContractId(contracinfo);
					confils.setFilingDate(fillingdate);
					confils.setContainerNumber(containerNumber);
					confils.setFillingUser(user);
					confils.setFilingStatus("已归档");
					this.tableService.saveOrUpdateEntity(confils);
				}else{
					//更新首先要获取到这条记录
					ContractFilings c=list.get(0);
					c.setFilingDate(fillingdate);
					c.setContainerNumber(containerNumber);
					c.setFillingUser(user);
					c.setFilingStatus("已归档");
					this.tableService.updateEntity(c);
				}	
				if(logger.isInfoEnabled()){
					 logger.info("提交成功!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
				returnResult.setContent(e.getMessage());
			}
		return returnResult;
	}
	
	/**
	 * @param request
	 * @param response
	 * 本金一次性开票
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveallcontractdoclistborrow.acl")
	@ResponseBody
	public JsonReturnResult saveallcontractdoclistborrow(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
			try {
				String jsonEquipsString = model.get("datas");
				String cid=model.get("cid");
				JSONArray resultArray = new JSONArray(jsonEquipsString);
				for(int i=0;i<resultArray.length();i++){
					JSONObject resultObj = resultArray.optJSONObject(i);
					String id=resultObj.getString("id");
					ContractDocListBorrow contractdoclistborrow= this.tableService.findEntityByID(ContractDocListBorrow.class, id);
					String memoback=resultObj.getString("memoback");
					String backdate=resultObj.getString("backdate");
					String backman=resultObj.getString("backman");
					String isbackback=resultObj.getString("isback");
					if(isbackback.equals("1")){
						contractdoclistborrow.setIsback("未借出");
					}else if (isbackback.equals("0")){
						contractdoclistborrow.setIsback("已借出");
					}
					contractdoclistborrow.setMemoback(memoback);
					contractdoclistborrow.setBackdate(backdate);
					contractdoclistborrow.setBack_man(backman);
					//contractdoclistborrow.setIsbackback(isbackback);
					this.tableService.updateEntity(contractdoclistborrow);
				}
				if(logger.isInfoEnabled()){
					 logger.info("提交成功!");
				}
				//throw new BusinessException("e");
			} catch (Exception e) {
				e.printStackTrace();
				returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
				returnResult.setContent(e.getMessage());
			}
			return returnResult;
	}
	private void saveContractApprovalFileInfo(ContractInfo info,String jsonDocString) throws Exception {
		JSONArray array = new JSONArray(jsonDocString);
		List<ContractDocList> list = new ArrayList<ContractDocList>(); 
		User modifyUser = (User) SecurityUtil.getPrincipal();
		String modifyDate = DateUtil.getSystemDateTime();
		for(int i=0;i<array.length();i++){
			ContractDocList doc = new ContractDocList();
			JSONObject docobj = array.getJSONObject(i);
			this.tableService.copyAndOverrideExistedValueFromJSONObject(docobj, doc,null, "");
			doc.setModifyDate(modifyDate);
			doc.setModificator(modifyUser);
			list.add(doc);
		}
		if(null!=list&&list.size()>0){
			this.tableService.saveOrUpdateAllEntities(list);
		}
	}
	
	
	/**
	 * 档案新增申请保存已导入的合同档案
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showContractAchives.acl")
	public String showContractAchives(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		System.out.println("model="+model);
		String returnstr = "";
		try {	
				this.contractCommService.showContractAchives(model);	
				returnstr=	"solutions/leasing/doc_manager/contract_archives/contract_archives_detail.jsp";
			//	returnstr = "{flag:\"true\",msg:\"成功！\",opertype:\"add\"}";
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage()
					+ "\",opertype:\"add\"}";
		}
		return returnstr;
	}
	@RequestMapping(value = "/saveContractArchives.acl")
	@ResponseBody
	public JsonReturnResult saveContractArchives(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			//model存放来自页面的数据
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			String str=model.get("arryid");
			String [] strarry=str.split(",");
			JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
			try {
				List<ContractFiling> list=new ArrayList<ContractFiling>();
				list=this.tableService.findEntityByIDArray(ContractFiling.class, strarry);
				List<ContractFiling> newlist=new ArrayList<ContractFiling>();
				if(list.size()>0){
					for (ContractFiling contractFiling : list) {
						//档案编号唯一校验
						String sqlquery="From ContractFiling c where  c.fillingNumber=? and c.filingStatus='已归档'";
						List<ContractFiling> queryList=this.tableService.
								findResultsByHSQL(sqlquery, contractFiling.getFillingNumber()==null?"*23*":contractFiling.getFillingNumber());
						if(queryList.size()>0){
							returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
							returnResult.setContent("档案编号已经存在,请修改档案编号后再归档！");
							return returnResult;
						}
						contractFiling.setFilingStatus("已归档");
						newlist.add(contractFiling);
					}
				}
				this.tableService.saveAllEntities(newlist);
				if(logger.isInfoEnabled()){
					 logger.info("提交成功!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
				returnResult.setContent(e.getMessage());
			}
		return returnResult;
	}
	
	@RequestMapping(value = "/checkArchives.acl")
	@ResponseBody
	public JsonReturnResult checkArchives(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			//model存放来自页面的数据
		    Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			String id=model.get("id");
			String querysql="  select 1 from CONTRACT_BORROW_HIS  c where c.contractFilingId=? and c.fileStatus  <> '3'";
			List<Map<String, Object>> querylist=new ArrayList<Map<String, Object>>();
			JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
			try {
				querylist=this.tableService.queryListBySql(querysql, id);
				if(querylist.size()>0){
					returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
					returnResult.setContent("该档案已经有借阅记录，不允许删除！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
				returnResult.setContent(e.getMessage());
			}
		return returnResult;
	}
	
}
