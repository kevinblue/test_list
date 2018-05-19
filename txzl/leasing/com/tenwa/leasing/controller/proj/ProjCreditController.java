package com.tenwa.leasing.controller.proj;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.tenwa.leasing.entity.proj.ProjCreditVote;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.entity.proj.ProjPreVote;
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
		try {
			
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			ProjCreditVote vote = new ProjCreditVote();
			//根据当前用户以及项目和
			String projid = model.get("projId");
			String docId = model.get("docid");
			String stepName = model.get("stepName");
			ProjInfo proj = (ProjInfo)this.tableService.findEntityByID(ProjInfo.class, projid);
			//根据当前的docid和用户
			String  voteHql = "from ProjCreditVote pcv where pcv.projId = ? and voter = ? and docId =? ";
			List<ProjCreditVote> votes = this.tableService.findResultsByHSQL(voteHql, proj,stepName,docId);
			if(votes != null && votes.size() > 0 ){
				vote = votes.get(0);
			}
			vote.setProjId(proj);
			vote.setDocId(docId);
			vote.setVoter(SecurityUtil.getPrincipal());
			vote.setVoteView(model.get("voteview"));
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
			logger.info(SecurityUtil.getPrincipal().getRealname()+"投票意见"+vote.getIsView()+"====="+vote.getVoteView());
		} catch (Exception e) {
			this.output(response, "{\"msg\":\"投票失败\"}");
		}
		return null;
	}
	@RequestMapping(value = "/addVote2.acl")
	public String addVote1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			ProjCreditVote vote = new ProjCreditVote();
			//根据当前用户以及项目和
			String projid = model.get("projId");
			String docId = model.get("docid");
			String setpname = model.get("stepname");
			
			ProjInfo proj = (ProjInfo)this.tableService.findEntityByID(ProjInfo.class, projid);
			//根据当前的docid和用户
			String  voteHql = "from ProjCreditVote pcv where pcv.projId = ? and stepname = ? and docId =? ";
			List<ProjCreditVote> votes = this.tableService.findResultsByHSQL(voteHql, proj,setpname,docId);
			if(votes != null && votes.size() > 0 ){
				vote = votes.get(0);
			}
			vote.setProjId(proj);
			vote.setDocId(docId);
			vote.setVoter(SecurityUtil.getPrincipal());
			vote.setVoteView(model.get("voteview"));
			vote.setIsView(model.get("isagree"));
			vote.setFlowName(model.get("flowname"));
			vote.setStepname(model.get("stepname"));
			if(null==vote.getCreator()){
				vote.setCreator(SecurityUtil.getPrincipal());
				vote.setCreateDate(DateUtil.getSystemDateTime());
			}else{
				vote.setModificator(SecurityUtil.getPrincipal());
				vote.setModifyDate(DateUtil.getSystemDateTime());
			}
			this.tableService.saveOrUpdateEntity(vote);
			//this.tableService.saveEntity(vote);
			if(logger.isInfoEnabled()){
				logger.info("新增成功!");
			}
			logger.info(SecurityUtil.getPrincipal().getRealname()+"投票意见"+vote.getIsView()+"====="+vote.getVoteView());
		} catch (Exception e) {
			this.output(response, "{\"msg\":\"投票失败\"}");
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
			 String projid = model.get("projId");
			 String docId = model.get("docid");
			 logger.info(projid+"------"+docId);
			 ProjInfo proj = (ProjInfo)this.tableService.findEntityByID(ProjInfo.class, projid);
			 //根据当前的docid和用户
			 String  voteHql = "from ProjCreditVote pcv where pcv.projId = ?  and docId =? order by stepname";
			 List<ProjCreditVote> votes = this.tableService.findResultsByHSQL(voteHql, proj , docId);
			 StringBuffer sb = new StringBuffer();
			 sb.append("{\"results\":[");
			 int i = 1;
			 int j = votes.size();
			 for(ProjCreditVote vote : votes){
				 sb.append("{\"stepname\":\"") ;
				 sb.append(vote.getStepname());
				 sb.append("\",\"name\":\"");
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
				 String projid = model.get("projId");
				 String docId = model.get("docid");
				 ProjInfo proj = (ProjInfo)this.tableService.findEntityByID(ProjInfo.class, projid);
				 String  voteHql = "from ProjCreditVote pcv where pcv.projId = ? and voter = ? and docId =? ";
				 List<ProjCreditVote> votes = this.tableService.findResultsByHSQL(voteHql, proj,SecurityUtil.getPrincipal(),docId);
				 StringBuffer sb = new StringBuffer();
				 sb.append("{\"results\":[");
				 int i = 1;
				 int j = votes.size();
				 for(ProjCreditVote vote : votes){
					 sb.append("{\"name\":\"") ;
					 sb.append(vote.getVoter().getUsername());
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
			//上会前判断是否上会
			@RequestMapping(value = "/addPreVote.acl")
			public String addPreVote(HttpServletRequest request, HttpServletResponse response) throws IOException  {
				try {
					Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
					ProjPreVote vote = new ProjPreVote();
					//根据当前用户以及项目和
					String projid = model.get("projId");
					String docId = model.get("docid");
					ProjInfo proj = (ProjInfo)this.tableService.findEntityByID(ProjInfo.class, projid);
					//根据当前的docid和用户
					String  voteHql = "from ProjPreVote pcv where pcv.projId = ? and voter = ? and docId =? ";
					List<ProjPreVote> votes = this.tableService.findResultsByHSQL(voteHql, proj,SecurityUtil.getPrincipal(),docId);
					if(votes != null && votes.size() > 0 ){
						vote = votes.get(0);
					}
					vote.setProjId(proj);
					vote.setDocId(docId);
					vote.setVoter(SecurityUtil.getPrincipal());
					vote.setPrevoteView(model.get("prevoteview"));
					vote.setPreisView(model.get("preisagree"));
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
					logger.info(SecurityUtil.getPrincipal().getRealname()+"投票意见"+vote.getPreisView()+"====="+vote.getPrevoteView());
				} catch (Exception e) {
					 this.output(response, "{\"msg\":\"投票失败\"}");
				}
				return null;
			}
			// #################
			@RequestMapping(value = "/getPreVotesByUser.acl")
			public String getPreVotesByUser(HttpServletRequest request,
					HttpServletResponse response) throws Exception {
				 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
				 String projid = model.get("projId");
				 String docId = model.get("docid");
				 ProjInfo proj = (ProjInfo)this.tableService.findEntityByID(ProjInfo.class, projid);
				 String  voteHql = "from ProjPreVote pcv where pcv.projId = ? and voter = ? and docId =? ";
				 List<ProjPreVote> votes = this.tableService.findResultsByHSQL(voteHql, proj,SecurityUtil.getPrincipal(),docId);
				 StringBuffer sb = new StringBuffer();
				 sb.append("{\"results\":[");
				 int i = 1;
				 int j = votes.size();
				 for(ProjPreVote vote : votes){
					 sb.append("{\"name\":\"") ;
					 sb.append(vote.getVoter().getUsername());
					 sb.append("\",\"view\":\"");
					 sb.append(vote.getPrevoteView());
					 sb.append("\",\"isview\":\"");
					 sb.append(vote.getPreisView());
					 sb.append("\"}");
					 if(i != j){
						 sb.append(",") ;
					 }
					 i++;
				 }
				 sb.append("]}");
				 this.output(response, sb.toString());
				 System.out.println(sb.toString());
				return null;
			}
	
}
