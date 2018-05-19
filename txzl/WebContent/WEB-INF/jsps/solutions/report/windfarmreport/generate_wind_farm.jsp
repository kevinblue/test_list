<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tlds/c.tld"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
jQuery(function(){
	if(reportkeyword =="年度报告"){
		var showTools = true;
		if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
		//获取父页面中保存在hidden域的值
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id: "wind_farm_report",
				renderTo: "id_table_wind_farm_report",
				width: globalClientWidth - 20,
				height: 260,
				lazyLoad: false,
				isClickLoad:false,
				remoteOper : false,
				showPager: false,
				multiSelect: false,
				showToolbar: showTools,
				tools: ['remove','-',
				        {
							html : '风电场年度运行报告',
							plain : true,
							iconCls : 'icon-save',
							handler : function(miniTable, buttonText) {
									
								
								var params=getParams2();
							     var cdates=miniTable.getData();
							     for(var i=0;i<cdates.length;i++){
					                    if(cdates[i].filename=="风电场年度运行报告模板.docx"){
					                   	 mini.alert("风电场年度运行报告模板已生成,无需重复生成");
					                    }
								    }	
								var fileTeplate=new fileTemplateConfig({
									isAttachment : true,
									attachmentParams: {
										module:'WorkflowAttchmentFiles',
										jbpmWorkflowHistoryInfoId : window.currentJbpmWorkflowHistoryInfoId ? window.currentJbpmWorkflowHistoryInfoId : '',
										attachmentFileDictId : 'root.FileType.Type3.TEST01',
										identifierOne:window.assignAttachmentKeyOne||"${currentProcessInstanceDBID}",
										identifierTwo:window.assignAttachmentKeyTwo||jQuery("#id_currentHistoryTaskInfo_keyOne").val(),
										identifierThree:window.assignAttachmentKeyThree||jQuery("#id_currentHistoryTaskInfo_keyTwo").val(),
										identifierFour:window.assignAttachmentKeyFour||jQuery("#id_currentHistoryTaskInfo_keyThree").val(),
										identifierFive:window.assignAttachmentKeyFive||jQuery("#id_currentHistoryTaskInfo_keyFour").val(),
										identifierSix:window.assignAttachmentKeySix||jQuery("#id_currentHistoryTaskInfo_keyFive").val(),
										identifierSeven:window.assignAttachmentKeySeven||jQuery("#id_currentHistoryTaskInfo_keySix").val(),
										identifierEight:window.assignAttachmentKeyEight||jQuery("#id_currentHistoryTaskInfo_keySeven").val(),
										identifierNine:window.assignAttachmentKeyNine||jQuery("#id_currentHistoryTaskInfo_keyEight").val(),
										identifierTen:window.assignAttachmentKeyTen||jQuery("#id_currentHistoryTaskInfo_keyNine").val()
									},
									templateno : 'F-201703005',
									tableid : 'wind_farm_report',
									modelname : '天信风电项目年度运行报告模版',
									returntype : 'listtocurpage',
									parames : params
								});
								if(cdates.length==0){
								fileTeplate.createFile();
								if (mini.get("id_customworkflowattachment")) {
									mini.get("id_customworkflowattachment").reload();
									mini.get("id_workflowhisAttachment").reload();
								}
								}
								saveCallBack();
							}
				        },
				        ],
				data: JsonUtil.decode('${empty json_onhire_report_str ? "[]" : json_onhire_report_str }'),
				updateOperCallBack: function(miniTable,formData){
				},
				columns: [
					{type: 'indexcolumn'},
					{type: 'checkcolumn'},
					{field: 'id', header: 'id', visible: false},
					{field: 'filename', header: '文件名称'},
					{field: 'createdate', header: '文件生成时间'},
					{field: 'create', header: '操作',renderer:function(e){ var cfalg=showTools;
					var temp=e;
					return showOperator(temp,cfalg);}}
				]	
			});
		});
	}else{

		var showTools = true;
		if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
		//获取父页面中保存在hidden域的值
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id: "wind_farm_report",
				renderTo: "id_table_wind_farm_report",
				width: globalClientWidth - 20,
				height: 260,
				lazyLoad: false,
				isClickLoad:false,
				remoteOper : false,
				showPager: false,
				multiSelect: false,
				showToolbar: showTools,
				tools: ['remove','-',
				        {
							html : '风电场月度运行报告',
							plain : true,
							iconCls : 'icon-save',
							handler : function(miniTable, buttonText) {
									
								
								var params=getParamswindreport();
							     var cdates=miniTable.getData();
							     for(var i=0;i<cdates.length;i++){
					                    if(cdates[i].filename=="风电场月度运行报告模板.docx"){
					                   	 mini.alert("风电场月度运行报告模板已生成,无需重复生成");
					                    }
								    }	
								var fileTeplate=new fileTemplateConfig({
									isAttachment : true,
									attachmentParams: {
										module:'WorkflowAttchmentFiles',
										jbpmWorkflowHistoryInfoId : window.currentJbpmWorkflowHistoryInfoId ? window.currentJbpmWorkflowHistoryInfoId : '',
										attachmentFileDictId : 'root.FileType.Type3.TEST01',
										identifierOne:window.assignAttachmentKeyOne||"${currentProcessInstanceDBID}",
										identifierTwo:window.assignAttachmentKeyTwo||jQuery("#id_currentHistoryTaskInfo_keyOne").val(),
										identifierThree:window.assignAttachmentKeyThree||jQuery("#id_currentHistoryTaskInfo_keyTwo").val(),
										identifierFour:window.assignAttachmentKeyFour||jQuery("#id_currentHistoryTaskInfo_keyThree").val(),
										identifierFive:window.assignAttachmentKeyFive||jQuery("#id_currentHistoryTaskInfo_keyFour").val(),
										identifierSix:window.assignAttachmentKeySix||jQuery("#id_currentHistoryTaskInfo_keyFive").val(),
										identifierSeven:window.assignAttachmentKeySeven||jQuery("#id_currentHistoryTaskInfo_keySix").val(),
										identifierEight:window.assignAttachmentKeyEight||jQuery("#id_currentHistoryTaskInfo_keySeven").val(),
										identifierNine:window.assignAttachmentKeyNine||jQuery("#id_currentHistoryTaskInfo_keyEight").val(),
										identifierTen:window.assignAttachmentKeyTen||jQuery("#id_currentHistoryTaskInfo_keyNine").val()
									},
									templateno : 'F-201703001',
									tableid : 'wind_farm_report',
									modelname : '风电场月度运行报告模板',
									returntype : 'listtocurpage',
									parames : params
								});
								if(cdates.length==0){
								fileTeplate.createFile();
								if (mini.get("id_customworkflowattachment")) {
									mini.get("id_customworkflowattachment").reload();
									mini.get("id_workflowhisAttachment").reload();
								}
								}
								saveCallBack();
							}
				        },
				        ],
				data: JsonUtil.decode('${empty json_onhire_report_str ? "[]" : json_onhire_report_str }'),
				updateOperCallBack: function(miniTable,formData){
				},
				columns: [
					{type: 'indexcolumn'},
					{type: 'checkcolumn'},
					{field: 'id', header: 'id', visible: false},
					{field: 'filename', header: '文件名称'},
					{field: 'createdate', header: '文件生成时间'},
					{field: 'create', header: '操作',renderer:function(e){ var cfalg=showTools;
					var temp=e;
					return showOperator(temp,cfalg);}}
				]	
			});
		});
	};
	
});
//保存成功提交后，后台返回
function saveCallBack() {
	return true;
}

function getParamswindreport(){
	var params={};
	params["flowunid"]=flowUnid;
	//params["contract_id"]="${requestScope['contract_info.id']}";
	//报表时间
	params['projinfo.projcustname3'] = reportdate;
	//项目名称
	params['projinfo.projcustname1'] = mini.get("projectname").getValue();
	params['projinfo.projcustname2'] = mini.get("projectname").getValue();
    //承租人
	params['projinfo.lessee'] = mini.get("lessee").getValue();
	//风电场位置
	params['projinfo.position'] = mini.get("position").getValue();
	//地形
	params['projinfo.terrain'] = mini.get("terrain").getValue();
	//风电场经度
	params['projinfo.longitude']=mini.get("longitude").getValue();
	//风电场纬度
	params['projinfo.latitude']=mini.get("latitude").getValue();
	//机型
	params['projinfo.type']=mini.get("type").getValue();
	//数量
	params['projinfo.number']=mini.get("number").getValue();
	//投产时间
	params['projinfo.productiontime']=mini.get("production_time").getValue();
	//风电场月度运行报表
	params['unitoperationdata']=JsonUtil.encode(mini.get('unit_operation_data').getData());
	params['unitfailuredata']=JsonUtil.encode(mini.get('unit_failure_data').getData());
	params['lossdata']=JsonUtil.encode(mini.get('loss_data').getData());
	
	//全场累计各月运行报表
	params['cumulativeoperatingreport1']=JsonUtil.encode(mini.get('cumulative_operating_report1').getData());
	params['cumulativeoperatingreport2']=JsonUtil.encode(mini.get('cumulative_operating_report2').getData());
	params['cumulativeoperatingreport3']=JsonUtil.encode(mini.get('cumulative_operating_report3').getData());
	
	//运行状况分析
	params['summary.lable_31']=mini.get("3.1").getValue();
	//故障数据分析
	params['summary.lable_32']=mini.get("3.2").getValue();
	//损失电量分析
	params['summary.lable_33']=mini.get("3.3").getValue();
	//功率曲线分析
	params['summary.lable_34']=mini.get("3.4").getValue();
	
	//月度运行报告结论
	//损失电量分析
	params['summary.lable_41']=mini.get("4.1").getValue();
	//功率曲线分析
	params['summary.lable_42']=mini.get("4.2").getValue();
	return params;
}
function getParams2(){
	var params={};
	//params["contract_id"]="${requestScope['contract_info.id']}";
	//项目信息
	params['windfarmdatalistyear1']=JsonUtil.encode(mini.get('wind_farm_data_list_year').getData());
	params['windfarmdatalistyear2']=JsonUtil.encode(mini.get('wind_farm_data_list_year').getData());
	
	//全场累计各月运行报表
	params['cumulativeoperatingreport1']=JsonUtil.encode(mini.get('cumulative_operating_report_year1').getData());
   // var windspeed = averageValue("cumulative_operating_report_year1","windspeed");
	alert("windspeed:"+windspeed); 
	params['cumulativeoperatingreport2']=JsonUtil.encode(mini.get('cumulative_operating_report_year2').getData());
	params['cumulativeoperatingreport3']=JsonUtil.encode(mini.get('cumulative_operating_report_year3').getData());
	
	
	//报表年份 
	params['summary.reportdate'] = reportdate;
	//运行状况分析
	params['summary.lable_31']=mini.get("3.1").getValue();
	//故障数据分析
	params['summary.lable_32']=mini.get("3.2").getValue();
	//损失电量分析
	params['summary.lable_33']=mini.get("3.3").getValue();
	//功率曲线分析
	params['summary.lable_34']=mini.get("3.4").getValue();
	
	//月度运行报告结论
	//损失电量分析
	params['summary.lable_41']=mini.get("4.1").getValue();
	//功率曲线分析
	params['summary.lable_42']=mini.get("4.2").getValue();
	return params;
}

function showOperator(temp,cfalg){
	var id = temp.record.id;
	var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
	var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
	var base3 = "<a href='javascript:void(0);' onclick='editoverdue(\"" + id + "\")'>编辑</a>";
	var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
	var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
	var tempright="2"
	var filename=temp.record.filename;
	if(filename=="风电场月度运行报告模板.docx"&&cfalg){
	    return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4;
	}else{
		return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3;
	}
}
//求和
function sum(tableid,valuekey){
	var sumdata=0;
	var tablevalue = mini.get(tableid).getData();
	for(var i = 0;i<tablevalue.length;i++ ){
		var row = tablevalue[i];
		sumdata = sumdata +row.projectname
	}
	return sumdata;
}
//求平均值
function averageValue(tableid,valuekey){
	var str=eval("var " + valuekey);
	var valuekey = valuekey;
	var averageValue=0;
	var tablevalue = mini.get(tableid).getData();
	for(var i = 0;i<tablevalue.length;i++ ){
		var row = tablevalue[0];
		//averageValue = averageValue +row.str
	}
	return averageValue/(tablevalue.length);
}
function editoverdue(id){
	var currentPageClientWidth  =  document.documentElement.clientWidth;
	var currentPageClientHeight =  document.documentElement.clientHeight;
	openFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:"2"});
}
function downloadFile(Id){
	attachmentDown({returnType:'file',url:"${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="+Id});
} 
function  readCreateWord(id){
	var currentPageClientWidth  =  document.documentElement.clientWidth;
 	var currentPageClientHeight =  document.documentElement.clientHeight;
 	openFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:"1"});
}

</script>
<div id="id_table_wind_farm_report"></div>