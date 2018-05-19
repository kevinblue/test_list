<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input style="display:none;"	class="mini-textarea" id="id_json_stock_patrol_report_str" name="json_stock_patrol_report_str" value='${empty json_stock_patrol_report_str ? "[]" : json_stock_patrol_report_str }'></input>
<script>
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask==true){showTools = false;}
	tenwa.createTable({
		id: "stock_patrol_report",
		renderTo: "id_table_stock_patrol_report",
		width: '100%',
		height: 300,
		lazyLoad: false,
		title: "",
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		showToolbar: showTools,
		tools: [
		       {
					html : '生成客户巡视报告',
					plain : true,
					iconCls : 'icon-ok',
					handler : function(miniTable, buttonText) 
					{	
						var params={},templateno ="";
						params["flowunid"] = flowUnid;
						getPageData(params);
						/*处理生成文档必须的字段数据*/
						params["condition.realname"] = "${sessionScope['login_realname']}"; 
						params["condition.custname"] = "${requestScope['contract_info.custname'] }"; 
						
						/*巡视内容 */
						var tempfactdate= mini.getbyName("contract_patrol_info.factpatroldate").getValue(); 
						if(tempfactdate != ""){
							params["condition.factpatroldate"] = tempfactdate.format("yyyy-MM-dd");
						}
						/* var temppatroldate = mini.getbyName("contract_patrol_info.patroldate").getValue();
						if(temppatroldate != ""){
							params["condition.patroldate"] = temppatroldate.format("yyyy-MM-dd");
						} */
						
						params["condition.position"] 
						= mini.getbyName("contract_patrol_info.position").getValue(); 
						
						/*处理 判断是否是医疗类型*/
						var industrytype = "${requestScope['contract_info.industrytype']}"; 
						if(industrytype=="medical")
						{
							templateno = "F-201604013";
							//**********整体经营信息**1 医疗******************
							var medical_operate_info_form=new mini.Form("medical_operate_info_list").getData();
							var invest_data = medical_operate_info_form.contract_invest_info;
							for(var key in invest_data){
								if(invest_data[key] == "") continue;
								params["medical."+key] = invest_data[key];
							}
							
							 
						} else {
							templateno = "F-201604012";
							//**********整体经营信息**2 非 医疗******************
							var nomedical_operate_info_form = new mini.Form("operate_info_list").getData();
							var invest_data = nomedical_operate_info_form.contract_invest_info;
							for (var key in invest_data) {
								if(invest_data[key] == "") continue;
								params["nomedical."+key] = invest_data[key];
							}
							
						}	
						
						/*租赁设备情况*/
						var json_eq = mini.get("leasing_info_list").getData();
						params["json_eq"] = mini.encode(json_eq);
						
						/*重大事项投资*/
						params["json_zdtz"] = mini.encode( mini.get("invest_info_list").getData() );
						params["zdtz.resfx"] = mini.getbyName("invest_info_list.resfx").getValue();
						
						/*融资负债情况*/
						params["proj_json"] = mini.encode( mini.get("proj_kehu").getData() );
						params["json_dk"] = mini.encode( mini.get("loan_task").getData() );
						params["json_rzqd"] = mini.encode( mini.get("channel_task").getData() );
						params["json_mainfz"] = mini.encode( mini.get("channel_type_task").getData() );
						params["json_usefz"] = mini.encode( mini.get("channel_type_other_task").getData() );
						params["rd.resfx"] = mini.getbyName("debt_info_list.resfx").getValue();
						/*担保人信息*/						
						params["dbr.jytzmemo"] = mini.getbyName("contract_patrol_info.jytzmemo").getValue();
						params["dbr.debtinfo"] = mini.getbyName("contract_patrol_info.debtinfo").getValue();
						params["dbr.resinfo"] = mini.getbyName("contract_patrol_info.resinfo").getValue();
						
						/*风控信息*/
						var risk_info_list_form = new mini.Form("risk_info_list").getData();
						var invest_data = risk_info_list_form.risk_info;
						for (var key in invest_data) {
							if(invest_data[key] == "") continue;
							params["fk."+key] = invest_data[key];
						}
						
						/*其他*/
						params["other.otherinfo"] = mini.getbyName("contract_patrol_info.otherinfo").getValue();
						
						/*本次巡视结论*/
						params["cur_res.resadvice"] = mini.getbyName("contract_patrol_info.resadvice").getValue();
						params["cur_res.qassettype"] = mini.getbyName("contract_patrol_info.qassettype").getValue();
						params["cur_res.curassettype"] = mini.getbyName("rawValue_contract_patrol_info.curassettype").getValue();
						params["cur_res.assetmng"] = mini.getbyName("contract_patrol_info.assetmng").getValue();
						params["cur_res.assetyj"] = mini.getbyName("contract_patrol_info.assetyj").getValue();
						
						var fileTeplate=new fileTemplateConfig({
							templateno : templateno,
							tableid : 'stock_patrol_report',
							modelname : '客户巡视报告',
							returntype : 'listtocurpage',
							parames : params
						});
						fileTeplate.createFile();
						saveCallBack();
					}
			  }
			, '-',
			'remove'
		],
		removeOperCallBack: function(miniTable){},
		data: JsonUtil.decode('${empty json_stock_patrol_report_str ? "[]" : json_stock_patrol_report_str }'),
		columns: [
			{type: 'indexcolumn',width:9},
			{type: 'checkcolumn',width:9},
			{field: 'id', header: 'id', visible: false},
			{field: 'filename', header: '文件名称'},
			{field: 'create', header: '操作',renderer:function(e){ var cfalg=showTools;
			var temp=e;
			return showOperator(temp,cfalg);}}
		]
	});
});

function  showOperator(temp,cfalg){
	var id = temp.record.id;   
	var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
	var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
	var base3 = "<a href='javascript:void(0);' onclick='editoverdue(\"" + id + "\")'>编辑</a>";
	var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
	var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
	var tempright="2"
		if(cfalg){
		    return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3;
		}else{
			return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3;
		}
}

function  readCreateWord(id){
	var currentPageClientWidth  =  document.documentElement.clientWidth;
 	var currentPageClientHeight =  document.documentElement.clientHeight;
 	openFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:"1"});
 	
}
function  printCreateWord(id){
	var currentPageClientWidth  =  document.documentElement.clientWidth;
 	var currentPageClientHeight =  document.documentElement.clientHeight;
 	openFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:"3"});
 	
}
function editoverdue(id){
	var currentPageClientWidth  =  document.documentElement.clientWidth;
	var currentPageClientHeight =  document.documentElement.clientHeight;
	openFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:"2"});
}
function downloadFile(Id){
	attachmentDown({returnType:'file',url:"${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="+Id});
} 
function getPageData(params) {

}
</script>
<div id = "id_table_stock_patrol_report"></div>