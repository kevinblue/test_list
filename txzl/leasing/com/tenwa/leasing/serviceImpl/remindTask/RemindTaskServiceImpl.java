package com.tenwa.leasing.serviceImpl.remindTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.entity.RemindTask;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.service.remindTask.RemindTaskService;
@Service(value = "remindTaskService")
public class RemindTaskServiceImpl implements RemindTaskService {
	@Resource
	private TableService tableService;
	
	@Override
	public void saveOnhireRemindTask() {
		Map<String, String> model = new HashMap<String, String>();
		List<Map<String, Object>> onhireRemindList = null;
		String sql ="";
		try {
			sql = this.tableService.getTableXMLJsonData("quartz/queryContractOnhireRemind.xml", model);
			System.out.println(">>>当前起租任务提醒:" + sql);
			onhireRemindList = this.tableService.queryListBySql(sql);
			String nowDate = DateUtil.getSystemDateTime();
			User user = (User) SecurityUtil.getPrincipal();
			List<RemindTask> taskList = new ArrayList<RemindTask>();
			RemindTask task = new RemindTask();
			for (Map<String, Object> map : onhireRemindList) {
				this.tableService.copyAndOverrideExistedValueFromMap(map, task);
				task.setCreateDate(nowDate);
				task.setCreator(user);
				taskList.add(task);
			}
			this.tableService.saveOrUpdateAllEntities(taskList);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void saveFundOffRemindTask() {
		Map<String, String> model = new HashMap<String, String>();
		String sql = "";
		List<Map<String, Object>> onhireRemindList = null;
		try {
			sql = this.tableService.getTableXMLJsonData("quartz/queryFundOffsetRemind.xml", model);
			System.out.println(">>>当前保证金抵扣任务提醒:" + sql);
			onhireRemindList = this.tableService.queryListBySql(sql);
			String nowDate = DateUtil.getSystemDateTime();
			User user = (User) SecurityUtil.getPrincipal();
			List<RemindTask> taskList = new ArrayList<RemindTask>();
			RemindTask task = new RemindTask();
			for (Map<String, Object> map : onhireRemindList) {
				this.tableService.copyAndOverrideExistedValueFromMap(map, task);
				task.setCreateDate(nowDate);
				task.setCreator(user);
				taskList.add(task);
			}
			this.tableService.saveOrUpdateAllEntities(taskList);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void addRemindTast(Map<String, String> model)
			throws Exception {
		RemindTask remintaskinfo=new RemindTask();
		remintaskinfo.setDeploypropPdid(model.get("deployproppdid"));
		remintaskinfo.setWorkflowParams(model.get("workflowparams"));
		remintaskinfo.setWorkflowName(model.get("workflowname"));
		remintaskinfo.setcId(model.get("cid"));
		remintaskinfo.setContractId(model.get("contractid"));
		remintaskinfo.setRemindUser(model.get("reminduser"));
		remintaskinfo.setProjectName(model.get("projectname"));
		remintaskinfo.setDocID(model.get("docid"));
		remintaskinfo.setStartDate(model.get("startdate"));
		String statu=model.get("statu");
		if(statu.equals("已发起")){
			remintaskinfo.setStatus("1");
		}else{
			remintaskinfo.setStatus("0");
		}
		remintaskinfo.setKeyOne(model.get("keyone"));
		remintaskinfo.setKeyTwo(model.get("keytwo"));
		remintaskinfo.setKeyThree(model.get("keythree"));
		this.tableService.saveEntity(remintaskinfo);
	}

	@Override
	public void updateRemindTast(Map<String, String> model) throws Exception {
		RemindTask remintaskinfo=this.tableService.findEntityByID(RemindTask.class,model.get("id"));
		remintaskinfo.setDeploypropPdid(model.get("deployproppdid"));
		remintaskinfo.setWorkflowParams(model.get("workflowparams"));
		remintaskinfo.setWorkflowName(model.get("workflowname"));
		remintaskinfo.setcId(model.get("cid"));
		remintaskinfo.setContractId(model.get("contractid"));
		remintaskinfo.setRemindUser(model.get("reminduser"));
		remintaskinfo.setProjectName(model.get("projectname"));
		remintaskinfo.setDocID(model.get("docid"));
		remintaskinfo.setStartDate(model.get("startdate"));
		String statu=model.get("statu");
		if(statu.equals("已发起")){
			remintaskinfo.setStatus("1");
		}else{
			remintaskinfo.setStatus("0");
		}
		remintaskinfo.setKeyOne(model.get("keyone"));
		remintaskinfo.setKeyTwo(model.get("keytwo"));
		remintaskinfo.setKeyThree(model.get("keythree"));
		this.tableService.updateEntity(remintaskinfo);
	}
}
