package com.tenwa.leasing.controller.proj;

import java.rmi.server.ObjID;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.controller.BaseController;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.finance.FinancialData;
import com.tenwa.leasing.entity.proj.ProjCreditVote;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;

/**
 * 
 * @author 张闯
 * @date 2013-6-5上午10:55:13
 * @info 本方信息的Action
 * @Copyright 
 * Tenwa
 */
@Controller(value = "projCreditController")
@RequestMapping(value = "/**/acl" )
public class ProjCreditController extends BaseController{
	private Logger logger=Logger.getLogger(ProjCreditController.class);
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "proCommService")
	private ProCommService proCommService;
	// #################
	@RequestMapping(value = "/addVote.acl")
	public String addVote(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		 ProjCreditVote vote = new ProjCreditVote();
		 //根据当前用户以及项目和
		 String projNumber = model.get("projNumber");
		 String docId = model.get("docid");
		 //根据当前的docid和用户
		 String  voteHql = "from ProjCreditVote pcv where pcv.projNumber = ? and voter = ? and docId =? ";
		 List<ProjCreditVote> votes = this.tableService.findResultsByHSQL(voteHql, projNumber,SecurityUtil.getPrincipal(),docId);
		 if(votes != null && votes.size() > 0 ){
			 vote = votes.get(0);
		 }
		 String voteview=model.get("voteview").replaceAll("\"", "\'").replaceAll("\r|\n", " ");
		 vote.setProjNumber(projNumber);
		 vote.setDocId(docId);
		 vote.setVoter(SecurityUtil.getPrincipal());
		 vote.setVoteView(voteview);
		 vote.setIsView(model.get("isagree"));
		 vote.setFlowName(model.get("flowname"));
		 if(null==vote.getCreator()){
			 vote.setCreator(SecurityUtil.getPrincipal());
			 vote.setCreateDate(DateUtil.getSystemDateTime());
		 }else{
			 vote.setModificator(SecurityUtil.getPrincipal());
			 vote.setModifyDate(DateUtil.getSystemDateTime());
		 }
		 this.tableService.saveOrUpdateEntity(vote);
		 if(logger.isInfoEnabled()){
			 logger.info("新增成功!");
		 }
		return null;
	}
	@RequestMapping(value = "/findbuss.acl")
	@ResponseBody
	public String findbuss(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		String projId = model.get("pid");
		ProjInfo projInfo = this.tableService.findEntityByID(ProjInfo.class, projId);
		Map<String,String> map=new HashMap<String, String>();
		proCommService.loadProjRentCalculation(projInfo, map);
		ObjectMapper mapper = new ObjectMapper();
		String returnInfo = mapper.writeValueAsString(map);
		return returnInfo;
	}

	// #################
		@RequestMapping(value = "/getVotes.acl")
		public String getVotes(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			 //根据当前用户以及项目和
			 String projNumber = model.get("projNumber");
			 String docId = model.get("docid");
			 //根据当前的docid和用户
			 String  voteHql = "from ProjCreditVote pcv where pcv.projNumber = ?  and docId =?";
			 List<ProjCreditVote> votes = this.tableService.findResultsByHSQL(voteHql, projNumber , docId);
			 StringBuffer sb = new StringBuffer();
			 sb.append("{\"results\":[");
			 int i = 1;
			 int j = votes.size();
			 for(ProjCreditVote vote : votes){
				 sb.append("{\"name\":\"") ;
				 sb.append(vote.getVoter().getRealname());
				 sb.append("\",\"view\":\"");
				 sb.append(vote.getVoteView());
				 sb.append("\",\"isview\":\"");
				 sb.append(vote.getIsView());
				 sb.append("\"}");
				 if(i != j){
					 sb.append(",") ;
				 }
				 i++;
			 }
			 sb.append("]}");
			 this.output(response, sb.toString());
			return null;
		}

		// #################
			@RequestMapping(value = "/getVotesByUser.acl")
			public String getVotesByUser(HttpServletRequest request,
					HttpServletResponse response) throws Exception {
				 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
				 String projNumber = model.get("projNumber");
				 String docId = model.get("docid");
				 String  voteHql = "from ProjCreditVote pcv where pcv.projNumber = ? and voter = ? and docId =? ";
				 List<ProjCreditVote> votes = this.tableService.findResultsByHSQL(voteHql, projNumber,SecurityUtil.getPrincipal(),docId);
				 StringBuffer sb = new StringBuffer();
				 sb.append("{\"results\":[");
				 int i = 1;
				 int j = votes.size();
				 for(ProjCreditVote vote : votes){
					 sb.append("{\"name\":\"") ;
					 sb.append(vote.getVoter().getUsername());
					 sb.append("\",\"view\":\"");
					 sb.append(vote.getVoteView().replaceAll("\n", "<br>"));
					 sb.append("\",\"isview\":\"");
					 sb.append(vote.getIsView());
					 sb.append("\"}");
					 if(i != j){
						 sb.append(",") ;
					 }
					 i++;
				 }
				 sb.append("]}");
				 this.output(response, sb.toString());
				return null;
			}

			@RequestMapping(value = "/checkFinancialStatements.acl")
			@ResponseBody //告诉spring 要返回值 
			public String checkFinancialStatements(HttpServletRequest request,
					HttpServletResponse response) throws Exception {
				Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
				String custId = model.get("custid");
				Map<String,Object> pram=new HashMap<String, Object>();
				pram.put("custInfo", this.tableService.findEntityByID(CustInfo.class, custId));
				List<FinancialData> fds = this.tableService.findEntityByProperties(FinancialData.class, pram);
				if(null!=fds&&fds.size()>0){
					return "existence";
				}else{
					return "unexistence";
				}
			}

}
