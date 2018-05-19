package com.tenwa.report.service;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.lang3.tuple.MutablePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;

import com.tenwa.business.service.BaseService;
import com.tenwa.report.entity.Report;
import com.tenwa.report.entity.ReportDataSource;
import com.tenwa.report.entity.TenwaReport;

public interface ReportService extends BaseService {

	public JSONObject getReportTreeAsJson(Report parentReport, Boolean isRoot, Boolean showHidden) throws Exception;

	public JSONArray getReportContentTreeAsJson(String reportId) throws Exception;

	public JSONArray getTableFiltersAsTreeJson(String id) throws Exception;
	
	public JSONArray getTableControlsAsTreeJson(String id) throws Exception;

	public String validateSQL(String datasourceId, String sql, String queryType, List<MutablePair<String, String>> params) throws Exception;

	public JSONArray getTableColumnsAsTreeJson(String tableId, String datasource, String sql, String queryType, List<MutablePair<String, String>> params);

	public void removeReport(String reportId) throws DataAccessException, Exception;

	public String saveTable(Map<String, String> model);

	public String updateAndQueryReportTree(String parent) throws Exception;

	public TenwaReport exportAll(String startId) throws Exception;

	public void updateLayout(String reportId, String layoutId, String contentId, String contentType, String action) throws Exception;

	public Report saveReport(Report report, String parentMenuId) throws Exception;

	// 导入报表
	public void uploadReport(TenwaReport report, String rootMenuId, String parentId, String timeStamp) throws Exception;

	public void updatePosition(String entityClass, String id, String pid, String parentField, Integer position) throws Exception;

	public Queue<String> getUploadMessage(String timeStamp);

	public void removeUploadMessage(String timeStamp);

	public String saveChart(Map<String, String> model) throws Exception;

	public JSONArray getChartColumnsAsTreeJson(String id, String datasource, String sql, String queryType, List<MutablePair<String, String>> paramValues);

	public JSONArray getChartFiltersAsTreeJson(String id) throws Exception;

	public String savePage(Map<String, String> model) throws JSONException;
    
	public List<ReportDataSource> getReportDataSourceByPage(ReportDataSource reportDataSource,int pageIndex,int pageSize,String sortField,String sortOrder ) throws Exception;

	public String getDataSourceAsTableJson() throws Exception;
	
	public String getMatchTableAsTableJson() throws Exception;
	
	public void runReportLazy() throws Exception;
	
	public String getUserTree() throws Exception;
	
	public void runOwnershipTransfersZZ() throws Exception;
	public void saveGlobal(String reportdate) throws Exception;
	public void saveGlobalFan(String reportdate) throws Exception;
	public void saveGlobalContract() throws Exception;
	public void runOnhireRemind() throws Exception;
	public void runLeaseRegister() throws Exception;
	public void runRentNotice() throws Exception;
	public void runOwnerShipTransfer() throws Exception;
	public void runPledgeAskingRemind() throws Exception;	
	public void runReceiveDate() throws Exception;
	public void runProjFinish() throws Exception;	
	public void runLandCertificateRemind() throws Exception;	
	public void runPledgeRegisterRemind() throws Exception;	
	public void runRelationCustProjRemind() throws Exception;
	public String updateMachineMainData() throws Exception;
	public String updateProjMainData() throws Exception ;
	public String updatePickupMainData() throws Exception;
	public void runMortgageContractRemind() throws Exception;
	public void runJieXiRemind() throws Exception;
	public void runLoanInvoicedRemind() throws Exception;
	public void runRentPatrolRemind() throws Exception;
	public void runCollectionDateRemind() throws Exception;
	public void runRegistrationChangeRemind() throws Exception;

	public void runZuhouXunShiRemind() throws Exception;
	public void runTouBao() throws Exception;
	public void runBaoXiDaoQi() throws Exception;
	public void runZuQianXiGaiZhang() throws Exception;
	
	public void runBaoHanDaoQi() throws Exception;
	public void runMianQian() throws Exception;
	public void runBuLiangZiChan() throws Exception;
	public void runBeiYong2() throws Exception;
	public void runBeiYong3() throws Exception;
	public void runBeiYong4() throws Exception;
	public void runBeiYong5() throws Exception;
	public void runBeiYong6() throws Exception;
	public void runBeiYong7() throws Exception;
	public void runBeiYong8() throws Exception;
	public void runBeiYong9() throws Exception;
	public void runBeiYong10() throws Exception;
	public void runBeiYong11() throws Exception;
	public void runBeiYong12() throws Exception;
	public void runBeiYong13() throws Exception;
	public void runBeiYong14() throws Exception;
	public void runBeiYong15() throws Exception;
	
}
