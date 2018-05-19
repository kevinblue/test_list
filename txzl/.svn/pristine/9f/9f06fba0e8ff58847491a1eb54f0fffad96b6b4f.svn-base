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
import com.tenwa.leasing.utils.BSDataBaseManager;

@Controller(value = "newSMController")
@RequestMapping(value = "/**/acl")
public class newSM {
	@Resource(name = "tableService")
	private TableService tableService;
	//BS机组主数据用户同步
	@RequestMapping(value = "/updateMachineMainData.acl")
	@ResponseBody
	public String updateMachineMainData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqlstr = " select distinct ci.wind_machine,nvl(tpm.id,'0') tpm_id,nvl(tpm.unit_num,'0') unitnum from "
				      +  "contract_info ci left join T_MACHINE_MAIN_DATA tpm "
			          +  "on ci.wind_machine=tpm.WIND_FIELD_NUM where ci.wind_machine is not null";
		List<Map<String,Object>> contractidlist = new ArrayList<Map<String,Object>>() ;//用来存放风机号不为空的list
		try{
		contractidlist = this.tableService.queryListBySql(sqlstr,null);
		}catch(Exception e){
			e.printStackTrace();
		}
		List<MachineMainData> listsaveData = new ArrayList<MachineMainData>();
		List<MachineMainData> listupdateData = new ArrayList<MachineMainData>();
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
        	conn.setAutoCommit(false);//将自动提交设置为手动提交
        	if(contractidlist.size()>0){
        	for (Map<String, Object> restlt : contractidlist) {
        		String selectSql="select * from gwsmprod.turbineinfor where 风场编号 ='"+restlt.get("wind_machine")+"'";
        		ps=conn.prepareStatement(selectSql);
        		String id=restlt.get("tpm_id").toString();
        		String unitnum=restlt.get("unitnum").toString();
        		ResultSet rs = ps.executeQuery();
    			while(rs.next()){
    				MachineMainData mmd=new MachineMainData();
    				String jznumber=rs.getString("机组编号");
    				if(!"0".equals(id)){
    					if(jznumber!=null&&jznumber.equals(unitnum)){
        					 mmd=this.tableService.findEntityByID(MachineMainData.class, id);
        						System.out.println(jznumber+"========"+unitnum);
          					 flag=true; 
        				}else{
        					continue;
        				}
    				}else{
    					flag=false; 
    				}
    				System.out.println(unitnum+"----------->");
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
    				 if(flag){//update
 					 	listupdateData.add(mmd);
 					 }else{//save
 						listsaveData.add(mmd);
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
			e.printStackTrace();
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
