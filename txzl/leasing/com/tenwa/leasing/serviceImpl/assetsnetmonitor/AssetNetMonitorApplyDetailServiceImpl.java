package com.tenwa.leasing.serviceImpl.assetsnetmonitor;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.leasing.entity.assetnetworkmonitor.AssetNetMonitorApply;
import com.tenwa.leasing.entity.assetnetworkmonitor.AssetNetMonitorApplyDetail;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.service.assetsnetmonitor.AssetNetMonitorApplyDetailService;

/**
 * 资产网络监控申请详细
 * @Title: AssetNetMonitorApplyDetailServiceImpl.java
 * @package: com.tenwa.leasing.serviceImpl.assetsnetmonitor
 * @author: tpf
 * @date 2016年04月07日 上午11:19:21 
 * @version V5.1
 */
@Service(value="assetNetMonitorApplyDetailService")
public class AssetNetMonitorApplyDetailServiceImpl extends AbstractBaseServiceImpl implements
		AssetNetMonitorApplyDetailService {
	
	@Resource
    private BaseDao baseDao;
	@Override
	public BaseDao getBussinessDao() throws Exception {
		return baseDao;
	}
	/**
	  * 修改资产网络监控申请详细
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void updateAssetsNetMonitorApplyDetail(Map<String, String> model)
			throws Exception {
		AssetNetMonitorApplyDetail anmad=new AssetNetMonitorApplyDetail();
		if(model.get("id")==null){
			AssetNetMonitorApply apply=this.baseDao.findEntityByID(AssetNetMonitorApply.class, model.get("applyid"));
			anmad.setApplyid(apply);
			CustInfo cust=this.baseDao.findEntityByID(CustInfo.class, model.get("custinfo"));
			anmad.setCustInfo(cust);
			anmad.setCustname(model.get("custinfoname"));
			anmad.setBaidu(model.get("baidu"));
			anmad.setJudgmentnet(model.get("judgmentnet"));
			anmad.setExecutionnet(model.get("executionnet"));
			anmad.setNegativecontrol(model.get("negativecontrol"));
		}else{			
			anmad = this.baseDao.findEntityByID(AssetNetMonitorApplyDetail.class,model.get("id"));
			anmad.setBaidu(model.get("baidu"));
			anmad.setJudgmentnet(model.get("judgmentnet"));
			anmad.setExecutionnet(model.get("executionnet"));
			anmad.setNegativecontrol(model.get("negativecontrol"));
		}
		/*if("".equals(model.get("id"))||null==model.get("id")){
			this.baseDao.copyAndOverrideExistedValueFromStringMap(model, anmad,null);
		}else{			
			anmad=this.baseDao.findEntityByID(AssetNetMonitorApplyDetail.class, model.get("id"));
			this.baseDao.copyAndOverrideExistedValueFromStringMap(model, anmad,null);
			
		}*/
		this.baseDao.saveOrUpdateEntity(anmad);
		//this.baseDao.saveOrUpdateEntity(anmad);
		
	}

	
	
}
