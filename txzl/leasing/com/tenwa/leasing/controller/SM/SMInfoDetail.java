package com.tenwa.leasing.controller.SM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.service.TableService;
import com.tenwa.leasing.entity.SM.MachineMainData;
import com.tenwa.leasing.entity.SM.PickUpMainData;
import com.tenwa.leasing.entity.SM.ProjMainData;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.utils.BSDataBaseManager;

@Controller(value = "SMInfoDetailController")
@RequestMapping(value = "/**/acl")
public class SMInfoDetail {
	@Resource(name = "tableService")
	private TableService tableService;
	//BS接口主数据用户同步
	@RequestMapping(value = "/updateProjMainData.acl")
	@ResponseBody
	public String updateProjMainData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqlstr = "select  distinct ci.wind_machine,nvl(tpm.id,'0') tpm_id "
				+ "from contract_info ci "
				+ "left join T_PROJ_MAIN_DATA tpm"
				+ " on ci.wind_machine=tpm.项目编号 "
		        +"where ci.wind_machine is not null ";
		List<Map<String,Object>> contractidlist = new ArrayList<Map<String,Object>>() ;//用来存放风机号不为空的list
		try {
			contractidlist = this.tableService.queryListBySql(sqlstr,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<ProjMainData> listSaveData = new ArrayList<ProjMainData>();
		List<ProjMainData> listupdateData = new ArrayList<ProjMainData>();
	    BSDataBaseManager dbm = BSDataBaseManager.getInstance();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
        	conn = dbm.getConnection();
        	if(conn==null){
        		return "失败！";
        	}
        	conn.setAutoCommit(false);//将自动提交设置为手动提交
        	boolean flag=false;
        if(contractidlist.size()>0){
        	for (Map<String, Object> restlt : contractidlist) {
        		String selectSql="select * from gwsmprod.project_last_view where instr('"+restlt.get("wind_machine")+"',项目编号)>0";
        		ps=conn.prepareStatement(selectSql);
        		ResultSet rs = ps.executeQuery();
    			while(rs.next()){
    				String id=restlt.get("tpm_id").toString();
    				ProjMainData pmd= new ProjMainData();
    				if(!"0".equals(id)){//update
    					pmd=this.tableService.findEntityByID(ProjMainData.class, id);
    					flag=true;
    				}
   				    pmd.setProjNum( rs.getString("项目编号"));
					pmd.setProjStatus(rs.getString("项目状态"));
					pmd.setProjStage(rs.getString("项目阶段"));
					pmd.setProjCapacity(rs.getString("项目容量"));
					pmd.setSetsNum(rs.getString("机组数量"));
					pmd.setLatitudeType(rs.getString("维度类型"));
					pmd.setLatitude(rs.getString("纬度"));
					pmd.setLongitudeType(rs.getString("经度类型"));
					pmd.setLongitude(rs.getString("经度"));
					pmd.setDistrict(rs.getString("片区"));
					pmd.setProjName(rs.getString("项目名称"));
					pmd.setProjManager(rs.getString("项目经理姓名"));
					pmd.setOwnerUnit(rs.getString("业主单位"));
					pmd.setContractSubject(rs.getString("合同主体"));
					pmd.setContractSigningDate(rs.getString("合同签订日期"));
					pmd.setWarrantyStarttime(rs.getString("质保期开始时间"));
					pmd.setWarrantyDuration(rs.getString("质保期时长"));
					pmd.setWindFieldAvailability(rs.getString("风场可用率"));
					pmd.setUnitAvailability(rs.getString("机组可用率"));
					pmd.setSinglePowerCurve(rs.getString("单台功率曲线"));
					pmd.setWindPowerCurve(rs.getString("风场功率曲线"));
					pmd.setPowerCurveDescription(rs.getString("功率曲线说明"));
					 if(flag){//update
					 	listupdateData.add(pmd);
					 }else{//save
						  listSaveData.add(pmd);
					 }
					}
			    }
        	 }
			if(listSaveData.size()>0){
				this.tableService.saveAllEntities(listSaveData);
			}
	       if(listupdateData.size()>0){
    		  	this.tableService.updateAllEntities(listupdateData);
			}
	        conn.commit();
			return "成功";
		} catch (SQLException e) {//回滚事物
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return "失败";
			}
			e.printStackTrace();
			return "失败";
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					conn = null;
				}
			}

		}	 	
	}
		//BS接货主数据用户同步
				@RequestMapping(value = "/updatePickupMainData.acl")
				@ResponseBody
				public String updatePickupMainData(HttpServletRequest request, HttpServletResponse response) throws Exception {
					String sqlstr = "select distinct ci.wind_machine,nvl(tpm.id_,'0') tpm_id_,nvl(tpm.id,'0') tpm_idstring"
							+ " from contract_info ci left join T_PICKUP_MAIN_DATA tpm on ci.wind_machine=tpm.风电场编号 "
							+ "where ci.wind_machine is not null";
					List<Map<String,Object>> contractidlist = new ArrayList<Map<String,Object>>() ;//用来存放风机号不为空的list
					try {
						contractidlist = this.tableService.queryListBySql(sqlstr,null);
					} catch (Exception e) {
						e.printStackTrace();
					}
					List<PickUpMainData> listsaveData = new ArrayList<PickUpMainData>();
					List<PickUpMainData> listupdateData = new ArrayList<PickUpMainData>();
					//连接数据库
					BSDataBaseManager  dbm=BSDataBaseManager.getInstance();
					Connection conn = null;
					PreparedStatement ps = null;
					boolean flag=false;
					try {
			        	conn = dbm.getConnection();
			           	if(conn==null){
			        		return "失败！";
			        	}
			        	conn.setAutoCommit(false);
			        	if(contractidlist.size()>0){
			        	for (Map<String, Object> restlt : contractidlist) {
			        		String selectSql="select * from gwsmprod.goodreceive_view_Receiving where 风电场编号 ='"+restlt.get("wind_machine")+"'";
			        		ps=conn.prepareStatement(selectSql);
			        		String id=restlt.get("tpm_id_").toString();
			        		String id_string=restlt.get("tpm_idstring").toString();
			        		ResultSet rs = ps.executeQuery();
			    			while(rs.next()){
			    				PickUpMainData pmd= new PickUpMainData();
			    				String NID=rs.getString("ID");
			    				if(!"0".equals(id)){
			    					if(NID!=null&&NID.equals(id_string)){
			        					 pmd=this.tableService.findEntityByID(PickUpMainData.class, id);
			        						System.out.println(NID+"========"+id_string);
			          					 flag=true; 
			        				}else{
			        					continue;
			        				}
			    				}else{
			    					flag=false; 
			    				}
			    					pmd.setId(rs.getString("ID"));
			    					pmd.setSysmodtime(rs.getString("SYSMODTIME"));
			    					pmd.setReservation(rs.getString("存放机位"));
			    					pmd.setLocation(rs.getString("存放位置"));
			    					pmd.setMaterialCode(rs.getString("物料编码"));
			    					pmd.setMaterialName(rs.getString("物料名称"));
			    					pmd.setBatchNum(rs.getString("批次号"));
			    					pmd.setS_id(rs.getString("序列号"));
			    					pmd.setAmount(rs.getString("数量"));
			    					pmd.setUnit(rs.getString("单位"));
			    					pmd.setReceivingNum(rs.getString("接货工单号"));
			    					pmd.setArea(rs.getString("片区"));
			    					pmd.setWindFieldNum(rs.getString("风电场编号"));
			    					pmd.setPickUpStartTime(rs.getString("接货开始时间"));
			    					pmd.setPickUpEndTime(rs.getString("接货结束时间"));
			    					if(flag){//update
			     					 	listupdateData.add(pmd);
			     					 }else{//save
			     						listsaveData.add(pmd);
			     					 }
			        				
			    					}
			    			  }
			            	}
			        		if(listsaveData.size()>0){
			    				this.tableService.saveAllEntities(listsaveData);
			    			}
			    	       if(listupdateData.size()>0){
			        		  	this.tableService.updateAllEntities(listupdateData);
			    			}
			            	conn.commit();
			    			return "成功";
					} catch (SQLException e) {//回滚事物
						try {
							conn.rollback();
						} catch (SQLException e1) {
							e1.printStackTrace();
							return "失败";
						}
						e.printStackTrace();
						return "失败";
					} finally {
						if (conn != null) {
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							} finally {
								conn = null;
							}
						}
					}	 	
				}
   }

