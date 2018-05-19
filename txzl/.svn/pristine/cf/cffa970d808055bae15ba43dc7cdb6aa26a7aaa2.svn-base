<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>人工匹配</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript">
//单笔租金回笼
tenwa.createTable({
	id: "singel_rent_income",
	renderTo: "id_table_container_single_income",
	width: '100%',
	height: '100%',
	title:'',
	lazyLoad: false,
	remoteOper: false,
	showPager: true,
	pageSize: 20,
	multiSelect : true,
	queryButtonColSpan: 2,
	tools: [{
		html: '核销',
		plain: false,
		iconCls: 'icon-folder',
		handler: function(miniTable, buttonText){
			manualHire(miniTable);
		}
	},'-',{
		html: '取消匹配',
		plain: false,
		iconCls: 'icon-folder',
		handler: function(miniTable, buttonText){
			var selectRowData = miniTable.getSelected();
			if(!selectRowData){
				mini.alert("请选择要取消匹配的租金计划!");
				return false;
			}
			backMatched(miniTable);
		}
	}],
	params: {ebid:"${param.ebid}"},
	xmlFileName: '/eleasing/workflow/rent/rent_income/rent_plan_matched.xml',
	columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'planid', header: 'id', visible: false},
				{field: 'id', header: 'id', visible: false},
				{field: 'contractnumber', header: '业务合同编号'},
				{field: 'custname', header: '承租人名称'},
				{field: 'rentlist', header: '期次'},
				{field: 'plandate', header: '计划日期'},
				{field: 'rent', header: '计划租金',type:'double',summary : true,dataType : "currency"},
				{field: 'corpus', header: '计划本金',type:'double',summary : true,dataType : "currency"},
				{field: 'interest', header: '计划租息',type:'double',summary : true,dataType : "currency"},
				{field: 'currentoverage', header: '租金余额',type:'double',summary : true,dataType : "currency"},
				{field: 'curcorpusoverage', header: '本金余额',type:'double',summary : true,dataType : "currency"},
				{field: 'curinterestoverage', header: '利息余额',type:'double',summary : true,dataType : "currency"}
	]
});
//单笔租金回笼
tenwa.createTable({
	id: "singel_rent_income_matched",
	renderTo: "id_table_container_single_income_matched",
	title:'',
	width: '100%',
	height: '100%',
	lazyLoad: false,
	remoteOper: false,
	showPager: true,
	pageSize: 20,
	multiSelect : true,
	queryButtonColSpan: 2,
	tools: [{
		html: '确定匹配',
		plain: false,
		iconCls: 'icon-folder',
		handler: function(miniTable, buttonText){
			var selectRowData = miniTable.getSelected();
			if(!selectRowData){
				mini.alert("请选择要匹配的租金计划!");
				return false;
			}
			//当剩余可核销金额<=匹配金额 不能在进行匹配
			if(Number(mini.get("mayopemoney").getValue().replace(/,/g,'')) <= 
					Number(mini.get("processmoney").getValue().replace(/,/g,''))){
				mini.alert("已匹配金额已等于可核销金额，不允许再进行匹配！！！");
				return false;
			}
			confirmMatched(miniTable);
		}
	}],
	xmlFileName: '/eleasing/workflow/rent/rent_income/rent_plan_wait_matched.xml',
	columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'contractnumber', header: '业务合同编号',queryConfig:{}},
				{field: 'custname', header: '承租人名称',queryConfig:{}},
				{field: 'rentlist', header: '期次',queryConfig:{}},
				{field: 'plandate', header: '计划日期',queryConfig:{
					newLine : true,
					type : 'date',
					range : true
				}},
				{field: 'rent', header: '计划租金',type:'double',summary : true,dataType : "currency"},
				{field: 'corpus', header: '计划本金',type:'double',summary : true,dataType : "currency"},
				{field: 'interest', header: '计划租息',type:'double',summary : true,dataType : "currency"},
				{field: 'currentoverage', header: '租金余额',type:'double',summary : true,dataType : "currency"},
				{field: 'curcorpusoverage', header: '本金余额',type:'double',summary : true,dataType : "currency"},
				{field: 'curinterestoverage', header: '利息余额',type:'double',summary : true,dataType : "currency"}
	]
});
function backMatched(miniTable){
	var selectedRows = miniTable.getSelecteds();
	var procids = "";
	for(var i=0;i<selectedRows.length;i++){
		procids = procids + selectedRows[i].id+",";
	}
	ajaxRequest({
		url : getRootPath() + "/acl/backMatched.acl",
		method : 'POST',
		success : function(res) {
			alert("操作成功");
			mini.get('singel_rent_income').reload();
			mini.get('singel_rent_income_matched').reload();
			refreshBankInfo();
		},
		async : false,
		failure : function(res) {
		},
		params : {
			procids : procids,
			ebid:"${param.ebid}"
		}
	});
}
function confirmMatched(miniTable){
	var selectedRows = miniTable.getSelecteds();
	var planids = "";
	var currentoverage="";
	for(var i=0;i<selectedRows.length;i++){
		planids = planids + selectedRows[i].id+",";
		currentoverage = currentoverage + selectedRows[i].currentoverage+","
	}
	ajaxRequest({
		url : getRootPath() + "/acl/confirmMatched.acl",
		method : 'POST',
		success : function(res) {
			alert("操作成功");
			mini.get('singel_rent_income').reload();
			mini.get('singel_rent_income_matched').reload();
			refreshBankInfo();
		},
		async : false,
		failure : function(res) {
		},
		params : {
			planids : planids,
			currentoverage:currentoverage,
			mayopemoney:mini.get("mayopemoney").getValue().replace(/,/g,''),
			processmoney:mini.get("processmoney").getValue().replace(/,/g,''),
			ebid:"${param.ebid}"
		}
	});
}
function manualHire(table) {
	ajaxRequest({
		url : getRootPath() + "/acl/manualHire.acl",
		method : 'POST',
		success : function(res) {
			alert("操作成功");
			mini.get('singel_rent_income').reload();
			mini.get('singel_rent_income_matched').reload();
			refreshBankInfo();
		},
		async : false,
		failure : function(res) {
		},
		params : {ebid:"${param.ebid}"}
	});
}
</script>
</head>	
<body>
<div id="id_table_container_ebankinfo" style="height: 120px;">
	    <div class="mini-panel" title="网银基本信息" showCollapseButton="true" style="width: 100%;">
			<table class="fillTable" style="width:100%" cellspacing="0" cellpadding="7" id="ebank_import_table">
				<tr class="tr-odd">
					<td class="td-content-title">网银编号：</td>
					<td class="td-content"><input id="ebdataid" name="ebdataid" class="mini-textbox" readOnly type="text" value="${requestScope['ebdataid']}"></td>
					<td class="td-content-title">付款人：</td>
					<td class="td-content"><input id="clientname" name="clientname" class="mini-textbox" readOnly type="text" value="${requestScope['clientname']}" /></td>
					<td class="td-content-title">到账时间：</td>
					<td class="td-content"><input id="factdate" name="factdate" class="mini-textbox" readOnly type="text" value="${requestScope['factdate']}"></td>
				</tr>
				<tr class="tr-even">
					<td class="td-content-title">到账金额：</td>
					<td class="td-content"><input id="factmoney" name="factmoney" class="mini-textbox" readOnly type="text" value="${requestScope['factmoney']}"></td>
					<td class="td-content-title">可核销金额：</td>
					<td class="td-content" ><input id="mayopemoney" name="mayopemoney" class="mini-textbox" readOnly type="text" value="${requestScope['mayopemoney']}" /></td>	
					<td class="td-content-title">匹配金额：</td>
					<td class="td-content"><input id="processmoney" name="processmoney" class="mini-textbox" readOnly type="text" value="${requestScope['processmoney']}"></td>
				</tr>
			</table>
	</div>
</div>
<div class="mini-panel" title="已匹配" showCollapseButton="true" style="width: 100%;height:35%">
	<div id="id_table_container_single_income" style="height: 100%;"></div>
</div>
<div class="mini-panel" title="待匹配" showCollapseButton="true" style="width: 100%;height:55%">
	<div id="id_table_container_single_income_matched" style="height: 90%;" ></div>
</div>
</body>
<script type="text/javascript">
mini.parse();
var form = new mini.Form("id_table_container_ebankinfo");
//跨页面传递的数据对象，克隆后才可以安全使用
jQuery(function(){
	refreshBankInfo();
})
function refreshBankInfo(){
	$.ajax({
	    url: urlPrefix+"/eleasing/workflow/rent/rent_income/manual_match_ebank_info.xml",
	    data:{ebid:"${param.ebid}"},
	    cache: false,
	    success: function (text) {   
	    	var result = mini.decode(text);
	        form.setData(result.datas[0]);
	    }
	});
}
</script>
</html>

