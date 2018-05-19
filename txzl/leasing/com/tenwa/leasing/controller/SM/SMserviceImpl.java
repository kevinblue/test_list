package com.tenwa.leasing.controller.SM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.tenwa.business.service.TableService;
import com.tenwa.leasing.entity.SM.MachineMainData;
import com.tenwa.leasing.entity.SM.PickUpMainData;
import com.tenwa.leasing.entity.SM.ProjMainData;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.utils.BSDataBaseManager;

@Controller(value = "SMserviceImplController")
public class SMserviceImpl {
	@Resource(name = "tableService")
	private TableService tableService;
	
	public String updateMachineMainData() throws Exception {
		String sqlstr = " select ci.wind_machine,nvl(tpm.id,'0') tpm_id from ";
				sqlstr +=  "contract_info ci left join T_MACHINE_MAIN_DATA tpm ";
						sqlstr +=   "on ci.wind_machine=tpm.WIND_FIELD_NUM where ci.wind_machine is not null ";
		List<Map<String,Object>> contractidlist = new ArrayList<Map<String,Object>>() ;//用来存放风机号不为空的list
		try{
		contractidlist = this.tableService.queryListBySql(sqlstr,null);
		} catch (Exception e) {
		e.printStackTrace();
		}
		List<MachineMainData> listData = new ArrayList<MachineMainData>();
		//连接数据库
		BSDataBaseManager  dbm=BSDataBaseManager.getInstance();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
        	conn = dbm.getConnection();
        	for (Map<String, Object> restlt : contractidlist) {
        		String selectSql="select * from gwsmprod.turbineinfor where 风场编号 ='"+restlt.get("wind_machine")+"'";
        		ps=conn.prepareStatement(selectSql);
        		ResultSet rs = ps.executeQuery();
    			while(rs.next()){
    				String id=restlt.get("tpm_id").toString();
    				MachineMainData mmd=new MachineMainData();		
    				if(!"0".equals(id)){
    					 mmd=this.tableService.findEntityByID(MachineMainData.class, id);
    				}
    				mmd.setUnitNum(rs.getString("机组编号"));
    				mmd.setWindFieldNum(rs.getString("风场编号"));
    				mmd.setWindFieldName(rs.getString("风场名称"));
    				mmd.setArea(rs.getString("片区"));
    				mmd.setProjDept(rs.getString("项目部"));
    				mmd.setProjManager(rs.getString("项目经理"));
    				mmd.setMachineStatus(rs.getString("机组状态"));
    				mmd.setMachineNum(rs.getString("运行机位号"));
    				mmd.setMachineNumSCA(rs.getString("SCADA风机编号"));
    				mmd.setFieldNumSCA(rs.getString("SCADA风场编号"));
    				mmd.setUnitCapacity(rs.getString("机组容量"));
    				mmd.setModel(rs.getString("机型"));
    				mmd.setWheelHeight(rs.getString("轮毂高度"));
    				mmd.setAddressOfIP(rs.getString("IP地址"));
    				mmd.setSubnetMask(rs.getString("子网掩码"));
    				mmd.setExcavationTime(rs.getString("基础开挖时间"));
    				mmd.setCastingTime(rs.getString("基础浇筑时间"));
    				mmd.setBoxInstallationTime(rs.getString("箱变安装时间"));
    				mmd.setPickUpStartTime(rs.getString("接货开始时间"));
    				mmd.setPickUpEndTime(rs.getString("接货结束时间"));
    				mmd.setLiftingStartTime(rs.getString("吊装开始时间"));
    				mmd.setLiftingEndTime(rs.getString("吊装结束时间"));
    				mmd.setPowerOnTime(rs.getString("上电时间"));
    				mmd.setStaticStartTime(rs.getString("静调开始时间"));
    				mmd.setStaticEndTime(rs.getString("静调结束时间"));
    				mmd.setMoveStartTime(rs.getString("动调开始时间"));
    				mmd.setMoveEndTime(rs.getString("动调结束时间"));
    				mmd.setCommissioningStime(rs.getString("试运行开始时间"));
    				mmd.setCommissioningEtime(rs.getString("试运行结束时间"));
    				mmd.setPreacceptanceTime(rs.getString("预验收通过时间"));
    				mmd.setTurnUnderWarranty(rs.getString("在建转质保时间"));
    				mmd.setMainProgramVnum(rs.getString("主控程序版本号"));
    				mmd.setTransformerVnum(rs.getString("变流版本号"));
    				mmd.setIsAnalyzedVnum(rs.getString("变桨版本号"));
    				mmd.setInitFileVnum(rs.getString("初始化文件版本号"));
    				mmd.setPropellerType(rs.getString("变桨类型"));
    				mmd.setConverterType(rs.getString("变流类型"));
    				mmd.setCold(rs.getString("冷却"));
    				mmd.setLeafBlade(rs.getString("叶片"));
    				mmd.setBusType(rs.getString("总线类型"));
    				mmd.setTowerDrum(rs.getString("塔筒"));
    				mmd.setBasicType(rs.getString("基础形式"));
    				mmd.setTowerConnection(rs.getString("塔架连接形式"));
    				mmd.setTowerDrawing(rs.getString("塔架图号"));
    				mmd.setExpectWarrantyTime(rs.getString("预计出质保时间"));
    				mmd.setFinHandEndTime(rs.getString("最终交接结束时间"));
    				mmd.setHoistingStime(rs.getString("吊装过程验收开始时间"));
    				mmd.setHoistingEtime(rs.getString("吊装过程验收结束时间"));
    				mmd.setTorqueStime(rs.getString("力矩验收开始时间"));
    				mmd.setTorqueEtime(rs.getString("力矩验收结束时间"));
    				mmd.setWiringStime(rs.getString("接线开始时间"));
    				mmd.setWiringEtime(rs.getString("接线结束时间"));
    				mmd.setAllacceptanceStime(rs.getString("整体验收开始时间"));
    				mmd.setAllacceptanceEtime(rs.getString("整体验收结束时间"));
    				mmd.setInacceptanceStime(rs.getString("内部验收开始时间"));
    				mmd.setInacceptanceEtime(rs.getString("内部验收结束时间"));
    				mmd.setWillacceptanceStime(rs.getString("预验收开始时间"));
    				mmd.setLasthandoverStime(rs.getString("最终交接开始时间"));
    				mmd.setLastacceptanceStime(rs.getString("最终交接验收开始时间"));
    				mmd.setLastacceptanceEtime(rs.getString("最终交接验收结束时间"));
    				mmd.setLastWarrantyTime(rs.getString("最终出质保时间"));
    				mmd.setSystCreateTime(rs.getString("系统创建时间"));
    				listData.add(mmd);
					}
    			rs.close();
			}
        	this.tableService.saveOrUpdateAllEntities(listData);
        	
        	if(conn==null){
        	}
			conn.setAutoCommit(false);
			conn.commit();
			return "成功";
		} catch (SQLException e) {//回滚事物
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
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
	
	
	
	
	public String updateProjMainData() throws Exception {
		List<ContractInfo> list =null;
		String sqlstr = "select ci.wind_machine,nvl(tpm.id,'0') tpm_id from contract_info ci left join T_PROJ_MAIN_DATA tpm on ci.wind_machine=tpm.项目编号 ";
		sqlstr +="where ci.wind_machine is not null";
		
		List<Map<String,Object>> contractidlist = new ArrayList<Map<String,Object>>() ;//用来存放风机号不为空的list
		try {
			
			contractidlist = this.tableService.queryListBySql(sqlstr,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		List<ProjMainData> listSaveData = new ArrayList<ProjMainData>();
		//连接数据库
		BSDataBaseManager  dbm=BSDataBaseManager.getInstance();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
        	conn = dbm.getConnection();
        	for (Map<String, Object> restlt : contractidlist) {
        		String selectSql="select * from gwsmprod.project_last_view where 项目编号 ='"+restlt.get("wind_machine")+"'";
        		ps=conn.prepareStatement(selectSql);
        		ResultSet rs = ps.executeQuery();
    			while(rs.next()){
    				String id=restlt.get("tpm_id").toString();
    				ProjMainData pmd= new ProjMainData();
    				if(!"0".equals(id)){
    					pmd=this.tableService.findEntityByID(ProjMainData.class, id);
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
			        listSaveData.add(pmd);
					}
    			this.tableService.saveOrUpdateAllEntities(listSaveData);
    			rs.close();
			}
        	
        	if(conn==null){
        	}
			conn.setAutoCommit(false);
			conn.commit();
			return "成功";
		} catch (SQLException e) {//回滚事物
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
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
	
	
	public String updatePickupMainData() throws Exception {
		List<ContractInfo> list =null;
		String sqlstr = "select ci.wind_machine,nvl(tpm.id_,'0') tpm_id from contract_info ci left join T_PICKUP_MAIN_DATA tpm on ci.wind_machine=tpm.风电场编号 ";
		sqlstr +="where ci.wind_machine is not null";
		List<Map<String,Object>> contractidlist = new ArrayList<Map<String,Object>>() ;//用来存放风机号不为空的list
		try {
			contractidlist = this.tableService.queryListBySql(sqlstr,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<PickUpMainData> listData = new ArrayList<PickUpMainData>();
		//连接数据库
		BSDataBaseManager  dbm=BSDataBaseManager.getInstance();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
        	conn = dbm.getConnection();
        	for (Map<String, Object> restlt : contractidlist) {
        		String selectSql="select * from gwsmprod.goodreceive_view_Receiving where 风电场编号 ='"+restlt.get("wind_machine")+"'";
        		ps=conn.prepareStatement(selectSql);
        		ResultSet rs = ps.executeQuery();
    			while(rs.next()){
    				String id=restlt.get("tpm_id").toString();
    				PickUpMainData pmd= new PickUpMainData();
    				if(!"0".equals(id)){
    					pmd=this.tableService.findEntityByID(PickUpMainData.class, id);
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
    					listData.add(pmd);
					}
    			this.tableService.saveOrUpdateAllEntities(listData);
    			rs.close();
			}
        	if(conn==null){
        	}
			conn.setAutoCommit(false);
			conn.commit();
			return "成功";
		} catch (SQLException e) {//回滚事物
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
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
